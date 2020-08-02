package com.ralph.gabb.projectpos.ui.setup

import androidx.lifecycle.ViewModel
import com.ralph.gabb.projectpos.data.body.TokenBody
import com.ralph.gabb.projectpos.data.repository.item.ItemRepository


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

class SetupViewModel(private val itemRepository: ItemRepository): ViewModel() {

    suspend fun fetchCategories(tokenBody: TokenBody) = itemRepository.fetchCategories(tokenBody)

    suspend fun fetchProducts(tokenBody: TokenBody) = itemRepository.fetchProducts(tokenBody)

}