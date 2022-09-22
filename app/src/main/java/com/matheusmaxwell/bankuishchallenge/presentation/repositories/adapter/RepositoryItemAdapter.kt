package com.matheusmaxwell.bankuishchallenge.presentation.repositories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheusmaxwell.bankuishchallenge.databinding.RepositoryItemListBinding
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain

class RepositoryItemAdapter(
    private var repositories: List<RepositoryDomain>,
    private var onClick: (repository: RepositoryDomain) -> Unit
): RecyclerView.Adapter<RepositoryItemViewHolder>() {

    private lateinit var binding: RepositoryItemListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder {
        binding = RepositoryItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        val repository = repositories[position]
        holder.itemView.setOnClickListener {
            onClick.invoke(repository)
        }
        holder.bind(repository)
    }

    override fun getItemCount(): Int = repositories.size

    fun update(list: List<RepositoryDomain>){
        val newList = repositories.toMutableList()
        newList.addAll(list)
        repositories = newList.distinctBy { it.id }
        notifyDataSetChanged()
    }

}

class RepositoryItemViewHolder(
    private val binding: RepositoryItemListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(repository: RepositoryDomain) {
        binding.repositoryName.text = repository.name
        binding.repositoryAuthor.text = repository.owner?.login
    }
}