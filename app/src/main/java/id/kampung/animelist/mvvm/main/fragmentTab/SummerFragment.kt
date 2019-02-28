package id.kampung.animelist.mvvm.main.fragmentTab


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.databinding.MainFragmentSummerBinding
import id.kampung.animelist.mvvm.main.AdapterList
import id.kampung.animelist.mvvm.main.MainActivity
import id.kampung.animelist.mvvm.main.MainItemClickAction
import id.kampung.animelist.mvvm.detail.MainDetailActivity


class SummerFragment : Fragment() {


    private lateinit var viewBinding: MainFragmentSummerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = MainFragmentSummerBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as MainActivity).obtainViewModel()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@SummerFragment)
        }

        showProgressBar()
        return viewBinding.root
    }

    private fun showProgressBar() {
        viewBinding.viewModel!!.showProgress.observe(this@SummerFragment, Observer { isShow ->
            ShowProgress(isShow)
        })
    }

    private fun ShowProgress(show: Boolean?) {
        if (show!!){
            viewBinding.progressbar.visibility = View.VISIBLE
        }else
            viewBinding.progressbar.visibility = View.GONE

    }


    override fun onResume() {
        super.onResume()
        viewBinding.viewModel!!.start()
    }


}
