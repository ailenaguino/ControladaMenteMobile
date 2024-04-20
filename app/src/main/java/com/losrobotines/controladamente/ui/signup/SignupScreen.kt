package com.losrobotines.controladamente.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.losrobotines.controladamente.R
import com.losrobotines.controladamente.ui.login.LoginScreen
import com.losrobotines.controladamente.ui.signup.ui.theme.ControladaMenteTheme
import com.losrobotines.controladamente.ui.theme.mainColor
import com.losrobotines.controladamente.ui.theme.secondaryColor
import java.util.Date

class SignupScreen : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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


    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun signUpScreen(contextAplication: Context) {
        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }
        var userName by remember { mutableStateOf("") }


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
                painterResource(id = R.drawable.logo_white_background3),
                contentDescription = "LogoAPP",
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 75.dp)
            )

            Spacer(modifier = Modifier.height(0.dp))

            OutlinedTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
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

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = userPassword,
                onValueChange = { userPassword = it },
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

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(35.dp),
                label = { Text("Ingrese su Nombre", color = mainColor) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = mainColor,
                    unfocusedBorderColor = mainColor,
                    cursorColor = mainColor
                )
            )

            Spacer(modifier = Modifier.height(13.dp))
            SelectSexo()



            Spacer(modifier = Modifier.height(55.dp))

            Button(
                onClick = {

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
                Text("Crea tu cuenta")
            }

            Spacer(modifier = Modifier.height(30.dp))

            ClickableText(
                text = AnnotatedString("Ya estoy Registrado"),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    val intent = Intent(contextAplication, LoginScreen::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    contextAplication.startActivity(intent)
                },
                style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Default,
                    color = mainColor
                )
            )
        }

    }


    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun SelectSexo() {
        val sexoList = arrayOf("ASASD", "FDFSFAD", "ZXCASDF")
        var expanded by remember { mutableStateOf(false) }
        var selectedSexo by remember { mutableStateOf("Seleccione su sexo") }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            TextField(
                value = selectedSexo,
                onValueChange = {},
                label = {
                    Text(text = "Sexo", color = mainColor) // Color del texto,
                },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = RoundedCornerShape(35.dp),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = mainColor, // Color del cursor
                    focusedIndicatorColor = Color.Transparent, // Color del indicador de foco
                    unfocusedIndicatorColor = Color.Transparent // Color del indicador sin foco
                ),
                textStyle = TextStyle(color = mainColor), // Color del texto,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 1.dp, end = 1.dp)
                    .menuAnchor()
                    .border(
                        width = 1.dp,
                        color = mainColor,
                        shape = RoundedCornerShape(35.dp)
                    )  // prueba del borde
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                sexoList.forEach { specialty ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = specialty,
                            )
                        },
                        onClick = {
                            selectedSexo = specialty
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}







