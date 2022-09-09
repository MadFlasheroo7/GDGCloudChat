package xyz.jayeshseth.gdgcloudchat.util.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import xyz.jayeshseth.gdgcloudchat.util.Constants.IS_CURRENT_USER
import xyz.jayeshseth.gdgcloudchat.util.Constants.MESSAGE
import xyz.jayeshseth.gdgcloudchat.util.Constants.MESSAGES
import xyz.jayeshseth.gdgcloudchat.util.Constants.SENT_BY
import xyz.jayeshseth.gdgcloudchat.util.Constants.SENT_ON

//import com.google.firebase.database.collection.DatabaseReference


class ChatViewModel : ViewModel() {
    init {
        receiveMessage()
    }

    private val _message = MutableLiveData("")
    val message: LiveData<String> = _message

    private val _messages =
        MutableLiveData(emptyList<Map<String, Any>>().toMutableList())
    val messages: LiveData<MutableList<Map<String, Any>>> = _messages

    /**
     * updates the message as user types
     */
    fun updateMessage(message: String) {
        _message.value = message
    }

    fun sendMessage() {
        val message: String =
            _message.value ?: throw IllegalArgumentException("empty message")
        if (message.isNotEmpty()) {
            Log.d("msg sent:", "success")
            Firebase.firestore.collection(MESSAGES).document().set(
                hashMapOf(
                    MESSAGE to message,
                    SENT_BY to Firebase.auth.currentUser?.uid,
                    SENT_ON to System.currentTimeMillis()
                )
            ).addOnSuccessListener {
                _message.value = ""
            }
        }
    }

    private fun receiveMessage() {
        Firebase.firestore.collection(MESSAGES)
            .orderBy(SENT_ON)
            .addSnapshotListener { value, e ->
                Log.d("msg received:", "$e")

                val list = emptyList<Map<String, Any>>().toMutableList()

                if (value != null) {
                    for (doc in value) {
                        val data = doc.data
                        data[IS_CURRENT_USER] =
                            Firebase.auth.currentUser?.uid.toString() == data[SENT_BY].toString()
                        list.add(data)
                    }
                }
                updateMessages(list)
            }
    }

    private fun updateMessages(list: MutableList<Map<String, Any>>) {
        _messages.value = list.asReversed()
    }
}