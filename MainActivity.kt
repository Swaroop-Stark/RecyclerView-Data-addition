package com.example.task

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var name: EditText? = null
    private var number: EditText? = null
    private var save: Button? = null
    private var recyclerView: RecyclerView? = null
    private var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myAdapter: MYAdapter
        var list: ArrayList<String>
        var list2: ArrayList<String>
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        name = dialog.findViewById<View>(R.id.name) as EditText
        number = dialog.findViewById<View>(R.id.number) as EditText
        save = dialog.findViewById<View>(R.id.saveBtn) as Button
        recyclerView = findViewById<View>(R.id.recycleView) as RecyclerView
        fab = findViewById<View>(R.id.fab) as FloatingActionButton
        list = ArrayList(5)
        list2 = ArrayList(5)
        fab!!.setOnClickListener {
            dialog.show()
        }
        save!!.setOnClickListener {
            if (name!!.length()!=0 && number!!.length()!=0) {
                myAdapter = MYAdapter(list, list2)
                recyclerView!!.setHasFixedSize(true)
                recyclerView!!.layoutManager = LinearLayoutManager(this)
                recyclerView!!.adapter = myAdapter
                list.add(name!!.text.toString())
                list2.add(number!!.text.toString())
                dialog.cancel()
                name!!.setText("")
                number!!.setText("")
            }else
            {
                Toast.makeText(this,"Fields are empty",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private inner class MYAdapter(internal var list: ArrayList<String>, internal var list2: ArrayList<String>) :
        RecyclerView.Adapter<MYAdapter.NameHolder>() {

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameHolder {
            val inflater = LayoutInflater.from(parent.context)
            val v = inflater.inflate(R.layout.list_style, null)
            return NameHolder(v)
        }

        override fun onBindViewHolder(holder: NameHolder, position: Int) {
            val t = list[position]
            val tt = list2[position]
            holder.nameT.text = t
            holder.phoneT.text = tt
        }

        internal inner class NameHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var nameT: TextView
            var phoneT: TextView

            init {
                nameT = itemView.findViewById<View>(R.id.nameText) as TextView
                phoneT = itemView.findViewById<View>(R.id.contactText) as TextView
            }
        }
    }
}
