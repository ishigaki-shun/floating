package com.example.s_ishigaki.floatingscreen

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder

class FloatingService: Service() {

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        startNotification()
    }

    private fun startNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("フローティングAPP")
                .setContentText("画面が浮いてるよ")
                .build()

        // フローティング画面を表示するためにServiceをフォアグラウンド化
        startForeground(1, notification)
    }

}
