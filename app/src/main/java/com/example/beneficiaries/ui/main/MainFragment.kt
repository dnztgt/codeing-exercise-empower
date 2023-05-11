package com.example.beneficiaries.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiaries.R
import com.example.beneficiaries.ui.details.DetailsFragment

class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }
    private val onClickListener: (String) -> Unit = { ssn ->
        parentFragmentManager.commit {
            replace(R.id.container, DetailsFragment.newInstance(ssn))
            addToBackStack(null)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemAdapter(onClickListener)
        val items = view.findViewById<RecyclerView>(R.id.items)
        items.layoutManager = LinearLayoutManager(context)
        items.adapter = adapter
        items.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
