package com.jetpackcompose.domain.usecase

import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.core.model.NetworkStatus
import com.jetpackcompose.core.model.UiDataState
import com.jetpackcompose.domain.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.model.PopularScreenUIState
import com.jetpackcompose.repository.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPopularRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    private val popularItems: ArrayList<PopularScreenUIState> = arrayListOf()

    suspend operator fun invoke(): UiDataState<List<PopularScreenUIState>> {
        return withContext(Dispatchers.Default) {

            val pizzaRequestJob = async {
                sendRequest(request = "Pizza", page = 2)
            }

            val pastaRequestJob = async {
                sendRequest(request = "Pasta", page = 3)
            }

            val soupRequestJob = async {
                sendRequest(request = "Soup")
            }

            val dessertRequestJob = async {
                sendRequest(request = "Cake")
            }

            val pizzaResult = pizzaRequestJob.await()
            val pastaResult = pastaRequestJob.await()
            val soupResult = soupRequestJob.await()
            val dessertResult = dessertRequestJob.await()

            if (pizzaResult == null || pastaResult == null || soupResult == null || dessertResult == null) {
                UiDataState.Error(NetworkStatus.NetworkError)
            } else {
                popularItems.add(PopularScreenUIState.MostSells(pizzaResult))
                popularItems.add(PopularScreenUIState.MostSells(pastaResult))
                popularItems.add(PopularScreenUIState.DessertProducts(dessertResult))
                popularItems.add(PopularScreenUIState.CheapProducts(soupResult))

                UiDataState.Success(popularItems)
            }
        }
    }

    private suspend fun sendRequest(page: Int = 1, request: String) =
        when (val result = repository.search(page, request)) {
            is NetworkDataState.Success -> result.data.results?.map {
                mapper.mapToDomainModel(it)
            } ?: listOf()
            is NetworkDataState.Error -> null
        }
}