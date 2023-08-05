package com.ink1804.dev.common.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class FlowViewModel : ViewModel() {

    private val <T>StateFlowProxy<T>.stateFlow: MutableStateFlow<T>
        get() = this.flowInternal as MutableStateFlow<T>

    private val <T>SharedFlowProxy<T>.sharedFlow: MutableSharedFlow<T>
        get() = this.flowInternal as MutableSharedFlow<T>

    protected fun <T> StateFlowProxy<T>.emit(value: T) = viewModelScope.launch {
        this@emit.stateFlow.emit(value)
    }

    protected fun <T> SharedFlowProxy<T>.emit(value: T) = viewModelScope.launch {
        this@emit.sharedFlow.emit(value)
    }

    fun <T> stateFlow(initialValue: T): StateFlowProxy<T> =
        StateFlowProxy(MutableStateFlow(initialValue))

    fun <T> sharedFlow(initialValue: T? = null, replay: Int = 0): SharedFlowProxy<T> =
        SharedFlowProxy<T>(MutableSharedFlow(replay = replay)).also { flowProxy ->
            initialValue?.let { value ->
                viewModelScope.launch {
                    flowProxy.sharedFlow.emit(value)
                }
            }
        }
}

suspend fun <T> FlowProxy<T>.collect(collector: FlowCollector<T>) =
    this.flowInternal.collect(collector)
