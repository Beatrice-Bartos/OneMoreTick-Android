package com.example.onemoretick.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.onemoretick.R
import com.example.onemoretick.interfaces.OnCategoryItemClick
import com.example.onemoretick.models.category.Category

class CategoryAdapter(
    private val categoryList: ArrayList<Category>,
    private val onCategoryItemClick: OnCategoryItemClick?
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var categoryConstraintLayouts: ArrayList<ConstraintLayout> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View =
            inflater.inflate(R.layout.category_item_cell, parent, false)

        val constraintLayout = view as ConstraintLayout
        if (categoryConstraintLayouts.isEmpty()) {
            constraintLayout.isSelected = true
        }
        categoryConstraintLayouts.add(constraintLayout)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = categoryList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        private val categoryItemTitle: TextView = view.findViewById(R.id.category_item_title)
        fun bind(category: Category) {
            categoryItemTitle.text = category.name
            view.setOnClickListener {
                categoryConstraintLayouts.onEach { cl -> cl.isSelected = false }
                (view as ConstraintLayout).isSelected = true
                onCategoryItemClick?.categoryItemClick(category)
            }
        }
    }
}