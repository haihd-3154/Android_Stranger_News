package com.example.strangernews.ui.view.setting

import android.app.Notification
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.work.*
import com.example.strangernews.base.BaseFragment
import com.example.strangernews.data.model.Article
import com.example.strangernews.databinding.FragmentSettingBinding
import com.example.strangernews.ui.viewmodel.HomeViewModel
import com.example.strangernews.ui.viewmodel.SettingViewModel
import com.example.strangernews.utils.notification.NotificationWorker
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    override val viewModel: SettingViewModel by viewModel()
    private var alertTime = "08:00"
    private var firstTime = false
    private var firstArticle : Article? = null
    private val homeVM : HomeViewModel by viewModel()

    override fun initView() {
        collectData()
        (activity as AppCompatActivity).supportActionBar?.title = toolbarTitle
        binding?.apply {
            switchDarkMode.apply {
                isChecked = checkAppMode()
                setOnCheckedChangeListener { _, boolean ->
                    updateAppMode(!boolean)
                    viewModel.updateAppMode(!boolean)
                }
            }
            switchDaily.apply {
                setOnCheckedChangeListener { _, boolean ->
                    if (boolean) {
                        if (firstTime) {
                            val cal = Calendar.getInstance()
                            val timeSetListener =
                                TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                                    alertTime = "$hour:$minute"
                                    txtTime.text = alertTime
                                    viewModel.updateDailNew(boolean, alertTime)
                                    createNotification(hour, minute)
                                }
                            TimePickerDialog(
                                context,
                                timeSetListener,
                                cal.get(Calendar.HOUR_OF_DAY),
                                cal.get(Calendar.MINUTE),
                                true
                            ).show()
                        }
                    } else {
                        viewModel.updateDailNew(boolean, alertTime)
                        stopNotification()
                    }
                }
            }
        }
    }

    override fun initData() {
        observerItem()
    }

    private fun observerItem() {
        homeVM.listArticles.observe(viewLifecycleOwner){
            firstArticle= it?.first()
        }
    }

    private fun checkAppMode(): Boolean =
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> true
            else -> false
        }

    private fun updateAppMode(value: Boolean) {
        when (value) {
            true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun createNotification(hour: Int, minute: Int){
        activity?.apply{
            val currentDate = Calendar.getInstance()
            val dueDate = Calendar.getInstance()
            dueDate.set(Calendar.HOUR_OF_DAY, hour)
            dueDate.set(Calendar.MINUTE, minute)
            dueDate.set(Calendar.SECOND, 0)
            if (dueDate.before(currentDate)) {
                dueDate.add(Calendar.HOUR_OF_DAY, 24)
            }
            val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(false)
                .build()
            val data = Data.Builder().apply {
                put(NotificationWorker.TITLE_PARAM, firstArticle?.title)
                put(NotificationWorker.URL_PARAM, firstArticle?.url)
                put(NotificationWorker.DESCRIPTION_PARAM, firstArticle?.description)
            }
            val work = PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.DAYS)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .setConstraints(constraints)
                .setInputData(data.build())
                .build()
            val workManager =WorkManager.getInstance(applicationContext)
            workManager.enqueueUniquePeriodicWork(workTag, ExistingPeriodicWorkPolicy.REPLACE, work)
        }
    }

    private fun stopNotification() {
        activity?.apply{
            val workManager =WorkManager.getInstance(applicationContext)
            workManager.cancelAllWorkByTag(workTag)
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding?.apply {
                    viewModel.dailyTime.first()?.let {
                        alertTime = it
                        txtTime.text = alertTime
                    }
                    viewModel.isDailyNews.first()?.let {
                        switchDaily.isChecked = it
                        firstTime = true
                    }
                }
            }
        }
    }

    companion object {

        private const val toolbarTitle = "Setting"
        private const val workTag = "noti"
        @JvmStatic
        fun newInstance() = SettingFragment()
    }
}
