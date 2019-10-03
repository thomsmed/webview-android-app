package com.thomsmed.webview

import android.util.Base64
import android.util.Log
import com.thomsmed.webview.webview.WebViewFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder


class LocalContentWebViewFragment : WebViewFragment() {

    override fun onResume() {
        super.onResume()
        context?.apply {
            val contentStream = assets.open("www/index.html")
            val reader = BufferedReader(InputStreamReader(contentStream))
            val builder = StringBuilder()
            var line = reader.readLine()
            do {
                builder.append(line)
                line = reader.readLine()
            } while (line != null)
            val contentString = builder.toString()
            val encodedContentString = Base64.encodeToString(contentString.toByteArray(), Base64.NO_PADDING)
            webView.loadData(encodedContentString, "text/html", "base64")
        }
    }


}
