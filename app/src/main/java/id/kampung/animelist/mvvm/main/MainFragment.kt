package id.kampung.animelist.mvvm.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kampung.animelist.mvvm.main.fragmentTab.PagerAdapter
import id.kampung.animelist.base.BaseFragment
import id.kampung.animelist.databinding.MainFragmentBinding


class MainFragment : BaseFragment() {
    private lateinit var viewBinding: MainFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        viewBinding = MainFragmentBinding.inflate(inflater!!, container, false).apply {
            viewModel = (activity as MainActivity).obtainViewModel()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@MainFragment)
        }

        val fragmentAdapter = PagerAdapter(fm = childFragmentManager)
        viewBinding.viewpagerMain.adapter = fragmentAdapter
        viewBinding.tabsMain.setupWithViewPager(viewBinding.viewpagerMain)
        return viewBinding.root
    }

    companion object {
        fun newInstance() = MainFragment().apply {
        }
    }

}

