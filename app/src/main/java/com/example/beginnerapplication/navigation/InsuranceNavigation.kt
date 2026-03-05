package com.example.beginnerapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.beginnerapplication.screens.details.DetailsScreen
import com.example.beginnerapplication.screens.home.HomeScreen
import com.example.beginnerapplication.screens.home.InsurerListContent
import com.example.beginnerapplication.screens.lifeInsurance.LifeInsuranceQuoteScreen
import com.example.beginnerapplication.screens.medicalAidSelection.MedicalAidSelection

@Composable
fun InsuranceNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = InsuranceScreens.HomeScreen.name) {

        //Home screen
        composable(InsuranceScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        //app/detail-screen/id=34
        composable(
            InsuranceScreens.DetailsScreen.name + "/{insurerName}",
            arguments = listOf(
                navArgument("insurerName")
                { type = NavType.StringType })
        ) { backStackEntry ->
//            (navController = navController)
            DetailsScreen(
                navController = navController,
                backStackEntry.arguments?.getString("insurerName")
            )
        }

        //Medical Aid Selection Screen
        composable(InsuranceScreens.MedicalAidSelectionScreen.name) {
            MedicalAidSelection(navController = navController)
        }

        //Life Insurance Quote Screen
        composable(InsuranceScreens.LifeInsuranceQuoteScreen.name) {
            LifeInsuranceQuoteScreen(navController = navController)
        }

        composable("insurers_list") {
            InsurerListContent(navController = navController)
        }
    }

}