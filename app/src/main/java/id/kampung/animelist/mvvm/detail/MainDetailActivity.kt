package id.kampung.animelist.mvvm.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import id.kampung.animelist.R
import id.kampung.animelist.base.BaseActivity
import id.kampung.animelist.util.obtainViewModel
import id.kampung.animelist.util.replaceFragmentInActivity



class MainDetailActivity : BaseActivity() {
    private lateinit var viewModel: MainDetailModel
     private var idAnime: String = "34134"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)
        val id = intent.getIntExtra("idAnime",0)
        idAnime = id.toString()

        if (Build.VERSION.SDK_INT >= 19) {
            val v = window.decorView
            v.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }


        setupFragment()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {

        }
    }
    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frame_main_content)
        MainDetailFragment.newInstance(idAnime).let {
            replaceFragmentInActivity(it,R.id.frame_main_content)
        }
    }
    fun obtainViewModel(): MainDetailModel = obtainViewModel(MainDetailModel::class.java)


}
