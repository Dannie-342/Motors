package com.example.motors.ui.theme

import android.inputmethodservice.Keyboard.Row
import android.media.tv.TvContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.size.OriginalSize
import com.example.motors.R

@Composable
fun LoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(), verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Back!",
            fontSize = 40.sp,
            color = Color.Blue,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.White).padding(20.dp).fillMaxWidth()
        )
        Text(
            text = "Sign Up into your account",
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.White).padding(20.dp).fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .height(200.dp).align(Alignment.CenterHorizontally)

        )

        OutlinedTextField(
            value = email,
            onValueChange = { newEmail -> email = newEmail },
            label = { Text(text = "Email Address") },
            placeholder = { Text(text = "Enter email") },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = {
                Icon(
                    Icons.Default.Email, contentDescription = "Email Icon"
                )
            })

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { newpassword -> password = newpassword },
            label = { Text(text = "Enter your password") },
            placeholder = { Text(text = "Enter password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") })

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Forgot Password?",
            fontSize = 15.sp,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.White).padding(20.dp).fillMaxWidth()


        )

        Button(
            onClick = {},
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        )
        {
            Text(
                text = buildAnnotatedString { append("Sign up!") },
                color = Color.Black,
                modifier = Modifier.wrapContentHeight().align(Alignment.CenterVertically)
                    .clickable {
                    })
        }
        Text(
            text = "------------------Or-----------------",
            fontSize = 15.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.White).padding(20.dp).fillMaxWidth()
        )

        Button(
            onClick = {},
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.LightGray)

        ){
            Row(
                modifier = Modifier.wrapContentHeight().align(Alignment.CenterVertically)
                    .clickable {
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "logo",
                    modifier = Modifier.size(24.dp)
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .height(25.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = buildAnnotatedString { append("Sign in with Google") },
                    color = Color.Black,
                )
            }



        }

        Button(
            onClick = {},
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
            Row(
                modifier = Modifier.wrapContentHeight().align(Alignment.CenterVertically)
                    .clickable {
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.facebook),
                    contentDescription = "logo",
                    modifier = Modifier.size(24.dp)
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .height(25.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = buildAnnotatedString { append("Sign in with Facebook") },
                    color = Color.Black,
                )



            }



        }


    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}

