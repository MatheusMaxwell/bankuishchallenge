package com.matheusmaxwell.bankuishchallenge.presentation.repositories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheusmaxwell.bankuishchallenge.databinding.RepositoryItemListBinding
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain

class RepositoryItemAdapter(
    private val repositories: List<RepositoryDomain>
): RecyclerView.Adapter<RepositoryItemViewHolder>() {

    private lateinit var binding: RepositoryItemListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder {
        binding = RepositoryItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository)
    }

    override fun getItemCount(): Int = repositories.size

}

class RepositoryItemViewHolder(
    private val binding: RepositoryItemListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(repository: RepositoryDomain) {
        binding.repositoryName.text = repository.name
        binding.repositoryAuthor.text = repository.owner?.login
    }
}