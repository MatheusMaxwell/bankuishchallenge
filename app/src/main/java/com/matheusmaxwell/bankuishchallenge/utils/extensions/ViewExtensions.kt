package com.matheusmaxwell.bankuishchallenge.utils.extensions

import android.view.View

fun View.setVisibilityForBoolean(visible: Boolean) {
    if (visible) makeVisible() else makeGone()
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}