package com.example.pictureinpicture

import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.Display
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.pictureinpicture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageView.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_android
            )
        )
        binding.btnPictureInPicture.setOnClickListener { startPictureInPicture() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startPictureInPicture() {
        val d: Display = windowManager.defaultDisplay
        val p = Point()
        d.getSize(p)
        val width: Int = p.x
        val height: Int = p.y

        val ratio = Rational(width, height)

        val builder = PictureInPictureParams.Builder()
        builder.setAspectRatio(ratio)
        enterPictureInPictureMode(builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            // Hide the full-screen UI (controls, etc.) while in picture-in-picture mode.
            Log.d("TAG", "isInPictureInPictureMode")
        } else {
            // Restore the full-screen UI.
            Log.d("TAG", "is NOT InPictureInPictureMode")
        }
    }
}