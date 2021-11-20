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

import es.miapp.kami_kaze.MediaItem.Type

data class MediaItem(
    val id: Int,
    val title: String,
    val thumb: String,
    val type: Type,
) {
    enum class Type { PHOTO, VIDEO }
}

fun getMedia() = (1..10).map {
    MediaItem(
        id = it,
        title = "Title $it",
        thumb = "https://picsum.photos/id/$it/400/400",
        type = if (it % 3 == 0) Type.VIDEO else Type.PHOTO
    )
}
