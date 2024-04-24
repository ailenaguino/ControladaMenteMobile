package com.losrobotines.controladamente.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.losrobotines.controladamente.R
import com.losrobotines.controladamente.data.firebaseAuth.Resource
import com.losrobotines.controladamente.ui.AuthViewModel
import com.losrobotines.controladamente.ui.login.LoginScreen
import com.losrobotines.controladamente.ui.signup.ui.theme.ControladaMenteTheme
import com.losrobotines.controladamente.ui.theme.mainColor
import com.losrobotines.controladamente.ui.theme.secondaryColor
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class SignupScreen : ComponentActivity() {


    private val viewModel by viewModels<AuthViewModel>()

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
                    signUpScreen(contextAplication, viewModel)
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun signUpScreen(contextAplication: Context, viewModel: AuthViewModel) {
        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }
        var userName by remember { mutableStateOf("") }
        var selectedSexo by remember { mutableStateOf("Seleccione su sexo") }

        val signupFlow = viewModel.signupFlow.collectAsState()

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
                painterResource(id = R.drawable.logo),
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
                label = { Text("Email"/*, color = mainColor*/) },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
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
                label = { Text("Contraseña"/*, color = mainColor*/) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
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
                label = { Text("Nombre"/*, color = mainColor*/) },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = mainColor,
                    unfocusedBorderColor = mainColor,
                    cursorColor = mainColor
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            SelectBirthday()

            Spacer(modifier = Modifier.height(13.dp))

            selectedSexo = SelectSexoDropMenu(selectedSexo)


            Spacer(modifier = Modifier.height(55.dp))

            Button(
                onClick = {
                    viewModel.signup(userEmail, userPassword, userName, selectedSexo)
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
                    startActivity(intent)
                    finish()
                },
                style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Default,
                    color = mainColor
                )
            )
        }
        signupFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val contetx = LocalContext.current
                    Toast.makeText(contetx, it.exception.message, Toast.LENGTH_LONG).show()
                }

                Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(68.dp),
                        color = secondaryColor,
                        strokeWidth = 5.dp
                    )
                }

                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        val intent = Intent(contextAplication, LoginScreen::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun SelectBirthday() {
        val date1 = remember { mutableStateOf("") }
        val isOpen = remember { mutableStateOf(false) }

        Row(verticalAlignment = Alignment.CenterVertically) {

            OutlinedTextField(
                readOnly = true,
                value = date1.value,
                label = { Text("Fecha de Nacimiento"/*, color = mainColor*/) },
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 2.dp),
                shape = RoundedCornerShape(35.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = mainColor,
                    unfocusedBorderColor = mainColor,
                    cursorColor = mainColor,
                )
            )
            IconButton(
                onClick = { isOpen.value = true }
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendar",
                    tint = secondaryColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 1.dp, end = 11.dp, top=2.dp)
                        .size(40.dp)
                )
            }
        }

        if (isOpen.value) {
            CustomDatePickerDialog(
                onAccept = {
                    isOpen.value = false // close dialog
                    if (it != null) { // Set the date
                        date1.value = Instant
                            .ofEpochMilli(it)
                            .atZone(ZoneId.of("UTC"))
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    }
                },
                onCancel = {
                    isOpen.value = false //close dialog
                }
            )
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomDatePickerDialog(
        onAccept: (Long?) -> Unit,
        onCancel: () -> Unit
    ) {
        val state = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = { onAccept(state.selectedDateMillis) }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = onCancel) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = state)
        }
    }

    
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun SelectSexoDropMenu(selectedSexo: String): String {
        var selectedSexo1 = selectedSexo
        val sexoList = arrayOf("ASASD", "FDFSFAD", "ZXCASDF")
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Surface(
                color = Color.Transparent,
            ) {
                TextField(
                    value = selectedSexo1,
                    onValueChange = {},
                    label = {
                        Text(text = "Sexo", color = mainColor) //
                    },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    shape = RoundedCornerShape(35.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Transparent, // Color del cursor
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 1.dp, end = 1.dp)
                        .menuAnchor()
                        .border(
                            width = 1.dp,
                            color = mainColor,
                            shape = RoundedCornerShape(35.dp)
                        )
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
                                selectedSexo1 = specialty
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
        return selectedSexo1
    }

}








