package vn.edu.usth.mobile_app.network
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import vn.edu.usth.mobile_app.model.ModelData

class ModelPagingSource:PagingSource<Int, ModelData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ModelData> {
        return try {
            val nextPage = params.key ?: 1
            val response = KtorClient.getModelPage(nextPage)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            Log.d("ModelPagingSource", e.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ModelData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}