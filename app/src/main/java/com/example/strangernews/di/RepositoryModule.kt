package com.example.strangernews.di

import com.example.strangernews.data.di.DataSourceModule
import com.example.strangernews.data.di.NetworkModule
import com.example.strangernews.data.di.RoomModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [NetworkModule::class, RoomModule::class, DataSourceModule::class])
@ComponentScan("com.example.strangernews.data")
class RepositoryModule
