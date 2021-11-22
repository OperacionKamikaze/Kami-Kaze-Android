/*
 * Copyright (c) 2021. Operación Kamikaze.
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

package es.miapp.kami_kaze.ui.screen.main

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import es.miapp.kami_kaze.R

@Composable
fun MainAppBar(context: Context) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },

        actions = {
            AppBarAction(imageVector = Icons.Default.Search) {
                Toast.makeText(context, "Buscar", Toast.LENGTH_SHORT).show()
            }
            AppBarAction(imageVector = Icons.Default.Share) {
                Toast.makeText(context, "Compartir", Toast.LENGTH_SHORT).show()
            }
        }
    )
}

@Composable
private fun AppBarAction(imageVector: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}