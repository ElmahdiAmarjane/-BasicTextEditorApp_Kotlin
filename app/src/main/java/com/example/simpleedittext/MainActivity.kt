package com.example.simpleedittext

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner_fonts = findViewById<Spinner>(R.id.spinnerFonts)
        val spinner_size = findViewById<Spinner>(R.id.spinnerSize)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.fonts, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val index = adapter.getPosition("cagliostro")
        spinner_fonts.adapter = adapter
        spinner_fonts.setSelection(index)
        /********************/
        val adaptersize = ArrayAdapter.createFromResource(
            this,
            R.array.numbers, android.R.layout.simple_spinner_item
        )
        adaptersize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val indexsize = adaptersize.getPosition("cagliostro")
        spinner_size.adapter = adaptersize
        spinner_size.setSelection(indexsize)
        /*******************/
        val bold = findViewById<TextView>(R.id.bold)

        val txt_area = findViewById<TextView>(R.id.area_text)
        bold.setOnClickListener {
            var string = SpannableStringBuilder(txt_area.getText())
            string.setSpan(
                StyleSpan(Typeface.BOLD), txt_area.selectionStart,
                txt_area.selectionEnd, 0
            )
            txt_area.setText(string)
        }
        val italic = findViewById<TextView>(R.id.italic)
        italic.setOnClickListener {
            var string = SpannableStringBuilder(txt_area.getText())
            string.setSpan(
                StyleSpan(Typeface.ITALIC), txt_area.selectionStart,
                txt_area.selectionEnd, 0
            )
            txt_area.setText(string)
        }
        val underline = findViewById<TextView>(R.id.underline)
        underline.setOnClickListener {
            var string = SpannableStringBuilder(txt_area.getText())
            string.setSpan(
                StyleSpan(Typeface.BOLD_ITALIC), txt_area.selectionStart,
                txt_area.selectionEnd, 0
            )
            txt_area.setText(string)
        }



        spinner_fonts.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, p1: View?,
                position: Int, p3: Long
            ) {
                val fontName = parent?.getItemAtPosition(position).toString().plus(".ttf")
                val style = Typeface.createFromAsset(assets, "font/$fontName")
                txt_area?.typeface = style // Note the safe call operator (?)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where nothing is selected
                // For example, you might want to set a default font
                val defaultStyle = Typeface.defaultFromStyle(Typeface.NORMAL)
                txt_area?.typeface = defaultStyle
            }
        }

    }
}