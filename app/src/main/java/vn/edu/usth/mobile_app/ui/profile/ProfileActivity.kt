package vn.edu.usth.mobile_app.ui.profile

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.TypedValueCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import vn.edu.usth.mobile_app.databinding.ActivityUserProfileBinding
import kotlin.math.abs


class ProfileActivity : AppCompatActivity() {
    private var _binding: ActivityUserProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var collapseToolbar: CollapsingToolbarLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var userAvatar: ImageView
    private lateinit var username: TextView
    private lateinit var joinDate: TextView

    private val EXPAND_AVATAR_SIZE = 128
    private val COLLAPSED_AVATAR_SIZE = 48

    private var avatarAnimateStartPointY: Float = 0F
    private var avatarSizeChangePercent = 0F
    private var lastSizeChangeValue = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        collapseToolbar = binding.collapseToolbarProfile
        appBarLayout = binding.appBarProfile
        userAvatar = binding.imageViewProfileAvatar
        username = binding.textViewProfileUsername
        joinDate = binding.textViewProfileJoin

        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            Log.d("Offest", verticalOffset.toString())
            Log.d("ScrollRange", appBarLayout.totalScrollRange.toString())

            val scrollRemainFraction = 1 - abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())
            avatarAnimateStartPointY = userAvatar.y
            avatarSizeChangePercent = (1 - (COLLAPSED_AVATAR_SIZE.toFloat() / EXPAND_AVATAR_SIZE)) * (1 - scrollRemainFraction)
            Log.d("ScrollRemain", scrollRemainFraction.toString())
            Log.d("AvatarSizeChange", avatarSizeChangePercent.toString())

            // Only update avatar when percent change. Else this will cause a calculation loop
            // Because updateAvatar() causes a layout change, which triggers this listener again
            if (lastSizeChangeValue != avatarSizeChangePercent) {
                lastSizeChangeValue = avatarSizeChangePercent
                updateAvatar()
            }

            // Fading effect
            username.alpha = scrollRemainFraction
            joinDate.alpha = scrollRemainFraction
        }
    }

    private fun updateAvatar() {
        // Reposition
        val deltaX = appBarLayout.width - (userAvatar.width + userAvatar.x)
        val deltaY = userAvatar.y * -1
        userAvatar.translationX = TypedValueCompat.dpToPx(deltaX * avatarSizeChangePercent, resources.displayMetrics)
//        userAvatar.translationY = TypedValueCompat.dpToPx(deltaY * avatarSizeChangePercent, resources.displayMetrics)

        // Resize
        userAvatar.layoutParams.apply {
            val sizeChangeValue = EXPAND_AVATAR_SIZE * (1- avatarSizeChangePercent)
            height = TypedValueCompat.dpToPx(sizeChangeValue, resources.displayMetrics).toInt()
            width = TypedValueCompat.dpToPx(sizeChangeValue, resources.displayMetrics).toInt()
        }
        userAvatar.requestLayout()
    }
}
