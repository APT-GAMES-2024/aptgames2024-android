package com.onemonth.aptgame.model

data class QuestionsModel(
    var question: String? = null,
    var createdAt: Long? = null,
    var createdUser: String? = null,
    var answers: List<String>? = null,
    var answerCount: Long? = null,
)