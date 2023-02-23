package com.example.cvtamplate2

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentContainerView
import com.example.cvtamplate2.fragments.BasicInfoFragment
import com.example.cvtamplate2.fragments.HobbiesFragment
import com.example.cvtamplate2.fragments.LanguageFragment
import com.example.cvtamplate2.fragments.SkillsFragment
import com.example.cvtamplate2.*

class ResumePage : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var profileImage: ImageView

    private lateinit var skillsBtn: Button
    private lateinit var hobbiesBtn: Button
    private lateinit var langBtn: Button
    private lateinit var careerBtn: Button


    private lateinit var toolbar: Toolbar

    private lateinit var fragContainer: FragmentContainerView

    private lateinit var basicInfoFragment: BasicInfoFragment

    private var cvObject: CvObject? = null

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }




    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resume_page)

        toolbar = findViewById(R.id.toolBar)

        sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)



        println("CV: $cvObject")


        name = findViewById(R.id.nameText)
        email = findViewById(R.id.emailText)
        setSupportActionBar(toolbar)

        name.text = cvObject!!.fullName
        email.text = cvObject!!.email




        basicInfoFragment = BasicInfoFragment.new(
            cvObject!!.fullName!!, cvObject!!.gender!!, cvObject!!.age!!, cvObject!!.email!!
        )

        val skillsFragment = SkillsFragment.new(
            androidP = cvObject!!.skillsScore!![ANDROID_KEY]!!,
            flutterP = cvObject!!.skillsScore!![FLUTTER_KEY]!!,
            iosP = cvObject!!.skillsScore!![iOS_KEY]!!,
        )

        val hobbiesFragment = HobbiesFragment.new(
            isMusic = cvObject!!.hobbies!![MUSIC]!!,
            isGames = cvObject!!.hobbies!![GAMES]!!,
            isSport = cvObject!!.hobbies!![SPORT]!!
        )

        val languageFragment = LanguageFragment.new(
            isArabic = cvObject!!.languages!![AR]!!,
            isEnglish = cvObject!!.languages!![EN]!!,
            isFrench = cvObject!!.languages!![FR]!!
        )

        supportFragmentManager.beginTransaction().add(
            R.id.fragContainer, skillsFragment
        ).commit()




    }
}
