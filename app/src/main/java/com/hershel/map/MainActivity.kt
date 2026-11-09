package com.hershel.map

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.locationcomponent.location

class MainActivity : Activity() {
 private lateinit var mapView: MapView
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  val root = FrameLayout(this)
  mapView = MapView(this)
  root.addView(mapView, FrameLayout.LayoutParams(-1,-1))
  val controls = LinearLayout(this).apply {
   orientation = LinearLayout.VERTICAL
   setPadding(20,35,20,20)
   setBackgroundColor(0xDD142033.toInt())
  }
  controls.addView(TextView(this).apply {
   text = "MAP  •  Explore"
   textSize = 22f
   setTextColor(android.graphics.Color.WHITE)
  })
  controls.addView(Button(this).apply {
   text = "Show my location"
   setOnClickListener { requestLocation() }
  })
  root.addView(controls, FrameLayout.LayoutParams(-1,-2,Gravity.TOP))
  setContentView(root)
  val dark = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
  mapView.mapboxMap.loadStyle(if (dark) Style.DARK else Style.MAPBOX_STREETS)
  if (hasLocation()) enableLocation()
 }
 private fun hasLocation() = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
  checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
 private fun requestLocation() {
  if (hasLocation()) enableLocation()
  else requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),100)
 }
 private fun enableLocation() {
  mapView.location.updateSettings { enabled = true; pulsingEnabled = true }
 }
 override fun onRequestPermissionsResult(requestCode:Int, permissions:Array<out String>, grantResults:IntArray) {
  super.onRequestPermissionsResult(requestCode,permissions,grantResults)
  if (requestCode==100 && hasLocation()) enableLocation()
 }
 override fun onDestroy() { mapView.onDestroy(); super.onDestroy() }
}