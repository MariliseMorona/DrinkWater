package com.example.drinkwater.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.drinkwater.R
import com.example.drinkwater.service.DrinkWaterReminderIntentService
import com.example.drinkwater.viewmodel.DrinkWaterReminderTask
import com.example.drinkwater.viewmodel.PreferencesUtils

class MainActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateWaterCount()

        val btn = findViewById<Button>(R.id.btn_drink)


        btn.setOnClickListener {
            incrementWaterHandler()

        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)
    }
    fun updateWaterCount() {
        val count = PreferencesUtils.getWaterCount(this)
        var counter = findViewById<TextView>(R.id.txt_counter)
        counter.text = "$count"
    }

    fun incrementWaterHandler(){
        val intent = Intent(this, DrinkWaterReminderIntentService::class.java)
        intent.action = DrinkWaterReminderTask.ACTION_INCREMENT_WATER_COUNT
        startService(intent)
    }

    override fun onDestroy(){
        super.onDestroy()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.unregisterOnSharedPreferenceChangeListener(this)
}

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if(PreferencesUtils.KEY_WATER_COUNT == key){
            updateWaterCount()
        }
    }
}