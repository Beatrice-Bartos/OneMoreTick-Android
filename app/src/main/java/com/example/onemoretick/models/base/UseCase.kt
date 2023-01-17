package com.example.onemoretick.models.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

typealias NoParams = Unit
typealias NoResult = Unit

/**
 * A UseCase executes a business case.
 * @param <Params> parameter class which is consumed by the UseCase
 * @param <Type> return type which will be returned by the UseCase
 */
abstract class UseCase<in Params, out Type>(protected val dispatcher: CoroutineDispatcher) where Type : Any? {
    /**
     * The business logic to execute.
     * @param params <Params> optional param for this use case execution.
     * @return <Type> the type of result.
     */
    protected abstract suspend fun execute(params: Params): Type

    open suspend operator fun invoke(params: Params): Result<Type, Exception> = withContext(dispatcher) {
        try {
            val result = execute(params)
            Result.Success(result)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}
