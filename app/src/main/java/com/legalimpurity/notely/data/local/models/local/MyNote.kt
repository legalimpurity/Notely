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
data class MyNote(private var noteTitle: String, private var noteGist: String) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var lastModifiedTime = Date()

    fun setNoteTitle(noteTitle: String)
    {
        this.noteTitle = noteTitle
        lastModifiedTime = Date()
    }

    fun setNoteGist(noteGist:String)
    {
        this.noteGist = noteGist
        lastModifiedTime = Date()
    }

    fun getNoteTitle() = noteTitle
    fun getNoteGist() = noteGist

    var fav = false
    var hearted = false

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
        id = parcel.readInt()
        fav = parcel.readByte() != 0.toByte()
        hearted = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(noteTitle)
        parcel.writeString(noteGist)
        parcel.writeInt(id)
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