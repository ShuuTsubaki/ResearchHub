package com.example.researchhub

data class ResearchItem(var id:String, var title:String, var description:String, var professor: String, var area: String){
    constructor():this("", "", "", "", "")
}