package com.toiapp.android.feature.services.places.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.toiapp.android.core.extensions.AppPreferences
import com.toiapp.android.core.extensions.load
import com.toiapp.android.core.extensions.parentScope
import com.toiapp.android.core.model.Image
import com.toiapp.android.feature.services.places.database.PlacesDatabase
import com.toiapp.android.feature.services.places.model.*
import com.toiapp.android.feature.services.places.network.PlacesApi
import com.toiapp.android.feature.services.places.network.PlacesBoundaryCallback
import kotlinx.coroutines.CoroutineScope
import okhttp3.MediaType
import okhttp3.RequestBody
import timber.log.Timber
import java.io.File

class PlacesRepository(
    private val placesDatabase: PlacesDatabase,
    private val placesApi: PlacesApi,
    private val currentUserId: String,
    private val preferences: AppPreferences
) {
    var placesBoundaryCallback: PlacesBoundaryCallback? = null

    fun getPlaces(
        coroutineScope: CoroutineScope,
        filter: LiveData<Filter?>,
        onlyFavorites: Boolean
    ): LiveData<PagedList<Place>> {
        placesBoundaryCallback = PlacesBoundaryCallback(10, coroutineScope, filter, onlyFavorites)
        return placesDatabase
            .placeDao()
            .run {
                when {
                    onlyFavorites -> getAllFavorites()
                    preferences.isVendor -> getMyPlaces(currentUserId)
                    else -> getAll()
                }
            }
            .toLiveData(10, boundaryCallback = placesBoundaryCallback)
    }

    fun invalidatePlaces() {
        placesBoundaryCallback?.invalidate()
    }

    suspend fun deleteAllPlaces() {
        placesDatabase.placeDao().deleteAll()
    }

    suspend fun favourite(place: Place) {
        val response = placesApi.favorite(
            FavoriteRequest(
                currentUserId,
                place.id
            )
        )
        if (response.isSuccessful) {
            val newPlace = place.copy(inFavorites = !place.inFavorites)
            placesDatabase.placeDao().insert(listOf(newPlace))
        }
    }

    suspend fun getPlace(id: Int): LiveData<Place> = parentScope {
        load {
            val place = placesApi.getPlace(id)
            place.galleryList = placesApi.getImages(id)
            val localPlace = placesDatabase.placeDao().getPlaceSync(id)
            val newPlace = place.copy(indexInResponse = localPlace?.indexInResponse ?: place.indexInResponse)
            placesDatabase.placeDao().insert(listOf(newPlace))
        }
        placesDatabase.placeDao().getPlace(id)
    }

    suspend fun getTypes(): LiveData<List<Type>> = parentScope {
        load {
            val types = placesApi.getTypes()
            placesDatabase.typeDao().insert(types)
        }
        placesDatabase.typeDao().getAllLive()
    }

    suspend fun getKitchens(): LiveData<List<Cuisine>> = parentScope {
        load {
            val remoteTypes = placesApi.getKitchens()
            placesDatabase.kitchenDao().deleteAllAndInsert(remoteTypes)
        }
        placesDatabase.kitchenDao().getAllLive()
    }

    suspend fun add(place: Place): Int = placesApi.addPlace(place).id
    suspend fun update(place: Place) = placesApi.updatePlace(place).isSuccessful
    suspend fun save(place: Place, images: List<Image>) = placesApi.save(place, images, images)
}
