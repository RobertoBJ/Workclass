package com.example.trabajoclases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trabajoclases.ui.theme.TrabajoclasesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            //creacion de temas
            TrabajoclasesTheme {
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
                    }
                }
            }
        }
    }
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
}
