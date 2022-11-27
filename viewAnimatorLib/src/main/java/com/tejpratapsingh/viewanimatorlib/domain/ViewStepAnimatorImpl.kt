package com.tejpratapsingh.viewanimatorlib.domain

import android.content.Context
import android.view.View
import com.tejpratapsingh.viewanimatorlib.interfaces.IOnAnimationFinishedListener
import com.tejpratapsingh.viewanimatorlib.interfaces.IStepAnimator
import kotlinx.coroutines.*

abstract class ViewStepAnimatorImpl<T : View>(private val view: T) : IStepAnimator {

    val mContext: Context by lazy {
        view.context
    }

    var isInLoop: Boolean = false
        private set

    var mFps: Int = 30
        private set

    var mStartFrame: Int = 0
        private set

    var mCurrentFrame = 0
        private set

    var mAnimationFinishedListener: IOnAnimationFinishedListener? = null
        private set

    override fun setFPS(fps: Int) {
        this.mFps = fps
    }

    override fun setStartFrame(frame: Int) {
        this.mStartFrame = frame
    }

    override fun setLooping(isLooping: Boolean) {
        this.isInLoop = isLooping
    }

    protected fun getView(): T {
        return view
    }

    override fun setOnAnimationFinishedListener(onAnimationFinishedListener: IOnAnimationFinishedListener) {
        this.mAnimationFinishedListener = onAnimationFinishedListener
    }

    private val uiScope = CoroutineScope(Dispatchers.Main)
    private var job: Job? = null
    override fun startAnimation() {
        job = uiScope.launch {
            nextFrame()
        }
    }

    private suspend fun nextFrame() {
        updateViewForFrame(mCurrentFrame++)
        if (mCurrentFrame < mStartFrame + getTotalFrames()) {
            delay(1000 / mFps.toLong())
            nextFrame()
        } else if (isInLoop) {
            mCurrentFrame = mStartFrame
            delay(1000 / mFps.toLong())
            nextFrame()
        } else {
            mAnimationFinishedListener?.onAnimationFinished()
        }
    }

    override fun stopAnimation(exception: Exception) {
        job?.cancel(CancellationException(exception.message))
    }

    override fun restartAnimation() {
        stopAnimation(Exception("Restarted by user"))
        mCurrentFrame = mStartFrame
        startAnimation()
    }

    override fun isAnimating(): Boolean {
        return mCurrentFrame < mStartFrame + getTotalFrames()
    }
}