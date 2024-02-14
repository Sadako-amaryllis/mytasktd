package com.example.td_android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.td_android.ui.AddTaskModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(navController)
                }
                composable("addTask") {
                    AddTaskScreen(navController, AddTaskModel())
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.vecteezy_ete_vacances_plage_icone_illustration_ou_3d_ete_icone_21658663),
            contentDescription = null,
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = "No task today!",
            modifier = Modifier.padding(18.dp)
        )
        FloatingActionButton(
            onClick =  { navController.navigate("addTask") },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End)
        ) {
            Text("+")
        }
    }
}

@Composable
fun AddTaskScreen(
    navController: NavController,
    addTaskModel: AddTaskModel
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        TaskForm(addTaskModel = addTaskModel)
    }
}

@Composable
fun TaskForm(
    addTaskModel: AddTaskModel,
    modifier: Modifier = Modifier
) {
    Column(modifier) {

        Button(
            onClick = { addTaskModel.createTask() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Create task")
        }
    }
}

