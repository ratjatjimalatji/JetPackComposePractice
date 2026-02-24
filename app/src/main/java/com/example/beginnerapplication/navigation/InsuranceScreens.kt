package com.example.beginnerapplication.navigation

enum class InsuranceScreens {
    //Each screen must be mentioned here
    HomeScreen,
    MedicalAidSelectionScreen;
    companion object {
        fun fromRoute(route:String): InsuranceScreens =
            when (route?.substringBefore("/")){
                HomeScreen.name -> HomeScreen
                MedicalAidSelectionScreen.name -> MedicalAidSelectionScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route %route is not recognised") as Throwable
            }
    }

}