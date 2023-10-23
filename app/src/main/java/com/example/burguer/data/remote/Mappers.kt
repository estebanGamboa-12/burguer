package com.example.burguer.data.remote

import com.example.burguer.domain.Burguer

fun BurguerApiModel.toModel(): Burguer =
    Burguer("1", this.title,this.discount,this.rate, this.eta, this.urlImage )