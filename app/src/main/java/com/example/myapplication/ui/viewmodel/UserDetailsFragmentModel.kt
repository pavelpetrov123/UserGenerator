package com.example.myapplication.ui.viewmodel

import com.example.myapplication.ui.tools.Constants
import java.text.SimpleDateFormat
import java.util.*

class UserDetailsFragmentModel : BaseViewModel() {

    val usrDateFmt = SimpleDateFormat(Constants.USER_DATE_FORMAT, Locale.US)

}
