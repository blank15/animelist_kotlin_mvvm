package id.kampung.animelist.mvvm.main.fragmentTab

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SummerFragment()
            }
            else -> {
                return UpCommingFragment()
            }
        }

    }

    override fun getCount(): Int {
        return  2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return  when(position){
            0 ->"Summer"
            else -> {
                return "UpComing "
            }
        }
        }
}