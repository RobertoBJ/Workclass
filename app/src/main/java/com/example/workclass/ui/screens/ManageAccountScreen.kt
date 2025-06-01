package com.example.workclass.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.workclass.data.model.AccountModel
import com.example.workclass.ui.components.TopBarComponent
import com.example.workclass.viewmodel.AccountViewModel


@Composable
fun ManageAccountScreen(
    navController: NavController,
    id: Int? = null,
    viewModel: AccountViewModel = viewModel()
) {
    val context = LocalContext.current
    val account = remember { mutableStateOf(AccountModel()) }

    // Cargar la cuenta si hay un ID
    LaunchedEffect(id) {
        if (id != null) {
            viewModel.getAccount(id) { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        account.value = it
                    }
                } else {
                    Log.d("debug", "Error al obtener cuenta")
                }
            }
        }
    }

    // Usamos Scaffold para tener un topBar fijo, y el contenido acomodado sin solaparse
    androidx.compose.material3.Scaffold(
        topBar = {
            TopBarComponent(
                title = "Add account",
                navController = navController,
                location = "manage_account_screen"
            )
        }
    ) { innerPadding ->
        // innerPadding evita que el contenido se dibuje debajo del topBar
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(innerPadding)  // Respeta la altura de la topBar
                .padding(10.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = account.value.name,
                maxLines = 1,
                label = { Text("Account Name") },
                onValueChange = {
                    account.value = account.value.copy(name = it)
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = account.value.username,
                maxLines = 1,
                label = { Text("Account Username") },
                onValueChange = {
                    account.value = account.value.copy(username = it)
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = account.value.password,
                maxLines = 1,
                label = { Text("Account Password") },
                onValueChange = {
                    account.value = account.value.copy(password = it)
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = account.value.description,
                maxLines = 1,
                label = { Text("Account Description") },
                onValueChange = {
                    account.value = account.value.copy(description = it)
                }
            )


            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(
                    secondaryContainer = Color.Black,  // Fondo del botón en negro
                    onSecondaryContainer = Color.White   // Texto del botón en blanco
                )
            ) {
                FilledTonalButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    onClick = {
                        if (id == null) {
                            // Crear
                            TryAccount(
                                account.value.name,
                                account.value.username,
                                account.value.password,
                                account.value.description,
                                viewModel,
                                context
                            )
                        } else {
                            // Actualizar
                            viewModel.updateAccount(id, account.value) { response ->
                                Toast.makeText(context, "Cuenta actualizada correctamente", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                ) {
                    Text(if (id == null) "Guardar cuenta" else "Actualizar cuenta")
                }
            }

        }
    }
}


fun TryAccount(name:String,username:String,password:String,description:String,viewModel: AccountViewModel, context: Context) {
    if (name == "" || username == "" || password == "" || description == "") {
        Toast.makeText(
            context,
            "Error agregue todos los datos ",
            Toast.LENGTH_SHORT
        ).show()
    } else {
        val add_Account = AccountModel(0, name, username, password, description)
        viewModel.createAccount(add_Account) { jsonResponse ->
            val CreateStatus = jsonResponse?.get("addAccount")?.asString
            Toast.makeText(
                context,
                "Se agrego la cuenta de forma exitosa",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}