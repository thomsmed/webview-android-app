package com.thomsmed.webview.webview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView


open class WebViewFragment : Fragment() {

    protected lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.webView = WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.userAgentString = settings.userAgentString + " Some custom UserAgent shiet!"
        }

        context?.apply {
            installPlugin(WebViewPlugin(webView, this))
            webView.webViewClient = CustomWebViewClient(this)
            webView.webChromeClient = CustomWebChromeClient(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return webView
    }

    fun installPlugin(plugin: WebViewPlugin) {
        val prefix = "plugins"
        val pluginName = plugin.javaClass.simpleName // Requires kotlin-reflect.jar
        this.webView.addJavascriptInterface(plugin, "$pluginName")
    }

}
