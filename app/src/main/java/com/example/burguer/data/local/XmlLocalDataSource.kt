package com.example.burguer.data.local

import android.content.Context
import com.example.burguer.app.ErrorApp
import com.example.burguer.domain.Burguer
import com.example.burguer.domain.SaveBurguerUseCase
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right



class XmlLocalDataSource (private  val context: Context){
    private val sharedPref = context.getSharedPreferences("burguers", Context.MODE_PRIVATE)

    fun saveBurguer(name:String,  minutes:String, percentTop:String, percentBottom:String):Either<ErrorApp,Boolean>{
        return try {
            with(sharedPref.edit()){
                putInt("id", (1..100).random())
                putString("username", name)
                putString("surname", minutes)
                putString("surname", percentTop)
                putString("surname", percentBottom)
                apply()
            }
            true.right()
        }catch (ex:Exception){
            ErrorApp.UnkowError.left()
        }
    }

    //val id:Int,
    // val name:String,
    // val minutes:String
    // ,val percentTop:String,
    // val percentBottom:String
    fun findBurguer(): Either<ErrorApp, Burguer> {
        return try {
            Burguer(
                sharedPref.getInt("id",0),
                sharedPref.getString("name", "")!!,
                sharedPref.getString("minutes", "")!!,
                sharedPref.getString("percentTop", "")!!,
                sharedPref.getString("percentBottom", "")!!
            ).right()
        }catch (ex:Exception){
            return ErrorApp.UnkowError.left()
        }
    }


}