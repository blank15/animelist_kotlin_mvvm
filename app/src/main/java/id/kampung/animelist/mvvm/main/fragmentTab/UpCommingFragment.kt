package id.kampung.animelist.mvvm.main.fragmentTab


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.databinding.MainFragmentUpCommingBinding
import id.kampung.animelist.mvvm.main.AdapterList
import id.kampung.animelist.mvvm.main.MainActivity
import id.kampung.animelist.mvvm.main.MainItemClickAction


class UpCommingFragment : Fragment(),MainItemClickAction {
    override fun onItemClicked(detailModel: DetailModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var viewBinding: MainFragmentUpCommingBinding
    private lateinit var mainAdapter: AdapterList
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
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpAnime()
    }

    override fun onResume() {
        super.onResume()
        viewBinding.viewModel!!.staratUpComing()
    }

    private fun setUpAnime() {
        val viewModel = viewBinding.viewModel

        if(viewModel != null){
            mainAdapter = AdapterList(ArrayList(),this)
            viewBinding.recyclerviewMain.adapter = mainAdapter

        }

    }

}
