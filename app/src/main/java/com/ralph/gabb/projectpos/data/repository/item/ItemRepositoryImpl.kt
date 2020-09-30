package com.ralph.gabb.projectpos.data.repository.item

import androidx.lifecycle.LiveData
import com.ralph.gabb.projectpos.data.body.TokenBody
import com.ralph.gabb.projectpos.data.data_source.item.ItemDataSource
import com.ralph.gabb.projectpos.data.response.CategoryResponse
import com.ralph.gabb.projectpos.data.response.ProductResponse
import com.ralph.gabb.projectpos.http.Wrapper


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

class ItemRepositoryImpl(private val itemDataSource: ItemDataSource): ItemRepository {

    override suspend fun fetchCategories(tokenBody: TokenBody): LiveData<out Wrapper<CategoryResponse>> {
        return kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            return@withContext itemDataSource.fetchCategories(tokenBody)
        }
    }

    override suspend fun fetchProducts(tokenBody: TokenBody): LiveData<out Wrapper<ProductResponse>> {
        return kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            return@withContext itemDataSource.fetchProducts(tokenBody)
        }
    }
}