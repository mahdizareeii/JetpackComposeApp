package com.jetpackcompose.domain.model

enum class FoodCategory(val foodName: String) {
    CHICKEN("Chicken"),
    BEEF("Beef"),
    PIZZA("Pizza"),
    PASTA("Pasta"),
    SOUP("Soup"),
    VEGETARIAN("Vegetarian"),
    KEBAB("Kebab"),
    UN_KNOW("UnKnow");

    companion object {
        fun getAllFood(): List<FoodCategory> {
            return values().toList().filter { it != UN_KNOW }
        }

        fun getFoodCategory(value: String): FoodCategory {
            return values().firstOrNull { it.foodName == value } ?: UN_KNOW
        }
    }
}