package com.example.researchhub

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener



class searchViewModel : ViewModel() {
    var searchList = MutableLiveData<List<ResearchItem>>()
    private var database = ArrayList<ResearchItem>()
    var areafilter = ArrayList<String>()
    var datab: FirebaseDatabase = FirebaseDatabase.getInstance();
    var myRef: DatabaseReference = datab.getReference("research");
    init{
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                database.clear()
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (snapshot:DataSnapshot in dataSnapshot.children) {
                    var item: ResearchItem? = snapshot.getValue<ResearchItem>(ResearchItem::class.java)
                    database.add(item!!)
                }
                searchList.value = database
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("E", "Failed to read value.", error.toException())
            }
        })
        /*
        database.add(
            ResearchItem("1","Big Data Visualization", "Learn to build tools for big-data visualization. Help human to understand big data",
            "Adam Wang", "HCI    Problem Solving    Databases"))
        database.add(
            ResearchItem("2","Automobile Networking", "Learn to build network system for automobiling use from the bottom. Team based research. More information about the project can be found at www.aaa.com",
                "Micheal Gates", "HCI    Systems    Networking    Software Engineering"))
        database.add(
            ResearchItem("3","Biological Information Analysis", "Learn to build tools to study the texture of sea urchin. Team based research. This research involve the use of innovative technology",
                "Chris Bryant", "Bioinformatics    Machine Learning    Data Mining"))
                */
    }

    fun  addResearch(title:String,professor:String,des:String,area:String) {
        var id:String = myRef.push().key!!
        myRef.child(id).setValue(ResearchItem(id,title,des,professor,area))
    }


}