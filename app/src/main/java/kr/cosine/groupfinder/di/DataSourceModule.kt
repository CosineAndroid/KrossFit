package kr.cosine.groupfinder.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.cosine.groupfinder.data.remote.CloudFunctionDataSource
import kr.cosine.groupfinder.data.remote.FirebaseDataSource
import kr.cosine.groupfinder.data.remote.RiotAsiaDataSource
import kr.cosine.groupfinder.data.remote.RiotKoreaDataSource
import kr.cosine.groupfinder.di.annotation.RiotAsiaRetrofit
import kr.cosine.groupfinder.di.annotation.RiotKoreaRetrofit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideFirebaseDataSource(): FirebaseDataSource {
        return object : FirebaseDataSource {
            override val firestore = Firebase.firestore
        }
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @RiotAsiaRetrofit
    fun provideRiotAsiaRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://asia.api.riotgames.com/lol/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @RiotKoreaRetrofit
    fun provideRiotKoreaRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://kr.api.riotgames.com/lol/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRiotAsiaDatasource(
        @RiotAsiaRetrofit retrofit: Retrofit,
    ): RiotAsiaDataSource {
        return retrofit.create(RiotAsiaDataSource::class.java)
    }

    @Provides
    fun provideRiotKoreaDatasource(
        @RiotKoreaRetrofit retrofit: Retrofit,
    ): RiotKoreaDataSource {
        return retrofit.create(RiotKoreaDataSource::class.java)
    }

    @Provides
    fun provideCloudFunctionRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://asia-northeast2-groupfinder-b2f8e.cloudfunctions.net/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCloudFunctionDatasource(
        retrofit: Retrofit,
    ): CloudFunctionDataSource {
        return retrofit.create(CloudFunctionDataSource::class.java)
    }
}