package com.example.workclass

import AppScreen
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workclass.data.model.database.AppDatabase
import com.example.workclass.data.model.database.DatabaseProvider
import com.example.workclass.ui.screens.AccountScreen
import com.example.workclass.ui.screens.BiometricScreen
import com.example.workclass.ui.screens.ComponentsScreen
import com.example.workclass.ui.screens.FavoriteAccountScreen
import com.example.workclass.ui.screens.HomeScreen
import com.example.workclass.ui.screens.LoginScreen
import com.example.workclass.ui.screens.MainMenuScreen
import com.example.workclass.ui.screens.ManageAccountScreen
import com.example.workclass.ui.screens.ReporteFotoApp
import com.example.workclass.ui.screens.TestScreen
import com.example.workclass.ui.screens.NotificationScreen
import com.example.workclass.ui.theme.WorkClassTheme
import androidx.fragment.app.FragmentActivity
import android.os.Build
import com.example.workclass.ui.screens.NotificationScreenPreview
import com.example.workclass.ui.screens.XboxScreen



class MainActivity : FragmentActivity() {
    lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) !=
                android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1001)
            }
        }
        super.onCreate(savedInstanceState)
        try {
            database = DatabaseProvider.getDatabase(this)
            Log.d("debug-db", "Database loaded successfully")
        } catch (exception: Exception) {
            Log.d("debug-db", "Error: $exception")
        }
        enableEdgeToEdge()
        setContent {
            WorkClassTheme {
                ComposeMultiScreenApp()
            }
        }
    }
}

@Composable
fun ComposeMultiScreenApp() {
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_menu") {
        composable("main_menu") { MainMenuScreen(navController) }
        composable("home_screen") { HomeScreen(navController) }
        composable("test_screen") { TestScreen(navController) }
        composable("xbox_screen") { XboxScreen(navController) }
        composable("components_screen") { ComponentsScreen(navController) }
        composable("login_screen") { LoginScreen(navController) }
        composable("account_screen") { AccountScreen(navController) }
        composable("camara_screen") { ReporteFotoApp(navController) }
        composable("agenda_screen") { AppScreen() }
        composable("manage_account_screen?id={id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            ManageAccountScreen(navController = navController, id = id)
        }
        composable("favorite_accounts_screen") { FavoriteAccountScreen(navController) }
        composable("notification_screen") { NotificationScreenPreview(navController) }
        composable("biometric_screen") {
            val context = LocalContext.current
            BiometricScreen(navController, onAuthSuccess = {
                Toast.makeText(context, "¡Autenticación exitosa!", Toast.LENGTH_SHORT).show()
            })
        }
    }
}

