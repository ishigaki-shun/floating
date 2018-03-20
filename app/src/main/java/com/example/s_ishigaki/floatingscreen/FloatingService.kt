package com.example.s_ishigaki.floatingscreen

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import android.graphics.PixelFormat
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager

class FloatingService: Service() {

    private lateinit var view: View
    private lateinit var windowManager: WindowManager

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Service起動中の通知を表示
        startNotification()

        // Viewをフローティングさせる
        startFloating()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        // フローティングしているViewを削除
        windowManager.removeView(view)
    }

    private fun startNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
                // アプリでユニークな ID
                "channel_1",
                // ユーザが「設定」アプリで見ることになるチャンネル名
                "channel_1",
                // チャンネルの重要度
                NotificationManager.IMPORTANCE_DEFAULT
        )

        // 端末にチャンネルを登録し、「設定」で見れるようにする
        manager.createNotificationChannel(channel)

        val notification = Notification.Builder(this, "channel_1")
                .setContentTitle("Floating App")
                .setContentText("フローティングサービス起動中")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()

        // フローティング画面を表示するためにServiceをフォアグラウンド化
        startForeground(1, notification)
    }

    private fun startFloating() {
        // WindowManagerを初期化
        windowManager = applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val layoutInflater = LayoutInflater.from(this)
        val layoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
        )

        // フローティングさせるViewを生成
        view = layoutInflater.inflate(R.layout.floating_service, null)

        // フローティング画面にViewを追加
        windowManager.addView(view, layoutParams)
    }
}
