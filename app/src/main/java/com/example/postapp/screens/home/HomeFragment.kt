package com.example.postapp.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.R
import com.example.postapp.databinding.FragmentHomeBinding
import com.example.postapp.screens.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null;
    private val viewModel by viewModels<HomeViewModel>()

    private var postAdapter: PostAdapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHomeBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initClickListener()
    }

    private fun initView() {
        postAdapter = PostAdapter()
        with(binding!!.postRV) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = postAdapter
        }

        viewModel.getPost().observe(viewLifecycleOwner, Observer { model->
            postAdapter.initLoad(model)
        })
    }

    private fun initClickListener() {
        postAdapter.onItemClick = { model, position ->
            val bundle = bundleOf(
                "model" to model
            )
            findNavController().navigate(R.id.posterDetailsFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}