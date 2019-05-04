package com.example.researchhub


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController



class FilterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_filter, container, false)
        //val model = ViewModelProviders.of(this).get(searchViewModel::class.java)
        view.findViewById<Button>(R.id.toList_bt).setOnClickListener {
            var area_filter = setFilter()
            view?.findNavController()?.navigate(R.id.action_filterFragment_to_listFragment, bundleOf("area_filter" to area_filter, "prof" to view.findViewById<EditText>(R.id.prof_filter).text.toString()))
        }
        return view
    }

    fun setFilter():String {
        val model = ViewModelProviders.of(this).get(searchViewModel::class.java)
        var toreturn = ""
        if (view?.findViewById<RadioButton>(R.id.radioButton1)!!.isChecked) {
            model.areafilter.add("ai")
            toreturn = toreturn+"ai "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton2)!!.isChecked) {
            model.areafilter.add("bio")
            toreturn = toreturn+"bio "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton3)!!.isChecked) {
            model.areafilter.add("data mining")
            toreturn = toreturn+"data mining "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton4)!!.isChecked) {
            model.areafilter.add("database")
            toreturn = toreturn+"database "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton5)!!.isChecked) {
            model.areafilter.add("hci")
            toreturn = toreturn+"hci "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton6)!!.isChecked) {
            model.areafilter.add("knowledge")
            toreturn = toreturn+"knowledge "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton7)!!.isChecked) {
            model.areafilter.add("network")
            toreturn = toreturn+"network "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton8)!!.isChecked) {
            model.areafilter.add("parrallel")
            toreturn = toreturn+"parrallel "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton9)!!.isChecked) {
            model.areafilter.add("software engineering")
            toreturn = toreturn+"software engineering "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton10)!!.isChecked) {
            model.areafilter.add("system")
            toreturn = toreturn+"system "
        }
        if (view?.findViewById<RadioButton>(R.id.radioButton11)!!.isChecked) {
            model.areafilter.add("theory")
            toreturn = toreturn+"theory "
        }
        return toreturn
        Log.d("BBB", model.areafilter.toString())
    }


}
