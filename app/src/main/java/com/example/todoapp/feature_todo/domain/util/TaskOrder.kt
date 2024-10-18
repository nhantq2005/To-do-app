package com.example.todoapp.feature_todo.domain.util

sealed class TaskOrder(val orderType:OrderType) {
    class Title(orderType: OrderType):TaskOrder(orderType)

    class Date(orderType: OrderType):TaskOrder(orderType)
}