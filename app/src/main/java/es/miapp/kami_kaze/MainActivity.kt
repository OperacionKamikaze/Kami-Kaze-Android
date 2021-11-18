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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import es.miapp.kami_kaze.ui.theme.KamiKazeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KamiKazeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

/**
 * Elementos de Compose:
 *
 * - Box // Equivalente a FrameLayout
 * - Row y Row Column // Sustituye al Linearlayout
 * - LazyRow, LazyColumn y LazyVerticalGrid // Sustituto de RecyclerView
 * - ConstraintLayout // Dominio por código
 * - Scaffold // Equivalente a Coordinator Layout (Pero diferente)
 * - Surface // Parecido al Box pero viene de Material
 * - Card // Representa un surface pero con elementos modificados
 */

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(
    showBackground = true,
    widthDp = 400,
    heightDp = 200
)
/*@Composable
fun DefaultPreview() {
    KamiKazeTheme {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Greeting("Android")
            Greeting(
                "Android",
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}*/

/*
@Composable
fun DefaultPreview() {
    KamiKazeTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting(
                name = "Android",
                modifier = Modifier
                    .background(Color.Red)
                    .weight(1f)
            )
            Greeting(
                name = "Android",
                modifier = Modifier
                    .background(Color.Yellow)
                    .weight(2f)
            )
            Greeting(
                name = "Android",
                modifier = Modifier.background(Color.Cyan)
            )
        }
    }
}*/

@Composable
fun DefaultPreview() {
    KamiKazeTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Greeting(
                name = "Android",
                modifier = Modifier
                    .background(Color.Red)
            )
            Greeting(
                name = "Android",
                modifier = Modifier
                    .background(Color.Yellow)
            )
            Greeting(
                name = "Android",
                modifier = Modifier.background(Color.Cyan)
            )
        }
    }
}