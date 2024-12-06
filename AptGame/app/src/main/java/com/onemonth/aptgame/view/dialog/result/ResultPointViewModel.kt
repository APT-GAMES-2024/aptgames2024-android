package com.onemonth.aptgame.view.dialog.result

import androidx.lifecycle.viewModelScope
import com.onemonth.aptgame.model.ApartmentType
import com.onemonth.aptgame.model.Reward
import com.onemonth.aptgame.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ResultPointViewModel @Inject constructor() : BaseViewModel() {

    val reward: StateFlow<Reward> = flow {
        emit(calculateReward())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = Reward(points = 0),
    )

    private fun calculateReward(): Reward {
        val points = Random.nextInt(MIN_POINTS, MAX_POINTS + 1)
        val apartmentType = if (Random.nextFloat() <= APARTMENT_PROBABILITY) {
            ApartmentType.entries.random()
        } else {
            null
        }

        return Reward(
            points = points,
            apartmentType = apartmentType,
        )
    }

    companion object {
        private const val MIN_POINTS = 1
        private const val MAX_POINTS = 100
        private const val APARTMENT_PROBABILITY = 0.3f
    }
}
