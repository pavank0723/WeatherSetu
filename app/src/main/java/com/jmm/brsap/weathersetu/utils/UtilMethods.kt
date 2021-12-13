package com.jmm.brsap.weathersetu.utils

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jmm.brsap.weathersetu.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

fun getWeatherIcon(isDay: Int, code: Int): Int {

    val dayIcon = HashMap<Int, Int>()
    dayIcon[1000] = R.drawable.sun_113
    dayIcon[1003] = R.drawable.sun_116
    dayIcon[1006] = R.drawable.sun_119
    dayIcon[1009] = R.drawable.cloud_122
    dayIcon[1030] = R.drawable.cloud_143
    dayIcon[1063] = R.drawable.sun_176_293_299
    dayIcon[1066] = R.drawable.cloud_179
    dayIcon[1069] = R.drawable.sun_182
    dayIcon[1072] = R.drawable.cloud_185
    dayIcon[1087] = R.drawable.sun_200_386
    dayIcon[1114] = R.drawable.cloud_227
    dayIcon[1117] = R.drawable.cloud_230
    dayIcon[1135] = R.drawable.fog_248
    dayIcon[1147] = R.drawable.fog_260
    dayIcon[1150] = R.drawable.cloud_263
    dayIcon[1153] = R.drawable.cloud_266
    dayIcon[1168] = R.drawable.cloud_281
    dayIcon[1171] = R.drawable.cloud_284
    dayIcon[1180] = R.drawable.sun_176_293_299
    dayIcon[1183] = R.drawable.cloud_296
    dayIcon[1186] = R.drawable.sun_176_293_299
    dayIcon[1189] = R.drawable.cloud_302
    dayIcon[1192] = R.drawable.sun_305_356
    dayIcon[1195] = R.drawable.cloud_308
    dayIcon[1198] = R.drawable.cloud_311
    dayIcon[1201] = R.drawable.cloud_314
    dayIcon[1204] = R.drawable.cloud_317
    dayIcon[1207] = R.drawable.cloud_320
    dayIcon[1210] = R.drawable.sun_179_323_335
    dayIcon[1213] = R.drawable.cloud_326

    dayIcon[1216] = R.drawable.sun_329
    dayIcon[1219] = R.drawable.cloud_332
    dayIcon[1222] = R.drawable.sun_179_323_335
    dayIcon[1225] = R.drawable.cloud_338
    dayIcon[1237] = R.drawable.cloud_350

    dayIcon[1240] = R.drawable.sun_353

    dayIcon[1243] = R.drawable.sun_305_356

    dayIcon[1246] = R.drawable.sun_359
    dayIcon[1249] = R.drawable.sun_362
    dayIcon[1252] = R.drawable.sun_365
    dayIcon[1255] = R.drawable.sun_368
    dayIcon[1258] = R.drawable.sun_371
    dayIcon[1261] = R.drawable.sun_374
    dayIcon[1264] = R.drawable.sun_377
    dayIcon[1273] = R.drawable.sun_200_386
    dayIcon[1276] = R.drawable.cloud_389
    dayIcon[1279] = R.drawable.sun_392
    dayIcon[1282] = R.drawable.cloud_395

    val nightIcon = HashMap<Int, Int>()
    nightIcon[1000] = R.drawable.moon_113
    nightIcon[1003] = R.drawable.moon_116
    nightIcon[1006] = R.drawable.moon_119
    nightIcon[1009] = R.drawable.cloud_122
    nightIcon[1030] = R.drawable.cloud_143
    nightIcon[1063] = R.drawable.moon_176_293_299
    nightIcon[1066] = R.drawable.cloud_179
    nightIcon[1069] = R.drawable.moon_182
    nightIcon[1072] = R.drawable.cloud_185
    nightIcon[1087] = R.drawable.moon_200_386
    nightIcon[1114] = R.drawable.cloud_227
    nightIcon[1117] = R.drawable.cloud_230
    nightIcon[1135] = R.drawable.fog_248
    nightIcon[1147] = R.drawable.fog_260
    nightIcon[1150] = R.drawable.cloud_263
    nightIcon[1153] = R.drawable.cloud_266
    nightIcon[1168] = R.drawable.cloud_281
    nightIcon[1171] = R.drawable.cloud_284
    nightIcon[1180] = R.drawable.moon_176_293_299
    nightIcon[1183] = R.drawable.cloud_296
    nightIcon[1186] = R.drawable.moon_176_293_299
    nightIcon[1189] = R.drawable.cloud_302
    nightIcon[1192] = R.drawable.moon_305_356
    nightIcon[1195] = R.drawable.cloud_308
    nightIcon[1198] = R.drawable.cloud_311
    nightIcon[1201] = R.drawable.cloud_314
    nightIcon[1204] = R.drawable.cloud_317
    nightIcon[1207] = R.drawable.cloud_320
    nightIcon[1210] = R.drawable.moon_179_323_335
    nightIcon[1213] = R.drawable.cloud_326

    nightIcon[1216] = R.drawable.moon_329
    nightIcon[1219] = R.drawable.cloud_332
    nightIcon[1222] = R.drawable.moon_179_323_335
    nightIcon[1225] = R.drawable.cloud_338
    nightIcon[1237] = R.drawable.cloud_350

    nightIcon[1240] = R.drawable.moon_353

    nightIcon[1243] = R.drawable.moon_305_356

    nightIcon[1246] = R.drawable.moon_359
    nightIcon[1249] = R.drawable.moon_362
    nightIcon[1252] = R.drawable.moon_365
    nightIcon[1255] = R.drawable.moon_368
    nightIcon[1258] = R.drawable.moon_371
    nightIcon[1261] = R.drawable.moon_374
    nightIcon[1264] = R.drawable.moon_377
    nightIcon[1273] = R.drawable.moon_200_386
    nightIcon[1276] = R.drawable.cloud_389
    nightIcon[1279] = R.drawable.moon_392
    nightIcon[1282] = R.drawable.cloud_395


    return if (isDay == 1) dayIcon[code]!!
    else nightIcon[code]!!


}

fun myTimeFormatter(time:String):String{
    val displayFormat = SimpleDateFormat("h a")
    val parseFormat = SimpleDateFormat("yyyy-mm-dd HH:mm")
    val date: Date = parseFormat.parse(time)
    return displayFormat.format(date)
}

fun myDateFormatter(date:String,format:String):String{
    val displayFormat = SimpleDateFormat(format)
    val parseFormat = SimpleDateFormat("yyyy-mm-dd HH:mm")
    val date: Date = parseFormat.parse(date)
    return displayFormat.format(date)
}

fun myDateFormatter1(date:String,format:String):String{
    val displayFormat = SimpleDateFormat(format)
    val parseFormat = SimpleDateFormat("yyyy-mm-dd")
    val date: Date = parseFormat.parse(date)
    return displayFormat.format(date)
}

fun TextView.setTemperature(temp: Any) {
    text = "${temp.toString().take(2)}°C"

}

fun TextView.setTemperature1(temp: Any) {
    text = "${temp.toString()}°C"

}

fun TextView.setTemperature2(temp: Any) {
    text = "${temp.toString().take(2)}°"

}

fun ImageView.setImageWithGlide(url: Any) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_error)
        .into(this)
}