package com.example.mobiledevelopmentcourselabapp

import android.Manifest
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mobiledevelopmentcourselabapp.databinding.ActivityMainBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.channelManager.NotificationChannelManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.threetenabp.AndroidThreeTen
import com.tbruyelle.rxpermissions3.RxPermissions

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val channelManager: NotificationChannelManager by lazy {
        NotificationChannelManager(NotificationManagerCompat.from(this), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidThreeTen.init(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navView: BottomNavigationView? = binding?.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val mainTabsSet = setOf(
            R.id.navigation_article,
            R.id.navigation_list,
            R.id.navigation_video,
            R.id.navigation_notifications
        )

        // Добавлявать новые элементы меню по их id
        val appBarConfiguration = AppBarConfiguration(mainTabsSet)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding?.navView?.isVisible = destination.id in mainTabsSet
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView?.setupWithNavController(navController)

        channelManager.createNotificationChannels()

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getNotificationRxPermission()
            else -> getNotificationManagerPermission(
                ContextCompat.getSystemService(
                    applicationContext,
                    NotificationManager::class.java
                ) as NotificationManager
            )
        }
    }

    // Main Activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return false
    }


    private fun getNotificationManagerPermission(notificationManager: NotificationManager) {
        if (notificationManager.areNotificationsEnabled().not()) showAlertDialog()
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Перейдите в настройки и разрешите уведомления")
            .setMessage("Так вы сможете получать напоминания")
            .setPositiveButton("Перейти") { _, _ -> }
            .setNegativeButton("Отменить", null)
            .setCancelable(false)
            .show()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getNotificationRxPermission() {
        RxPermissions(this).requestEach(Manifest.permission.POST_NOTIFICATIONS)
            .subscribe {
                if (it.granted.not()) showAlertDialog()
            }
    }
}