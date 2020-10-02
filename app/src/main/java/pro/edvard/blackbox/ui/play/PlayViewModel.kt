package pro.edvard.blackbox.ui.play

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import pro.edvard.blackbox.model.Level


class PlayViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _levels: MutableLiveData<MutableList<Level>> = MutableLiveData()

    private val _currentLevel: MutableLiveData<Level> = MutableLiveData()
    val currentLevel: LiveData<Level>
        get() = _currentLevel

    private val _isLastLevel: MutableLiveData<Boolean> = MutableLiveData()
    val isLastLevel: LiveData<Boolean>
        get() = _isLastLevel

    fun getLevels() {
        _levels.value = Level.gelAllLevels()
    }

    fun getCurrentLevel(levelNumber: Int) {
        if (levelNumber != _levels.value!!.size) {
            _currentLevel.value = _levels.value!![levelNumber]
        }
    }

    fun goToNextLevel() {
        if (currentLevel.value?.number!! < _levels.value!!.size) {
            _currentLevel.value = _levels.value!![_currentLevel.value!!.number]
        } else {
            _isLastLevel.value = true
        }
    }

}