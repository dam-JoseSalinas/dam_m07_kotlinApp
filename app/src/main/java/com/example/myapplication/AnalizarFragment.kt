package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var btnGenerarPdf:Button
private lateinit var btnGenerarGrafico:Button

/**
 * A simple [Fragment] subclass.
 * Use the [AnalizarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnalizarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment_analizar, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Asignar referencias a los botones
        btnGenerarPdf = view.findViewById(R.id.btn_generar_pdf)
        btnGenerarGrafico = view.findViewById(R.id.btn_generar_grafico)

        // Obtener referencias a los botones usando las id definidas en el layout XML
        btnGenerarPdf.setOnClickListener {
            val intent = Intent(requireContext(), FormularioPedirInforme::class.java)
            startActivity(intent)
        }

        btnGenerarGrafico.setOnClickListener {
            val intent = Intent(requireContext(), MostrarGrafico::class.java)
            startActivity(intent)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PersonalFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AnalizarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}