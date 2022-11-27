package com.tejpratapsingh.viewanimator.animation

import android.widget.TextView
import com.tejpratapsingh.viewanimatorlib.animation.Easings
import com.tejpratapsingh.viewanimatorlib.animation.Interpolators
import com.tejpratapsingh.viewanimatorlib.animation.MotionInterpolator
import com.tejpratapsingh.viewanimatorlib.domain.ViewStepAnimatorImpl

class TextAnimationExample(textView: TextView) : ViewStepAnimatorImpl<TextView>(textView) {
    override fun getTotalFrames(): Int {
        return 100
    }

    override fun updateViewForFrame(frame: Int) {
        getView().text = MotionInterpolator.interpolateForRange(
            interpolator = Interpolators(Easings.SIN_IN),
            currentFrame = frame,
            frameRange = Pair(0, 100),
            valueRange = Pair(0F, 100F)
        ).toString()
    }
}