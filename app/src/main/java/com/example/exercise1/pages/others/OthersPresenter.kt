package com.example.exercise1.pages.others

import com.example.exercise1.data.remote.apiservice.ApiResult
import com.example.exercise1.data.remote.repository.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class OthersPresenter @Inject constructor(
    private val remoteRepository: RemoteRepository,
) : Others.Presenter {

    private lateinit var othersView: Others.View


    override fun attachView(view: Others.View) {
        this.othersView = view
    }


    override fun loadRandomImage() {
        othersView.showLoading(true)
        CoroutineScope(Dispatchers.IO).launch {
            remoteRepository.getRandomDog().collect {
                when (it) {
                    is ApiResult.ErrorResult -> {
                        withContext(Dispatchers.Main){
                            othersView.showLoading(false)
                            othersView.showError(it.errorMessage.orEmpty())
                        }
                    }
                    is ApiResult.SuccessResult -> {
                        withContext(Dispatchers.Main) {
                            othersView.showLoading(false)
                            othersView.showData(it.data?.message.orEmpty())
                        }
                    }
                }

            }
        }
    }


}