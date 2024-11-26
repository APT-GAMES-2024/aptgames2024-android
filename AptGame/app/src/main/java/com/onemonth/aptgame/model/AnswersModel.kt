package com.onemonth.aptgame.model

data class AnswersModel(
    var answer: String? = null,
    var answerName: String? = null, //질문자 닉네임
    var createdAt: Long? = null,
    val questionId: String? = null,
)
