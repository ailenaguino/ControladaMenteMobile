package com.losrobotines.controladamente.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.losrobotines.controladamente.ui.signup.SignupScreen

class LoginScreen : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            com.losrobotines.controladamente.ui.theme.ControladaMenteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val contextAplication = LocalContext.current.applicationContext
                    LoginScreen(contextAplication)
                }
            }
        }
    }

    private fun Context.findActivity(): Activity {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) return context
            context = context.baseContext
        }
        throw IllegalStateException("no activity")
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("PrivateResource", "NotConstructor")
    @Composable
    fun LoginScreen(contextAplication: Context) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Spacer(modifier = Modifier.height(0.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Ingrese su email") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Ingrese su contraseña") },
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(findActivity()) { task ->
                            if (task.isSuccessful) {
                                Log.i("login", "signInWithEmail:success")
                                val user = auth.currentUser
                                Toast.makeText(
                                    baseContext,
                                    "Success!!",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            } else {
                                Log.w("login", "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext,
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 30.dp, end = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text("Iniciar sesión")
            }

            Spacer(modifier = Modifier.height(25.dp))

            ClickableText(
                text = AnnotatedString("¿No tenes cuenta? Registrate aca"),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    val intent = Intent(contextAplication, SignupScreen::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    contextAplication.startActivity(intent)
                },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(25.dp))

            ClickableText(
                text = AnnotatedString("¿Olvidaste tu contraseña?"),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {

                },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Black
                )
            )
        }

    }
}