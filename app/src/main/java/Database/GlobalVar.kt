package Database

import Model.Animal

class GlobalVar {
    companion object{
        val STORAGE_PERMISSION_CODE: Int = 100
        val listDataAnimal = ArrayList<Animal>()
    }
}