package id.kampung.animelist.mvvm.main

import android.os.Bundle
import id.kampung.animelist.R
import id.kampung.animelist.base.BaseActivity
import id.kampung.animelist.util.obtainViewModel
import id.kampung.animelist.util.replaceFragmentInActivity

class MainActivity : BaseActivity(){
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment()
        setupViewModel()
    }
    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frame_main_content)
        MainFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frame_main_content)
        }
    }
    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {

        }
    }
    fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)

}
