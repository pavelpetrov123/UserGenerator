package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.adapter.UsersListRecyclerViewAdapter
import com.example.myapplication.ui.extension.observe
import com.example.myapplication.ui.tools.Constants.KEY_USER_EXTRA
import com.example.myapplication.ui.viewmodel.UsersListFragmentModel
import kotlinx.android.synthetic.main.fragment_users_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersListFragment : BaseFragment() {

    private val userListFragmentModel: UsersListFragmentModel by viewModel()

    override val layoutId = R.layout.fragment_users_list

    override val toolbarId = R.id.toolbar

    override var backIconColorId: Int = R.color.white

    override val displayHomeAsUpEnabled = false

    private var adapter: UsersListRecyclerViewAdapter? = null

    override fun initViewModel() {
        observe(userListFragmentModel.getUsersLiveData, ::onUsersList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.users_list_fragment_label)
        initAdapter()
        fillAdapter(userListFragmentModel.lastUsersList)
        userListFragmentModel.getUsers()
    }

    private fun onUsersList(users: List<User>?) {
        fillAdapter(users)
    }

    private fun initAdapter() {
        if (usersRecyclerView?.adapter == null) {
            initRecyclerView()
            adapter = UsersListRecyclerViewAdapter()
            adapter?.onSelectItemListener = { user ->
                navigateToDetailsScreen(user)
            }
            usersRecyclerView?.adapter = adapter
        }
    }

    private fun fillAdapter(list: List<User>?) {
        adapter?.submitList(list)
    }

    private fun navigateToDetailsScreen(user: User?) {
        val args = Bundle()
        args.putSerializable(KEY_USER_EXTRA, user)
        findNavController().navigate(R.id.action_usersListFragment_to_usersDetailsFragment, args)
    }

    private fun initRecyclerView() {
        usersRecyclerView.layoutManager = LinearLayoutManager(activity)
        usersRecyclerView?.recycledViewPool?.setMaxRecycledViews(0, 100)
    }
}
