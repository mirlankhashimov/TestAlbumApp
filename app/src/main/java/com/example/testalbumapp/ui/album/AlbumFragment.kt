package com.example.testalbumapp.ui.album

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.example.testalbumapp.core.BaseFragment
import com.example.testalbumapp.R
import com.example.testalbumapp.domain.model.Album
import com.example.testalbumapp.utils.ConnectivityUtil
import com.example.testalbumapp.utils.observe
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumFragment : BaseFragment(R.layout.fragment_album) {
    private val viewModel: AlbumViewModel by viewModel()
    private val albumAdapter by lazy { AlbumAdapter(this::onSelectAlbum) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        album_rv.adapter = albumAdapter
        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(context!!)
        viewModel.albums.observe(viewLifecycleOwner) {
            albumAdapter.submitList(it)
        }
    }

    private fun onSelectAlbum(album: Album) {
        val bundle = bundleOf("albumId" to album.id)
        navigate(R.id.action_album_screen_to_photo_screen, bundle)
    }
}