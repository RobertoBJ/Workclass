package com.example.trabajoclases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.trabajoclases.screens.MainMenuScreen
import com.example.trabajoclases.screens.HomeScreen
import com.example.trabajoclases.screens.TestScreen
import com.example.trabajoclases.ui.theme.TrabajoclasesTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            //creacion de temas
            TrabajoclasesTheme {
                ComposeMultiScreenApp()



            }
        }
    }

    @Composable
    fun ComposeMultiScreenApp(){
        val navController = rememberNavController()
        SetupNavGraph(navController = navController)
    }
    @Composable
    fun SetupNavGraph(navController: NavHostController){
        NavHost(navController = navController, startDestination = "main_menu"){
            composable("main_menu") { MainMenuScreen(navController) }
            composable("home_screen") { HomeScreen(navController) }
            composable("test_screen") { TestScreen(navController) }
            }
        }
}
