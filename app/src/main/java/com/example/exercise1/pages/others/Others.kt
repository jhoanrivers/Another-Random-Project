package com.example.exercise1.pages.others

interface Others {

    interface View {

        fun showLoading(value: Boolean)

        fun showData(value: String)

        fun showError(errorMessage: String)

    }


    interface Presenter {
        fun attachView(view: Others.View)
        fun loadRandomImage()
    }
}