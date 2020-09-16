package com.ralph.gabb.projectpos.ui.main

import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseActivity
import com.ralph.gabb.projectpos.base.BaseFragment
import com.ralph.gabb.projectpos.base.BaseFragment.Companion.newInstance
import com.ralph.gabb.projectpos.extra.emptyString
import com.ralph.gabb.projectpos.ui.main.shop.ShopFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var lastBackStackName = emptyString()

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun viewCreated() {
        setUpPaneWidth()
        displayNavigation()

        openFragment(newInstance(ShopFragment::class.java), "Shop")
    }

    private fun setUpPaneWidth() {
        val params = LinearLayout.LayoutParams(paneWidth(), LinearLayout.LayoutParams.MATCH_PARENT)
        containerNav.layoutParams = params
    }

    private fun displayNavigation() {
        rvNavigation.setHasFixedSize(true)
        rvNavigation.layoutManager = LinearLayoutManager(this)
        rvNavigation.adapter = NavigationAdapter(this) {

            navigationSelected(it)

        }
    }

    private fun navigationSelected(navigation: Navigation) {
        if (navigation.name == lastBackStackName) return

        when (navigation) {
            Navigation.Home -> {
                openFragment(newInstance(ShopFragment::class.java), navigation.name)
            }

            Navigation.Receipts -> {
                openFragment(newInstance(ReceiptsFragment::class.java), navigation.name)
            }

            Navigation.Settings -> {
                openFragment(newInstance(SettingsFragment::class.java), navigation.name)
            }
        }
    }

    private fun openFragment(fragment: Fragment?, backStackName: String) {
        fragment?.apply {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            if (supportFragmentManager.findFragmentByTag(backStackName) != null) {
                val baseFragment = supportFragmentManager.findFragmentByTag(backStackName) as BaseFragment
                transaction
                    .show(baseFragment)
                    .commitAllowingStateLoss()
            } else {
                transaction
                    .add(R.id.containerFragment, this, backStackName)
                    .commitAllowingStateLoss()
            }

            supportFragmentManager.findFragmentByTag(lastBackStackName)?.apply {
                supportFragmentManager.beginTransaction()
                    .hide(this)
                    .commitAllowingStateLoss()
            }
        }

        lastBackStackName = backStackName
    }


    private fun paneWidth(): Int {
        val displayMetrics = resources.displayMetrics
        return ((0.10 * displayMetrics.widthPixels)).toInt()
    }
}
