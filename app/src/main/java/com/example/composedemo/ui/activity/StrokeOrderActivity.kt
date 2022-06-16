package com.example.composedemo.ui.activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.composedemo.R
import com.example.composedemo.ui.widget.StrokeOrderView
import com.example.composedemo.utils.XLogger
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class StrokeOrderActivity : AppCompatActivity() {

    var svgSix: String? = null
    var svgOne: String? = null
    lateinit var strokeOrderView1: StrokeOrderView
    lateinit var strokeOrderView2: StrokeOrderView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stroke_order_layout)

        strokeOrderView1 = findViewById(R.id.stroke_order_view1)
        strokeOrderView2 = findViewById(R.id.stroke_order_view2)

        findViewById<Button>(R.id.btn_load_svg_six).setOnClickListener {
            val name = "data/像.json" // 需要将 svg.json 放在 assets 或特定路径下
            svgSix = loadSvgJson(name)
            showTips("加载$name ->$svgSix")
            svgSix?.let {
                showTips("start draw -> $name")
                strokeOrderView1.setStrokesBySvg(it)
            }
        }

        findViewById<Button>(R.id.btn_load_svg_one).setOnClickListener {
            val name = "data/巅.json"
            svgOne = loadSvgJson(name)
            showTips("加载$name ->$svgOne")
            svgOne?.let {
                showTips("start draw -> $name")
                strokeOrderView2.setStrokesBySvg(it)
            }
        }
    }

    private fun loadSvgJson(file: String): String? {
        var reader: BufferedReader? = null
        var inputStreamReader: InputStreamReader? = null
        try {
            val inputStream: InputStream = assets.open(file)
            inputStreamReader = InputStreamReader(inputStream)
            reader = BufferedReader(inputStreamReader)
            var line: String?
            val entity = java.lang.StringBuilder()
            while (reader.readLine().also { line = it } != null) {
                entity.append(line)
            }
            return entity.toString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            try {
                inputStreamReader?.close()
                reader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }

    private fun showTips(str: String) {
        XLogger.d(str)
    }

    companion object {
        private const val TAG = "StrokeOrderActivity"
    }
}