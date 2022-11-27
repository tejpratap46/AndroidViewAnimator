package com.tejpratapsingh.viewanimator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tejpratapsingh.viewanimator.animation.SecondHandAnimation
import com.tejpratapsingh.viewanimator.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    private val uiScope = CoroutineScope(Dispatchers.Main)
    private lateinit var binding: ActivityMainBinding
    private lateinit var secondHandAnimation: SecondHandAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        secondHandAnimation = SecondHandAnimation(binding.ivSecondHand).apply {
            setFPS(60)
            setLooping(true)
        }

        binding.ivSecondHand.setOnClickListener {
            secondHandAnimation.restartAnimation()
        }
    }
}