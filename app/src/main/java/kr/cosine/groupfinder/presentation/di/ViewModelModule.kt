package kr.cosine.groupfinder.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kr.cosine.groupfinder.data.repository.AccountRepositoryImpl
import kr.cosine.groupfinder.data.repository.PostRepositoryImpl
import kr.cosine.groupfinder.domain.repository.AccountRepository
import kr.cosine.groupfinder.domain.repository.PostRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl
    ) : AccountRepository

    @Binds
    @ViewModelScoped
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository
}