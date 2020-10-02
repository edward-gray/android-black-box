package pro.edvard.blackbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import pro.edvard.blackbox.util.NetworkConnection

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var networkConnection: NetworkConnection
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configuration()
    }

    private fun configuration() {
        // navigation component jetpack
        navigationSetup()

        // Internet access handled with this object
        networkConnection = NetworkConnection(this)
        handleNetworkConnection()
    }

    private fun navigationSetup() {
        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.main_container) as NavHostFragment? ?: return
        navController = host.navController
    }

    private fun handleNetworkConnection() {
        networkConnection.observe(this, { isConnected ->
            isConnected?.let {
                handleInternetUI(isConnected)
            } ?: handleInternetUI(false)
        })
    }

    private fun handleInternetUI(available: Boolean) {
        if (available) {
            println("DEBUG: INTERNET CONNECTED")

        } else {
            println("DEBUG: INTERNET DISCONNECTED")
        }
    }
}