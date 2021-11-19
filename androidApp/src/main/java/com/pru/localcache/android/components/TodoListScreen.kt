package com.pru.localcache.android.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pru.localcache.CRM_CustomerVehicles
import com.pru.localcache.QR_Codes
import com.pru.localcache.db.repository.DataBaseSDK
import com.pru.localcache.models.TodoItem
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun TodoListScreen(dataBaseSDK: DataBaseSDK, navController: NavController) {
    val todoList = remember {
        mutableStateListOf<TodoItem>()
    }
    LaunchedEffect(key1 = true) {
        dataBaseSDK.getTodoDAO().getTodosASFlow().collectLatest {
            todoList.addAll(it)
        }
        dataBaseSDK.getCustomerVehiclesDAO().insertCrmCustomerVehicles(CRM_CustomerVehicles(
            CustomerVehicleID = "111",
            ID = 0,
            AccountID = "",
            WebCustomerVehicleID = null,
            VehicleNo = "",
            VehicleOwnerTypeCode = "",
            Company = null,
            Active = null,
            ModifiedFrom = "",
            CreatedBy = "",
            CreatedDate = "",
            ModifiedBy = "",
            ModifiedDate = "",
            OrgID = 0,
            VehicleTypeCode = ""
        ))
        dataBaseSDK.getQRDAO().insertQRCODES(
            listOf(
                QR_Codes(
                    QRNo = "1",
                    QRImage = null,
                    QRImageName = "22",
                    ModifiedBy = "",
                    ModifiedDate = "",
                    OrgID = 0
                ),
                QR_Codes(
                    QRNo = "2",
                    QRImage = null,
                    QRImageName = "111",
                    ModifiedBy = "",
                    ModifiedDate = "",
                    OrgID = 0
                ),
            )
        )
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate("AddScreen/${Long.MIN_VALUE}")
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }) {
        LazyColumn {
            items(todoList.size) { index ->
                UserListItem(todoItem = todoList[index]) {
                    navController.navigate("AddScreen/${it.id}")
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun UserListItem(todoItem: TodoItem, click: (TodoItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        onClick = {
            click.invoke(todoItem)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = todoItem.id.toString(), fontSize = 16.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = todoItem.title ?: "", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = todoItem.description ?: "", fontSize = 14.sp)
        }
    }
}