package com.ralph.gabb.projectpos.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ralph.gabb.projectpos.R
import kotlinx.android.synthetic.main.widget_quantity.view.*


/*
 * Created by Ralph Gabrielle Orden on 9/5/20.
 */

class QuantityView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var onQuantityUpdate: (Int) -> Unit

    private var currentAmount = 1

    init {
        val view = toView(context, this)
        this.addView(view)
    }

    private fun toView(context: Context, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_quantity, parent, false)

        val textAdd = view.wQtyAdd
        val textSub = view.wQtySub
        val textAmount = view.wQtyAmount

        textAmount.text = currentAmount.toString()

        textAdd.setOnClickListener {
            if (currentAmount < 99) {
                currentAmount = currentAmount.inc()
            }
            textAmount.text = currentAmount.toString()

            onQuantityUpdate(currentAmount)
        }

        textSub.setOnClickListener {
            if (currentAmount > 1) {
                currentAmount = currentAmount.dec()
            }
            textAmount.text = currentAmount.toString()

            onQuantityUpdate(currentAmount)
        }

        return view
    }

    fun setOnQuantityChangeListener(onQuantityUpdate: (Int) -> Unit) {
        this.onQuantityUpdate = onQuantityUpdate
    }

}