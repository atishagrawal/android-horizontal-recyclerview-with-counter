package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var myAdapter: MyAdapter? = null
    private var itemCount: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        itemCount = findViewById<TextView>(R.id.itemCount)


        val items: MutableList<String> = ArrayList()
        for (i in 1..40) {
            items.add("$i")
        }

        myAdapter = MyAdapter(items)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.run {
            setLayoutManager(layoutManager)
            setAdapter(myAdapter)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    updateItemCount(layoutManager)
                }
            })
        }


//        // Attach the SnapHelper to the RecyclerView
//        val snapHelper = StartSnapHelper()
//        snapHelper.attachToRecyclerView(recyclerView)
//
//
//        // Add the end padding item decoration
//        val padding = resources.displayMetrics.widthPixels / 5
//        recyclerView?.addItemDecoration(EndPaddingItemDecoration(padding))

        updateItemCount(layoutManager)
    }

    private fun updateItemCount(layoutManager: LinearLayoutManager) {
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        val remainingItems = totalItemCount - (firstVisibleItemPosition + visibleItemCount)
        itemCount!!.text = "+ $remainingItems"

        if (remainingItems == 0) {
            itemCount!!.visibility = View.GONE
        } else {
            itemCount!!.visibility = View.VISIBLE
        }

    }
}

class MyAdapter(private val items: List<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
//        val params = view.layoutParams as RecyclerView.LayoutParams
//        params.width = parent.width / 4 // Adjust width to fit 4 items
//        view.layoutParams = params
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.itemText)
    }
}