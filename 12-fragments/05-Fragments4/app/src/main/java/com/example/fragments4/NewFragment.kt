package com.example.fragments4

import android.content.Context
import android.graphics.Color.CYAN
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragments4.MainActivity.Companion.COLOR_BACK
import com.example.fragments4.MainActivity.Companion.NUM_FRAGMENT
import kotlinx.android.synthetic.main.fragment_new.*

class NewFragment : Fragment() {

    interface DatoDevuelto {
        fun datoActualizado(dato: String)
    }

    private val TAG = "FragmentNew"
    private var numFrag = 0
    private var colorBack = CYAN

    var datoFragment: DatoDevuelto? = null

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
        if (context is DatoDevuelto) {
            datoFragment = context
        } else {
            throw RuntimeException(
                context!!.toString() + " debe implementar DatoDevuelto")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        // Comprobamos que se han pasado parámetros
        // desde la llamada, deberemos utilizar la clase
        // Bundle para poder hacerlo.
        if(arguments != null) {
            numFrag = arguments!!.getInt(NUM_FRAGMENT)
            colorBack = arguments!!.getInt(COLOR_BACK, CYAN)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(
            R.layout.fragment_new,
            container,
            false)
    }

    // creada y evitamos posibles fallos.
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        frameRoot.setBackgroundColor(colorBack)
        tv_fragment.text = "Fragment\n${numFrag}"
        sendBtn.setOnClickListener {
            actualizarDato()
        }
    }
    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }
    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }
    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }
    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }
    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
        datoFragment = null
    }

    fun actualizarDato() {
        if (!editResult.text.isEmpty())
            datoFragment?.datoActualizado(editResult.text.toString())
        else datoFragment?.datoActualizado("Sin información")
    }
}