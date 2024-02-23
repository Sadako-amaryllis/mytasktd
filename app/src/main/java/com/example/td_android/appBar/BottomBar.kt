package com.example.td_android.appBar

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BottomBar(navController: NavHostController) {
    Surface(
        color = Color.Magenta,
    ) {
        BottomAppBar(
            modifier = Modifier.height(56.dp), // Hauteur du BottomAppBar
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Color.Black
                        )
                    },
                    label = {
                        Text(
                            text = "Home",
                            color = Color.Black
                        )
                    },
                    selected = false,
                    onClick = { /* Action lorsque l'élément est cliqué */ }
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Planning",
                            tint = Color.Black
                        )
                    },
                    label = {
                        Text(
                            text = "Planning",
                            color = Color.Black
                        )
                    },
                    selected = false,
                    onClick = { /* Action lorsque l'élément est cliqué */ }
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Mail",
                            tint = Color.Black
                        )
                    },
                    label = {
                        Text(
                            text = "Mail",
                            color = Color.Black
                        )
                    },
                    selected = false,
                    onClick = { /* Action lorsque l'élément est cliqué */ }
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.Black
                        )
                    },
                    label = {
                        Text(
                            text = "Settings",
                            color = Color.Black
                        )
                    },
                    selected = false,
                    onClick = { /* Action lorsque l'élément est cliqué */ }
                )
            }
        }
    }
}
