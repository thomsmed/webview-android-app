package com.thomsmed.webview.webview

import android.content.Context
import android.os.Build
import android.os.Handler
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView

class WebViewPlugin(
    private val webView: WebView,
    private val context: Context) {

    @JavascriptInterface
    fun echo(string: String?) {
        // Does not run on main thread
        string?.let {
            val response = "Echo: $it"

            // Must call this on the UI-thread!!!
            val handler = Handler(context.mainLooper)
            handler.post {
                val jsCode = "console.log('$response');"
                webView.evaluateJavascript(jsCode) {
                    Log.d("MY_TAG", "Result from evaluatingJavascript: $it")
                }
                // can use webView.loadUrl() with "javascript:console.log('Use this on api 18 or lower')
            }
        }
    }
}