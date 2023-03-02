package com.example.cvtamplate2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import com.example.cvtamplate2.fragments.BasicInfoFragment
import com.example.cvtamplate2.fragments.HobbiesFragment
import com.example.cvtamplate2.fragments.LanguageFragment
import com.example.cvtamplate2.fragments.SkillsFragment

class Result : AppCompatActivity() {

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
    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.layout.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun navigateToFragment(
        fragContainer: Int,
        fragManager: FragmentManager,
        fragment: Fragment,
        navStack: String
    ) {
        fragManager.beginTransaction().replace(
            fragContainer, fragment
        ).addToBackStack(navStack).commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        basicInfoFragment = BasicInfoFragment.new(
            intent.getStringExtra("fullname").toString(), intent.getStringExtra("gender").toString(),
            intent.getStringExtra("age")!!,intent.getStringExtra("email").toString()
        )
        toolbar = findViewById(R.id.toolBar)





        name = findViewById(R.id.nameText)
        email = findViewById(R.id.emailText)

        name.text = intent.getStringExtra("fullname")
        email.text = intent.getStringExtra("email")


        profileImage = findViewById(R.id.profilePic)
        fragContainer = findViewById(R.id.fragContainer)

        skillsBtn = findViewById(R.id.skillsBtn)
        hobbiesBtn = findViewById(R.id.hobbiesBtn)
        langBtn = findViewById(R.id.langBtn)
        careerBtn = findViewById(R.id.careerBtn)

        profileImage.setImageURI(
            intent.getStringExtra("profile")?.toUri()
        )

        supportActionBar?.hide()




        val skillsFragment = SkillsFragment.new(
            androidP = intent.getFloatExtra("android", 0.0F).toInt(),
            flutterP = intent.getFloatExtra("flutter",0.0F).toInt(),
            iosP = intent.getFloatExtra("ios",0.0F).toInt(),

            )

        val hobbiesFragment = HobbiesFragment.new(
            isMusic = intent.getBooleanExtra("music",false),
            isGames = intent.getBooleanExtra("games",false),
            isSport = intent.getBooleanExtra("sports",false)
        )

        val languageFragment = LanguageFragment.new(
            isArabic = intent.getBooleanExtra("arabic",false),
            isEnglish = intent.getBooleanExtra("english",false),
            isFrench = intent.getBooleanExtra("french",false)
        )

        supportFragmentManager.beginTransaction().add(
            R.id.fragContainer, skillsFragment
        ).commit()


        skillsBtn.setOnClickListener {
            navigateToFragment(R.id.fragContainer, supportFragmentManager, skillsFragment, "nav")
        }

        hobbiesBtn.setOnClickListener {
            navigateToFragment(R.id.fragContainer, supportFragmentManager, hobbiesFragment, "nav")
        }

        langBtn.setOnClickListener {
            navigateToFragment(R.id.fragContainer, supportFragmentManager, languageFragment, "nav")
        }

    }
}