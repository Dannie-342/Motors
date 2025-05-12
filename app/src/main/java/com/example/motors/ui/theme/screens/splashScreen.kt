package com.example.motors.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.motors.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToNext: () -> Unit){
    val splashScreenDuration =3000L
    LaunchedEffect(Unit) {
        delay(splashScreenDuration)
        onNavigateToNext()
    }
    Box(modifier = Modifier.
    fillMaxSize().
    background(Color.Black),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally){

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "App logo",
                modifier = Modifier.size(180.dp))


            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Welcome to Autoluxe Motors Purchasing App",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic ,
                fontFamily = FontFamily.SansSerif,
                color = Color.Blue,)




        }
    }




}