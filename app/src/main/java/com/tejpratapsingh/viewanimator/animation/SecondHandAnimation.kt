package com.tejpratapsingh.viewanimator.animation

import android.widget.ImageView
import com.tejpratapsingh.viewanimatorlib.domain.ViewStepAnimatorImpl


class SecondHandAnimation(imageView: ImageView) : ViewStepAnimatorImpl<ImageView>(imageView) {
    companion object {
        private const val TAG = "SecondHandAnimation"
    }

    override fun getTotalFrames(): Int {
        return 60 * mFps
    }

    override fun updateViewForFrame(frame: Int) {
        getView().rotation = frame.toFloat() * 6F / mFps
    }
}