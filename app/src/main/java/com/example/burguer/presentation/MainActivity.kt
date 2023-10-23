package com.example.burguer.presentation

import GsonSerialization
import XmlLocalDataSource
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.burguer.R
import com.example.burguer.data.BurguerDataRepository
import com.example.burguer.data.remote.ApiMockRemoteDataSource
import com.example.burguer.databinding.ActivityMainBinding
import com.example.burguer.domain.Burguer
import com.example.burguer.domain.GetBurguerUseCase
import com.example.burguer.app.extension.load
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val viewModel:MainModelView by lazy{
        MainModelView(
            GetBurguerUseCase(BurguerDataRepository(XmlLocalDataSource(this,GsonSerialization()), ApiMockRemoteDataSource()))

        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupObservers()
        viewModel.loadBurguer()
    }

    private lateinit var binding: ActivityMainBinding

    private fun setupBinding(){
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private  fun setupView(){
    }
    private fun setupObservers(){
        val observer=Observer<MainModelView.UiState>{
            it.errorApp?.apply {
                showError(it)
            }
            it.burguer?.let {
                bindData(it)
            }
        }
        viewModel.uiState.observe(this,observer)
    }
    private fun showError(error: MainModelView.UiState) {
        Snackbar.make(
            binding.root,
            getString(R.string.error),
            Snackbar.LENGTH_SHORT
        ).show()
    }
    //se introduce el texto en la vista.
    private fun bindData(burguer: List<Burguer>){

        binding.apply {
            imagen.load(burguer[0].url)
            title.text=burguer[0].title
            eta.text=burguer[0].eta
            discount.text=burguer[0].discount
            rate.text=burguer[0].rate

        }

    }




}