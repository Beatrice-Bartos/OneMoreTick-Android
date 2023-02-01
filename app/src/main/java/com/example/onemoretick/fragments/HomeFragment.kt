package com.example.onemoretick.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onemoretick.R
import com.example.onemoretick.adapters.CategoryAdapter
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.interfaces.OnItemClick
import com.example.onemoretick.models.category.Category
import com.example.onemoretick.models.task.Task

class HomeFragment : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null
    private var categories: ArrayList<Category> = arrayListOf()
    private var adapter: CategoryAdapter = CategoryAdapter(categories, object : OnItemClick {
        override fun categoryItemClick(category: Category?) {
            // TODO: Process category click
        }

        override fun taskItemClick(task: Task?) {
            TODO("Not yet implemented")
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Read categories
        categories.add(Category(0, "All"))
        categories.add(Category(1, "Work"))
        categories.add(Category(2, "School"))
        categories.add(Category(3, "Home"))
        categories.add(Category(4, "Other"))
        adapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(view.findViewById(R.id.categories_list))
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    companion object {
        const val TAG_HOME = "TAG_HOME"
        fun newInstance(userId: Int): HomeFragment {
            val args = Bundle()
            val fragment: HomeFragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}