package blog.jinhyun.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
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
        binding.webView.loadUrl("https://www.google.com")

        binding.editTextTextUri.setOnEditorActionListener { _, actionId, _ ->
            // 설정한 키보드 액션(imeOptions)이 작동했다면 입력된 `url`을 웹뷰에 로드
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.webView.loadUrl(binding.editTextTextUri.text.toString())
                true
            } else {
                false
            }
        }
    }

    /**
     * 선택 메뉴 생성
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 메뉴 리소스 지정
        menuInflater.inflate(R.menu.main, menu)
        // `true`: 액티비티에게 메뉴가 있다고 인식
        return true
    }

    /**
     * 선택 메뉴의 아이템 선택시 호출되는 함수
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemHome, R.id.itemGoogle -> {
                binding.webView.loadUrl("https://www.google.com")
                return true
            }
            R.id.itemNaver -> {
                binding.webView.loadUrl("https://www.naver.com")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * 뒤로가기 버튼 동작 재정의
     */
    override fun onBackPressed() {
        // 뒤로 갈 수 있다면 뒤로 가기 수행
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        }
        // 뒤로 갈 수 없다면 원래 동작(앱 종료) 수행
        else {
            super.onBackPressed()
        }
    }
}