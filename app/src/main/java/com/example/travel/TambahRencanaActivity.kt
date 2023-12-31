package com.example.travel
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.travel.databinding.ActivityTambahRencanaBinding

class TambahRencanaActivity : AppCompatActivity() {
    lateinit var binding: ActivityTambahRencanaBinding

    companion object {
        const val EXTRA_TGLJALAN = "extra_tglJalan"
        const val EXTRA_KOTA_ASAL = "extra_kotaAsal"
        const val EXTRA_KOTA_TUJUAN = "extra_kotaTujuan"
        const val EXTRA_STASIUN_ASAL = "extra_stasiunAsal"
        const val EXTRA_STASIUN_TUJUAN = "extra_stasiunTujuan"
        const val EXTRA_KELAS_KERETA = "extra_kelasKereta"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var calendar = Calendar.getInstance()
        var hargaTerbaru: Int = 0

        super.onCreate(savedInstanceState)
        binding = ActivityTambahRencanaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Random Generator
        fun generateRandomPrice(): Int {
            val minPrice = 5000
            val maxPrice = 10000
            return (minPrice..maxPrice).random()
        }

        with(binding){
            // Tambah harga tiap Spinner
            fun updatePrice() {
                val randomPrice = generateRandomPrice()
                hargaTerbaru = randomPrice
                binding.price.text = "$randomPrice"
            }

            var selectedKotaAsal: String = ""
            binding.kotaAsal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                    if (position != 0) {
                        updatePrice()
                        selectedKotaAsal = kotaAsal.getItemAtPosition(position).toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            var selectedKotaTujuan: String = ""
            binding.kotaTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                    if (position != 0) {
                        val newPrice = generateRandomPrice() as Int
                        val currentPrice = hargaTerbaru as Int
                        val updatedPrice = currentPrice + newPrice
                        hargaTerbaru = updatedPrice
                        binding.price.text = "$updatedPrice"

                        selectedKotaTujuan = kotaTujuan.getItemAtPosition(position).toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            var selectedStasiunAsal: String = ""
            binding.stasiunAsal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                    if (position != 0) {
                        val newPrice = generateRandomPrice() as Int
                        val currentPrice = hargaTerbaru as Int
                        val updatedPrice = currentPrice + newPrice
                        hargaTerbaru = updatedPrice
                        binding.price.text = "$updatedPrice"

                        selectedStasiunAsal = stasiunAsal.getItemAtPosition(position).toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            var selectedStasiunTujuan: String = ""
            binding.stasiunTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                    if (position != 0) {
                        val newPrice = generateRandomPrice() as Int
                        val currentPrice = hargaTerbaru as Int
                        val updatedPrice = currentPrice + newPrice
                        hargaTerbaru = updatedPrice
                        binding.price.text = "$updatedPrice"

                        selectedStasiunTujuan = stasiunTujuan.getItemAtPosition(position).toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            var selectedKelasKereta: String = ""
            binding.classKereta.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                    if (position != 0) {
                        val newPrice = generateRandomPrice() as Int
                        val currentPrice = hargaTerbaru as Int
                        val updatedPrice = currentPrice + newPrice
                        hargaTerbaru = updatedPrice
                        binding.price.text = "$updatedPrice"

                        selectedKelasKereta = classKereta.getItemAtPosition(position).toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            binding.snackBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    hargaTerbaru += 5000
                    binding.snackBox.setTextColor(resources.getColor(R.color.white))
                } else{
                    hargaTerbaru -= 5000
                    binding.snackBox.setTextColor(resources.getColor(R.color.black))
                }
                binding.price.text = "$hargaTerbaru"
            }

            binding.lunchBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    hargaTerbaru += 5000
                    binding.lunchBox.setTextColor(resources.getColor(R.color.white))
                } else {
                    hargaTerbaru -= 5000
                    binding.lunchBox.setTextColor(resources.getColor(R.color.black))
                }
                binding.price.text = "$hargaTerbaru"
            }

            binding.freeWifi.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    hargaTerbaru += 5000
                    binding.freeWifi.setTextColor(resources.getColor(R.color.white))
                } else {
                    hargaTerbaru -= 5000
                    binding.freeWifi.setTextColor(resources.getColor(R.color.black))
                }
                binding.price.text = "$hargaTerbaru"
            }

            binding.stopkontak.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    hargaTerbaru += 5000
                    binding.stopkontak.setTextColor(resources.getColor(R.color.white))
                } else {
                    hargaTerbaru -= 5000
                    binding.stopkontak.setTextColor(resources.getColor(R.color.black))
                }
                binding.price.text = "$hargaTerbaru"
            }

            binding.airConditioner.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    hargaTerbaru += 5000
                    binding.airConditioner.setTextColor(resources.getColor(R.color.white))
                } else {
                    hargaTerbaru -= 5000
                    binding.airConditioner.setTextColor(resources.getColor(R.color.black))
                }
                binding.price.text = "$hargaTerbaru"
            }

            binding.kursiDepan.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    hargaTerbaru += 5000
                    binding.kursiDepan.setTextColor(resources.getColor(R.color.white))
                } else {
                    hargaTerbaru -= 5000
                    binding.kursiDepan.setTextColor(resources.getColor(R.color.black))
                }
                binding.price.text = "$hargaTerbaru"
            }

            // Set Date
            binding.calendarDialog.setOnClickListener {
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(this@TambahRencanaActivity, R.style.CustomDatePickerTheme,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    binding.tglJalan.setText(selectedDate)
                }, year, month, day)

                datePickerDialog.show()
            }

            with(binding){
                val username = intent.getStringExtra(RegisterActivity.EXTRA_USERNAME_REG)
                val email = intent.getStringExtra(RegisterActivity.EXTRA_EMAIL_REG)

                inputPesan.setOnClickListener {
                    val alertDialog = AlertDialog.Builder(this@TambahRencanaActivity)
                    alertDialog.setTitle("Konfirmasi")
                    alertDialog.setMessage("Anda yakin ingin melakukan pemesanan?")
                    alertDialog.setPositiveButton("Ya") { dialog, which ->
                        val intentToDashboard = Intent(this@TambahRencanaActivity, ProfileActivity::class.java)
                        intentToDashboard.putExtra(EXTRA_TGLJALAN, tglJalan.text.toString())
                        intentToDashboard.putExtra(EXTRA_KOTA_ASAL, selectedKotaAsal)
                        intentToDashboard.putExtra(EXTRA_KOTA_TUJUAN, selectedKotaTujuan)
                        intentToDashboard.putExtra(EXTRA_STASIUN_ASAL, selectedStasiunAsal)
                        intentToDashboard.putExtra(EXTRA_STASIUN_TUJUAN, selectedStasiunTujuan)
                        intentToDashboard.putExtra(EXTRA_KELAS_KERETA, selectedKelasKereta)
                        intentToDashboard.putExtra(RegisterActivity.EXTRA_USERNAME_REG, username)
                        intentToDashboard.putExtra(RegisterActivity.EXTRA_EMAIL_REG, email)
                        startActivity(intentToDashboard)
                    }
                    alertDialog.setNegativeButton("Batal") { dialog, which ->
                        // Tidak melakukan apa-apa jika pengguna membatalkan dialog
                    }
                    alertDialog.show()
                }

                backBtn.setOnClickListener {
                    val alertDialog = AlertDialog.Builder(this@TambahRencanaActivity)
                    alertDialog.setTitle("Konfirmasi")
                    alertDialog.setMessage("Semua perubahan akan dihapus. Apakah Anda yakin ingin kembali?")
                    alertDialog.setPositiveButton("Ya") { dialog, which ->
                        val intentToDashboard =
                            Intent(this@TambahRencanaActivity, ProfileActivity::class.java)
                        intentToDashboard.putExtra(RegisterActivity.EXTRA_USERNAME_REG, username)
                        intentToDashboard.putExtra(RegisterActivity.EXTRA_EMAIL_REG, email)
                        startActivity(intentToDashboard)
                    }
                    alertDialog.setNegativeButton("Batal") { dialog, which ->
                        //
                    }
                    alertDialog.show()
                }
            }
        }
    }
}