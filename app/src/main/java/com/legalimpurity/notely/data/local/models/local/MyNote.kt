package com.legalimpurity.notely.data.local.models.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by rkhanna on 26/1/18.
 */
@Entity(tableName = "MyNotes")
class MyNote() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var lastModifiedTime: Date = Date()

    var noteTitle: String = ""
    var noteGist: String = ""
    var fav = false
    var hearted = false

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        noteTitle = parcel.readString()
        noteGist = parcel.readString()
        fav = parcel.readByte() != 0.toByte()
        hearted = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(noteTitle)
        parcel.writeString(noteGist)
        parcel.writeByte(if (fav) 1 else 0)
        parcel.writeByte(if (hearted) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyNote> {
        override fun createFromParcel(parcel: Parcel): MyNote {
            return MyNote(parcel)
        }

        override fun newArray(size: Int): Array<MyNote?> {
            return arrayOfNulls(size)
        }
    }

}