package Model

import android.os.Parcel
import android.os.Parcelable

data class Animal(
    var name:String?,
    var species:String?,
    var age:Int?,

): Parcelable{
    constructor(parcel: Parcel):this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
    ){}

    var imageUri: String = ""

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(species)
        parcel.writeValue(age)

    }

    companion object CREATOR : Parcelable.Creator<Animal>{
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }
}
