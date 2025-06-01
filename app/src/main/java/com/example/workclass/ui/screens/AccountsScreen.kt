package com.example.workclass.ui.screens
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.workclass.data.model.AccountModel
import com.example.workclass.data.model.database.AppDatabase
import com.example.workclass.data.model.database.DatabaseProvider
import com.example.workclass.data.model.toAccountEntity
import com.example.workclass.ui.components.AccountCardComponent
import com.example.workclass.ui.components.AccountDetailCardComponent
import com.example.workclass.ui.components.TopBarComponent
import com.example.workclass.viewmodel.AccountViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavController,
    viewModel: AccountViewModel = viewModel()
) {
    var accounts by remember { mutableStateOf<List<AccountModel>>(emptyList()) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var accountDetail by remember { mutableStateOf<AccountModel?>(null) }
    val context = LocalContext.current


    // Función para cargar cuentas desde API
    fun loadAccounts() {
        viewModel.getAccounts { response ->
            if (response.isSuccessful) {
                accounts = response.body() ?: emptyList()
            } else {
                Log.d("debug", "Failed to load accounts")
            }
        }
    }

    LaunchedEffect(Unit) {
        loadAccounts()
    }

    Column {
        TopBarComponent("Accounts", navController, "accounts_screen")

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(accounts) { account ->
                AccountCardComponent(
                    account.id,
                    account.name,
                    account.username,
                    account.imageURL ?: "",
                    onButtonClick = {
                        viewModel.getAccount(account.id) { response ->
                            if (response.isSuccessful) {
                                accountDetail = response.body()
                                showBottomSheet = true
                            }
                        }
                    }
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }
            ) {
                AccountDetailCardComponent(
                    accountDetail?.id ?: 0,
                    accountDetail?.name ?: "",
                    accountDetail?.username ?: "",
                    accountDetail?.password ?: "",
                    accountDetail?.imageURL ?: "",
                    accountDetail?.description ?: "",
                    navController = navController,
                    onSaveClick = {
                        accountDetail?.let { acc ->
                            try {
                                viewModel.createAccount(acc) { result ->
                                    if (result != null) {
                                        Toast.makeText(context, "Cuenta creada", Toast.LENGTH_SHORT).show()
                                        showBottomSheet = false
                                        loadAccounts()  // Recarga la lista después de crear
                                    } else {
                                        Toast.makeText(context, "Error al crear cuenta", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } catch (e: Exception) {
                                Log.d("debug-db", "Error en createAccount: $e")
                                e.printStackTrace()
                                Toast.makeText(context, "Excepción al crear cuenta", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    onDeleteClick = { id ->
                        viewModel.deleteAccount(id) { result ->
                            if (result != null) {
                                Toast.makeText(context, "Cuenta eliminada", Toast.LENGTH_SHORT).show()
                                showBottomSheet = false

                                // También eliminar de BD local (en coroutine IO)
                                CoroutineScope(Dispatchers.IO).launch {
                                    try {
                                        val db = DatabaseProvider.getDatabase(context)
                                        val accountDao = db.accountDao()

                                        // Aquí necesitarás tener el AccountEntity para eliminarlo localmente,
                                        // puedes obtenerlo desde la BD o convertirlo desde AccountModel si lo tienes.
                                        // Por ejemplo, si tienes la cuenta en 'accountDetail':
                                        accountDetail?.let { account ->
                                            accountDao.delete(account.toAccountEntity())
                                        }
                                    } catch (e: Exception) {
                                        Log.e("DB_ERROR", "Error al eliminar localmente: $e")
                                    }
                                }

                                // Finalmente recargar las cuentas desde API
                                loadAccounts()

                            } else {
                                Toast.makeText(context, "Error al eliminar cuenta", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )
            }
        }
    }
}

