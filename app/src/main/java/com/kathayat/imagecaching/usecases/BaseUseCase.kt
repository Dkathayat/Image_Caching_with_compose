package com.kathayat.imagecaching.usecases

interface BaseUseCase<In, Out> {
    suspend fun execute(input: In): Out
}