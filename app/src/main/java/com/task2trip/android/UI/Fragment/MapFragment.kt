package com.task2trip.android.UI.Fragment

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.task2trip.android.R
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : BaseFragment(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_map
    }

    override fun initComponents(view: View) {
        googleMapView.getMapAsync(this)
    }

    override fun onMapReady(gm: GoogleMap?) {
        googleMap = gm ?: return
        with(googleMap) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-33.862, 151.21), 16f))
        }
    }
}