package com.example.researchhub

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.navigation.findNavController



class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.search_list)
        val adapter = ResearchListAdapter()

        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val model = ViewModelProviders.of(this).get(searchViewModel::class.java)
        //model.initial_list()
        var prof_filter = this.arguments?.getString("prof")
        var area_filter_list = ArrayList<String>()
        if (prof_filter == null) {

            prof_filter = ""
        }
        var area_filter = this.arguments?.getString("area_filter")
        if (area_filter != null) {
            var temp = area_filter.split(' ')
            temp = temp.filter { it != ""  && it != null}
            for (i in temp) {
                area_filter_list.add(i)
            }
            Log.d("AB", area_filter_list.toString())
        }
        model.searchList.observe(
            this,
            Observer<List<ResearchItem>>{ lists ->
                lists?.let{
                    Log.d("DD", model.areafilter.toString())
                    adapter.setResearches(it, area_filter_list, prof_filter.toString())
                }
            }
        )

        view.findViewById<Button>(R.id.filter_bt).setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_listFragment_to_filterFragment)}
        view.findViewById<Button>(R.id.clear_button).setOnClickListener {
            adapter.reset()
        }

        view.findViewById<SearchView>(R.id.searchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filterBySearch(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.filterBySearch(query)
                return false
            }

        })
        view.findViewById<Button>(R.id.list_to_home_bt).setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_listFragment_to_homeFragment)
        }
        return view
    }
    inner class ResearchListAdapter():
        RecyclerView.Adapter<ResearchListAdapter.ResearchViewHolder>(){
        private var researches = emptyList<ResearchItem>()
        private var backup = emptyList<ResearchItem>()
        internal fun setResearches(researches: List<ResearchItem>, area: ArrayList<String>, prof:String) {
            this.researches = researches
            this.backup = researches
            Log.d("CC", area.toString())
            filtered(area, prof)
            notifyDataSetChanged()
        }

        fun filterBySearch(query: String?) {
            researches = backup.filter { it.title.contains(query!!) || it.area.contains(query!!) || it.description.contains(query!!) || it.professor.contains(query!!)}
            notifyDataSetChanged()
        }

        fun reset() {
            researches = backup
            notifyDataSetChanged()
        }

        fun filtered(area: ArrayList<String>, professor: String) {
            Log.d("AAA", area.toString())
            researches = backup.filter{containArea(area, it.area)}
            Log.d("AAA", researches.toString())
            researches = researches.filter {it.professor.contains(professor)}
            Log.d("AAA", professor)
            Log.d("AAA", researches.toString())
            notifyDataSetChanged()
        }

        fun containArea(areafilter:ArrayList<String>, area:String):Boolean {
            if (areafilter.count() == 0) {
                return true
            }
            for (i in areafilter) {
                if (area.toLowerCase().contains(i)) {
                    return true
                }
            }
            return false
        }

        override fun getItemCount(): Int {

            return researches.size
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchViewHolder {


            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)
            return ResearchViewHolder(v)
        }

        override fun onBindViewHolder(holder: ResearchViewHolder, position: Int) {


            //holder.bindItems(movieList[position])

            holder.view.findViewById<TextView>(R.id.title_text).text=researches[position].title

            holder.view.findViewById<TextView>(R.id.professor_text).text=researches[position].professor
            holder.view.findViewById<TextView>(R.id.description_text).text=researches[position].description
            holder.view.findViewById<TextView>(R.id.research_area).text=researches[position].area


            holder.itemView.setOnClickListener(){
                //val simpleDate = SimpleDateFormat("dd/MM/yyyy")
                //val date = simpleDate.format(movies[position].release_date)
                view?.findNavController()?.navigate(R.id.action_listFragment_to_detailFragment,
                    bundleOf("title" to researches[position].title, "professor" to researches[position].professor,"description" to researches[position].description,"area" to researches[position].area)
                )
            }

        }
        inner class ResearchViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
            override fun onClick(view: View?){

                if (view != null) {


                }


            }


        }
    }

}
