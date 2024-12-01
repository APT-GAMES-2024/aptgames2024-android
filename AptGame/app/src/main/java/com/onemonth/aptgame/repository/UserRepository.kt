package com.onemonth.aptgame.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.onemonth.aptgame.model.UserModel
import com.onemonth.aptgame.model.user.UserData
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

    suspend fun updateUserCardData(aptCardId: String) = suspendCancellableCoroutine { continuation ->
        val transactionReference = db.collection("User").document(UserData.getUserData().deviceId.toString())

        db.runTransaction { transaction ->

            val userDataSnapshot = transaction.get(transactionReference).toObject(UserModel::class.java)

            //이미 보유하고 있으면
            if (userDataSnapshot?.aptCards?.contains(aptCardId) == true) {
                continuation.safeResume(false)
                return@runTransaction
            } else { //보유하지 않으면
                userDataSnapshot?.aptCards?.add(aptCardId)
                transaction.set(transactionReference, userDataSnapshot ?: return@runTransaction)
                continuation.safeResume(true)
                return@runTransaction
            }


        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }

    suspend fun getUserData(deviceId: String) =
        suspendCancellableCoroutine { continaution ->
            db.collection("User").document(deviceId).get()
                .addOnCompleteListener {
                    val userData = it.result.toObject(UserModel::class.java)
                    continaution.safeResume(userData ?: return@addOnCompleteListener)
                }.addOnFailureListener {
                    continaution.resumeWithException(it)
                }
        }
}