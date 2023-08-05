package com.ink1804.dev.common.ui.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

open class FlowProxy<T> internal constructor(internal val flowInternal: Flow<T>)
open class StateFlowProxy<T> internal constructor(flow: MutableStateFlow<T>) : FlowProxy<T>(flow)
open class SharedFlowProxy<T> internal constructor(flow: MutableSharedFlow<T>) : FlowProxy<T>(flow)
