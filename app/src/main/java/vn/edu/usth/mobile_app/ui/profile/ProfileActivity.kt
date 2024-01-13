package vn.edu.usth.mobile_app.ui.profile

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.TypedValueCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import androidx.fragment.app.FragmentStatePagerAdapter
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

    private var avatarAnimateStartPointX: Float = 0F
    private var avatarAnimateStartPointY: Float = 0F

    private var avatarSizeChangePercent = 0.25F
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

        val toolbar = binding.materialToolbarProfile
        toolbar.setNavigationOnClickListener{
            finish()
        }

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

        val tabLayout = binding.tabLayoutProfile
        val viewPager = binding.viewPagerProfile
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun updateAvatar() {
        avatarAnimateStartPointX = userAvatar.x
        avatarAnimateStartPointY = userAvatar.y

        // Calculate the new size
        val newSize = EXPAND_AVATAR_SIZE + (COLLAPSED_AVATAR_SIZE - EXPAND_AVATAR_SIZE) * avatarSizeChangePercent - userAvatar.marginEnd
        // Apply size change
        val sizeInPixels = TypedValueCompat.dpToPx(newSize, resources.displayMetrics).toInt()
        userAvatar.layoutParams.apply {
            height = sizeInPixels
            width = sizeInPixels
        }

        // Calculate final positions for collapsed state
        val finalX = (TypedValueCompat.dpToPx(collapseToolbar.width.toFloat(), resources.displayMetrics).toInt())/2 - TypedValueCompat.dpToPx(collapseToolbar.marginEnd.toFloat(), resources.displayMetrics).toInt()*2 - TypedValueCompat.dpToPx(COLLAPSED_AVATAR_SIZE.toFloat(), resources.displayMetrics).toInt()*2// Adjust for the margin of the avatar
        val finalY = TypedValueCompat.dpToPx(collapseToolbar.height.toFloat(), resources.displayMetrics).toInt()/2 - TypedValueCompat.dpToPx(collapseToolbar.marginTop.toFloat(), resources.displayMetrics).toInt() - TypedValueCompat.dpToPx(COLLAPSED_AVATAR_SIZE.toFloat(), resources.displayMetrics).toInt()

        // Calculate deltas for animation
        val deltaX = finalX - avatarAnimateStartPointX
        val deltaY = finalY - avatarAnimateStartPointY

        // Apply translation
        userAvatar.translationX = (deltaX * avatarSizeChangePercent).toFloat()
        userAvatar.translationY = deltaY * avatarSizeChangePercent

        userAvatar.scaleX = 1 - avatarSizeChangePercent + 0.1F
        userAvatar.scaleY = 1 - avatarSizeChangePercent + 0.1F

        userAvatar.requestLayout()

    }
}