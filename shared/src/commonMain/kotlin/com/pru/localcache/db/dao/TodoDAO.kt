package com.pru.localcache.db.dao

import com.pru.localcache.TBL_TODOS
import com.pru.localcache.TodosQueries
import com.pru.localcache.models.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoDAO(private val dbQuery: TodosQueries) {
    @Throws(Exception::class)
    fun insertTodo(todos: TodoItem) {
        dbQuery.insertTodo(
            todos.id,
            todos.title,
            todos.description,
            status = todos.status,
            testColumn = todos.testColumn
        )
    }

    @Throws(Exception::class)
    fun getTodosASFlow(): Flow<List<TodoItem>> = flow {
        emit(dbQuery.getTodos().executeAsList().map {
            it.mapTodoItem()
        })
    }

    @Throws(Exception::class)
    fun getTodos(): List<TodoItem> {
        return dbQuery.getTodos().executeAsList().map {
            it.mapTodoItem()
        }
    }

    @Throws(Exception::class)
    fun getTodo(id: Long): TodoItem {
        return dbQuery.getTodo(id).executeAsOne().mapTodoItem()
    }


    private fun TBL_TODOS.mapTodoItem(): TodoItem {
        return TodoItem(
            id = id, title = title, description = description, status = status,
            testColumn = testColumn
        )
    }

}