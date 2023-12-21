package com.example.travel.Admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travel.R

/**
 * A simple [Fragment] subclass.
 * Use the [TravelFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TravelFormFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_travel_form, container, false)
    }
}