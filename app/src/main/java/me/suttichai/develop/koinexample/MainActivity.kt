package me.suttichai.develop.koinexample

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import me.suttichai.develop.koinexample.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override val resLayoutId: Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.observerEvent(this, Observer {
            handlerLoading(it == MainViewModel.Event.LOADING)
        })

        supportFragmentManager.beginTransaction().add(R.id.container, MainFragment()).commit()
    }

    private fun handlerLoading(isLoading: Boolean) {
        if (isLoading) {
            loading.visibility = View.VISIBLE
            container.visibility = View.INVISIBLE
        } else {
            loading.visibility = View.GONE
            container.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.callApi()
    }
}