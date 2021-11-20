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

package es.miapp.kami_kaze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import es.miapp.kami_kaze.ui.theme.KamiKazeTheme

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KamiKazeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MediaList()
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun MediaList() {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(getMedia()) { item -> MediaListItem(item) }
    }
}

@ExperimentalCoilApi
//@Preview(showBackground = true)
@Composable
fun MediaListItem(item: MediaItem) {
    Column(
        modifier = Modifier.width(200.dp)
    ) {
        Box(
            modifier = Modifier
                .height(height = 200.dp),
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
                    modifier = Modifier.size(92.dp),
                    tint = Color.White
                )
            }

            //            Icon(painter = painterResource(id = R.drawable.ic_launcher_background))
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
                .padding(16.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

//@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun ButtonText() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hellow World World World World World World World World",
            fontSize = 25.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace,
            textDecoration = TextDecoration.LineThrough,
            textAlign = TextAlign.Center,
            lineHeight = 2.em,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h4.copy(
                shadow = Shadow(
                    offset = Offset(10f, 10f),
                    blurRadius = 5f,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            )
        )
    }
}