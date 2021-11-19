package com.pru.localcache.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pru.localcache.Greeting
import com.pru.localcache.db.repository.DataBaseSDK
import com.pru.localcache.android.components.AddScreen
import com.pru.localcache.android.components.TodoListScreen
import com.pru.localcache.android.databinding.ActivityMainBinding
import com.pru.localcache.db.DatabaseDriverFactory
import com.pru.localcache.models.TodoItem

fun greet(): String {
    return Greeting().greeting()
}

@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataBaseSDK = DataBaseSDK(DatabaseDriverFactory(this))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.composeView.setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "listScreen") {
                    composable("listScreen") {
                        TodoListScreen(dataBaseSDK = dataBaseSDK, navController = navController)
                    }
                    composable(
                        "AddScreen/{todo_item}",
                        arguments = listOf(navArgument("todo_item") {
                            type = NavType.LongType
                        })
                    ) {
                        val id: Long = it.arguments?.getLong("todo_item") ?: Long.MIN_VALUE
                        var todoItem: TodoItem? = null
                        if (id != Long.MIN_VALUE) {
                            try {
                                todoItem = dataBaseSDK.getTodoDAO().getTodo(id)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        AddScreen(
                            dataBaseSDK = dataBaseSDK,
                            navController = navController,
                            todoItem = todoItem
                        )
                    }
                }
            }
        }
    }
}
