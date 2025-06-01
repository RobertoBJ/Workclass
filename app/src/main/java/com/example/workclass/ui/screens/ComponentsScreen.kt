package com.example.workclass.ui.screens

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.workclass.R
import com.example.workclass.data.model.MenuModel
import com.example.workclass.data.model.PostCardModel
import com.example.workclass.ui.components.PostCardComponent
import kotlinx.coroutines.launch
import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import java.util.Calendar
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

import androidx.compose.material3.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun ComponentsScreen(navController: NavHostController) {
    val menuOptions = arrayOf(
        MenuModel(1, "Buttons", "first", Icons.Filled.AccountBox),
        MenuModel(2, "Floating Buttons", "second", Icons.Filled.Call),
        MenuModel(3, "Progress", "third", Icons.Filled.Star),
        MenuModel(4, "Chips", "fourth", Icons.Filled.Star),
        MenuModel(5, "Sliders", "fifth", Icons.Filled.DateRange),
        MenuModel(6, "Switches", "sixth", Icons.Filled.PlayArrow),
        MenuModel(7, "Badges", "seventh", Icons.Filled.Warning),
        MenuModel(8, "Snack Bar", "eighth", Icons.Filled.MailOutline),
        MenuModel(9, "Alert Dialogs", "ninth", Icons.Filled.ThumbUp),
        MenuModel(10, "Bars", "then", Icons.Filled.ThumbUp),
        MenuModel(11, "Input Fields", "inputfields", Icons.Filled.Person),
        MenuModel(12, "Date Pickers", "datepicker", Icons.Filled.DateRange),
        MenuModel(13, "Pull to Refresh", "pulltorefresh", Icons.Filled.Refresh),
        MenuModel(14, "Bottom Sheets", "bottomsheet", Icons.Filled.Star),
        MenuModel(15, "Segmented Buttons", "segmented", Icons.Filled.Star)
    )

    var option by remember { mutableStateOf("first") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                LazyColumn {
                    items(menuOptions) { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = "") },
                            label = { Text(item.title) },
                            selected = false,
                            onClick = {
                                option = item.option
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        Column {
            when (option) {
                "first" -> Buttons()
                "second" -> FloatingButtons()
                "third" -> Progress()
                "fourth" -> Chips()
                "fifth" -> Sliders()
                "sixth" -> Switches()
                "seventh" -> Badges()
                "eighth" -> SnackBars()
                "ninth" -> AlertDialogs()
                "then" -> Bars()
                "inputfields" -> InputFields()
                "datepicker" -> DatePickers()
                "pulltorefresh" -> PullToRefreshScreen()
                "bottomsheet" -> BottomSheets()
                "segmented" -> SegmentedButtons()
                else -> { }
            }
        }
    }
}

@Composable
fun Buttons() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = {}) {
            Text("Filled Button")
        }
        FilledTonalButton(onClick = {}) {
            Text("Tonal Button")
        }
        OutlinedButton(onClick = {}) {
            Text("Outlined Button")
        }
        ElevatedButton(onClick = {}) {
            Text("Elevated Button")
        }
        TextButton(onClick = {}) {
            Text("Text Button")
        }
    }
}

@Composable
fun FloatingButtons() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
        SmallFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
        LargeFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
        ExtendedFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
            Text("Extended Button")
        }
    }
}

@Composable
fun Progress() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        CircularProgressIndicator(modifier = Modifier.width(64.dp))
    }
}

@Composable
fun Chips() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AssistChip(
            onClick = { },
            label = { Text("Assist Chip") },
            leadingIcon = {
                Icon(
                    Icons.Filled.AccountBox,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text("Filter Chip") },
            leadingIcon = if (selected) {
                {
                    Icon(
                        Icons.Filled.AccountBox,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            } else null
        )
        InputChipexample("Dismiss", {})
    }
}

@Composable
fun InputChipexample(
    text: String,
    onDismiss: () -> Unit,
) {
    var enabled by remember { mutableStateOf(true) }
    if (!enabled) return

    InputChip(
        label = { Text(text) },
        selected = enabled,
        onClick = {
            onDismiss()
            enabled = !enabled
        },
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Icon Person",
                modifier = Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Icon Close",
                modifier = Modifier.size(InputChipDefaults.AvatarSize)
            )
        }
    )
}

@Composable
fun Sliders() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var sliderPosition by remember { mutableFloatStateOf(50f) }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            steps = 10,
            valueRange = 0f..100f
        )
        Text(
            text = sliderPosition.toString(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Switches() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var checked by remember { mutableStateOf(true) }
        Switch(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        var checked2 by remember { mutableStateOf(true) }
        Switch(
            checked = checked2,
            onCheckedChange = { checked2 = it },
            thumbContent = if (checked2) {
                {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Switch Icon",
                        modifier = Modifier.size(InputChipDefaults.AvatarSize)
                    )
                }
            } else null
        )
        var checked3 by remember { mutableStateOf(true) }
        Checkbox(
            checked = checked3,
            onCheckedChange = { checked3 = it }
        )
    }
}

@Composable
fun Badges() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var itemCount by remember { mutableIntStateOf(0) }
        BadgedBox(
            badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text(itemCount.toString())
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Shopping cart Icon"
            )
        }
        Button(onClick = { itemCount++ }) {
            Text("Add Item")
        }
    }
}

@Composable
fun SnackBars() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val snackState = remember { SnackbarHostState() }
        val snackScope = rememberCoroutineScope()

        SnackbarHost(hostState = snackState)

        fun launchSnackBar() {
            snackScope.launch {
                snackState.showSnackbar("The message has been sent")
            }
        }

        Button(onClick = { launchSnackBar() }) {
            Text("Send Message")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogs() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var showAlertDialog by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }

        if (showAlertDialog) {
            AlertDialog(
                icon = { Icon(Icons.Filled.Warning, contentDescription = "Warning button") },
                title = { Text("Confirm Delete") },
                text = { Text("Are you sure you want to delete this?") },
                onDismissRequest = {},
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Confirmed"
                            showAlertDialog = false
                        }
                    ) {
                        Text("YES")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Canceled"
                            showAlertDialog = false
                        }
                    ) {
                        Text("NO")
                    }
                }
            )
        }
        Button(onClick = { showAlertDialog = true }) {
            Text("Delete File")
        }
        Text(selectedOption)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Bars() {
    Column(modifier = Modifier.fillMaxSize()) {
        MediumTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.secondary 
            ),
            title = { Text("Screen title") },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Button", tint = Color.White)

                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "Setting Button", tint = Color.White)
                }
            }
        )
        val arrayPost = arrayOf(
            PostCardModel(1, "Title 1", "Text 1", R.drawable.image),
            PostCardModel(2, "Title 2", "Text 2", R.drawable.image),
            PostCardModel(3, "Title 3", "Text 3", R.drawable.image),
            PostCardModel(1, "Title 4", "Text 4", R.drawable.image),
            PostCardModel(2, "Title 5", "Text 5", R.drawable.image),
            PostCardModel(3, "Title 6", "Text 6", R.drawable.image),
            PostCardModel(1, "Title 7", "Text 7", R.drawable.image),
            PostCardModel(2, "Title 8", "Text 8", R.drawable.image),
            PostCardModel(3, "Title 9", "Text 9", R.drawable.image)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(arrayPost) { item ->
                PostCardComponent(item.id, item.title, item.text, item.image)
            }
        }
        BottomAppBar(
            containerColor = Color.Black,
            contentColor = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Build, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                }
            }
        }
    }
}


@Composable
fun Adaptive() {
    val windowInfo = currentWindowAdaptiveInfo()
    val windowSize = windowInfo.windowSizeClass
    val height = windowSize.windowHeightSizeClass
    val width = windowSize.windowWidthSizeClass

    // Compact width < 600 dp Phone Portrait
    // Medium width >= 600 dp < 840 dp Tablet Portrait
    // Expanded width >= 840 dp Tablet Landscape
    // Compact height < 480 dp Phone Landscape
    // Medium height >= 480 dp < 900 dp Tablet Landscape or Phone Portrait
    // Expanded height >= 900 dp Tablet Portrait

    Column {
        Text("Window Size: $windowSize")
        Text("Height: $height")
        Text("Width: $width")
    }
}
@Composable
fun InputFields() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Ingrese su texto") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {  }) {
            Text("Enviar")
        }
    }
}
@Composable
fun DatePickers() {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("") }


    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        },
        year,
        month,
        day
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (selectedDate.isEmpty()) "Seleccione una fecha" else "Fecha seleccionada: $selectedDate",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { datePickerDialog.show() }) {
            Text("Abrir Date Picker")
        }
    }
}

@Composable
fun PullToRefreshScreen() {
    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)
    val coroutineScope = rememberCoroutineScope()

    val images = listOf(R.drawable.download, R.drawable.portada_nemo, R.drawable.image)
    var imageIndex by remember { mutableStateOf(0) }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                delay(2000)
                imageIndex = (imageIndex + 1) % images.size
                isRefreshing = false
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Image(
                painter = painterResource(id = images[imageIndex]),
                contentDescription = "Imagen ${imageIndex + 1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheets() {
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showSheet = true }) {
            Text("Mostrar Bottom Sheet")
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Este es un Bottom Sheet", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Aquí puedes agregar el contenido que desees.")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // Cierra el bottom sheet
                        showSheet = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cerrar")
                }
            }
        }
    }
}

@Composable
fun SegmentedButtons() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val segments = listOf("Option 1", "Option 2", "Option 3")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        segments.forEachIndexed { index, segment ->
            Button(
                onClick = { selectedIndex = index },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedIndex == index)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surface,
                    contentColor = if (selectedIndex == index)
                        MaterialTheme.colorScheme.onPrimary
                    else
                        MaterialTheme.colorScheme.onSurface
                ),
                shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                    segments.lastIndex -> RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                    else -> RoundedCornerShape(0.dp)
                },
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                modifier = Modifier.weight(1f)
            ) {
                Text(segment)
            }
            if (index < segments.lastIndex) {
                Spacer(modifier = Modifier.width(1.dp))
            }
        }
    }
}