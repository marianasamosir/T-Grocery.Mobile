package org.d3if3159.t_grocery.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.d3if3159.t_grocery.database.BarangDao
import org.d3if3159.t_grocery.model.Barang

class MainViewModel(dao: BarangDao): ViewModel() {
    val data: StateFlow<List<Barang>> = dao.getBarang().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(0L),
        initialValue = emptyList()
    )

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching =  MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    @OptIn(FlowPreview::class)
    val _data = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(data){text, _data ->
            if (text.isBlank()){
                _data
            } else {
                _data.filter {
                    it.doesMactchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(0),
            data.value
        )
    fun onSearchTextChange(text: String){
        _searchText.value = text
    }
}