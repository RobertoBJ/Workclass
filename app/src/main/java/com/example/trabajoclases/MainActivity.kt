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
/*
                Column {
                   Column() {
                       TextComposable("Roberto")
                       TextComposable()
                       TextComposable()
                       TextComposable()
                   }
                    Row() {
                        TextComposable()
                        TextComposable()
                        TextComposable()
                        TextComposable()
                    }
                    Column {
                        ModiferExamle2()
                        ModiferExamle4()
                        CustomText()
                    }
                }
                */

            }
        }
    }
    /*
    @Preview(showBackground = true)
    @Composable
    fun TextComposable(name:String = "Empty"){
        Text("Welcome")
        Text(name)
    }
    @Preview(showBackground = true)
    @Composable
    fun ModiferExamle1(){
        Column(modifier = Modifier.padding(50.dp, 30.dp, 20.dp, 10.dp)) {
            Text("Hello World")
        }

    }
    @Preview(showBackground = true)
    @Composable
    fun ModiferExamle2(){
        //
        Column(modifier = Modifier.padding(24.dp).fillMaxWidth().clickable(onClick = { clickAction() })) {
            Text("Hello World")
        }
    }
    fun clickAction(){
        println("Column Clicked")
    }
    @Preview(showBackground = true)
    @Composable
    fun ModiferExamle3(){
        Column(modifier = Modifier.fillMaxHeight().padding(16.dp).background(Color.Cyan)
            .border(width = 2.dp, color = Color.Black).width(200.dp), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {
            TextComposable("1")
            TextComposable("2")
            TextComposable("3")
            TextComposable("4")
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun ModiferExamle4() {
        Box(modifier = Modifier. padding(10.dp). height(300.dp). width(300.dp)){
            Text("1", Modifier.align(Alignment.TopStart))
            Text("1", Modifier.align(Alignment.TopCenter))
            Text("1", Modifier.align(Alignment.TopEnd))
            Text("1", Modifier.align(Alignment.BottomStart))
            Text("1", Modifier.align(Alignment.BottomCenter))
            Text("1", Modifier.align(Alignment.BottomEnd))

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CustomText () {
        Column() {
            Text(stringResource(R.string.example_text),color = colorResource(R.color.purple_500),
                fontSize = 20.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.ExtraBold
            )
            val gradientColors = listOf(Color.Cyan, Color.Blue, Color.Magenta)
            Text(stringResource(R.string.example_text), style = TextStyle(brush = Brush.linearGradient(colors = gradientColors )))
        }
    }

     */
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
