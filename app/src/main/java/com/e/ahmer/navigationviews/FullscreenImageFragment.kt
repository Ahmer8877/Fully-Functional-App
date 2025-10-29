package com.e.ahmer.navigationviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.hdodenhof.circleimageview.CircleImageView

class FullscreenImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fullscreen_image, container, false)

        // Correct ImageView ID
        val imageView = view.findViewById<CircleImageView>(R.id.fullscreenImageFragment)

        // Argument se image resource lo(me pic full screen )
        val imageResId = arguments?.getInt("image_res", -1)
        if (imageResId != null && imageResId != -1) {
            imageView.setImageResource(imageResId)
        }

        // Image pe click karne se back jaaye
        imageView.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }

}

