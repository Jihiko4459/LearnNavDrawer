package com.example.learnnavdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnnavdrawer.ui.theme.GreenJC
import com.example.learnnavdrawer.ui.theme.LearnNavDrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnNavDrawerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LearnNavDrawer()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnNavDrawer(){
    val navigationController= rememberNavController()
    val coroutineScope= rememberCoroutineScope()
    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
    val context= LocalContext.current.applicationContext


    ModalNavigationDrawer(
        drawerState=drawerState,
        gesturesEnabled = true,
        drawerContent = { 
            ModalDrawerSheet {
                Box(modifier = Modifier
                    .background(GreenJC)
                    .fillMaxWidth()
                    .height(150.dp)){
                    Text(text = "")
                }
                Divider()
                NavigationDrawerItem(label = {Text(text="Home", color = GreenJC)},
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "home", tint= GreenJC)},
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Home.screen){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = {Text(text="Profile", color = GreenJC)},
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "profile", tint= GreenJC)},
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Profile.screen){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = {Text(text="Settings", color = GreenJC)},
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "setting", tint= GreenJC)},
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Setting.screen){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = {Text(text="Logout", color = GreenJC)},
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "logout", tint= GreenJC)},
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                    })
            }
        }
    ) {
        Scaffold(
            topBar = {
                val coroutineScope= rememberCoroutineScope()
                TopAppBar(title = { Text(text = "WhatsApp")},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = GreenJC,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Rounded.Menu, contentDescription = "MenuButton"
                            )
                        }
                    })
            }
        ) {
            NavHost(navController = navigationController,
                startDestination = Screens.Home.screen){
                composable(Screens.Home.screen){ Home()}
                composable(Screens.Profile.screen){ Profile() }
                composable(Screens.Setting.screen){ Setting() }
        }

        }

    }
}
