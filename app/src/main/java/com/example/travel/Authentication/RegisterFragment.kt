package com.example.travel.Authentication

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travel.R
import com.example.travel.databinding.FragmentRegisterBinding

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        val view = binding.root

        // Show/Hide Password
        val passVisibility = binding.passVisibility
        val pass = binding.passwordReg

        passVisibility.setOnClickListener {
            if (pass.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                // Atur menjadi teks tersembunyi (password)
                pass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                passVisibility.setImageResource(R.drawable.visibility_off_24)
            } else {
                // Atur menjadi teks terlihat
                pass.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passVisibility.setImageResource(R.drawable.visibility_24)
            }
        }
        // Set Date
        binding.calendarDialog.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.CustomDatePickerTheme,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    binding.ttl.setText(selectedDate)
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        // Hitung Umur
        fun calculateAge(dateOfBirth: String): Int {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val currentDate = Calendar.getInstance()
            val dobDate = dateFormat.parse(dateOfBirth)
            val dobCalendar = Calendar.getInstance()
            dobCalendar.time = dobDate

            var years = currentDate.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR)
            val currentMonth = currentDate.get(Calendar.MONTH)
            val dobMonth = dobCalendar.get(Calendar.MONTH)

            if (currentMonth < dobMonth || (currentMonth == dobMonth && currentDate.get(Calendar.DATE) < dobCalendar.get(
                    Calendar.DATE
                ))) {
                years--
            }
            return years
        }

        with(binding){
            val authActivity = requireActivity()


        }

        return view
    }
}