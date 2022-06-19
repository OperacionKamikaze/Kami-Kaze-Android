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
import es.kamikaze.app.databinding.FragmentTutorialFourthBinding
import es.kamikaze.components.util.bindData

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DFourthTutorialFragment : Fragment() {

    private var _binding: FragmentTutorialFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTutorialFourthBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        val firstPhoto = "https://i.pinimg.com/originals/e4/ea/8c/e4ea8c9fbcf16da0ed3eeb3f6188b7a3.jpg"
        val secondPhoto = "https://wallpaperaccess.com/full/3124395.jpg"
        val thirdPhoto = "https://mobwallpapershd.com/wp-content/uploads/2021/07/Ninja-Wallpaper-Download.jpeg"

        b.ivFirst.bindData(firstPhoto)
        b.ivSecond.bindData(secondPhoto)
        b.ivThird.bindData(thirdPhoto)

        b.ivFirst.setOnClickListener {
            bundle.putString("imgFirst", firstPhoto)
            findNavController().navigate(R.id.action_DFourthTutorialFragment_to_lastTutorialFragment, bundle)
        }

        b.ivSecond.setOnClickListener {
            bundle.putString("imgFirst", secondPhoto)
            findNavController().navigate(R.id.action_DFourthTutorialFragment_to_lastTutorialFragment, bundle)
        }

        b.ivThird.setOnClickListener {
            bundle.putString("imgFirst", thirdPhoto)
            findNavController().navigate(R.id.action_DFourthTutorialFragment_to_lastTutorialFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}