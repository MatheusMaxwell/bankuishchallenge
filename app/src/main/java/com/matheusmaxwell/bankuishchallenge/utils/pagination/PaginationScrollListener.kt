package com.matheusmaxwell.bankuishchallenge.utils.pagination

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener
/**
 * Supporting only LinearLayoutManager for now.
 *
 * @param layoutManager
 */
    (var layoutManager: LinearLayoutManager?) : RecyclerView.OnScrollListener() {

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        layoutManager?.let {
            val visibleItemCount = it.childCount
            val totalItemCount = it.itemCount
            val firstVisibleItemPosition = it.findFirstVisibleItemPosition()

            if (!isLoading() && !isLastPage()) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    loadMoreItems()
                }
            }
        }


    }
    abstract fun loadMoreItems()
}