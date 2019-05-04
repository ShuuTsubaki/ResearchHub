package com.example.researchhub


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<Button>(R.id.home_to_add_bt).setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_addFragment)
        }
        view.findViewById<Button>(R.id.home_to_list_bt).setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_listFragment)
        }

        return view
    }


}
