package com.example.beginnerapplication.navigation

enum class InsuranceScreens {
    //Each screen must be mentioned here
    HomeScreen,
    LifeInsuranceQuoteScreen,
    VehicleInsuranceInputScreen,
    DetailsScreen,
    HomeInsuranceInputScreen,
    PersonalInsuranceInputScreen,
    BusinessInsuranceInputScreen,
    MedicalAidSelectionScreen;


    companion object {
        fun fromRoute(route:String): InsuranceScreens =
            when (route?.substringBefore("/")){
                HomeScreen.name -> HomeScreen
                MedicalAidSelectionScreen.name -> MedicalAidSelectionScreen
                VehicleInsuranceInputScreen.name -> VehicleInsuranceInputScreen
                DetailsScreen.name -> DetailsScreen
                HomeInsuranceInputScreen.name -> HomeInsuranceInputScreen
                PersonalInsuranceInputScreen.name -> PersonalInsuranceInputScreen
                BusinessInsuranceInputScreen.name -> BusinessInsuranceInputScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route %route is not recognised") as Throwable
            }
    }

}