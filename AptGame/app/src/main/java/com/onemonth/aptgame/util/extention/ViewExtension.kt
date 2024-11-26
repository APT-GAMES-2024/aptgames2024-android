package com.onemonth.aptgame.util.extention

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

fun RecyclerView.disableItemAnimator() {
    (this.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
}


fun View.setOnSingleClickListener(onClickAction: (View) -> Unit) {
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    // 클릭 상태를 나타내는 변수
    var isClickable = true

    // 코루틴을 통해 클릭 이벤트를 처리합니다.
    coroutineScope.launch {
        // callbackFlow를 사용하여 클릭 이벤트를 받아옵니다.
        callbackFlow {
            val listener = View.OnClickListener {
                // 클릭 가능한 상태인 경우에만 값을 전송하여 클릭 이벤트를 전달합니다.
                if (isClickable) {
                    this.trySend(it).isSuccess
                    // 클릭 이벤트가 전달되면 클릭 가능한 상태를 false로 변경합니다.
                    isClickable = false
                }
            }
            setOnClickListener(listener)

            // Flow 종료 시 클릭 리스너를 제거합니다.
            awaitClose {
                setOnClickListener(null)
            }
        }.collect { clickedView ->
            // 클릭 이벤트가 들어오면 지연을 위한 코루틴을 시작합니다.
            launch {
                // 클릭 이벤트 처리
                onClickAction(clickedView)

                // 1초 동안 클릭 방지
                delay(1000)

                // 클릭 가능한 상태를 다시 true로 변경합니다.
                isClickable = true
            }
        }
    }
}