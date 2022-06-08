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

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.kamikaze.app.model.User;

public class RecyclerUsersAdapter extends ListAdapter<User, RecyclerUsersViewHolder> {

    public RecyclerUsersAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecyclerUsersViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerUsersViewHolder holder, int position) {
        User current = getItem(position);
        Log.v("xyz", current.toString());
        holder.bind(current.toString(), current.getOro(), current.getAt(),
                current.getDef(), current.getExp(), current.getPs(), current.getVel());
    }

    public static class UserDiff extends DiffUtil.ItemCallback<User> {

        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            //return oldItem.getNumTelef().equals(newItem.getNumTelef());
            return true;
        }
    }
}
