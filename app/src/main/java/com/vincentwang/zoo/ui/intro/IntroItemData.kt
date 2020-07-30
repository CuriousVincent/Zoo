package com.vincentwang.zoo.ui.intro

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class IntroData(
    val results: List<Result>
):Parcelable
@Parcelize
data class Result(
    val E_Category: String,
    val E_Geo: String,
    val E_Info: String,
    val E_Memo: String,
    val E_Name: String,
    val E_Pic_URL: String,
    val E_URL: String,
    val E_no: String
):Parcelable