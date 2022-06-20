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
import es.kamikaze.app.databinding.FragmentTutorialThirdBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CThirdTutorialFragment : Fragment() {

    private var _binding: FragmentTutorialThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTutorialThirdBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b.kzBocadillo.setData(
            "Imagen de perfil", 18F, "Ahora en la siguiente pantalla, tendrás que elegir que foto prefieres de perfil" +
                    " para tu personaje.", 15F
        )
        b.kzBocadillo.setUnderlineTitle()
        b.kzBocadillo.setDescriptionMargin(null, null, null, null)
        b.btIrAJugar.setOnClickListener {
            findNavController().navigate(R.id.action_CThirdTutorialFragment2_to_DFourthTutorialFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}