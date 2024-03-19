package com.abloom.mery.presentation.common.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import com.abloom.mery.R
import com.abloom.mery.presentation.common.util.dp
import kotlin.properties.Delegates.observable

class MeryAppBar(
    context: Context,
    attrs: AttributeSet,
) : ConstraintLayout(context, attrs) {

    private var navigation: View? = null
    private var title: View? = null
    private var action: View? = null

    var isActionEnabled: Boolean by observable(true) { _, _, newValue ->
        action?.isEnabled = newValue
    }

    var onNavigationClickListener: OnClickListener? by observable(null) { _, _, newValue ->
        navigation?.setOnClickListener(newValue)
    }

    var onActionClickListener: OnClickListener? by observable(null) { _, _, newValue ->
        action?.setOnClickListener(newValue)
    }

    init {
        setupView()
        setupChildViews(attrs)
        setupConstraint()
    }

    private fun setupView() {
        minHeight = APP_BAR_HEIGHT
        maxHeight = APP_BAR_HEIGHT
        setPadding(APP_BAR_HORIZONTAL_PADDING, 0, APP_BAR_HORIZONTAL_PADDING, 0)
    }

    private fun setupChildViews(attrs: AttributeSet) {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.MeryAppBar,
            0,
            0
        ).use { typedArray ->
            setupNavigation(typedArray)

            title = typedArray.getText(R.styleable.MeryAppBar_title)?.let { createTitleView(it) }

            action = typedArray.getText(R.styleable.MeryAppBar_action)?.let { createActionView(it) }
        }
        addView(navigation)
        addView(title)
        addView(action)
    }

    private fun setupNavigation(typedArray: TypedArray) {
        val navigationIcon = typedArray.getDrawable(R.styleable.MeryAppBar_navigationIcon)
        val navigationText = typedArray.getText(R.styleable.MeryAppBar_navigationText)
        require(!(navigationIcon != null && navigationText != null)) { "MeryAppBar의 navigationIcon과 navigationText 둘 중 하나만 사용할 수 있습니다." }
        navigation = navigationIcon?.let { createNavigationIconView(it) }
            ?: navigationText?.let { createNavigationTextView(it) }
    }

    private fun createNavigationIconView(drawable: Drawable) = ImageView(context).apply {
        id = generateViewId()
        layoutParams = LayoutParams(NAVIGATION_ICON_BUTTON_SIZE, NAVIGATION_ICON_BUTTON_SIZE)
        setImageDrawable(drawable)
    }

    private fun createNavigationTextView(text: CharSequence) = TextView(context).apply {
        id = generateViewId()
        this.text = text
        setTextAppearance(R.style.callout)
        typeface = ResourcesCompat.getFont(context, R.font.nanum_square_neo_bold)
        val color = ContextCompat.getColor(context, R.color.primary_08)
        setTextColor(color)
        setPadding(
            BUTTON_HORIZONTAL_PADDING,
            BUTTON_VERTICAL_PADDING,
            BUTTON_HORIZONTAL_PADDING,
            BUTTON_VERTICAL_PADDING
        )
    }

    private fun createTitleView(text: CharSequence) = TextView(context).apply {
        id = generateViewId()
        this.text = text
        setTextAppearance(R.style.body)
        typeface = ResourcesCompat.getFont(context, R.font.nanum_square_neo_bold)
        setTextColor(Color.BLACK)
    }

    private fun createActionView(text: CharSequence) = TextView(context).apply {
        id = generateViewId()
        this.text = text
        setTextAppearance(R.style.callout)
        typeface = ResourcesCompat.getFont(context, R.font.nanum_square_neo_bold)
        val colors = ContextCompat.getColorStateList(context, R.color.text_button)
        setTextColor(colors)
        setPadding(
            BUTTON_HORIZONTAL_PADDING,
            BUTTON_VERTICAL_PADDING,
            BUTTON_HORIZONTAL_PADDING,
            BUTTON_VERTICAL_PADDING
        )
    }

    private fun setupConstraint() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)

        constraintSet.setupNavigationConstraint()
        constraintSet.setupTitleConstraint()
        constraintSet.setupActionConstraint()

        constraintSet.applyTo(this)
    }

    private fun ConstraintSet.setupNavigationConstraint() {
        val navigation = navigation ?: return
        constrainWidth(navigation.id, ConstraintSet.WRAP_CONTENT)
        constrainHeight(navigation.id, ConstraintSet.WRAP_CONTENT)
        connect(navigation.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        connect(navigation.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        connect(navigation.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
    }

    private fun ConstraintSet.setupTitleConstraint() {
        val title = title ?: return
        constrainWidth(title.id, ConstraintSet.WRAP_CONTENT)
        constrainHeight(title.id, ConstraintSet.WRAP_CONTENT)
        connect(title.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        connect(title.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        connect(title.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        connect(title.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
    }

    private fun ConstraintSet.setupActionConstraint() {
        val action = action ?: return
        constrainWidth(action.id, ConstraintSet.WRAP_CONTENT)
        constrainHeight(action.id, ConstraintSet.WRAP_CONTENT)
        connect(action.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        connect(action.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        connect(action.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
    }

    companion object {

        private val APP_BAR_HEIGHT = 56.dp
        private val APP_BAR_HORIZONTAL_PADDING = 16.dp
        private val NAVIGATION_ICON_BUTTON_SIZE = 22.dp
        private val BUTTON_HORIZONTAL_PADDING = 4.dp
        private val BUTTON_VERTICAL_PADDING = 5.dp
    }
}

@BindingAdapter("app:isActionEnabled")
fun MeryAppBar.setActionEnabled(enabled: Boolean) {
    isActionEnabled = enabled
}

@BindingAdapter("app:onNavigationClick")
fun MeryAppBar.setOnNavigationClick(onClickListener: OnClickListener) {
    onNavigationClickListener = onClickListener
}

@BindingAdapter("app:onActionClick")
fun MeryAppBar.setOnActionClick(onClickListener: OnClickListener) {
    onActionClickListener = onClickListener
}
