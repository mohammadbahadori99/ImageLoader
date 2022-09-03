package com.example.imageloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()
    lateinit var imageDataAdapter:ImageDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.rv_image_data)
        imageDataAdapter = ImageDataAdapter {adapterOnClick(it)}
        recyclerView.adapter = imageDataAdapter
        button.setOnClickListener {
            vm.fetchImages()
            lifecycleScope.launch {
                vm.myList.observe(this@MainActivity) {
                    imageDataAdapter.submitList(it)
                }
            }
        }
    }
    private fun adapterOnClick(imageData: ImageDataView) {
        Toast.makeText(this, imageData.path , Toast.LENGTH_LONG).show()
    }


}