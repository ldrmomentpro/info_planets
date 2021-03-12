package com.ldrmomentpro.planets

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setNavigationItemSelectedListener(this)

        val list: MutableList<ListItem> = mutableListOf()
        list.addAll(fillArray(
            getImageId(R.array.solar_system_image),
            resources.getStringArray(R.array.solar_system_title),
            resources.getStringArray(R.array.solar_system_content)))

        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(list, this)
        rcView.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            R.id.id_sun -> {

                adapter?.updateAdapter(fillArray(
                    getImageId(R.array.sun_image),
                    resources.getStringArray(R.array.sun),
                    resources.getStringArray(R.array.sun_content)))
            }

            R.id.id_planets -> {

                adapter?.updateAdapter(fillArray(
                    getImageId(R.array.planets_image),
                    resources.getStringArray(R.array.planets),
                    resources.getStringArray(R.array.planets_content)))
            }

            R.id.id_sputiniki -> Toast.makeText(this, "Pressed sputniki: $item", Toast.LENGTH_SHORT).show()
            R.id.id_compass -> startActivity(Intent(this,CompassActivity::class.java))
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun fillArray(imageArrayId: IntArray, titleArray: Array<String>, contentArray: Array<String>): List<ListItem> {
        val listItemArray = mutableListOf<ListItem>()
        for (i in 0 until titleArray.size) {
            val listItem = ListItem(imageArrayId[i], titleArray[i], contentArray[i])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageId(imageArrayId: Int): IntArray {
        val tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for (i in ids.indices) {
            ids[i] = tArray.getResourceId(i, 0)
        }
        tArray.recycle()
        return ids
    }
}