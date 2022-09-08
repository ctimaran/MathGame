package com.stasoft.mathgame

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Transition
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.stasoft.mathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.hide(WindowInsetsCompat.Type.systemBars())



        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.layoutRegistro.visibility = View.GONE
        binding.space.visibility = View.GONE
        setContentView(binding.root)
        animate()

        binding.button.setOnClickListener(){
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)

        }
    }


    override fun onStart() {
        super.onStart()
    }

    private fun showLayout(){
        binding.space.visibility = View.VISIBLE

        binding.layoutRegistro.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha (1f)
                .setDuration(1000)
                .setListener(null)
        }

        binding.imageView.apply {
            animate()
                .setDuration(1000)
        }
    }

    private fun animate(){
        val rotateAnimation:RotateAnimation
        val scaleAnimation: ScaleAnimation
        val alphaAnimation: AlphaAnimation

        scaleAnimation = ScaleAnimation(
            0f,1f, 0f,1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)

        scaleAnimation.fillAfter = true
        scaleAnimation.duration = 1000

        val animationSet: AnimationSet
        animationSet = AnimationSet(false)
        animationSet.addAnimation(scaleAnimation)


        rotateAnimation = RotateAnimation(360f,0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.startOffset = 500
        rotateAnimation.duration = 2000
        rotateAnimation.repeatMode = Animation.REVERSE
        rotateAnimation.repeatCount = 1



        rotateAnimation.setAnimationListener  (object: Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                //TODO("Not yet implemented")
            }

            override fun onAnimationEnd(p0: Animation?) {
                showLayout()
            }

            override fun onAnimationRepeat(p0: Animation?) {
                //TODO("Not yet implemented")
            }

        }
        )
      //  binding.imageView.startAnimation(rotateAnimation)

        animationSet.addAnimation(rotateAnimation)
        binding.imageView.startAnimation(animationSet)



        alphaAnimation = AlphaAnimation(0.2f, 1.0f)
        alphaAnimation.duration = 2000
        alphaAnimation.startOffset = 1000
        alphaAnimation.fillAfter = true




       // Thread.sleep(3000)


    }
}