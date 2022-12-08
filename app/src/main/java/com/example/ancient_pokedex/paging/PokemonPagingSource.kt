package com.example.ancient_pokedex.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.models.Result

class PokemonPagingSource(private val service: PokemonService) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
       return try {
           val currentPage = params.key ?: 0
           val response = service.getNextPage(currentPage)
           val data = response.body()?.results ?: emptyList()
           var nextPage = Uri.parse(response.body()?.next).getQueryParameter("offset")?.toInt()
           //Can probably change this when implementing room to grab table row value as the number for the pokemon.
           for(item in data) {
               val dexNum = Uri.parse(item.url).lastPathSegment?.toInt()!!
               if(dexNum < 10000) {
                   item.id = dexNum
               } else {
                   nextPage = null
                   break
               }
           }

           LoadResult.Page(
               data = data,
               prevKey = if(currentPage == 0) null else 1,
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