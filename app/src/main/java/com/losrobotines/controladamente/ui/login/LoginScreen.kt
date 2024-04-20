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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.losrobotines.controladamente.R
import com.losrobotines.controladamente.ui.signup.SignupScreen
import com.losrobotines.controladamente.ui.theme.mainColor
import com.losrobotines.controladamente.ui.theme.secondaryColor


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

        Image(
            painterResource(id = R.drawable.img),
            contentDescription = "fondo",
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(49.dp))
            Image(
                painterResource(id = com.losrobotines.controladamente.R.drawable.logo_white_background3),
                contentDescription = "LogoAPP",
                modifier = Modifier
                    .size(280.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 80.dp)
            )

            email = email(email)

            Spacer(modifier = Modifier.height(5.dp))

            password = password(password)

            Spacer(modifier = Modifier.height(36.dp))

            SignInBotton(email, password)

            Spacer(modifier = Modifier.height(25.dp))

            ClickableText(
                text = AnnotatedString("Olvidé mi contraseña"),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {

                },
                style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Default,
                    color = mainColor
                )
            )

            Spacer(modifier = Modifier.height(55.dp))

            signupScreenBotton(contextAplication)

        }

    }

    @Composable
    private fun signupScreenBotton(contextAplication: Context) {
        OutlinedButton(
            onClick = {
                val intent = Intent(contextAplication, SignupScreen::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                contextAplication.startActivity(intent)
            },
            border = BorderStroke(2.dp, secondaryColor), //
            shape = RoundedCornerShape(15.dp), // Personaliza la esquina redondeada
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Soy nuevo,",
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "quiero registrarme",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    @Composable
    private fun SignInBotton(email: String, password: String) {
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
                .padding(start = 80.dp, end = 80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = secondaryColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text("Iniciar sesión")
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun password(password: String): String {
        var password1 = password
        OutlinedTextField(
            value = password1,
            onValueChange = { password1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(35.dp),
            label = { Text("Ingrese su contraseña", color = mainColor) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = mainColor,
                unfocusedBorderColor = mainColor,
                cursorColor = mainColor
            )
        )
        return password1
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun email(email: String): String {
        var email1 = email
        OutlinedTextField(
            value = email1,
            onValueChange = { email1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(35.dp),
            label = { Text("Ingrese su email", color = mainColor) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = mainColor,
                unfocusedBorderColor = mainColor
            )
        )
        return email1
    }

}