package com.example.ancient_pokedex.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.model.Result

class PokemonPagingSource(private val service: PokemonService) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
       return try {
           val currentPage = params.key ?: 1
           val response = service.getNextPage(currentPage)
           val data = response.body()?.results ?: emptyList()
           val responseData = mutableListOf<Result>()
           responseData.addAll(data)

           LoadResult.Page(
               data = responseData,
               prevKey = if(currentPage == 1) null else 1,
               nextKey = currentPage.plus(1)
           )
       } catch (e: Exception) {
           LoadResult.Error(e)
       }

    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

}