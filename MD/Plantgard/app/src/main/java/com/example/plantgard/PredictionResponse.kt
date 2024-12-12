package com.example.plantgard.network

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Represents the prediction response from the API.
 * Contains a message, errors, and data with information about the plant type and disease.
 */
data class PredictionResponse(
    val message: String,
    val errors: ErrorResponse?,
    val data: Data
)

/**
 * Error details returned from the API.
 */
data class ErrorResponse(
    val code: Int,
    val message: String
)

/**
 * Data related to plant type and disease information.
 */
data class Data(
    val plant_type: String,
    val disease: Disease,
    val user: User,
    val createdAt: String,
    val updatedAt: String
) {
    /**
     * Formats the 'createdAt' timestamp to a more user-friendly format.
     *
     * @return Formatted date string
     */
    fun getFormattedCreatedAt(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = dateFormat.parse(createdAt)
        val formattedDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return formattedDate.format(date ?: Date())
    }
}

/**
 * Disease details associated with a plant.
 */
data class Disease(
    val type: String,
    val description: String,
    val treatment: String,
    val image: String = "", // Default value for image
    val prevention: String
)

/**
 * User information related to the prediction.
 * Implemented Parcelable for performance when passing data in intents.
 */
data class User(
    val uid: String,
    val name: String,
    val email: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(name)
        parcel.writeString(email)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
