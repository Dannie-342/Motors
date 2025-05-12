package com.example.motors.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.motors.ui.theme.screens.SplashScreen
import com.example.motors.ui.theme.screens.clients.AddclientScreen
import com.example.motors.ui.theme.screens.clients.UpdateclientScreen
import com.example.motors.ui.theme.screens.clients.ViewClients
import com.example.motors.ui.theme.screens.home.HomeScreen
import com.example.motors.ui.theme.screens.login.LoginScreen
import com.example.motors.ui.theme.screens.register.RegisterScreen


@Composable
fun AppNavHost(navController:NavHostController= rememberNavController(),startDestination: String= ROUTE_SPLASH){
    NavHost(navController,startDestination=startDestination){
        composable(ROUTE_SPLASH){ SplashScreen { navController.navigate(ROUTE_REGISTER){
            popUpTo(
                ROUTE_SPLASH){inclusive=true}} }}
        composable ( ROUTE_REGISTER) { RegisterScreen(navController) }
        composable (ROUTE_LOGIN){ LoginScreen(navController) }
        composable (ROUTE_HOME){ HomeScreen(navController) }
        composable (ROUTE_ADD_CLIENT){ AddclientScreen(navController) }
        composable(ROUTE_VIEW_CLIENT){ ViewClients(navController) }
        composable("$ROUTE_UPDATE_CLIENT/{clientId}") {
                passedData -> UpdateclientScreen(
            navController, passedData.arguments?.getString("clientId")!! )
        }

    }
}






