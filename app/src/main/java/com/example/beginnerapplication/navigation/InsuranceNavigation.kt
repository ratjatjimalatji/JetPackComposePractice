package com.example.beginnerapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.beginnerapplication.screens.details.DetailsScreen
import com.example.beginnerapplication.screens.home.HomeScreen

@Composable
fun InsuranceNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = InsuranceScreens.HomeScreen.name) {


        composable(InsuranceScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        //app/detail-screen/id=34
        composable(InsuranceScreens.DetailsScreen.name+"/{insurerName}",
            arguments = listOf(navArgument("insurerName")
            {type = NavType.StringType})
        ) {
            backStackEntry ->
//            (navController = navController)
            DetailsScreen(navController = navController,
            backStackEntry.arguments?.getString("insurerName"))
        }

        composable("details/{insurerName}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("insurerName")
            // DetailScreen(name)
        }
    }


}