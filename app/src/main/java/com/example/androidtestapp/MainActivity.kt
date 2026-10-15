package com.example.androidtestapp

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private var clicks = 0
    private lateinit var counterText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 36, 28, 28)
            setBackgroundColor(Color.rgb(245, 247, 250))
        }

        val title = TextView(this).apply {
            text = "Тестовая программа"
            textSize = 26f
            setTextColor(Color.rgb(25, 45, 75))
            gravity = Gravity.CENTER
        }
        content.addView(title, matchWrap())

        val subtitle = TextView(this).apply {
            text = "Простое приложение для проверки запуска Android-программы."
            textSize = 16f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 16, 0, 24)
        }
        content.addView(subtitle, matchWrap())

        counterText = TextView(this).apply {
            textSize = 20f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            text = "Нажатий: 0"
            setPadding(0, 12, 0, 12)
        }
        content.addView(counterText, matchWrap())

        val counterButton = Button(this).apply {
            text = "Нажми меня"
            setOnClickListener {
                clicks++
                counterText.text = "Нажатий: $clicks"
            }
        }
        content.addView(counterButton, matchWrap())

        val infoButton = Button(this).apply {
            text = "Информация об устройстве"
            setOnClickListener { showDeviceInfo() }
        }
        content.addView(infoButton, matchWrap())

        val resetButton = Button(this).apply {
            text = "Сбросить счётчик"
            setOnClickListener {
                clicks = 0
                counterText.text = "Нажатий: 0"
            }
        }
        content.addView(resetButton, matchWrap())

        val footer = TextView(this).apply {
            text = "Версия приложения: 1.0"
            textSize = 13f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 0)
        }
        content.addView(footer, matchWrap())

        val scroll = ScrollView(this)
        scroll.addView(content)
        setContentView(scroll)
    }

    private fun showDeviceInfo() {
        val info = listOf(
            "Версия Android: ${Build.VERSION.RELEASE}",
            "Уровень API: ${Build.VERSION.SDK_INT}",
            "Производитель: ${Build.MANUFACTURER}",
            "Модель: ${Build.MODEL}",
            "Устройство: ${Build.DEVICE}"
        ).joinToString("\n")

        android.app.AlertDialog.Builder(this)
            .setTitle("Данные устройства")
            .setMessage(info)
            .setPositiveButton("ОК", null)
            .show()
    }

    private fun matchWrap() = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}
