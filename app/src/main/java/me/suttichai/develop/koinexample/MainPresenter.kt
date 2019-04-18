package me.suttichai.develop.koinexample

class MainPresenter() {

    fun sort(it: MutableList<Character>?): MutableList<Character> {
        val data: MutableList<Character> = mutableListOf()

        it?.forEach {
            data.add(it)
        }

        return data
    }

    fun isLoading(it: MutableList<Character>?): Boolean {
        return it == null
    }
}