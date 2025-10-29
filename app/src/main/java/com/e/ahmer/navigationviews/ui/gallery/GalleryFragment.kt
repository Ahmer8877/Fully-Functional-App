package com.e.ahmer.navigationviews.ui.gallery

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.e.ahmer.navigationviews.R
import com.e.ahmer.navigationviews.databinding.FragmentGalleryBinding

@Suppress("DEPRECATION")
class GalleryFragment : Fragment() {
    lateinit var mediaPlayer: MediaPlayer
    var totaltime : Int =0

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = inflater.inflate(R.layout.fragment_gallery, container, false)

      //set song (media player) code
        mediaPlayer= MediaPlayer.create(requireContext(),R.raw.gabroo)

        mediaPlayer.setVolume(1f,1f)
        totaltime=mediaPlayer.duration
        val play=rootView.findViewById<ImageButton>(R.id.playbtn)
        val pauseb=rootView.findViewById<ImageButton>(R.id.pausebtn)
        val stopb=rootView.findViewById<ImageButton>(R.id.resetbtn)
        val seekbar=rootView.findViewById<SeekBar>(R.id.seekBar)

        play.setOnClickListener {
            mediaPlayer.start()
        }
        pauseb.setOnClickListener {
            mediaPlayer.pause()
        }
        stopb.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }
        seekbar.max=totaltime
        //seek bar change listener code
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaPlayer.seekTo(progress)
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        //set seek bar progress running with song play and with the try and exception method
        val handler= android.os.Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                try {
                    seekbar.progress=mediaPlayer.currentPosition
                    handler.postDelayed(this,1000)
                }catch (exection :java.lang.Exception){
                    seekbar.progress=0

                }

            }

        },0)


        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}