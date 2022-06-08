/*
 * Copyright (c) 2022. Operación Kami - Kaze.
 *
 * Licensed under the GNU General Public License v3.0
 *
 * https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Permissions of this strong copyleft license are conditioned on making available complete
 * source code of licensed works and modifications, which include larger works using a licensed
 * work, under the same license. Copyright and license notices must be preserved. Contributors
 * provide an express grant of patent rights.
 */

package es.kamikaze.app.ui.tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import es.kamikaze.app.MainActivity
import es.kamikaze.app.databinding.FragmentTutorialZlastBinding
import es.kamikaze.app.model.User

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LastTutorialFragment : Fragment() {

    private var _binding: FragmentTutorialZlastBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTutorialZlastBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b.kzBocadillo.setData(
            "¡Hola de nuevo!", 12F, "Yo soy Carmelo, cuando estés preparado, vamos a proceder a crear a tu personaje " +
                    "y así poder empezar tu nueva aventura. Cuidado con los enemigos cercanos a tí.", 9F
        )

        b.btIrAJugar.setOnClickListener {
            val prefs = requireActivity().getSharedPreferences("userCreate", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("singletonUser", Gson().toJson(User.getInstancia()))
            editor.apply()

            startActivity(Intent(context, MainActivity::class.java))
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}