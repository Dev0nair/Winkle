package com.ismaelgr.winkle.domain.usecase

import com.ismaelgr.winkle.data.repository.needs.StorageRepositoryNeed

class UploadImagesUseCase(private val storageRepositoryNeed: StorageRepositoryNeed) {

    fun execute(
        idProfile: String,
        onAllSuccess: (downLoadImages: List<String>) -> Unit,
        onError: (String) -> Unit,
        vararg imagesToUpload: String
    ) {
        val images = imagesToUpload
            .filter { it.startsWith("local:") }
            .map { it.replace("local:", "") }
        val listDownloadImages = arrayListOf<String>()
        uploadAll(idProfile, images, 0, onAllSuccess, onError, listDownloadImages)
    }

    /**
     * Recursive method that iterates when the image is completely uploaded with ot without error.
     *
     * Check if the position of the list of images to upload exists (because the count+1 of each iteration)
     * and then check if the file where actual Uri is pointing.
     *
     * Then checks the result of the upload and then tries to get the downloadURL which is the string we will
     * save within the product object.
     * */
    private fun uploadAll(
        idProfile: String,
        list: List<String>,
        count: Int,
        onAllSuccess: (downLoadImages: List<String>) -> Unit,
        onError: (String) -> Unit,
        listDownloadImages: ArrayList<String>
    ) {
        if (count < list.size) {
            val imgToUpload = list[count]
            storageRepositoryNeed.uploadFile(idProfile, imgToUpload)
                .subscribe({ downloadUrl ->
                    listDownloadImages.add(downloadUrl)
                    uploadAll(idProfile, list, count + 1, onAllSuccess, onError, listDownloadImages)
                }, { error ->
                    onError(error.message.toString())
                    uploadAll(idProfile, list, count + 1, onAllSuccess, onError, listDownloadImages)
                })
        } else {
            onAllSuccess(listDownloadImages)
        }
    }
}