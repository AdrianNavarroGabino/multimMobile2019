package com.example.tabs1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class FruitsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Log.d("Fruits", "onCreateView")
        return inflater!!.inflate(
            R.layout.fragment_webview_fruits,
            container,
            false)
    }
}