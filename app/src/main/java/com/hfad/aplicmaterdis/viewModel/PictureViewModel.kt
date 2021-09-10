package com.hfad.aplicmaterdis.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.aplicmaterdis.BuildConfig
import com.hfad.aplicmaterdis.repository.PODServerResponse
import com.hfad.aplicmaterdis.repository.PictureData
import com.hfad.aplicmaterdis.repository.RequesrOnServerAPIIml
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureViewModel(
    private val liveDataToObserver: MutableLiveData<PictureData> = MutableLiveData(),
    private val retrofitIml: RequesrOnServerAPIIml = RequesrOnServerAPIIml()
) : ViewModel(){

    fun getData():LiveData<PictureData>{
        sendServerRequest()
        return liveDataToObserver
    }

    private fun sendServerRequest() {
       liveDataToObserver.value = PictureData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureData.Error(Throwable("Нет ключа"))

        }else {
            retrofitIml.getRetrofit().getPictures(apiKey).enqueue( object : Callback<PODServerResponse>{
                override fun onResponse(
                    call: Call<PODServerResponse>,
                    response: Response<PODServerResponse>
                ) {

                    if (response.isSuccessful && response.body() !=null){
                        liveDataToObserver.value =
                            PictureData.Success(response.body()!!)
                    }else {
                    val message = response.message()
                    if (message.isNullOrEmpty()) {
                        liveDataToObserver.value =
                            PictureData.Error(Throwable("Тело сообщения пустое"))

                    } else{
                        liveDataToObserver.value = PictureData.Error(Throwable(message))
                    }
                }
                }

                override fun onFailure(call: Call<PODServerResponse>, t: Throwable) {
                    liveDataToObserver.value = PictureData.Error(t)
                }
            })
        }
    }

}