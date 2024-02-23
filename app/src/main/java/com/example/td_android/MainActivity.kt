package com.example.td_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
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
import com.example.td_android.appBar.BottomBar
import com.example.td_android.appBar.TopBar
import com.example.td_android.checkbox.CustomCheckbox
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


data class ApiResponse(
    val data: List<TaskData>,
)

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
                topBar = { TopBar() },
                bottomBar = { BottomBar(navController) },
                content = {
                    NavHost(navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(navController)
                        }
                        composable("addTask") {
                            AddTaskScreen(AddTaskModel())
                        }
                    }
                }
            )
        }
    }
}
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
                println("$TAG: onResponse: $tasks")
                tasksState = tasks
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            println("$TAG: onFailure: ${t.message}")
        }
    })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (tasksState.isNullOrEmpty()) {
            // Afficher le contenu lorsque la liste de tâches est vide
        } else {
            tasksState?.forEach { task ->
                Text(
                    text = "My Tasks \uD83D\uDC4B",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = task.attributes.title,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = task.attributes.description,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = task.attributes.deadline,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                        CustomCheckbox()
                        // Bouton Edit
                        IconButton(
                            onClick = { /* Action lorsque le bouton Edit est cliqué */ }
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit icon")
                        }
                        // Bouton Delete
                        IconButton(
                            onClick = { /* Action lorsque le bouton Delete est cliqué */ }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete icon")
                        }
                    }
                }
            }
        }

        // FloatingActionButton pour ajouter une nouvelle tâche
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