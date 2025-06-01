package com.example.workclass.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.workclass.data.model.AccountEntity
import com.example.workclass.data.model.dao.AccountDao
import com.example.workclass.data.model.database.AppDatabase
import com.example.workclass.data.model.database.DatabaseProvider
import com.example.workclass.ui.components.FavoriteAccountCard
import com.example.workclass.ui.components.TopBarComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun FavoriteAccountScreen(navController: NavController) {

    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    val accountDao = db.accountDao()

    // Observa la lista reactiva de cuentas
    val accountsdb by accountDao.getAll().collectAsState(initial = emptyList())

    // Función para insertar nueva cuenta
    fun insertAccount(account: AccountEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                accountDao.insert(account)
            } catch (e: Exception) {
                Log.e("FavoriteAccountScreen", "Insert error: $e")
            }
        }
    }

    Column {
        TopBarComponent(
            "Favorite Accounts",
            navController,
            "favorite_accounts_screen"
        )

        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(accountsdb) { accountdb ->
                MaterialTheme(
                    colorScheme = MaterialTheme.colorScheme.copy(
                        surfaceVariant = Color(0xFF73C2D7),
                        onSurfaceVariant = Color.Black
                    )
                ) {
                    FavoriteAccountCard(
                        accountdb.id ?: 0,
                        accountdb.name ?: "",
                        accountdb.username ?: "",
                        accountdb.password ?: "",
                        accountdb.description ?: "",
                        accountdb.imageURL ?: "",
                        onDeleteClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    accountDao.delete(accountdb)
                                } catch (exception: Exception) {
                                    Log.d("debug-db", "Error: $exception")
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}