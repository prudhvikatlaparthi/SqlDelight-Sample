package com.pru.localcache.android.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pru.localcache.db.repository.DataBaseSDK
import com.pru.localcache.models.TodoItem

@Composable
fun AddScreen(
    dataBaseSDK: DataBaseSDK,
    navController: NavController,
    todoItem: TodoItem? = null
) {
    var title by remember {
        mutableStateOf(todoItem?.title ?: "")
    }
    var description by remember {
        mutableStateOf(todoItem?.description ?: "")
    }
    var status by remember {
        mutableStateOf(todoItem?.status ?: "")
    }
    var testColumn by remember {
        mutableStateOf(todoItem?.testColumn ?: "")
    }
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(value = title, onValueChange = {
            title = it
        }, label = {
            Text("Enter title")
        })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = description, onValueChange = {
            description = it
        }, label = {
            Text("Enter description")
        })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = status, onValueChange = {
            status = it
        }, label = {
            Text("Enter status")
        })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = testColumn, onValueChange = {
            testColumn = it
        }, label = {
            Text("Enter testColumn")
        })
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (title.isBlank()) {
                Toast.makeText(context, "Please Enter title", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val newtonItem = TodoItem(
                id = todoItem?.id,
                title = title,
                description = description,
                status = status,
                testColumn = testColumn
            )
            dataBaseSDK.getTodoDAO().insertTodo(todos = newtonItem)
            navController.popBackStack()
        }) {
            Text("Save")
        }
    }
}