package com.matheusmaxwell.bankuishchallenge.presentation.repositories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheusmaxwell.bankuishchallenge.R
import com.matheusmaxwell.bankuishchallenge.databinding.FragmentRepositoriesBinding
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain
import com.matheusmaxwell.bankuishchallenge.presentation.repositories.adapter.RepositoryItemAdapter
import com.matheusmaxwell.bankuishchallenge.utils.extensions.makeGone
import com.matheusmaxwell.bankuishchallenge.utils.extensions.makeVisible
import com.matheusmaxwell.bankuishchallenge.utils.pagination.PaginationScrollListener
import com.matheusmaxwell.bankuishchallenge.utils.viewState.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response.error


class RepositoriesFragment : Fragment() {

    private lateinit var binding: FragmentRepositoriesBinding
    private var activityContext: FragmentActivity? = null
    private val viewModel: RepositoriesViewModel by viewModel()
    private var adapter: RepositoryItemAdapter? = null
    private var rvLayoutManager: LinearLayoutManager? = null
    private var page = 1
    private var isLastPage: Boolean = false
    private var isLoadingMore: Boolean = false



    override fun onResume() {
        viewModel.fetchRepositories(1)
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityContext = requireActivity()
        binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservables()
    }

    private fun setupView() = with(binding){

        rvLayoutManager = LinearLayoutManager(activityContext)

        swipeContainerRepositories.setOnRefreshListener {
            page = 1
            adapter = null
            viewModel.fetchRepositories(page)
        }

        rvRepositories.addOnScrollListener(object : PaginationScrollListener(rvLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoadingMore
            }

            override fun loadMoreItems() {
                isLoadingMore = true
                page++
                viewModel.fetchRepositories(page)
            }
        })
    }

    private fun createRepositoriesList(list: List<RepositoryDomain>) = with(binding) {
        isLoadingMore = false
        swipeContainerRepositories.isRefreshing = false
        if(adapter == null){
            rvRepositories.removeAllViews()
            rvRepositories.layoutManager = rvLayoutManager
            adapter = RepositoryItemAdapter(list, ::onRepositoryClickItem)
            rvRepositories.adapter = adapter
        }
        else{
            adapter?.update(list)
        }

    }

    private fun onRepositoryClickItem(repository: RepositoryDomain){
        adapter = null
        val action = RepositoriesFragmentDirections.actionRepositoriesFragmentToRepositoryDetailFragment(repository)
        findNavController().navigate(action)
    }


    private fun setupObservables(){
        activityContext?.let { owner ->
            viewModel.viewStateRepositoriesLiveData.observe(owner) { viewState ->
                when (viewState) {
                    is ViewState.Loading -> {
                        binding.loading.progressLoading.makeVisible()
                    }
                    is ViewState.Empty -> {
                        binding.loading.progressLoading.makeGone()
                        createRepositoriesList(emptyList())
                    }
                    is ViewState.Success -> {
                        binding.loading.progressLoading.makeGone()
                        createRepositoriesList(viewState.data)
                    }
                    is ViewState.Error -> {
                        binding.loading.progressLoading.makeGone()
                        Toast.makeText(
                            activityContext,
                            getString(R.string.error_has_ocurred),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}