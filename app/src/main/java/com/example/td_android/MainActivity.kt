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
import com.example.td_android.ui.TaskForm
import com.squareup.moshi.Json
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


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

data class TaskResponse(
    @Json(name = "tasks") val tasks: List<Task>
)

data class Task(
    val id: Int,
    val title: String,
    val description: String
)

interface TaskApiService {
    @Headers("Authorization: YOUR_API_KEY")
    @GET("/api/tasks")
    suspend fun getTasks(): TaskResponse
}

class TaskRepository {
    private val apiService: TaskApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:1337") // Use your actual base URL
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        apiService = retrofit.create(TaskApiService::class.java)
    }

    suspend fun fetchTasks(): List<Task> = withContext(Dispatchers.IO) {
        val taskResponse = apiService.getTasks()
        println("Fetched tasks: ${taskResponse.tasks}") // Print fetched tasks for debugging
        taskResponse.tasks
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
    addTaskModel: AddTaskModel
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        TaskForm(addTaskModel = addTaskModel)
    }
}

