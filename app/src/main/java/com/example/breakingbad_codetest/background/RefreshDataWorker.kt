package com.example.breakingbad_codetest.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.breakingbad_codetest.repository.CharactersRepository
import timber.log.Timber

/**
 * WorkManager automatically calls Worker.doWork() on a background thread.
 * Periodically syncing application data with a server
 */
class RefreshDataWorker(private val charactersRepository: CharactersRepository, appContext: Context, params:WorkerParameters): CoroutineWorker(appContext, params) {

    companion object{
        const val WORK_NAME="com.example.breakingbad_codetest.background.RefreshDataWorker"
    }
    override suspend fun doWork(): Result {
        /**
         * Sync the backend API data with local database even if user is not using the app or device restarts
         */
        try{
            charactersRepository.refreshCharacters();
            Timber.d("WorkManager: sync in progress")
        }
        catch (e:Exception){
            Timber.e("WorkManager error: ${e.localizedMessage}")
            return Result.retry()
        }
        return Result.success()
    }
}