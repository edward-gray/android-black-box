package pro.edvard.blackbox.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_web_view.*
import pro.edvard.blackbox.R
import pro.edvard.blackbox.util.NetworkConnection

@AndroidEntryPoint
class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private val viewModel: WebViewViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var networkConnection: NetworkConnection

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        networkConnection = NetworkConnection(requireContext())

        handleWebViewSetting()
        handleNetworkConnectionAndWebView(savedInstanceState)
    }

    private fun handleNetworkConnectionAndWebView(savedInstanceState: Bundle?) {
        networkConnection.observe(viewLifecycleOwner) {networkAvailable ->
            if (networkAvailable != null) {
                if (networkAvailable) {
                    loadWebView(savedInstanceState)
                } else {
                    navController.navigate(R.id.action_webViewFragment_to_gameMenuFragment)
                }
            }
        }
    }

    private fun loadWebView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val html5url = "http://html5test.com/"
            web_webview.loadUrl(html5url)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun handleWebViewSetting() {
        // handling setting
        CookieManager.getInstance().acceptCookie()
        web_webview.webViewClient = WebViewClient()
        web_webview.settings.javaScriptEnabled = true
        web_webview.settings.allowContentAccess = true
        web_webview.settings.loadWithOverviewMode = true
        web_webview.settings.loadsImagesAutomatically = true
        web_webview.settings.domStorageEnabled = true
        web_webview.settings.setAppCacheEnabled(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        web_webview.saveState(outState)
        viewModel.setWebView(web_webview)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        web_webview.restoreState(savedInstanceState)
    }

}