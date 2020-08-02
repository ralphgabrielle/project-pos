package com.ralph.gabb.projectpos.data.data_source.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ralph.gabb.projectpos.data.body.TokenBody
import com.ralph.gabb.projectpos.data.response.CategoryResponse
import com.ralph.gabb.projectpos.data.response.ProductResponse
import com.ralph.gabb.projectpos.http.AppNetworkService
import com.ralph.gabb.projectpos.http.Wrapper


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

class ItemDataSourceImpl(private val networkService: AppNetworkService): ItemDataSource {

    override suspend fun fetchCategories(tokenBody: TokenBody): LiveData<out Wrapper<CategoryResponse>> {
        val value = MutableLiveData<Wrapper<CategoryResponse>>()
        val suspendCall = networkService.fetchCategories(tokenBody)
        value.postValue(suspendCall)
        return value
    }

    override suspend fun fetchProducts(tokenBody: TokenBody): LiveData<out Wrapper<ProductResponse>> {
        val value = MutableLiveData<Wrapper<ProductResponse>>()
        val suspendCall = networkService.fetchProducts(tokenBody)
        value.postValue(suspendCall)
        return value
    }
}