package com.onemonth.aptgame.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.onemonth.aptgame.model.UserModel
import com.onemonth.aptgame.util.extention.safeResume
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class UserRepository @Inject constructor(private val db: FirebaseFirestore) {

    suspend fun createUser(user: UserModel) =
        suspendCancellableCoroutine { continaution ->
            db.collection("User").document(user.deviceId ?: return@suspendCancellableCoroutine)
                .set(user)
                .addOnSuccessListener {
                    continaution.resume(true)
                }.addOnFailureListener {
                    continaution.resumeWithException(it)
                }
        }

    suspend fun isExistUserData(deviceId: String) = suspendCancellableCoroutine { continuation ->
            val collection = db.collection("User")
            collection.document(deviceId).get().addOnSuccessListener {

                continuation.safeResume(it.exists())
            }.addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }
        }
}