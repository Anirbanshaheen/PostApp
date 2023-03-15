package com.example.postapp.screens.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.databinding.FragmentPosterDetailsBinding
import com.example.postapp.models.postModel.PostModelItem
import com.example.postapp.screens.HomeViewModel
import com.example.postapp.screens.home.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PosterDetailsFragment : Fragment() {

    private var binding: FragmentPosterDetailsBinding? = null
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var model : PostModelItem

    private var postCommentAdapter: PostCommentAdapter = PostCommentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPosterDetailsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get model from Home page
        val bundle: Bundle? = arguments
        bundle?.let {
            model = it.getParcelable("model")!!
        }

        initView()
    }

    private fun initView() {
        //binding?.descriptionTV?.text = model.body

        viewModel.getPostDetails(model.id).observe(viewLifecycleOwner, Observer {
            binding?.descriptionTV?.text = it.body
        })

        postCommentAdapter = PostCommentAdapter()
        with(binding!!.postCommentRV) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = postCommentAdapter
        }

        viewModel.getPostComment(model.id).observe(viewLifecycleOwner, Observer {
            postCommentAdapter.initLoad(it)
        })
    }
}