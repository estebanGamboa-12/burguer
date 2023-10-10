package com.example.burguer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.burguer.R
import com.example.burguer.data.BurguerDataRepository
import com.example.burguer.data.local.XmlLocalDataSource
import com.example.burguer.domain.Burguer
import com.example.burguer.domain.GetBurguerUseCase
import com.example.burguer.domain.SaveBurguerUseCase

class MainActivity : AppCompatActivity() {

    val viewModel:MainModelView by lazy{
        MainModelView(
            SaveBurguerUseCase(BurguerDataRepository(XmlLocalDataSource(this))),
            GetBurguerUseCase(BurguerDataRepository(XmlLocalDataSource(this))),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupObservers()
        recoverData()
        viewModel.loadBurguer()
    }

    private  fun setupView(){
        val actionButtonSave=findViewById<Button>(R.id.action_save)
        actionButtonSave.setOnClickListener {
        viewModel.saveBurguer(
            getBurguerInput(),
            getMinutesInput(),
            getPercentBottomInput(),
            getPercentTopInput())
            Log.d("@dev", getMinutesInput()+ getBurguerInput() +getPercentBottomInput()+getPercentTopInput())

        }

    }
    private fun recoverData(){

        val actionButtonRecover=findViewById<Button>(R.id.action_recover)
        actionButtonRecover.setOnClickListener {
            viewModel.loadBurguer()
        }

    }

    private fun setupObservers(){
        val observer=Observer<MainModelView.UiState>{
            it.burguer?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this,observer)
    }
    //se introduce el texto en la vista.
    private fun bindData(burguer: Burguer){
        setBurguerInput(burguer.name)
        setMinutesInput(burguer.minutes)
        setPercentBottomInput(burguer.percentBottom)
        setPercentTopInput(burguer.percentTop)

    }


    private fun setBurguerInput(burguer:String){
        findViewById<TextView>(R.id.name_burguer).setText(burguer)
    }
    private fun setMinutesInput(minutes:String){
        findViewById<TextView>(R.id.porcentaje).setText(minutes + "min")
    }
    private fun setPercentTopInput( percentTop:String){
        findViewById<TextView>(R.id.name_porcentajeTop).setText(percentTop +"%")
    }
    private fun setPercentBottomInput(percentBottom:String){
        findViewById<TextView>(R.id.name_porcentajeBottom).setText(percentBottom + "%")
    }





    //se recogen todos los inputs
    private fun getBurguerInput():String=
        findViewById<EditText>(R.id.label_name_Burguer).text.toString()
    private fun getMinutesInput():String=
        findViewById<EditText>(R.id.label_minutes).text.toString()
    private fun getPercentBottomInput():String=
        findViewById<EditText>(R.id.label_porcentajeBottom).text.toString()
    private fun getPercentTopInput():String=
        findViewById<EditText>(R.id.label_porcentajeTop).text.toString()
}