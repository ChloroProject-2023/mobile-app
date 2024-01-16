package vn.edu.usth.mobile_app.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import vn.edu.usth.mobile_app.model.ModelData
import vn.edu.usth.mobile_app.network.ModelPagingSource

class ExploreViewModel: ViewModel() {
    val modelList: Flow<PagingData<ModelData>> = Pager(PagingConfig(pageSize = 10)) {
        ModelPagingSource()
    }.flow.cachedIn(viewModelScope)
}