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
                sendRequest(request = "Pizza")
            }

            val beefRequestJob = async {
                sendRequest(request = "Beef")
            }

            val soupRequestJob = async {
                sendRequest(request = "Soup")
            }

            popularItems.add(
                PopularScreenUIState.MostSells(
                    pizzaRequestJob.await()
                )
            )

            popularItems.add(
                PopularScreenUIState.MostSells(
                    beefRequestJob.await()
                )
            )

            popularItems.add(
                PopularScreenUIState.CheapProducts(
                    soupRequestJob.await()
                )
            )

            if (popularItems.isNotEmpty())
                UiDataState.Success(popularItems)
            else
                UiDataState.Error(NetworkStatus.NetworkError)
        }
    }

    private suspend fun sendRequest(page: Int = 1, request: String) =
        when (val result = repository.search(page, request)) {
            is NetworkDataState.Success -> {
                result.data.results?.map {
                    mapper.mapToDomainModel(it)
                } ?: listOf()
            }
            is NetworkDataState.Error -> {
                listOf()
            }
        }
}