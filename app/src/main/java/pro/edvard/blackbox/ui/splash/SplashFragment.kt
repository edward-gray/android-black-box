package pro.edvard.blackbox.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_splash.*
import pro.edvard.blackbox.R

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var navController : NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        handleGif()
        start()
    }

    private fun start() {
        Handler().postDelayed({
            navController.navigate(R.id.action_splashFragment_to_webViewFragment)
        }, 5000)
    }

    private fun handleGif() {
        Glide.with(this).load(R.drawable.gif_splash).into(splash_image)
    }

}