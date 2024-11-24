package com.onemonth.aptgame.model

data class RuleModel(
    var rules: List<Rule>? = null
) {
    data class Rule(
        var title: String? = null,
        var content: String? = null
    )
}
