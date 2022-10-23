package com.example.myapplication.domain.manager

import com.example.myapplication.domain.model.User

interface RandomUserGenerator {
    fun generate(): User
}