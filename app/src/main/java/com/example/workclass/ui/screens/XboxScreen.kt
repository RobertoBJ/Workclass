package com.example.workclass.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workclass.R


@Composable
fun XboxScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        UserHeader()
        Spacer(modifier = Modifier.height(16.dp))
        FeaturedGame()
        Spacer(modifier = Modifier.height(16.dp))
        FriendsSection()
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBarr()
    }
}

@Composable
fun UserHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.goku_ico),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("RBJ",fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text("ULTIMATE", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text("59,653 G  |  2,874", fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* Notifications */ }) {
            Icon(
                painter = painterResource(id = R.drawable.notifications),
                contentDescription = "Notifications",
                tint = Color.White
            )
        }
    }
}


@Composable
fun FeaturedGame() {
    val games = listOf(
        Pair(R.drawable.haloinfinite, "Halo Infinite"),
        Pair(R.drawable.fh5, "Forza Horizon 5"),
        Pair(R.drawable.re4remake, "Resident Evil 4 Remake"),
        Pair(R.drawable.eldenring, "Elden Ring")
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(games) { game ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(350.dp)
                    .height(500.dp)
                    .background(Color.Black)
            ) {
                Column(modifier = Modifier.background(Color.DarkGray)) {
                    Image(
                        painter = painterResource(id = game.first),
                        contentDescription = "Featured Game",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(430.dp)
                    )
                    Text(
                        game.second,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        "Ya disponible",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun FriendsSection() {
    val friends = listOf(
        "Último visto hace 7 h: Inicio",
        "Último visto hace 1 h: Inicio",
        "Último visto hace 10 h: Inicio",
        "Último visto hace 24 h: Inicio",
        "Último visto hace 9 h: Inicio"
    )

    Column(modifier = Modifier.background(Color.Black)) {
        Text("Amigos disponibles", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            friends.forEach { status ->
                FriendItem(status)
            }
        }
    }
}

@Composable
fun FriendItem(status: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .size(140.dp)
            .background(Color.Black)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mf),
                contentDescription = "Friend Avatar",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Text(
                status,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}



@Composable
fun BottomNavigationBarr() {
    NavigationBar(modifier = Modifier.fillMaxWidth().background(Color.Black), containerColor = Color.Black ) {
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.home), contentDescription = "Home", tint = Color.White) },
            label = { Text("Inicio", color = Color.White) },
            selected = false,
            onClick = { /* Home */ }
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.friends), contentDescription = "Friends", tint = Color.White) },
            label = { Text("Amigos", color = Color.White) },
            selected = false,
            onClick = { /* Friends */ }
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.search), contentDescription = "Search", tint = Color.White) },
            label = { Text("Buscar", color = Color.White) },
            selected = false,
            onClick = { /* Search */ }
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.gamepass), contentDescription = "Game Pass", tint = Color.White) },
            label = { Text("GamePass", color = Color.White) },
            selected = false,
            onClick = { /* Game Pass */ }
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.glibrary), contentDescription = "My Library", tint = Color.White) },
            label = { Text("Fotos", color = Color.White) },
            selected = false,
            onClick = { /* My Library */ }
        )
    }
}