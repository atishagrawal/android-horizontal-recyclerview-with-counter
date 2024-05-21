/**
 * @Author: Created by Atish Agrawal
 * @Date: 20/05/24 1:48 pm.
 * @Email: atishagrawal@countrydelight.in
 * @Phone: +91-9716987018
 */

package com.example.myapplication


/**
 * Class Definition
 *
 */
import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class StartSnapHelper : LinearSnapHelper() {
    override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray {
        val out = IntArray(2)
        out[0] = layoutManager.getDecoratedLeft(targetView) - layoutManager.paddingLeft
        out[1] = 0
        return out
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        return if (layoutManager is RecyclerView.SmoothScroller.ScrollVectorProvider) {
            findStartView(layoutManager)
        } else {
            super.findSnapView(layoutManager)
        }
    }

    private fun findStartView(layoutManager: RecyclerView.LayoutManager): View? {
        if (layoutManager.childCount == 0) {
            return null
        }

        var closestChild: View? = null
        val start = layoutManager.paddingLeft
        var closestStart = Int.MAX_VALUE

        for (i in 0 until layoutManager.childCount) {
            val child = layoutManager.getChildAt(i)
            val childStart = layoutManager.getDecoratedLeft(child!!)

            if (childStart < closestStart) {
                closestStart = childStart
                closestChild = child
            }
        }

        return closestChild
    }
}
