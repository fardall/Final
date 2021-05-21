package com.finalproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.finalproject.R
import com.finalproject.adapter.UserAdapter
import com.finalproject.data.api.response.UserItem
import com.finalproject.databinding.FragmentHomeBinding
import com.finalproject.databinding.FragmentProfileBinding
import com.finalproject.utils.setImageUrl
import com.finalproject.viewmodel.GithubUserViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var githubUserViewModel: GithubUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()
    }

    private fun getViewModel() {
        githubUserViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(GithubUserViewModel::class.java)

        githubUserViewModel.initRepository(requireContext())

        githubUserViewModel.getDetail().observe(this, {
            if(it != null) {
                updateUserData(it)
            }
        })

        "Fardal-01".let { githubUserViewModel.detailUser(it) }
    }

    private fun updateUserData(data: UserItem) {
        binding.ivProfile.setImageUrl(requireContext(), data.avatar_url, binding.pbProfile)
        binding.nameProfile.text = data.login
        binding.idProfile.text = data.id.toString()
        binding.urlProfile.text = data.html_url
        binding.createdProfile.text = data.created_at
        binding.updatedProfile.text = data.updated_at
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}