package com.example.leftoverflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.leftoverflow.ui.SearchActivity
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavHost()
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen() }
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    // waiting 3s, then home screen
    LaunchedEffect(Unit) {
        delay(3000) // 3 seconds
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    // White screen with app logo (I designed it myself :3 )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.leftoverflow2),
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp)
            )
        }
    }
}

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.leftoverflow3),
            contentDescription = "Logo",
            modifier = Modifier
                .size(192.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Welcome
        Text(
            text = "Welcome!",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "What are we cooking?",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Search bar

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.LightGray, RoundedCornerShape(50))
                .clickable {
                    val intent = Intent(context, SearchActivity::class.java)
                    context.startActivity(intent)
                }
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text("search, eggs, cake…", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Favourite Recipes
        Text("Favourite recipes", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(5) { index ->
                Card(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(150.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("Recipe $index")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Explore
        Text("Explore", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(5) { index ->
                Card(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(150.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("Explore $index")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}