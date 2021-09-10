package com.hfad.aplicmaterdis.repository

sealed class PictureData{
    data class Success(val serverResponse: PODServerResponse): PictureData()
    data class Error(val error: Throwable):PictureData()
    data class Loading(val Process: Int?):PictureData()
}
