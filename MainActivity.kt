package com.fasibank.ebank

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to your main layout XML file
        setContentView(R.layout.activity_main) 

        val webView: WebView = findViewById(R.id.my_webview)
        
        // 1. Configure WebView Settings for FOSS Compliance and Security
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true // Enable JavaScript if your website needs it
        webSettings.domStorageEnabled = true 
        
        // **Crucial F-Droid step: Do NOT use Non-Free Network Services**
        // Ensure you are only loading FOSS or open-access content.
        // We'll load the F-Droid website itself as an example.
        val targetUrl = "https://fasibank.base44.app"

        // 2. Set a WebViewClient to stay within the app and handle links
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true // Load all URLs within this WebView
            }
        }

        // 3. Load the URL
        webView.loadUrl(targetUrl)
    }
    
    // 4. Handle back button presses (navigate back in the WebView history)
    override fun onBackPressed() {
        val webView: WebView = findViewById(R.id.my_webview)
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}