package com.example.beginnerapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beginnerapplication.screens.home.HomeScreen

@Composable
fun InsuranceNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = InsuranceScreens.HomeScreen){
        composable(InsuranceScreens.HomeScreen.name){

            HomeScreen(navController = navController)
        }
    }


}