package pro.edvard.blackbox.ui.play

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import pro.edvard.blackbox.R
import pro.edvard.blackbox.util.NetworkConnection

@AndroidEntryPoint
class PlayFragment : Fragment(R.layout.fragment_play) {

    private lateinit var navController: NavController
    private lateinit var networkConnection: NetworkConnection

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        networkConnection = NetworkConnection(requireContext())
    }

}