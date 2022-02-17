package com.jetpackcompose.domain.usecase

import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.core.model.NetworkStatus
import com.jetpackcompose.domain.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.repository.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPopularRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    private val categories = listOf(
        "pizza",
        "soup"
    )

    suspend operator fun invoke(): NetworkDataState<List<Recipe>> {
        val popularItems = ArrayList<Recipe>()

        return withContext(Dispatchers.Default) {

            for (item in categories) {
                val job = async {
                    sendRequest(request = item)
                }

                popularItems.addAll(
                    job.await()
                )
            }

            if (popularItems.isNotEmpty())
                NetworkDataState.Success(
                    popularItems
                )
            else
                NetworkDataState.Error(
                    NetworkStatus.NetworkError
                )
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