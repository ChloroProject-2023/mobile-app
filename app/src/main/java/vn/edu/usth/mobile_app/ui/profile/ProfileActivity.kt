package vn.edu.usth.mobile_app.ui.profile

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.TypedValueCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayoutMediator
import vn.edu.usth.mobile_app.databinding.ActivityUserProfileBinding
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan


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
//            Log.d("Offest", verticalOffset.toString())
//            Log.d("ScrollRange", appBarLayout.totalScrollRange.toString())

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
        val viewPagerAdapter = ViewPagerAdapter(this)

        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Information"
                1 -> tab.text = "Profile"
                2 -> tab.text = "Models"
            }
        }.attach()
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

        val screenWidth = resources.displayMetrics.widthPixels
        val screenHeight = resources.displayMetrics.heightPixels

        val finalX = screenWidth/2 - TypedValueCompat.dpToPx(COLLAPSED_AVATAR_SIZE.toFloat(), resources.displayMetrics).toInt()/2 -TypedValueCompat.dpToPx(collapseToolbar.marginEnd.toFloat(), resources.displayMetrics).toInt()
        val finalY = screenHeight/2 - TypedValueCompat.dpToPx(COLLAPSED_AVATAR_SIZE.toFloat(), resources.displayMetrics).toInt()/2 - TypedValueCompat.dpToPx(collapseToolbar.marginTop.toFloat(), resources.displayMetrics).toInt()

        val deltaX = finalX * tan(avatarSizeChangePercent * 90 * Math.PI / 180).toFloat()
        var deltaY = finalY * cos(avatarSizeChangePercent *90 *Math.PI/180).toFloat() * sin(avatarSizeChangePercent *90 *Math.PI/180).toFloat()
        // Correction screen height
        deltaY -= deltaY * (screenHeight - 2550) / 3000 * avatarSizeChangePercent * 0.5F

//        Log.d("ScreenHeight", screenHeight.toString())
//        if (screenHeight > 3000) {
//            deltaY *= sin(avatarSizeChangePercent * 90 * Math.PI / 180).toFloat()
//        }

        // Apply translation
        userAvatar.translationX = (deltaX * avatarSizeChangePercent).toFloat()
        userAvatar.translationY = deltaY * avatarSizeChangePercent

        userAvatar.scaleX = 1 - avatarSizeChangePercent + 0.1F
        userAvatar.scaleY = 1 - avatarSizeChangePercent + 0.1F

        userAvatar.requestLayout()

    }
}