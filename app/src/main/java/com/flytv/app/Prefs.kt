package com.flytv.app

import android.content.Context

object Prefs {
    private const val FILE = "flytv"
    private const val KEY_URL = "url"

    fun getUrl(c: Context): String =
        c.getSharedPreferences(FILE, Context.MODE_PRIVATE).getString(KEY_URL, "") ?: ""

    fun setUrl(c: Context, url: String) =
        c.getSharedPreferences(FILE, Context.MODE_PRIVATE).edit().putString(KEY_URL, url).apply()

    /** Добавляет https://, если схема не указана */
    fun normalize(raw: String): String {
        val s = raw.trim()
        if (s.isEmpty()) return ""
        return if (s.startsWith("http://", true) || s.startsWith("https://", true)) s else "https://$s"
    }
}
