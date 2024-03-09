package com.example.aviatickets.fragment

import OfferListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.aviatickets.R
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.network.ApiClient
import com.example.aviatickets.model.service.FakeService
import kotlinx.coroutines.launch


class OfferListFragment : Fragment() {

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter = OfferListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        fetchOffers()
        adapter.submitList(FakeService.offerList)
    }

    private fun fetchOffers() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val offerList = ApiClient.createService().getOffers()
                adapter.submitList(offerList)
            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка при загрузке данных", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun sortByPrice() {
        val sortedList = adapter.currentList.sortedBy { it.price }
        adapter.submitList(sortedList)
    }

    fun sortByDuration() {
        val sortedList = adapter.currentList.sortedBy { it.flight.duration }
        adapter.submitList(sortedList)
    }



    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter

            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        /**
                         * implement sorting by price
                         */
                        sortByPrice()

                    }

                    R.id.sort_by_duration -> {
                        /**
                         * implement sorting by duration
                         */

                        sortByDuration()
                    }
                }
            }
        }
    }
}