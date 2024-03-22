package com.muffar.remindtask.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.usecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
) : ViewModel() {
    fun init() {
        viewModelScope.launch {
            val taskList = listOf(
                Task(
                    title = "Mengerjakan Tugas Akhir",
                    description = "Menyelesaikan implementasi fitur-fitur utama aplikasi",
                    deadline = System.currentTimeMillis(),
                    priority = PriorityType.HIGH
                ),
                Task(
                    title = "Belanja Bulanan",
                    description = "Membeli keperluan makanan dan barang-barang rumah tangga",
                    deadline = System.currentTimeMillis(),
                    priority = PriorityType.LOW
                ),
                Task(
                    title = "Janjian dengan Klien",
                    description = "Bertemu dengan klien untuk presentasi proyek",
                    deadline = System.currentTimeMillis(),
                    priority = PriorityType.MEDIUM,
                )
            )

//            taskList.forEach {
//                taskUseCase.saveTask(it)
//            }
        }
    }
}