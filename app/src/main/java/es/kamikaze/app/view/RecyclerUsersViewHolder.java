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

package es.kamikaze.app.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import es.kamikaze.app.viewmodel.AndroidViewModel;

public class RecyclerUsersViewHolder extends RecyclerView.ViewHolder{
    private final View view;

    private AndroidViewModel viewModel;

    public RecyclerUsersViewHolder(@NonNull View itemView){
        super(itemView);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) itemView.getContext()).get(AndroidViewModel.class);

        view = itemView;
    }

    @SuppressLint("ResourceType")
    public void bind(String text, int oro, int At,
                int Def, int Exp, int Ps, int Vel){

    }

    public static RecyclerUsersViewHolder create(ViewGroup parent){
        //View view= LayoutInflater.from(parent.getContext())
        //        .inflate(R.layout.item_ventas,parent,false);
        //return new RecyclerVentasViewHolder(view);
        return null;
    }
}
