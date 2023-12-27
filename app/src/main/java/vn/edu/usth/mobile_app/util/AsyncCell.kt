package vn.edu.usth.mobile_app.util

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.asynclayoutinflater.view.AsyncLayoutInflater

/**
 * AsyncCell is a FrameLayout that can be inflated asynchronously.
 * This class is abstract because data binding is different for each layout. Having a variable of type ViewBinding wont work.
 */
abstract class AsyncCell(
    context: Context,
    private val layoutId: Int,
): FrameLayout(context, null, 0, 0){
    private var inflated = false
    private lateinit var bindingFunc: AsyncCell.()->Unit

    init {
        layoutParams = LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    fun inflate() {
        AsyncLayoutInflater(context).inflate(layoutId, this) { view, _, _ ->
            inflated = true
            addView(createDataBindingView(view))
            bindingFunc()
        }
    }

    fun bindWhenInflated(bindFunc: AsyncCell.()->Unit) {
        if (inflated){
            bindFunc()
        }
        else {
            bindingFunc = bindFunc
        }
    }

    abstract fun createDataBindingView(view: View): View?
}