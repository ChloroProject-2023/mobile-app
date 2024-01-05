package vn.edu.usth.mobile_app.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewData (
    var id: Int = -1,
    var authorId: Int = -1,
    var authorName: String = "",
    var stars: Int = 0,
    var date: Long = 1,
    var comment: String = ""
)