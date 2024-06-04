package kr.cosine.groupfinder.domain.repository

import android.app.Activity
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

interface FirebaseRepository {

    val reference: CollectionReference

    fun addSnapshotListener(listener: EventListener<QuerySnapshot>): ListenerRegistration {
        return reference.addSnapshotListener(listener)
    }

    fun addSnapshotListener(activity: Activity, listener: EventListener<QuerySnapshot>) {
        reference.addSnapshotListener(activity, listener)
    }

    suspend fun getDocumentSnapshots(): List<DocumentSnapshot> {
        return reference.get().await().documents
    }
}