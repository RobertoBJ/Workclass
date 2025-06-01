package com.example.workclass.ui.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(title:String, navController: NavController, location:String){
    TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,    // color de fondo de la Top App Bar
            titleContentColor = Color.White,  // color del título
            actionIconContentColor = Color.White // color de los íconos en la Top App Bar
        ),
        actions = {
            if (location == "accounts_screen") {
                IconButton(onClick = {
                    navController.navigate("manage_account_screen")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = "Go to saved account"
                    )
                }
                IconButton(onClick = {
                    navController.navigate("favorite_accounts_screen")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Icon"
                    )
                }
            }
        }
    )
}
