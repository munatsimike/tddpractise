package com.example.tddpractise.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val name: String, val surname: String) {

}