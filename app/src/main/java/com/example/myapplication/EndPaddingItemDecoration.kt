/**
 * @Author: Created by Atish Agrawal
 * @Date: 20/05/24 1:52 pm.
 * @Email: atishagrawal@countrydelight.in
 * @Phone: +91-9716987018
 */

package com.example.myapplication

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Class Definition
 *
 */
class EndPaddingItemDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        if (position == itemCount - 1) {
            outRect.right = padding
        }
    }
}