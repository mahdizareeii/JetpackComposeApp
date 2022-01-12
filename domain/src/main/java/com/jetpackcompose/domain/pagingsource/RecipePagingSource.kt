package com.jetpackcompose.domain.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import retrofit2.HttpException
import java.io.IOException

class RecipePagingSource(
    private val searchRecipesUseCase: SearchRecipesUseCase,
    private val query: String
) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return /*if (invalid) 1 else state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }*/ 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            val position = params.key ?: 1
            return when (val result = searchRecipesUseCase.invoke(position, query)) {
                is NetworkDataState.Success -> {
                    LoadResult.Page(
                        data = result.data,
                        nextKey = if (result.data.isNullOrEmpty()) null else position.inc(),
                        prevKey = if (position == 1) null else position.dec()
                    )
                }
                is NetworkDataState.Error -> {
                    LoadResult.Error(Throwable(result.networkStatus.message))
                }
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}