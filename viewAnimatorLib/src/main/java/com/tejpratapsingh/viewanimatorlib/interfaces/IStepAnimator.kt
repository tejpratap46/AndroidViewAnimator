package com.tejpratapsingh.viewanimatorlib.interfaces

interface IStepAnimator {
    fun setFPS(fps: Int)

    fun getTotalFrames(): Int

    fun setStartFrame(frame: Int)

    fun updateViewForFrame(frame: Int)

    fun startAnimation()

    fun stopAnimation(exception: Exception)

    fun restartAnimation()

    fun isAnimating(): Boolean

    fun setLooping(isLooping: Boolean)

    fun setOnAnimationFinishedListener(onAnimationFinishedListener: IOnAnimationFinishedListener)
}