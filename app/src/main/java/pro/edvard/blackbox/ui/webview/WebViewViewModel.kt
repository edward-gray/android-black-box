package pro.edvard.blackbox.ui.webview

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class WebViewViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _webView: MutableLiveData<WebView> = MutableLiveData()
    val webView: LiveData<WebView>
        get() = _webView

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebView(webView: WebView) {
        // handling setting
        webView.webViewClient = WebViewClient()
        webView.settings.allowContentAccess = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.setAppCacheEnabled(true)
        _webView.value = webView
    }

}