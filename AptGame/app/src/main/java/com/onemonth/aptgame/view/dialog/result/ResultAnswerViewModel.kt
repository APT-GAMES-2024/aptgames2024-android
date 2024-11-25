package com.onemonth.aptgame.view.dialog.result

import androidx.lifecycle.viewModelScope
import com.onemonth.aptgame.model.Answer
import com.onemonth.aptgame.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ResultAnswerViewModel @Inject constructor() : BaseViewModel() {

    val answerUiState: StateFlow<ResultAnswerUiState> = flow {
        emit(getTempAnswers())
    }
        .asResult()
        .map { result ->
            when (result) {
                is Result.Loading -> ResultAnswerUiState.Loading
                is Result.Success -> ResultAnswerUiState.Success(result.data)
                is Result.Error -> ResultAnswerUiState.Error(result.throwable)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ResultAnswerUiState.Loading,
        )

    // 임시 표기용 데이터 목록. 프로덕트에서는 실제 DB에서 로드 필요.
    private fun getTempAnswers(): List<Answer> = listOf(
        Answer(1, "NickName", "A Text Of Answer1"),
        Answer(2, "NickName", "A Text Of Answer2"),
        Answer(3, "NickName", "A Text Of Answer3"),
        Answer(4, "NickName", "A Text Of Answer4"),
        Answer(5, "NickName", "A Text Of Answer5"),
        Answer(6, "NickName", "A Text Of Answer6"),
        Answer(7, "NickName", "A Text Of Answer7"),
    )
}

sealed interface ResultAnswerUiState {
    data object Loading : ResultAnswerUiState
    data class Success(val answers: List<Answer>) : ResultAnswerUiState
    data class Error(val throwable: Throwable) : ResultAnswerUiState
}
