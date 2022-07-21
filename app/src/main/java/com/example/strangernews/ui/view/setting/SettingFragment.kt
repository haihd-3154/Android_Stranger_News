package com.example.strangernews.ui.view.setting

import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.strangernews.base.BaseFragment
import com.example.strangernews.databinding.FragmentSettingBinding
import com.example.strangernews.ui.viewmodel.SettingViewModel
import com.example.strangernews.utils.extension.showToast
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    private val toolbarTitle = "Setting"
    private var alertTime = "08:00"
    private var firstTime = false
    override val viewModel: SettingViewModel by viewModel()

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
                    }
                }
            }
        }
    }

    override fun initData() {
        observerItem()
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

    private fun observerItem() {
        viewModel.apply {
            errorResponse.observe(viewLifecycleOwner) {
                context.showToast(it?.message.toString())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingFragment()
    }
}
