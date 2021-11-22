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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import es.miapp.kami_kaze.R
import es.miapp.kami_kaze.model.MediaItem
import es.miapp.kami_kaze.model.getMedia

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun MediaList(modifier: Modifier = Modifier, context: Context) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_xxsamll)),
        cells = GridCells.Adaptive(dimensionResource(id = R.dimen.cell_min_width)),
        modifier = modifier
    ) {
        items(getMedia()) { item ->
            MediaListItem(
                item = item,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_xsamll)),
                context = context
            )
        }
    }
}

@ExperimentalCoilApi
//@Preview(showBackground = true)
@Composable
fun MediaListItem(item: MediaItem, modifier: Modifier = Modifier, context: Context) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.cell_thumb_height))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberImagePainter(
                    data = item.thumb,
                    builder = {
                        transformations(BlurTransformation(LocalContext.current))
                    }
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (item.type == MediaItem.Type.VIDEO) {
                Icon(
                    imageVector = Icons.Default.PlayCircleOutline,
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.cell_play_icon_size)),
                    tint = Color.White
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Button(onClick = { onClickButtons(context = context, string = item.id) }) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

/*@ExperimentalFoundationApi
@ExperimentalCoilApi
@Preview
@Composable
fun MediaListPreview() {
    MediaList(context = context)
}*/

private fun onClickButtons(context: Context, string: Int) =
    Toast.makeText(context, "Botón $string", Toast.LENGTH_SHORT).show()