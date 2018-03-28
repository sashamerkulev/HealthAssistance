package ru.health.assistance.domain.dto

import android.support.annotation.NonNull
import java.util.*

/**
 * Created by sasha_merkulev on 28.03.2018.
 */

data class User(@NonNull val id : String,
                val micard_seria : String,
                val micard_number : String,
                val surname : String,
                val name : String,
                val patronymic : String,
                val birth_date : Date,
                val sex : Int,
                val document : String,
                val visa_number : String,
                val purpose : String,
                val host : String,
                val photo : String,
                val from : Date,
                val to : Date)