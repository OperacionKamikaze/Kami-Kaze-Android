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

package es.kamikaze.app.model;

public abstract class Item {

    private int precio = 0;

    public abstract void usar();

    public int getPrecio() {
        return precio;
    }

    //El precio de la venta siempre será algo mas bajo que el de la compra
    public int getPrecioVenta() {
        return (int) ((precio - (precio % 0.8)) * 0.8);
    }
}