package com.example.s_ishigaki.floatingscreen

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.s_ishigaki.floatingscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // onCreateで初期化する
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DataBindingのインスタンス化
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // 画面をフローティングさせるための権限を許可する
        requestPermission()

        // 各種Buttonのテキストを設定する
        initButtonConfig()

        // 各種Buttonのボタンアクションを設定する
        initButtonAction()
    }

    private fun requestPermission() {
        if (!hasOverlayPermission()) {
            requestOverlayPermission(1)
        }
    }

    /**
     * 各種Buttonのテキストの設定
     */
    private fun initButtonConfig() {
        val buttonConfig = ButtonData("start", "stop", "finish")
        binding.config = buttonConfig
    }

    /**
     * 各種Buttonのボタンアクションの設定
     */
    private fun initButtonAction() {
        binding.startButton.setOnClickListener { view ->
            // フローティング画面を表示する
            val intent = Intent(this, FloatingService::class.java)
            startService(intent)
        }
        binding.stopButton.setOnClickListener { view ->
            // フローティング画面を非表示にする
            val intent = Intent(this, FloatingService::class.java)
            stopService(intent)
        }
        binding.finishButton.setOnClickListener { view ->
            // MainActivityを終了する
            finish()
        }
    }
}
