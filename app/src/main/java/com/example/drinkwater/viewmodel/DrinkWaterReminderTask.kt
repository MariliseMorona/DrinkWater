package com.example.drinkwater.viewmodel

import android.content.Context

class DrinkWaterReminderTask {

    companion object{
        const val ACTION_INCREMENT_WATER_COUNT = "action_increment_water_count"

        private fun incrementWaterCount(context: Context) =
            PreferencesUtils.incrementWater(context)

        fun executeTask(context: Context, action: String?){
            if(ACTION_INCREMENT_WATER_COUNT == action){
                incrementWaterCount(context)
            }
        }
    }
}