package com.leveloper.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class SamplePagingSource @Inject constructor() : PagingSource<Int, String>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        return try {
            val next = params.key ?: 0
            val response = getPagingData(next)

            LoadResult.Page(
                data = response.data,
                prevKey = if (next == 0) null else next - 1,
                nextKey = next + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun getPagingData(page: Int): PagingSample {
        val result = mutableListOf<String>()

        val start = page * 10
        for (i in start until start + 10) {
            result.add("$i item")
        }

        return PagingSample(
            data = result,
            page = page + 1
        )
    }
}