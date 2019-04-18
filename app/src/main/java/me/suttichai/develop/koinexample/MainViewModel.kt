package me.suttichai.develop.koinexample

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel constructor(private val repo: CharacterRepository, private val presenter: MainPresenter) :
    ViewModel() {

    private val data: MutableLiveData<MutableList<Character>> = MutableLiveData()
    private val event: MutableLiveData<Event> = MutableLiveData()

    init {
        repo.observerData().observeForever {
            if (presenter.isLoading(it)) {
                event.postValue(Event.LOADING)
                return@observeForever
            }

            data.postValue(presenter.sort(it))
            event.postValue(Event.SHOW)
        }
    }

    fun callApi() {
        repo.getData()
    }

    fun observerData(owner: LifecycleOwner, ob: Observer<MutableList<Character>>) {
        data.observe(owner, ob)
    }

    fun observerEvent(owner: LifecycleOwner, ob: Observer<Event>) {
        event.observe(owner, ob)
    }

    override fun onCleared() {
        super.onCleared()
        repo.cleared()
    }

    enum class Event {
        LOADING,
        SHOW
    }
}