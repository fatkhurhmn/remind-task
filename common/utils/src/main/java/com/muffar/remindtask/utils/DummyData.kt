package com.muffar.remindtask.utils

import com.muffar.remindtask.model.Note
import com.muffar.remindtask.model.PriorityType
import com.muffar.remindtask.model.StatusType
import com.muffar.remindtask.model.Task
import java.util.UUID
import kotlin.random.Random

object DummyData {
    fun generateTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
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
            tasks.add(task)
        }

        return tasks
    }

    fun generateNotes(): List<Note> {
        val titles = listOf(
            "Rencana Rapat Bulanan",
            "Catatan Presentasi Proyek Baru",
            "Ide-ide Pengembangan Produk",
            "Pertemuan dengan Klien XYZ",
            "Ringkasan Diskusi Tim",
            "Perencanaan Acara Workshop",
            "Review Kinerja Karyawan",
            "Pembaruan Jadwal Proyek",
            "Hasil Riset Pasar Terbaru",
            "Catatan Pelatihan Manajemen Waktu"
        )

        val descriptions = listOf(
            "Rencana untuk rapat bulanan yang akan datang, termasuk agenda yang akan dibahas dan daftar kehadiran yang telah dikonfirmasi. Diskusi akan difokuskan pada peninjauan kinerja bulan lalu, pembaruan proyek, dan rencana tindak lanjut.",
            "Catatan rinci tentang presentasi proyek baru kepada stakeholder. Ini mencakup tinjauan singkat tentang latar belakang proyek, tujuan, ruang lingkup, dan rencana pelaksanaan. Juga termasuk pertanyaan dan umpan balik yang muncul selama presentasi.",
            "Ide-ide kreatif untuk mengembangkan fitur baru pada produk kami. Ide-ide ini muncul dari analisis pasar terbaru dan umpan balik pelanggan. Beberapa ide yang disorot mungkin perlu diuji lebih lanjut sebelum diimplementasikan.",
            "Ringkasan hasil pertemuan dengan klien XYZ, termasuk permintaan perubahan spesifikasi dan penjadwalan ulang. Dibahas pula solusi yang diusulkan untuk mengatasi tantangan yang dihadapi dan langkah-langkah selanjutnya dalam proyek.",
            "Ringkasan diskusi tim dalam pertemuan terbaru. Ini termasuk pembahasan tentang progres proyek, hambatan yang dihadapi, dan langkah-langkah yang diambil untuk mengatasi masalah tersebut. Juga mencakup keputusan penting yang diambil dalam pertemuan.",
            "Perencanaan detail untuk acara workshop yang akan datang, termasuk agenda acara, daftar pembicara, dan materi presentasi. Tujuan acara ini adalah untuk meningkatkan keterampilan dan pengetahuan tim dalam bidang tertentu.",
            "Evaluasi kinerja karyawan dalam siklus penilaian bulan ini. Ini mencakup peninjauan kinerja individu, identifikasi kekuatan dan kelemahan, serta pembahasan tentang rencana pengembangan karier untuk setiap karyawan.",
            "Pembaruan jadwal proyek berdasarkan perkembangan terkini, termasuk estimasi waktu tambahan yang mungkin diperlukan untuk menyelesaikan tugas tertentu. Ini memungkinkan manajemen untuk mengambil tindakan proaktif dalam mengelola risiko proyek.",
            "Hasil dan analisis terbaru dari riset pasar yang dilakukan oleh tim riset kami. Ini mencakup temuan utama, tren pasar, dan implikasi potensialnya bagi strategi pemasaran dan pengembangan produk kami.",
            "Catatan penting dari pelatihan manajemen waktu yang diikuti kemarin. Ini mencakup teknik produktivitas yang dipelajari, strategi pengelolaan waktu, dan langkah-langkah praktis untuk meningkatkan efisiensi kerja sehari-hari."
        )

        val notes = mutableListOf<Note>()

        for (i in titles.indices) {
            notes.add(
                Note(
                    UUID.randomUUID(),
                    titles[i],
                    descriptions[i],
                    System.currentTimeMillis()
                )
            )
        }

        return notes
    }
}