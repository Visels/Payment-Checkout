package com.lemu.pay.checkout.di

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import com.lemu.pay.checkout.data.api.Constants
import com.lemu.pay.checkout.data.api.CheckoutApi
import com.lemu.pay.checkout.data.api.SelfOkhttpClient
import com.lemu.pay.checkout.data.local.PreferencesHelper
import com.lemu.pay.checkout.repository.CheckoutRepository
import com.lemu.pay.checkout.repository.CheckoutRepositoryImpl
import com.lemu.pay.checkout.repository.MyRepository
import com.lemu.pay.checkout.repository.MyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesMyApi(preferencesHelper: PreferencesHelper):CheckoutApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(SelfOkhttpClient(preferencesHelper).client)
            .build()
            .create(CheckoutApi::class.java)
    }


    @Provides
    @Singleton
    fun providesMyRepository(apis: CheckoutApi, app:Application):MyRepository {
        return MyRepositoryImpl(apis,app)
    }

    @Provides
    @Singleton
    fun providesCheckoutRepository(api:CheckoutApi) : CheckoutRepository {
        return CheckoutRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesPreferencesHelper(@ApplicationContext context:Context):PreferencesHelper{
        return PreferencesHelper(context)
    }

}