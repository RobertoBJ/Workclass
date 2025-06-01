package com.example.workclass.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainMenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        Text("Main Menu Screen")
        Button(
            onClick = { navController.navigate("home_screen") }
        ) {
            Text("Main Menu")

        }
        Button(
            onClick = { navController.navigate("test_screen") }
        ) {
            Text("TestScreen")

        }

        Button(
            onClick = { navController.navigate("xbox_screen") }
        ) {
            Text("XboxScreen")

        }
        Button(
            onClick = { navController.navigate("components_screen") }
        ) {
            Text("ComponentsScreen")
        }
        Button(
            onClick = { navController.navigate("login_screen") }
        ) {
            Text("LoginScreen")
        }
        Button(
            onClick = { navController.navigate("camara_screen") }
        ) {
            Text("Camara Api")
        }
        Button(
            onClick = { navController.navigate("agenda_screen") }
        ) {
            Text("Agenda Api")
        }
        Button(onClick = { navController.navigate("notification_screen") }) {
            Text("Ir a Notificaciones")
        }
        Button(
            onClick = { navController.navigate("biometric_screen")}

        ) {
            Text("Biometric Sensor")
        }

    }
}