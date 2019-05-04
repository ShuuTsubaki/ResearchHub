package com.example.researchhub


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import org.w3c.dom.Text

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add, container, false)
        val model = ViewModelProviders.of(this).get(searchViewModel::class.java)
        view.findViewById<Button>(R.id.add_bt).setOnClickListener {
            var title:String = view.findViewById<EditText>(R.id.title_input).text.toString()
            var prof:String = view.findViewById<EditText>(R.id.professor_input).text.toString()
            var des:String = view.findViewById<EditText>(R.id.des_input).text.toString()
            var area:String = view.findViewById<EditText>(R.id.area_input).text.toString()
            model.addResearch(title, prof, des, area)
            view?.findNavController()?.navigate(R.id.action_addFragment_to_listFragment)
        }
        return view
    }


}
