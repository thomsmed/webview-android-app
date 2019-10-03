package com.thomsmed.webview.webview

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient(
    private val context: Context) : WebViewClient() {

    override fun onPageCommitVisible(view: WebView?, url: String?) {
        super.onPageCommitVisible(view, url)
        view?.apply {
            // TODO: Can plugins be injected here?
            val jsThatInstallsPlugins = "window.plugins = { echo: WebViewPlugin.echo };"
            evaluateJavascript(jsThatInstallsPlugins, {

            })
            val jsThatInsertScript = """
                var head = document.getElementsByTagName('head')[0];
                var script = document.createElement('script');
                script.type = 'text/javascript';
                script.text = 'console.log("Hello there!");'
                head.appendChild(script);
            """.trimIndent()
            evaluateJavascript(jsThatInsertScript, {

            })
        }
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.d("MY_TAG","Stated loading content")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        Log.d("MY_TAG","Finished loading content")
    }
}