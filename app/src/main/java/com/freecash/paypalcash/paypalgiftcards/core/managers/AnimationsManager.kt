package com.freecash.paypalcash.paypalgiftcards.core.managers

import android.animation.Animator
import android.view.View

class AnimationsManager {

    companion object {
        var SCALE_MIN = 0.9f
        var SCALE_MAX = 1f
        var DURATION = 150L
    }

    var isAnimationPlaying: Boolean = false

    fun btnClick(view: View, onStart: () -> Unit, onFinish: () -> Unit) {
        if (!isAnimationPlaying) {
            isAnimationPlaying = true
            scale(view, SCALE_MIN, DURATION, { onStart() }, {
                scale(view, SCALE_MAX, DURATION, {}, { onFinish(); isAnimationPlaying = false })
            })
        }
    }

    fun scale(view: View, value: Float, duration: Long, onStart: () -> Unit, onFinish: () -> Unit) {
        view.animate().scaleX(value).scaleY(value).setDuration(duration).setListener(listener(onStart, onFinish)).start()
    }

    fun listener(onStart: () -> Unit, onFinish: () -> Unit): Animator.AnimatorListener {
        return object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) { onFinish() }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) { onStart() }

        }
    }
}
