package id.kampung.animelist.mvvm.main.fragmentTab


import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.databinding.FragmentSummerBinding
import id.kampung.animelist.mvvm.main.AdapterList
import id.kampung.animelist.mvvm.main.MainActivity
import id.kampung.animelist.mvvm.main.MainItemClickAction
import id.kampung.animelist.mvvm.detail.MainDetailActivity


class SummerFragment : Fragment(),MainItemClickAction {
    override fun onItemClicked(detailModel: DetailModel) {
        val intent = Intent(context, MainDetailActivity::class.java)
        intent.putExtra("idAnime",detailModel.mal_id )
        startActivity(intent)
    }

    private lateinit var viewBinding: FragmentSummerBinding
    private lateinit var mainAdapter: AdapterList
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentSummerBinding.inflate(inflater!!, container, false).apply {
            viewModel = (activity as MainActivity).obtainViewModel()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel

        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpAnime()
    }

    override fun onResume() {
        super.onResume()
        viewBinding.viewModel!!.start()
    }

    private fun setUpAnime() {
        val viewModel = viewBinding.viewModel

          if(viewModel != null){
              mainAdapter = AdapterList(ArrayList(),this)
              viewBinding.recyclerviewMain.adapter = mainAdapter

          }

    }
}
