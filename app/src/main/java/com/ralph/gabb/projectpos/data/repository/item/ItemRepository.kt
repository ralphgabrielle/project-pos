package com.ralph.gabb.projectpos.data.repository.item

import androidx.lifecycle.LiveData
import com.ralph.gabb.projectpos.data.body.TokenBody
import com.ralph.gabb.projectpos.data.response.CategoryResponse
import com.ralph.gabb.projectpos.data.response.ProductResponse
import com.ralph.gabb.projectpos.http.Wrapper


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

interface ItemRepository {

    suspend fun fetchCategories(tokenBody: TokenBody): LiveData<out Wrapper<CategoryResponse>>

    suspend fun fetchProducts(tokenBody: TokenBody): LiveData<out Wrapper<ProductResponse>>

}