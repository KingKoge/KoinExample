package me.suttichai.develop.koinexample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity</*T : ViewDataBinding,*/ VM : ViewModel> : AppCompatActivity() {

    abstract val resLayoutId: Int

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(resLayoutId)
    }
}