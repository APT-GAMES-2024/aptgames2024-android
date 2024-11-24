package com.onemonth.aptgame.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.onemonth.aptgame.model.ExampleModel
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ExampleFirebaseRepository @Inject constructor(private val db: FirebaseFirestore) {

    suspend fun createUser(user: ExampleModel) =
        suspendCancellableCoroutine { continaution ->
            db.collection("User").document(user.name ?: return@suspendCancellableCoroutine)
                .set(user)
                .addOnSuccessListener {
                    continaution.resume(true)
                }.addOnFailureListener {
                    continaution.resumeWithException(it)
                }
        }
}