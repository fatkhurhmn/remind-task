package com.muffar.remindtask.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.usecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
) : ViewModel() {
    fun init() {
        viewModelScope.launch {
            val titles = listOf(
                "Mengerjakan Tugas Akhir",
                "Belanja Bulanan",
                "Janjian dengan Klien",
                "Mengurus Surat-surat",
                "Berolahraga",
                "Membaca Buku",
                "Menonton Film",
                "Mengikuti Workshop",
                "Berkebun",
                "Mendengarkan Musik"
            )

            val descriptions = listOf(
                "Menyelesaikan implementasi fitur-fitur utama aplikasi",
                "Membeli keperluan makanan dan barang-barang rumah tangga",
                "Bertemu dengan klien untuk presentasi proyek",
                "Mengurus berkas administrasi",
                "Melakukan latihan fisik rutin",
                "Membaca buku tentang sejarah",
                "Menonton film komedi terbaru",
                "Mengikuti workshop pemrograman",
                "Merawat tanaman-tanaman di kebun",
                "Mendengarkan musik klasik"
            )


            for (i in titles.indices) {
                val task = Task(
                    title = titles[i],
                    description = descriptions[i],
                    deadline = System.currentTimeMillis() + Random.nextLong(86400000 * 7),
                    priority = PriorityType.entries.toTypedArray().random(),
                    status = StatusType.entries.toTypedArray().random()
                )
//                taskUseCase.saveTask(task)
            }
        }
    }
}