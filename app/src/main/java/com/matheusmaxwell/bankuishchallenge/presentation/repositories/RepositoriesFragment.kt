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
import com.matheusmaxwell.bankuishchallenge.utils.viewState.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response.error


class RepositoriesFragment : Fragment() {

    private var _binding: FragmentRepositoriesBinding? = null
    private var activityContext: FragmentActivity? = null
    private val viewModel: RepositoriesViewModel by viewModel()
    private var adapter: RepositoryItemAdapter? = null

    private val binding get() = _binding!!


    override fun onResume() {
        viewModel.fetchRepositories("language:kotlin", 1)
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityContext = requireActivity()
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_RepositoriesFragment_to_RepositoryDetailFragment)
//        }
        setupView()
        setupObservables()
    }

    private fun setupView(){

    }

    private fun createRepositoriesList(list: List<RepositoryDomain>) = with(binding) {
        rvRepositories.removeAllViews()
        rvRepositories.layoutManager = LinearLayoutManager(activityContext)
        adapter = RepositoryItemAdapter(list)
        rvRepositories.adapter = adapter
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