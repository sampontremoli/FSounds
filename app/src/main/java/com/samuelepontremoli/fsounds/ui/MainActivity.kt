package com.samuelepontremoli.fsounds.ui

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.samuelepontremoli.fsounds.R
import com.samuelepontremoli.fsounds.commons.BaseActivity
import com.samuelepontremoli.fsounds.commons.BasePresenter
import com.samuelepontremoli.fsounds.ui.detail.SoundDetailFragment
import com.samuelepontremoli.fsounds.ui.detail.SoundDetailPresenter
import com.samuelepontremoli.fsounds.ui.search.SoundSearchFragment
import com.samuelepontremoli.fsounds.ui.search.SoundSearchPresenter

/**
* FSounds - Created by s.pontremoli on 24/07/2017.
*/
class MainActivity: BaseActivity() {

    private var presenter: BasePresenter? = null

    private val toolbar: Toolbar by lazy { findViewById(R.id.mainToolbar) as Toolbar }

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        initDefaultFragment()
    }

    private fun initDefaultFragment() {
        val searchFragment = SoundSearchFragment.newInstance()
        if (presenter == null) {
            presenter = SoundSearchPresenter(searchFragment)
        }
        changeFragment(searchFragment, true, SoundSearchFragment.TAG)
    }

    fun initDetailFragment(soundId: Int) {
        val detailFragment = SoundDetailFragment.newInstance()
        presenter = SoundDetailPresenter(detailFragment, soundId)
        changeFragment(detailFragment, false, SoundDetailFragment.TAG)
        showBackButton()
    }

    fun showBackButton() {
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_main_toolbar, menu)
        return true
    }

}