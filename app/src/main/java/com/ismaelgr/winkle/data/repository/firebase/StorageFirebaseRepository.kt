package com.ismaelgr.winkle.data.repository.firebase

import android.net.Uri
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.google.firebase.storage.FirebaseStorage
import com.ismaelgr.winkle.data.repository.needs.StorageRepositoryNeed
import com.ismaelgr.winkle.util.Mapper
import io.reactivex.rxjava3.core.Single
import java.io.File

class StorageFirebaseRepository : StorageRepositoryNeed {

    override fun uploadFile(idProfile: String, localFilePath: String): Single<String> =
        Single.create { emitter ->
            val reference = FirebaseStorage.getInstance().getReference(idProfile)
            val file = File(localFilePath)
            if (file.exists()) {
                val fileUri = Uri.fromFile(file)
                val imageRef =
                    reference.child("images/${fileUri.lastPathSegment}")

                val uploadTask = imageRef.putFile(fileUri)

                uploadTask
                    .continueWithTask { imageRef.downloadUrl }
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            emitter.onSuccess(it.result.toString())
                        } else {
                            emitter.onError(Error("Cannot upload file ${file.name}. ${it.exception?.message.toString()}"))
                        }
                    }
            } else {
                emitter.onError(Error("File $localFilePath not found"))
            }
        }
}