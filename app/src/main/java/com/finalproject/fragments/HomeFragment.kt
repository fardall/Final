package com.finalproject.fragments

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.finalproject.R
import com.finalproject.adapter.UserAdapter
import com.finalproject.data.api.response.UserItem
import com.finalproject.databinding.ActivityMainBinding
import com.finalproject.databinding.FragmentHomeBinding
import com.finalproject.utils.createSearchViewMenu
import com.finalproject.utils.gone
import com.finalproject.utils.visible
import com.finalproject.viewmodel.GithubUserViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var githubUserViewModel: GithubUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()
        showRecyclerGrid()
        searchUser("fardal")
    }

    private fun getViewModel(){
        githubUserViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(GithubUserViewModel::class.java)

        githubUserViewModel.initRepository(requireContext())

        githubUserViewModel.getListSearch().observe(this,{
            if(it != null){
                updateListUser(it)
                showLoading(false)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().createSearchViewMenu(menu){
            searchUser(it)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun showRecyclerGrid(){
        binding.rvMain.layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.HORIZONTAL,false)
        userAdapter = UserAdapter(requireContext(), mutableListOf())
        binding.rvMain.adapter = userAdapter
    }

    private fun searchUser(query: String){
        showLoading(true)
        githubUserViewModel.searchUser(query)
    }

    private fun updateListUser(listUser: List<UserItem>) {
        userAdapter.data.clear()
        userAdapter.data.addAll(listUser)
        userAdapter.notifyDataSetChanged()
        if(listUser.isEmpty()){
            binding.rvMain.gone()
            binding.tvEmpty.visible()
        }
    }

    private fun showLoading(state: Boolean){
        if(state){
            binding.rvMain.gone()
            binding.progressBar.visible()
            binding.tvEmpty.gone()
        } else {
            binding.rvMain.visible()
            binding.progressBar.gone()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}