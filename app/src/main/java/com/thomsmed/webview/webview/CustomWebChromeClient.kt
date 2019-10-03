package com.thomsmed.webview.webview

import android.content.Context
import android.util.Log
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView

class CustomWebChromeClient(
    private val context: Context) : WebChromeClient() {

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
//        return super.onJsAlert(view, url, message, result)
        Log.d("MY_TAG","Alert: $message")
        result?.confirm() // webView is blocked until the result.cancel() og result.confirm() is called
        return true
    }
}