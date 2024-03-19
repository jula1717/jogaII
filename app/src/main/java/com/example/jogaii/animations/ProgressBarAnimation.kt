package com.example.jogaii.animations

import android.view.animation.Animation
import android.view.animation.Transformation
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar

class ProgressBarAnimation(private val progressBar: RoundCornerProgressBar, private val from: Float, private val to: Float) : Animation() {
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime
        progressBar.setProgress(value.toInt())
    }
}