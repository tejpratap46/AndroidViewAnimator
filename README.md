# AndroidViewAnimator
Easily animate any view with complete control on each view. Written in kotlin

Here is a demo of time animation:

https://user-images.githubusercontent.com/10910252/204153463-ae4ab5d5-3f39-4f6c-8f2c-b68a4df9e816.mp4

Here is the example:
```kotlin
class SecondHandAnimation(imageView: ImageView) : ViewStepAnimatorImpl<ImageView>(imageView) {
    companion object {
        private const val TAG = "SecondHandAnimation"
    }

    override fun getTotalFrames(): Int {
        // How may frames you want it to run,
        // For looping, make sure to give total frames till which you want to repeat
        return 60 * mFps
    }

    override fun updateViewForFrame(frame: Int) {
        // animate your view here
        getView().rotation = frame.toFloat() * 6F / mFps
    }
}
```

And call it using:
```kotlin
secondHandAnimation = SecondHandAnimation(binding.ivSecondHand).apply {
    // Set fps for the animation
    setFPS(60)
    // set if looping
    setLooping(true)
}

binding.ivSecondHand.setOnClickListener {
    // start animation by calling restartAnimation() or startAnimation()
    secondHandAnimation.restartAnimation()
}
```