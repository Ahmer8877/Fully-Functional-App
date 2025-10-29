package com.e.ahmer.navigationviews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.e.ahmer.navigationviews.databinding.FragmentHelpBinding

class HelpFrag : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)

        //content variable
        val contactButton=binding.root.findViewById<Button>(R.id.contactButton)
        // 👇 Example: "Contact Support" button action
        contactButton.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_VIEW)
                emailIntent.data = Uri.parse("https://wa.me/qr/ECJDRCYFI53AA1")
            startActivity(emailIntent)
            }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
