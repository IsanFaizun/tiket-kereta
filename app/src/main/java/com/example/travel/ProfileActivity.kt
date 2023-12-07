package com.example.travel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travel.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val tanggalJln = intent.getStringExtra(TambahRencanaActivity.EXTRA_TGLJALAN) ?: ""
            val kotaAsal = intent.getStringExtra(TambahRencanaActivity.EXTRA_KOTA_ASAL) ?: ""
            val kotaTujuan = intent.getStringExtra(TambahRencanaActivity.EXTRA_KOTA_TUJUAN) ?: ""
            val stasiunAsal = intent.getStringExtra(TambahRencanaActivity.EXTRA_STASIUN_ASAL) ?: ""
            val stasiunTujuan = intent.getStringExtra(TambahRencanaActivity.EXTRA_STASIUN_TUJUAN) ?: ""
            val kelasKereta = intent.getStringExtra(TambahRencanaActivity.EXTRA_KELAS_KERETA) ?: ""

            binding.tanggalTxt.text = tanggalJln
            binding.asalTxt.text = kotaAsal
            binding.tujuanTxt.text = kotaTujuan
            binding.stasiunAsalTxt.text = stasiunAsal
            binding.stasiunTujuanTxt.text = stasiunTujuan
            binding.kelasTxt.text = kelasKereta

            binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year" // Format tanggal yang dipilih
                if (selectedDate == tanggalJln) {
                    Toast.makeText(this@ProfileActivity, "Anda punya rencana pada tanggal tersebut", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ProfileActivity, "Tidak ada rencana di hari tersebut", Toast.LENGTH_SHORT).show()
                }
            }

            val username = intent.getStringExtra(RegisterActivity.EXTRA_USERNAME_REG)
            val email = intent.getStringExtra(RegisterActivity.EXTRA_EMAIL_REG)

            binding.usernameMain.text = username
            binding.emailMain.text = email

            inputRencana.setOnClickListener {
                val intentToAddRencana = Intent(this@ProfileActivity, TambahRencanaActivity::class.java)
                intentToAddRencana.putExtra(RegisterActivity.EXTRA_USERNAME_REG, username)
                intentToAddRencana.putExtra(RegisterActivity.EXTRA_EMAIL_REG, email)
                startActivity(intentToAddRencana)
            }

            logout.setOnClickListener {
                val intentToLogin = Intent(this@ProfileActivity, RegisterActivity::class.java)
                startActivity(intentToLogin)
            }
        }
    }
}