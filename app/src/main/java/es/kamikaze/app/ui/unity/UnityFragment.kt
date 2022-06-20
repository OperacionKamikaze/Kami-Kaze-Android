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

package es.kamikaze.app.ui.unity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.unity3d.player.UnityPlayer
import es.kamikaze.app.databinding.FragmentUnityBinding
import es.kamikaze.app.ui.activities.MainActivity

class UnityFragment : Fragment() {

    private var _binding: FragmentUnityBinding? = null
    private var unityPlayer: UnityPlayer? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        unityPlayer = UnityPlayer(activity)
        _binding = FragmentUnityBinding.inflate(inflater, container, false)

        unityPlayer?.let {
            b.unityLayout.addView(it.view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            it.requestFocus()
            it.windowFocusChanged(true)
        }
        return b.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        unityPlayer?.quit()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        unityPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        unityPlayer?.resume()
    }
}