package com.example.motors.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import  com.example.motors.R



@Composable
fun RegisterScreen(){
    var firstname by remember { mutableStateOf( "") }
    var lastname by remember { mutableStateOf( "") }
    var email by remember { mutableStateOf( "") }
    var password by remember { mutableStateOf( "") }
    var phonenumber by remember { mutableStateOf( "") }
    val context = LocalContext.current
    val passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(), verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register Here!!",
            fontSize = 40.sp,
            color = Color.Blue,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.White).padding(20.dp).fillMaxWidth()
        )
        Text(
            text = "Let's Get Started!",
            fontSize = 30.sp,
            color = Color.Magenta,
            textAlign = TextAlign.Center
            ,modifier = Modifier.background(Color.White).padding(20.dp).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .height(200.dp)
        )
        OutlinedTextField(
            value = firstname,
            onValueChange = { newFirstName -> firstname = newFirstName },
            label = { Text(text = "Enter  first name") },
            placeholder = { Text(text = "Please fill out this field") },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),leadingIcon = { Icon(
                Icons.Default.Person, contentDescription = "Person Icon") })

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lastname, onValueChange = { newLastName -> lastname = newLastName },
            label = { Text(text = "Enter last name") },
            placeholder = { Text(text = "Please fill out this field") },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),leadingIcon = { Icon(
                Icons.Default.Person, contentDescription = "Person Icon") })

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phonenumber, onValueChange = { newPhoneNumber -> phonenumber = newPhoneNumber },
            label = { Text(text = "Enter phone number") },
            placeholder = { Text(text = "Please fill out this field") },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),leadingIcon = { Icon(
                Icons.Default.Phone, contentDescription = "phone Icon") })

        OutlinedTextField(
            value = email, onValueChange = { newEmail -> email = newEmail },
            label = { Text(text = "Enter your email") },
            placeholder = { Text(text = "Please fill out this field") },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally), leadingIcon = { Icon(
                Icons.Default.Email, contentDescription = "Email Icon") })

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password, onValueChange = { newpassword -> password = newpassword  },
            label = { Text(text = "Enter your password") },
            placeholder = { Text(text = "Please enter fill out this field") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.wrapContentWidth().align
                (Alignment.CenterHorizontally), leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") })


        Spacer(modifier = Modifier.height(8.dp))




        Button(onClick = {
        }, modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            shape = RoundedCornerShape(0.9.dp),
        )
        { Text(
            text = buildAnnotatedString { append("Register") },
            modifier = Modifier.wrapContentHeight().align(Alignment.CenterVertically)
                .clickable {


                })
        }


    }

}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
