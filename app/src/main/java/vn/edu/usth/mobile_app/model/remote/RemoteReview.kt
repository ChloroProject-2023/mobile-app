package vn.edu.usth.mobile_app.model.remote

import vn.edu.usth.mobile_app.model.ReviewData
import java.text.SimpleDateFormat
import java.util.Locale

data class RemoteReview (
    val id: Int,
    val stars: Float,
    val comment: String,
    val user_id: Int,
    val username: String,
    val model_id: Int,
    val createTime: String,
)

fun RemoteReview.toReviewData(): ReviewData {
    // 2024-01-16T07:51:17.549+00:00
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US)
    val timestamp = format.parse(createTime)?.time
    return ReviewData(
        id = id,
        authorId = user_id,
        authorName = username,
        stars = stars.toInt(),
        date = timestamp?: 0,
        comment = comment,
    )
}