package pro.edvard.blackbox.ui.gameMenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
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
        handleNetworkConnection()
    }

    private fun handleNetworkConnection() {
        networkConnection.observe(viewLifecycleOwner) { networkAvailable ->
            if (networkAvailable != null) {
                if (networkAvailable) {
                    navController.navigate(R.id.action_gameMenuFragment_to_webViewFragment)
                } else {
                    // todo:: let's play
                }
            }
        }
    }


}