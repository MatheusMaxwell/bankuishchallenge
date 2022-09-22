package com.matheusmaxwell.bankuishchallenge.presentation.repositoryDetail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.matheusmaxwell.bankuishchallenge.R
import com.matheusmaxwell.bankuishchallenge.databinding.FragmentRepositoryDetailBinding
import com.matheusmaxwell.bankuishchallenge.domain.model.RepositoryDomain
import com.matheusmaxwell.bankuishchallenge.utils.extensions.setVisibilityForBoolean


class RepositoryDetailFragment : Fragment() {

    private var _binding: FragmentRepositoryDetailBinding? = null
    private val args: RepositoryDetailFragmentArgs by navArgs()
    private var repository: RepositoryDomain? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repository = args.repository
        _binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

    }

    private fun setupView() = with(binding){
        repository?.let { repo ->
            txtRepositoryName.text = repo.name
            txtRepositoryFullName.text = repo.fullName
            txtRepositoryDescription.text = repo.description
            txtRepositoryForks.text = repo.forks.toString()
            txtRepositoryIssues.text = repo.openIssues.toString()
            txtRepositoryWatchers.text = repo.watchers.toString()
            imgRepositoryPrivate.setVisibilityForBoolean(repo.private)
            imgRepositoryPublic.setVisibilityForBoolean(!repo.private)
            txtPrivateOrPublic.text = if (repo.private) "Private" else "Public"
            txtRepositoryUrl.text = repo.url
            txtRepositoryUrl.setTextColor(Color.BLUE)
            txtRepositoryUrl.paint?.isUnderlineText = true
            txtRepositoryUrl.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.url))
                startActivity(browserIntent)
            }



            val path = repo.owner?.avatarUrl
            val req = RequestOptions()
                .placeholder(R.drawable.ic_baseline_avatar_24)
                .error(R.drawable.ic_baseline_avatar_24)
                .dontAnimate()
            Glide.with(requireContext())
                .load(path)
                .apply(req)
                .into(imgRepositoryOwnerAvatar)
            txtOwnerName.text = repo.owner?.login
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}