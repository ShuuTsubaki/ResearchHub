package com.example.researchhub


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_detail, container, false)



        return view
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        var title = this.arguments?.getString("title")
        var professor = this.arguments?.getString("professor")
        var description = this.arguments?.getString("description")
        var area = this.arguments?.getString("area")

        view.findViewById<TextView>(R.id.detail_title).text = title
        view.findViewById<TextView>(R.id.detail_professor).text = professor
        view.findViewById<TextView>(R.id.detail_description).text = description
        view.findViewById<TextView>(R.id.detail_areas).text = area

    }


}
