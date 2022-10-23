package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.tools.Constants.KEY_USER_EXTRA
import com.example.myapplication.ui.viewmodel.UserDetailsFragmentModel
import kotlinx.android.synthetic.main.fragment_user_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsFragment : BaseFragment() {

    private val userDetailsFragmentModel: UserDetailsFragmentModel by viewModel()

    override val layoutId = R.layout.fragment_user_details

    override val toolbarId = R.id.toolbar

    override var backIconColorId: Int = R.color.white

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.user_details_fragment_label)
        val user: User? = arguments?.getSerializable(KEY_USER_EXTRA) as User?
        fillUserInfo(user)
    }

    private fun fillUserInfo(user: User?) {
        userIdText.text = user?.id
        userNameText.text = user?.name
        user?.date?.run {
            userDateText.text = userDetailsFragmentModel.usrDateFmt.format(this)
        }
    }
}