package pro.edvard.blackbox.ui.play

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_play.*
import pro.edvard.blackbox.R
import pro.edvard.blackbox.util.NetworkConnection

@AndroidEntryPoint
class PlayFragment : Fragment(R.layout.fragment_play) {

    private lateinit var navController: NavController
    private lateinit var networkConnection: NetworkConnection
    private lateinit var playPresenter: PlayPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        networkConnection = NetworkConnection(requireContext())
        configuration()
    }

    private fun configuration() {
        playPresenter = PlayPresenter(this)
    }

    fun buttonConfig(isLastLevel: Boolean = false) {
        play_button_next_level.setOnClickListener {
            if (isLastLevel) {
                navController.navigate(R.id.action_playFragment_to_gameMenuFragment)
            } else {
                handleNextLevel()
            }
        }
    }

    private fun handleNextLevel() {
        TODO("Not yet implemented")
    }

}