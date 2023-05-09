package com.app.musicplayer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.app.musicplayer.Constant.CALL
import com.app.musicplayer.Constant.CLOUD
import com.app.musicplayer.Constant.DRIVE
import com.app.musicplayer.Constant.MENU
import com.app.musicplayer.Constant.MUSIC
import com.app.musicplayer.Constant.NAVIGATION
import com.app.musicplayer.Constant.PHOTOS
import com.app.musicplayer.fragment.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val homeFragment = HomeFragment()
    private val callFragment = CallFragment()
    private val navigationFragment = NavigationFragment()
    private val playerFragment = PlayerFragment()
    private val videoLibraryFragment = VideoLibraryFragment()
    private val widgetFragment = WidgetFragment()
    private val addFragment = AddFragment()

    private var activeFragment: Fragment = playerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = WindowInsetsControllerCompat(
            window, window.decorView
        )
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        music_img.setOnClickListener(this)
        menu_img.setOnClickListener(this)
        call_img.setOnClickListener(this)
        photos_img.setOnClickListener(this)
        cloud_img.setOnClickListener(this)
        navigation_img.setOnClickListener(this)
        drive_img.setOnClickListener(this)
        init()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.music_img -> {
                setSelectedView(MUSIC)
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(playerFragment)
                    .commit()
                activeFragment = playerFragment
            }

            R.id.menu_img -> {
                setSelectedView(MENU)
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(homeFragment)
                    .commit()
                activeFragment = homeFragment
            }

            R.id.call_img -> {
                setSelectedView(CALL)
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(callFragment)
                    .commit()
                activeFragment = callFragment
            }

            R.id.cloud_img -> {
                setSelectedView(CLOUD)
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(videoLibraryFragment)
                    .commit()
                activeFragment = videoLibraryFragment
            }

            R.id.photos_img -> {
                setSelectedView(PHOTOS)
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(addFragment)
                    .commit()
                activeFragment = addFragment
            }

            R.id.navigation_img -> {
                setSelectedView(NAVIGATION)
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(navigationFragment)
                    .commit()
                activeFragment = navigationFragment
            }

            R.id.drive_img -> {
                setSelectedView(DRIVE)
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(widgetFragment)
                    .commit()
                activeFragment = widgetFragment
            }

            else -> {
                setSelectedView(MUSIC)
                supportFragmentManager.beginTransaction().hide(activeFragment).show(playerFragment)
                    .commit()
                activeFragment = playerFragment
            }
        }
    }

    private fun init() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, videoLibraryFragment, "VideoLibrary").hide(videoLibraryFragment)
            add(R.id.container, callFragment, "Call").hide(callFragment)
            add(R.id.container, playerFragment, "Player")
            add(R.id.container, widgetFragment, "Widget").hide(widgetFragment)
            add(R.id.container, addFragment, "Add").hide(addFragment)
            add(R.id.container, navigationFragment, "Navigation").hide(navigationFragment)
            add(R.id.container, homeFragment, "Home").hide(homeFragment)
        }.commit()

        setSelectedView(MUSIC)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setSelectedView(title: String) {
        music_img.setImageDrawable(getDrawable(R.drawable.baseline_music_24))
        navigation_img.setImageDrawable(getDrawable(R.drawable.baseline_navigation_24))
        call_img.setImageDrawable(getDrawable(R.drawable.baseline_call_24))
        menu_img.setImageDrawable(getDrawable(R.drawable.baseline_menu_24))
        photos_img.setImageDrawable(getDrawable(R.drawable.baseline_photos_24))
        cloud_img.setImageDrawable(getDrawable(R.drawable.baseline_cloud_24))
        drive_img.setImageDrawable(getDrawable(R.drawable.baseline_drive_24))
        when (title) {
            MUSIC -> music_img.setImageDrawable(getDrawable(R.drawable.baseline_music_24_white))
            NAVIGATION -> navigation_img.setImageDrawable(getDrawable(R.drawable.baseline_navigation_24_white))
            CALL -> call_img.setImageDrawable(getDrawable(R.drawable.baseline_call_24_white))
            MENU -> menu_img.setImageDrawable(getDrawable(R.drawable.baseline_menu_24_white))
            PHOTOS -> photos_img.setImageDrawable(getDrawable(R.drawable.baseline_photos_24_white))
            CLOUD -> cloud_img.setImageDrawable(getDrawable(R.drawable.baseline_cloud_24_white))
            DRIVE -> drive_img.setImageDrawable(getDrawable(R.drawable.baseline_drive_24_white))
        }
    }


}