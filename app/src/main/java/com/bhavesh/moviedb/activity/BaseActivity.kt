package com.bhavesh.moviedb.activity

import androidx.appcompat.app.AppCompatActivity
import com.bhavesh.moviedb.retrofit.ApiInterface
import com.talkingtb.talkingtb.remote.ApiClient

open class BaseActivity : AppCompatActivity() {

    val apiService: ApiInterface by lazy { ApiClient.getClient().create(ApiInterface::class.java) }
}