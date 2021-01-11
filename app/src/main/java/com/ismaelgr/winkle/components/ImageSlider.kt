package com.ismaelgr.winkle.components

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ismaelgr.winkle.R
import com.ismaelgr.winkle.util.GlideLoader
import com.jsibbold.zoomage.ZoomageView
import kotlinx.android.synthetic.main.image_slider_view.view.*
import kotlin.math.roundToInt

class ImageSlider(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private lateinit var slots: LinearLayout
    private lateinit var viewPager: ViewPager
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private var userActionWithImage: (String) -> Unit = {}

    private val unselectedDot = android.R.color.white
    private val selectedDot = R.color.colorPrimaryDark
    private val dotsPanelAlpha = 0.6f

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.image_slider_view, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        slots = image_slider_dots
        viewPager = image_slider_viewpager
        imagePagerAdapter = ImagePagerAdapter({ userActionWithImage(it) }, context)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                refresh(position)
            }

            override fun onPageSelected(position: Int) {
                refresh(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })

        slots.background.alpha = (255 * dotsPanelAlpha).roundToInt()

        viewPager.adapter = imagePagerAdapter
    }

    fun addImage(vararg image: String) {
        imagePagerAdapter.addImage(*image)
        setDots()
    }

    private fun setDots() {
        // quitamos todos los puntos
        slots.removeAllViews()

        imagePagerAdapter.getImages().forEachIndexed { i, _ ->
            slots.addView(newDot().apply { setOnClickListener { viewPager.setCurrentItem(i, true) } }, i)
        }

    }

    private fun refresh(positionShownDot: Int) {
        slots.children.forEachIndexed { index, view ->
            view.background.setTint(
                ContextCompat.getColor(
                    context,
                    if (positionShownDot == index) selectedDot else unselectedDot
                )
            )
        }
    }

    private fun newDot(): View = View(context).apply {
        background = resources.getDrawable(R.drawable.image_slider_dot, context.theme)
        layoutParams = LayoutParams(
            context.resources.getDimension(R.dimen.image_slider_dot_size).roundToInt(),
            context.resources.getDimension(R.dimen.image_slider_dot_size).roundToInt()
        ).apply {
            marginEnd =
                context.resources.getDimension(R.dimen.image_slider_dot_marginEnd).roundToInt()
            alpha = 1f
        }
    }

    fun setOnImageClick(onImageClick: (String) -> Unit) {
        userActionWithImage = onImageClick
    }

    class ImagePagerAdapter(
        private val userActionClick: (String) -> Unit,
        private val context: Context
    ) : PagerAdapter() {

        private var images: ArrayList<String> = arrayListOf()

        override fun getCount(): Int = images.size

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            notifyDataSetChanged()
        }

        fun getImages() = images

        fun reset() {
            images.clear()
            notifyDataSetChanged()
        }

        fun addImage(vararg image: String) {
            images.addAll(image)
            notifyDataSetChanged()
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean =
            view == `object` as ImageView

        override fun instantiateItem(container: ViewGroup, position: Int): ImageView {
            val imageView = ImageView(context)
            if (position < images.size) {
                val image = images[position]
                imageView.run {
                    setOnClickListener { userActionClick(image) }
                    adjustViewBounds = true
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                GlideLoader.load(imageView, image)
                container.addView(imageView)
            }
            return imageView
        }
    }
}