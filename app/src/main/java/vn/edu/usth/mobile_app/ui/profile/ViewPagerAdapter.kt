package vn.edu.usth.mobile_app.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> InfoFragment()
            1 -> ResourceFragment()
            2 -> ModelFragment()
            else -> InfoFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Information"
            1 -> "Datasets"
            2 -> "Models"
            else -> ""
        }
    }
}
