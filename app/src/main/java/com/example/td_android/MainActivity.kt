package com.example.td_android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.example.td_android.ui.TaskForm
import androidx.compose.material3.*
import androidx.compose.runtime.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import com.example.td_android.TaskData


data class ApiResponse(
    val data: List<TaskData>,
)

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
                    AddTaskScreen(AddTaskModel())
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    
    val api = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TaskerAPI::class.java)

    var tasksState by remember { mutableStateOf<List<TaskData>?>(null) }

    api.getTasks().enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val tasks = response.body()?.data // Access the data field
                tasksState = tasks
                for (task in tasks!!) {
                    println("$TAG: onSuccess: ${task.attributes}")
                }
            } else {
                println("$TAG: onERROR: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            println("$TAG: onFailure: ${t.message}")
        }
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (tasksState.isNullOrEmpty()) {
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
        } else {
            tasksState?.forEach { task ->
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
//                    onClick = {
//                        navController.navigate("task/${task.id}")
//                    }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Titre: ${task.attributes.title}")
                        Text(text = "Description: ${task.attributes.description}")
                        Text(text = "Deadline: ${task.attributes.deadline}")
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate("addTask") },
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
    addTaskModel: AddTaskModel
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        TaskForm(addTaskModel = addTaskModel)
    }
}