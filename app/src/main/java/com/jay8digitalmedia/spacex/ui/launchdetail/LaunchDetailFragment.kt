package com.jay8digitalmedia.spacex.ui.launchdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.jay8digitalmedia.spacex.R
import com.jay8digitalmedia.spacex.databinding.LaunchDetailFragmentBinding
import com.jay8digitalmedia.spacex.di.Injectable
import kotlinx.android.synthetic.main.launch_detail_fragment.*
import javax.inject.Inject


class LaunchDetailFragment : Fragment(), Injectable {

    private val args: LaunchDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LaunchDetailViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: LaunchDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.launch_detail_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }


    private fun setupObservers() {
        viewModel.getLaunch(args.launchId).observe(viewLifecycleOwner, { launch ->
            launch?.let { launchResource ->
                launchDetailLaunchTitle?.visibility = View.VISIBLE
                binding.launch = launchResource.data
                launchResource.data?.rocket?.let { rocketId ->
                    loadRocket(rocketId)
                }
            }
        })
    }

    private fun loadRocket(id: String) {
        viewModel.getRocket(id).observe(viewLifecycleOwner, { rocket ->
            rocket?.let { rocketResource ->
                launchDetailRocketTitle?.visibility = View.VISIBLE
                launchDetailWikiButton?.visibility = View.VISIBLE
                launchDetailWikiButton?.setOnClickListener {
                    goToUrl(rocketResource.data?.wikipedia)
                }
                binding.rocket = rocketResource.data
            }
        })
    }

    private fun goToUrl(url: String?) {
        url?.let {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it)
            startActivity(intent)
        }
    }
}