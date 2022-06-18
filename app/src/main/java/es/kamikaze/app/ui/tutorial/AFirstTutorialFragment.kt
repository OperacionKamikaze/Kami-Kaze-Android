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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import es.kamikaze.app.R
import es.kamikaze.app.databinding.FragmentTutorialFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AFirstTutorialFragment : Fragment() {

    private var _binding: FragmentTutorialFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTutorialFirstBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b.kzBocadillo.setData(
            "Bienvenido a Kami - Kaze", 18F, "Hola, yo soy Modesto y seré tu guía. Te daré " +
                    "algunos consejos: ", 15F
        )

        b.kzBocadillo.setDescriptionMargin(null, 60, null, null)

        b.btIrAJugar.setOnClickListener {
            findNavController().navigate(R.id.action_firstTutorialFragment_to_secondTutorialFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}