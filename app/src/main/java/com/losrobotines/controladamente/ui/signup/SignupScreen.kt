package com.losrobotines.controladamente.ui.signup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.losrobotines.controladamente.ui.signup.ui.theme.ControladaMenteTheme

class SignupScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControladaMenteTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val contextAplication = LocalContext.current.applicationContext
                    signUpScreen(contextAplication)
                }
            }
        }
    }
}


@Composable
fun signUpScreen(contextAplication: Context) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(210.dp))
        Text(
            text = "Crea tu cuenta",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),

            )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            label = { Text("Email") },
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            label = { Text("Contraseña") },
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, start = 50.dp, end = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text("Registrarse")
        }

    }
}



