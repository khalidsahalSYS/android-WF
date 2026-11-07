package com.khalidsahalsys.wifiproxyguide

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            layoutDirection = View.LAYOUT_DIRECTION_RTL
            setPadding(dp(24), dp(28), dp(24), dp(24))
        }

        content.addView(textView("دليل إعداد وكيل Wi‑Fi", 26f, true, 0, 12))
        content.addView(textView(
            "يساعدك هذا التطبيق على الوصول إلى إعدادات Wi‑Fi في Android وإعداد وكيل يدوي لشبكة تملكها أو لديك إذن باستخدامها.",
            17f, false, 0, 20
        ))

        val settingsButton = Button(this).apply {
            text = "فتح إعدادات Wi‑Fi"
            setOnClickListener { openWifiSettings() }
        }
        content.addView(settingsButton)

        content.addView(textView("طريقة الإعداد", 20f, true, 0, 10))
        content.addView(textView(
            "1. افتح إعدادات Wi‑Fi واتصل بالشبكة المطلوبة.\n" +
                "2. افتح تفاصيل الشبكة أو خيار تعديل الشبكة.\n" +
                "3. ابحث عن إعداد الوكيل واختر يدوي (Manual).\n" +
                "4. أدخل عنوان الخادم والمنفذ اللذين زوّدك بهما مسؤول الشبكة، ثم احفظ.\n\n" +
                "قد تختلف أسماء القوائم حسب الشركة وإصدار Android. لإلغاء الوكيل، أعد الإعداد إلى بلا (None).",
            16f, false, 0, 20
        ))

        content.addView(textView("مهم: حدود Android", 20f, true, 0, 10))
        content.addView(textView(
            "تطبيق Android عادي لا يستطيع عادةً تغيير وكيل Wi‑Fi للنظام برمجيًا، ولا يحوّل الهاتف تلقائيًا إلى جسر Wi‑Fi أو نقطة وصول متزامنة. هذا التطبيق لا يمرّر حركة الشبكة ولا يراقبها ولا يفك تشفيرها. إذا أردت مشاركة الاتصال أو توسيع التغطية، استخدم نقطة الاتصال المدمجة أو راوترًا/مكرّرًا مناسبًا.",
            16f, false, 0, 8
        ))

        val scrollView = ScrollView(this).apply {
            isFillViewport = true
            addView(content)
        }
        setContentView(scrollView)
    }

    private fun openWifiSettings() {
        try {
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        } catch (_: ActivityNotFoundException) {
            try {
                startActivity(Intent(Settings.ACTION_SETTINGS))
            } catch (_: ActivityNotFoundException) {
                Toast.makeText(this, "تعذر فتح إعدادات الجهاز", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun textView(
        value: String,
        sizeSp: Float,
        bold: Boolean,
        topDp: Int,
        bottomDp: Int
    ) = TextView(this).apply {
        text = value
        textSize = sizeSp
        if (bold) setTypeface(typeface, android.graphics.Typeface.BOLD)
        gravity = Gravity.START
        textDirection = View.TEXT_DIRECTION_FIRST_STRONG
        setLineSpacing(dp(3).toFloat(), 1f)
        setPadding(0, dp(topDp), 0, dp(bottomDp))
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
