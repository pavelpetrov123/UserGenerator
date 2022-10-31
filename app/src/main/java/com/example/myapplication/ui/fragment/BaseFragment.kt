package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import timber.log.Timber

private var EMPTY_RES_ID = com.google.android.material.R.drawable.navigation_empty_icon

abstract class BaseFragment: Fragment() {

    abstract val layoutId: Int

    @get:IdRes
    abstract val toolbarId: Int

    @get:DrawableRes
    open val backIconResId: Int = androidx.appcompat.R.drawable.abc_ic_ab_back_material

    @get:ColorInt
    open var backIconColorId: Int = EMPTY_RES_ID

    open val displayHomeAsUpEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${this::class.java} onCreate")
        initViewModel()
    }

    protected open fun initViewModel() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(view)
    }

    private fun setupToolbar(view: View) {
        (activity as? AppCompatActivity)?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
            view.findViewById<Toolbar>(toolbarId).also {
                setSupportActionBar(it)
                this.supportActionBar?.let { supportBar ->
                    supportBar.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
                    supportBar.setDisplayShowTitleEnabled(true)
                    if (backIconColorId != EMPTY_RES_ID) {
                        val backIcon = ResourcesCompat.getDrawable(resources, backIconResId, null)
                        backIcon?.colorFilter =
                            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                                backIconColorId,
                                BlendModeCompat.DST
                            )
                        supportBar.setHomeAsUpIndicator(backIcon)
                    } else {
                        supportBar.setHomeAsUpIndicator(backIconResId)
                    }
                }
            }
        }
    }

    protected fun setTitle(titleResId: Int) {
        val titleStr = getString(titleResId)
        setTitle(titleStr)
    }

    protected fun setTitle(title: String) {
        (activity as? AppCompatActivity)?.apply {
            supportActionBar?.title = title
        }
        view?.findViewById<Toolbar>(toolbarId)?.also {
            it.title = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onNavigateBack(true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    open fun handleBackButtonClick(isMenuHomeButton: Boolean = false): Boolean {
        return when {
            parentFragmentManager.backStackEntryCount > 1 -> {
                parentFragmentManager.popBackStack()
                true
            }
            else -> false
        }
    }

    private fun onNavigateBack(isMenuHomeButton: Boolean) {
        if (!handleBackButtonClick(isMenuHomeButton)) {
            requireActivity().finish()
        }
    }
}