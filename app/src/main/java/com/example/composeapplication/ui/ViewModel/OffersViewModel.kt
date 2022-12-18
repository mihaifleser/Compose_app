package com.example.composeapplication.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeapplication.model.DefaultOffer
import com.example.composeapplication.model.OfferItemViewModel
import kotlinx.coroutines.delay
import java.lang.Thread.sleep

class OffersViewModel : ViewModel() {

    private val _items = MutableLiveData<List<OfferItemViewModel>>()
    val items: LiveData<List<OfferItemViewModel>> = _items

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private var currentItems: List<OfferItemViewModel> = DefaultOffer.values().map { it.viewModel }

    init {
        _items.postValue(currentItems)
    }

    val deleteItem: (item: OfferItemViewModel) -> Unit = { item ->
        currentItems = currentItems.filter { it != item }
        _items.postValue(currentItems)
    }

    val refreshItems: () -> Unit = {
        _isRefreshing.value = true
        println("refreshing...")
        currentItems = DefaultOffer.values().map { it.viewModel }
        _items.postValue(currentItems.map { it })
        println("Done refreshing...")
        _isRefreshing.value = false
    }
}