package com.example.ancient_pokedex.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.model.Result
import java.net.URI

class PokemonPagingSource(private val service: PokemonService) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
       return try {
           val currentPage = params.key ?: 0
           val response = service.getNextPage(currentPage)
           val data = response.body()?.results ?: emptyList()
           val responseData = mutableListOf<Result>()
           val nextPage = Uri.parse(response.body()?.next).getQueryParameter("offset")?.toInt()
           responseData.addAll(data)

           LoadResult.Page(
               data = responseData,
               prevKey = if(currentPage == 1) null else 1,
               nextKey = nextPage
           )
       } catch (e: Exception) {
           LoadResult.Error(e)
       }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

}