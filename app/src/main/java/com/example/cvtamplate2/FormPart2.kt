package com.example.cvtamplate2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity


class FormPart2 : AppCompatActivity() {

    private lateinit var androidSk: SeekBar
    private lateinit var iosSk: SeekBar
    private lateinit var flutterSk: SeekBar

    private lateinit var arabicChbx: CheckBox
    private lateinit var frenchChbx: CheckBox
    private lateinit var englishChbx: CheckBox



    private lateinit var musicChbx: CheckBox
    private lateinit var sportChbx: CheckBox
    private lateinit var gamesChbx: CheckBox

    private lateinit var submitBtn: Button

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_part2)
        supportActionBar?.title = resources.getString(R.string.title1)

        androidSk = findViewById(R.id.andrSkBar)
        iosSk = findViewById(R.id.iosSkBar)
        flutterSk = findViewById(R.id.fltrSkBar)
        arabicChbx = findViewById(R.id.ar)
        frenchChbx = findViewById(R.id.fr)
        englishChbx = findViewById(R.id.en)
        musicChbx = findViewById(R.id.music)
        sportChbx = findViewById(R.id.sport)
        gamesChbx = findViewById(R.id.games)

        submitBtn = findViewById(R.id.submitBtn)

        sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        submitBtn.setOnClickListener {
            var  email = intent.getStringExtra("email").toString()
            var  age = intent.getStringExtra("age").toString()
            var  gender = intent.getStringExtra("gender").toString()



            Intent(this, Result::class.java).let { i ->

                i.putExtra("fullname",intent.getStringExtra("fullname"))
                i.putExtra("email",email)
                i.putExtra("age",age)
                i.putExtra("gender",gender)
                i.putExtra("android",androidSk.autofillValue)
                i.putExtra("ios",iosSk.autofillValue)
                i.putExtra("flutter",flutterSk.autofillValue)
                i.putExtra("french",frenchChbx.isChecked)
                i.putExtra("arabic",arabicChbx.isChecked)
                i.putExtra("english",englishChbx.isChecked)
                i.putExtra("sports",sportChbx.isChecked)
                i.putExtra("games",gamesChbx.isChecked)
                i.putExtra("music",musicChbx.isChecked)

            }
            finish()
        }

    }
}