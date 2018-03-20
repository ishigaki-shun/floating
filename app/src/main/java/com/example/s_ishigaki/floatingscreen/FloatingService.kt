package com.example.s_ishigaki.floatingscreen

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context

class FloatingService: Service() {

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        startNotification()
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
                .setContentTitle("通知タイトル")
                .setContentText("通知コンテンツ")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()

        // フローティング画面を表示するためにServiceをフォアグラウンド化
        startForeground(1, notification)
    }
}
