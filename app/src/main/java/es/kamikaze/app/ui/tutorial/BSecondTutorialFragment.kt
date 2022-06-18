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
import es.kamikaze.app.databinding.FragmentTutorialSecondBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BSecondTutorialFragment : Fragment() {

    private var _binding: FragmentTutorialSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTutorialSecondBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b.kzBocadillo.setData(
            "", 18F, "Mira bien el mapa\nEstate atento a tu alrededor\nCuidado con los enemigos\n¡Diviértete!", 15F
        )
        b.kzBocadillo.setUnderlineTitle()
        b.kzBocadillo.setDescriptionMargin(null, null, null, null)
        b.btIrAJugar.setOnClickListener {
            findNavController().navigate(R.id.action_secondTutorialFragment_to_CThirdTutorialFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}