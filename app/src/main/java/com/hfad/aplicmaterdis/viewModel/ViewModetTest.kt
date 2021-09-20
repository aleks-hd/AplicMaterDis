package com.hfad.aplicmaterdis.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.aplicmaterdis.model.TestSlider

class ViewModetTest(private val liveDataToObserverTest: MutableLiveData<TestSlider> = MutableLiveData()): ViewModel() {

    fun getLiveData(): LiveData<TestSlider> {
        return liveDataToObserverTest
    }
    fun getLiveDataTest(zna: Float)
    {
        liveDataToObserverTest.value = TestSlider(zna)

    }
}