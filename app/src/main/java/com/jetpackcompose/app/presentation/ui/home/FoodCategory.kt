package com.jetpackcompose.app.presentation.ui.home

enum class FoodCategory(val foodName: String) {
    CHICKEN("Chicken"),
    BEEF("Beef"),
    PIZZA("Pizza"),
    PASTA("Pasta"),
    SOUP("Soup"),
    VEGETARIAN("Vegetarian"),
    KEBAB("Kebab");

    companion object {
        fun getAllFood(): List<FoodCategory> {
            return values().toList()
        }

        fun getFoodCategory(value: String): FoodCategory? {
            return values().firstOrNull { it.foodName == value }
        }
    }
}