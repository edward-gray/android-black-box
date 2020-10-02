package pro.edvard.blackbox.ui.gameMenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_game_menu.*
import pro.edvard.blackbox.R
import pro.edvard.blackbox.util.NetworkConnection

@AndroidEntryPoint
class GameMenuFragment : Fragment(R.layout.fragment_game_menu) {

    private lateinit var navController: NavController
    private lateinit var networkConnection: NetworkConnection

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        networkConnection = NetworkConnection(requireContext())

        buttonsConfig()
        handleNetworkConnection()
    }

    private fun handleNetworkConnection() {
        networkConnection.observe(viewLifecycleOwner) { networkAvailable ->
            if (networkAvailable != null) {
                handleUI(networkAvailable)
            }
        }
    }

    private fun handleUI(networkAvailable:Boolean) {
        if (networkAvailable) {
            game_menu_image.setImageResource(R.drawable.ic_sadly_happy)
            game_menu_text_info.text = requireContext().getString(R.string.menu_internet_on)
            game_menu_button_go_back.visibility = View.VISIBLE
        } else {
            game_menu_image.setImageResource(R.drawable.ic_sad)
            game_menu_text_info.text = requireContext().getString(R.string.menu_internet_off)
            game_menu_button_go_back.visibility = View.GONE
        }
    }

    private fun buttonsConfig() {
        game_menu_button_go_back.setOnClickListener {
            navController.navigate(R.id.action_gameMenuFragment_to_webViewFragment)
        }
        game_menu_button_play.setOnClickListener {
            navController.navigate(R.id.action_gameMenuFragment_to_playFragment)
        }
    }


}