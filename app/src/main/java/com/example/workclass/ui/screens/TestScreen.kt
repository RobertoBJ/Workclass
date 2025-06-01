package com.example.workclass.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.example.workclass.R

@Composable
fun TestScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column {
            TextComposable("Roberto")
            TextComposable()
            TextComposable()
            TextComposable()
        }
        Row {
            TextComposable()
            TextComposable()
            TextComposable()
            TextComposable()
        }
        Column {
            ModifierExample1()
            ModifierExample2()
            ModifierExample3()
            ModifierExample4()
            CustomText()
        }
    }
}

@Composable
fun TextComposable(name: String = "Empty") {
    Column {
        Text("Welcome")
        Text(name)
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample1() {
    Column(modifier = Modifier.padding(50.dp, 30.dp, 20.dp, 10.dp)) {
        Text("Hello World")
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample2() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clickable(onClick = { clickAction() })
    ) {
        Text("Hello World")
    }
}

fun clickAction() {
    Log.d("Click", "Column Clicked")
}

@Preview(showBackground = true)
@Composable
fun ModifierExample3() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .background(Color.Cyan)
            .border(width = 2.dp, color = Color.Black)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TextComposable("1")
        TextComposable("2")
        TextComposable("3")
        TextComposable("4")
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample4() {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .height(300.dp)
            .width(300.dp)
            .background(Color.Cyan)
    ) {
        Text("1", Modifier.align(Alignment.TopStart))
        Text("2", Modifier.align(Alignment.TopCenter))
        Text("3", Modifier.align(Alignment.TopEnd))
        Text("4", Modifier.align(Alignment.BottomStart))
        Text("5", Modifier.align(Alignment.BottomCenter))
        Text("6", Modifier.align(Alignment.BottomEnd))
    }
}

@Preview(showBackground = true)
@Composable
fun CustomText() {
    Column {
        Text(
            text = stringResource(id = R.string.example_text),
            color = colorResource(id = R.color.purple_500),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradientColors = listOf(Color.Cyan, Color.Blue, Color.Magenta)
        Text(
            text = stringResource(id = R.string.example_text),
            style = TextStyle(brush = Brush.linearGradient(colors = gradientColors))
        )
    }
}

