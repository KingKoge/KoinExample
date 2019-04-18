package me.suttichai.develop.koinexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterRepository(private val api: ApiService, private val db: DatabaseService) {
    private var composite: CompositeDisposable? = null
    private val data: MutableLiveData<MutableList<Character>?> = MutableLiveData()

    init {
        composite = CompositeDisposable()
    }

    fun observerData(): LiveData<MutableList<Character>?> {
        return data
    }

    fun cleared() {
        composite?.clear()
    }

    fun getData() {
        composite?.add(
            db.characterDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("Repository", "get all character: $it")
                    handlerLocalResource(it)
                }, {
                    Log.d("Repository", "get all character: $it")
                    handlerLocalResource(null)
                })
        )
    }

    private fun handlerLocalResource(characters: MutableList<Character>?) {
        if (characters.isNullOrEmpty()) {
            data.postValue(null)
            callServiceGetCharacters()
        } else {
            data.postValue(characters)
            //todo service check last update
        }
    }

    private fun callServiceGetCharacters() {
        val request = api.create().getCharacters()

        composite?.add(
            request.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    handlerApiResponse(it)
                }, {
                    handlerApiError(it)
                })
        )
    }

    private fun handlerApiResponse(result: ApiResult<MutableList<Character>>) {
        if (result.meta.code == 200) {
            composite?.add(
                Completable.fromCallable {
                    db.characterDao().insertAll(result.data)
                }.subscribeOn(Schedulers.io())
                    .subscribe {
                        data.postValue(result.data)
                    }
            )
        } else {
            handlerApiError(Throwable("Error Code: ${result.meta.code}"))
        }
    }

    private fun handlerApiError(error: Throwable) {
        Log.e("Repository", error.message, error)
        data.postValue(mutableListOf())
    }
}