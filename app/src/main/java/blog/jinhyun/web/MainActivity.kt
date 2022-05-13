package blog.jinhyun.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import blog.jinhyun.web.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.webView.apply {
            // 자바스크립트 기능 활성화
            settings.javaScriptEnabled = true
            // WebViewClient 생성으로, 자체 웹브라우저 작동을 막고 웹뷰에 페이자가 표시됨
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.editTextTextUri.setText(url!!)
                }

            }
        }

        // `Url`를 전달하여 페이지 로드
        binding.webView.loadUrl("https://www.google.com/")
    }
}