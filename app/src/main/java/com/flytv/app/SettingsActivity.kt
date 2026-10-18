package com.flytv.app

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val input = findViewById<EditText>(R.id.urlInput)
        val save = findViewById<Button>(R.id.saveButton)
        input.setText(Prefs.getUrl(this))
        input.setSelection(input.text.length)

        fun doSave() {
            val url = Prefs.normalize(input.text.toString())
            if (url.isEmpty()) {
                input.error = getString(R.string.url_empty)
                return
            }
            Prefs.setUrl(this, url)
            finish() // MainActivity подхватит новый адрес в onResume
        }

        save.setOnClickListener { doSave() }
        input.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) { doSave(); true } else false
        }
    }
}
