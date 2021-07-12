package com.example.drinkwater.service

import android.app.IntentService
import android.content.Intent
import com.example.drinkwater.viewmodel.DrinkWaterReminderTask

class DrinkWaterReminderIntentService : IntentService("DrinkWaterReminderIntentService"){

    override fun onHandleIntent(intent: Intent?){
        val action = intent?.action
        DrinkWaterReminderTask.executeTask(this, action)

    }
}