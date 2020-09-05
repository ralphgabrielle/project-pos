package com.ralph.gabb.projectpos.ui.main.shop.order_item

import androidx.recyclerview.widget.LinearLayoutManager
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseDialogFragment
import com.ralph.gabb.projectpos.data.db.Product
import com.ralph.gabb.projectpos.data.db.ProductVariant
import com.ralph.gabb.projectpos.data.local.ItemOrder
import com.ralph.gabb.projectpos.ui.main.shop.adapter.ItemVariantAdapter
import kotlinx.android.synthetic.main.dialog_item_order.*
import java.text.DecimalFormat


/*
 * Created by Ralph Gabrielle Orden on 9/5/20.
 */

class OrderItemDialog: BaseDialogFragment() {

    private lateinit var onItemAdded: (ItemOrder) -> Unit
    private lateinit var product: Product
    private lateinit var itemOrder: ItemOrder

    private val decimalFormat = DecimalFormat("Php ###,###.00")

    override val layoutId: Int?
        get() = R.layout.dialog_item_order

    override fun viewCreated() {
        initItemOrder()
        initVariants(product)

        listenToUserEvents()
    }

    private fun initItemOrder() {
        val firstVariant = product.variants[0]
        itemOrder = ItemOrder(product = product, qty = 1, variant = firstVariant)

        variantSelected(firstVariant)
    }

    private fun listenToUserEvents() {
        quantityView.setOnQuantityChangeListener {
            quantityUpdated(it)
        }

        bCancel.setOnClickListener {
            dismiss()
        }

        bAdd.setOnClickListener {
            addItemOrder()
        }
    }

    private fun addItemOrder() {
        onItemAdded(itemOrder)

        dismiss()
    }

    private fun quantityUpdated(quantity: Int) {
        val currentVariant = itemOrder.variant
        val variantPrice = currentVariant.price
        val totalItemOrderAmount = variantPrice * quantity

        itemOrder.qty = quantity
        itemOrder.total = totalItemOrderAmount

        tvProductPrice.text = decimalFormat.format(totalItemOrderAmount)
    }

    fun displayProduct(product: Product) {
        this.product = product
    }

    fun setOnItemOrderAdded(onItemAdded: (ItemOrder) -> Unit) {
        this.onItemAdded = onItemAdded
    }

    private fun initVariants(product: Product) {
        rvVariants.setHasFixedSize(true)
        rvVariants.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvVariants.adapter = ItemVariantAdapter(context!!, product.variants) {

            variantSelected(it)
        }
    }

    private fun variantSelected(productVariant: ProductVariant) {
        val currentQty = itemOrder.qty
        val variantPrice = productVariant.price
        val totalItemOrderAmount = variantPrice * currentQty

        itemOrder.variant = productVariant
        itemOrder.total = totalItemOrderAmount

        tvProductPrice.text = decimalFormat.format(totalItemOrderAmount)
    }

    class ItemOrderBuilder() {

        var itemOrderDialog =
            OrderItemDialog()

        fun displayProduct(product: Product) : ItemOrderBuilder {
            itemOrderDialog.displayProduct(product)
            return this
        }

        fun build(): OrderItemDialog {
            return itemOrderDialog
        }

        fun setOnItemOrderAdded(function: (ItemOrder) -> Unit): ItemOrderBuilder {
            itemOrderDialog.setOnItemOrderAdded(function)
            return this
        }
    }
}

