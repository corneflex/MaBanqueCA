package com.scdc.mabanque.features.banks.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.scdc.core.ui.BaseViewModel
import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.domain.model.Operation
import com.scdc.mabanque.features.banks.domain.model.Resource
import com.scdc.mabanque.features.banks.domain.usecase.GetBanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankViewModel @Inject constructor(private val getBankUseCase: GetBanksUseCase): BaseViewModel() {

    //val banksLiveData: LiveData<Resource<List<Bank>>> = getBankUseCase().asLiveData();

    private val _state = mutableStateOf(BankUiState())
    val state: State<BankUiState> = _state
    init {
        getBanks()
    }

    private fun updateState(banks:List<Bank>){

        val groupBanks = banks.groupBy { it.isCA }
        val caBanks = groupBanks[1]?.sortedBy { it.name } ?: emptyList()
        val otherBanks = groupBanks[0]?.sortedBy { it.name } ?: emptyList()
        _state.value = state.value.copy(
            caBanks = caBanks,
            otherBanks = otherBanks,
            accounts = mapOperations(banks),
            isLoading = false
        )
    }

    private fun mapOperations(banks: List<Bank>) =
        banks.flatMap { it.accounts }
            .map { it -> it.copy(operations = it.operations.sortedWith(compareByDescending<Operation> { it.date }.thenBy { it.title })) }
            .associateBy { it.id }

    private fun getBanks() {
        viewModelScope.launch {

            getBankUseCase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            caBanks = emptyList(),
                            otherBanks = emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                       // handleFailure(Throwable("error"))
                        updateState(result.data)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            caBanks = emptyList(),
                            otherBanks = emptyList(),
                            isLoading = false
                        )
                        handleFailure(result.error)

                    }
                }
            }.launchIn(this)
        }
    }
}