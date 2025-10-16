package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DieViewModel: ViewModel() {

    private var dieSides: Int = 6
    private val currentRoll : MutableLiveData<Int> = MutableLiveData()

    fun getSides(): Int {
        return dieSides
    }

    fun throwDie (){
        currentRoll.value = (Random.nextInt(dieSides) + 1)
    }

    fun getRolledNumber(): LiveData<Int> {
        return currentRoll
    }

}

