package id.kampung.animelist.mvvm.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.kampung.animelist.base.BaseFragment
import id.kampung.animelist.databinding.DetailFragmentBinding


class MainDetailFragment : BaseFragment() {
    private lateinit var viewBinding: DetailFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = DetailFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as MainDetailActivity).obtainViewModel()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@MainDetailFragment)

        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val idAnime = arguments.getString("idAnime")

        viewBinding.viewModel?.start(idAnime)
    }

    companion object {
        fun newInstance(idAnime : String) = MainDetailFragment().apply {
            val args = Bundle()

            args.putString("idAnime", idAnime)

            val fragment = MainDetailFragment()

            fragment.arguments = args

            return fragment
        }
    }
}
