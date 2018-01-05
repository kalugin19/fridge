package ru.kalugin19.fridge.android.pub.v2.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Сущность - продукт
 *
 * @author Kalugin Valerij
 */
class Product : Parcelable {
    var createdDate: Long = 0
    var name: String? = null
        set(value) {
            field = value
            if (name != null) {
                this.upperName = value?.toUpperCase()
            }
        }

    var upperName: String? = null
        private set
    var photo: String? = null
    var spoiledDate: Long = 0
    var key: String? = null
    var photoName: String? = null
    var date: String? = null


    @Exclude
    var typeMember: String? = null

    @Exclude
    var ownerEmail: String? = null



    constructor()

    constructor(name: String?, createdDate: Long, spoiledDate: Long, photo: String) {
        this.name = name
        if (name != null) {
            this.upperName = name.toUpperCase()
        }
        this.createdDate = createdDate
        this.spoiledDate = spoiledDate
        this.photo = photo
    }

    constructor(name: String, date: String) {
        this.name = name
        this.upperName = name.toUpperCase()
        this.date = date

    }

    fun getFormattedSpoiledDate(): String{
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return formatter.format(Date(spoiledDate))
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(createdDate)
        dest.writeString(name)
        dest.writeString(upperName)
        dest.writeString(photo)
        dest.writeLong(spoiledDate)
        dest.writeString(key)
        dest.writeString(photoName)
        dest.writeString(date)
        dest.writeString(typeMember)
        dest.writeString(ownerEmail)
    }

    protected constructor(`in`: Parcel) {
        createdDate = `in`.readLong()
        name = `in`.readString()
        upperName = `in`.readString()
        photo = `in`.readString()
        spoiledDate = `in`.readLong()
        key = `in`.readString()
        photoName = `in`.readString()
        date = `in`.readString()
        typeMember = `in`.readString()
        ownerEmail = `in`.readString()
    }

    companion object {
        val CREATOR: Parcelable.Creator<Product> = object : Parcelable.Creator<Product> {
            override fun createFromParcel(`in`: Parcel): Product {
                return Product(`in`)
            }

            override fun newArray(size: Int): Array<Product?> {
                return arrayOfNulls(size)
            }
        }
    }
}
