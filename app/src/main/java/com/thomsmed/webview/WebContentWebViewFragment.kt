package com.thomsmed.webview

import com.thomsmed.webview.webview.WebViewFragment


class WebContentWebViewFragment : WebViewFragment() {

    override fun onResume() {
        super.onResume()
        webView.loadUrl("https://developer.android.com/")
    }
}