package com.example.learnnavdrawer

sealed class Screens (val screen:String){
    data object Home:Screens("home")
    data object Profile:Screens("profile")
    data object Setting:Screens("setting")

}