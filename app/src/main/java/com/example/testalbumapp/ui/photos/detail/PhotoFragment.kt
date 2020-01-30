package com.example.testalbumapp.ui.photos.detail

import android.os.Bundle
import android.view.View
import com.example.testalbumapp.core.BaseFragment
import com.example.testalbumapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


class PhotoFragment: BaseFragment(R.layout.fragment_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(arguments?.getString("imageId")).into(image)
    }
}