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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import es.kamikaze.app.R
import es.kamikaze.app.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var b: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        b = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(b.root)
    }
}