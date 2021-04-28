package com.jay8digitalmedia.spacex.ui.launchlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jay8digitalmedia.spacex.R
import com.jay8digitalmedia.spacex.di.Injectable
import kotlinx.android.synthetic.main.launch_list_fragment.*
import javax.inject.Inject
import com.jay8digitalmedia.spacex.util.Status.ERROR
import com.jay8digitalmedia.spacex.util.Status.LOADING
import com.jay8digitalmedia.spacex.util.Status.SUCCESS

class LaunchListFragment : Fragment(R.layout.launch_list_fragment), Injectable {

    private val launchRecyclerViewAdapter = LaunchListRecyclerViewAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LaunchListViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        launchSortToggleButton?.addOnButtonCheckedListener { _, checkedId, _ ->
            launchRecyclerViewAdapter.sortByDate = checkedId == R.id.launchSortDateButton
        }

        launchSuccessToggleButton?.setOnCheckedChangeListener { _, isChecked ->
            launchRecyclerViewAdapter.filterBySuccess = isChecked
        }

        launchRecyclerView?.apply {
            adapter = launchRecyclerViewAdapter
        }

        launchRecyclerViewAdapter.onItemClick = { id ->
            findNavController().navigate(LaunchListFragmentDirections.actionLaunchListFragmentToLaunchDetailFragment(id))
        }
    }

    private fun setupObservers() {
        viewModel.getLaunches().observe(viewLifecycleOwner,  {
            it?.let { resource ->
                resource.data?.let { data ->
                    launchRecyclerViewAdapter.launches = data
                }

                when (resource.status) {
                    SUCCESS -> {
                        launchProgressBar?.visibility = View.GONE
                    }
                    ERROR -> {
                        launchProgressBar?.visibility = View.GONE
                    }
                    LOADING -> {
                        launchProgressBar?.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}