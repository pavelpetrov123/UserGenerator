package com.example.myapplication.domain.manager.impl

import com.example.myapplication.domain.manager.RandomUserGenerator
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.tools.Constants
import java.util.*
import kotlin.math.abs

class RandomUserGeneratorImpl: RandomUserGenerator {

    private val rand = Random()

    override fun generate(): User {
        val userId = abs(rand.nextInt())
        return User(
            userId.toString(),
            String.format(Constants.USERNAME_TEMPLATE, userId),
            Calendar.getInstance().time)
    }
}