package id.kampung.animelist.mvvm.main.fragmentTab


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kampung.animelist.databinding.MainFragmentUpCommingBinding
import id.kampung.animelist.mvvm.main.MainActivity


class UpCommingFragment : Fragment(){

    private lateinit var viewBinding: MainFragmentUpCommingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = MainFragmentUpCommingBinding.inflate(inflater!!, container, false).apply {
            viewModel = (activity as MainActivity).obtainViewModel()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel

        }
        showProgressBar()
        return viewBinding.root
    }

    private fun showProgressBar() {
        viewBinding.viewModel!!.showProgress2.observe(this@UpCommingFragment, Observer { isShow ->
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
        viewBinding.viewModel!!.staratUpComing()
    }


}
