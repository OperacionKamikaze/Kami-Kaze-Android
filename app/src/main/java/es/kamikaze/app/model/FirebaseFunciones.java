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

import static androidx.fragment.app.FragmentManager.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseFunciones {

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private MutableLiveData<List<User>> users = new MutableLiveData<>();

    public void addUser(User user){
        db.child("user").child("pepito").setValue(user);
    }

    public MutableLiveData<List<User>> readUser(){
        List<User> users1 = new ArrayList<User>();
        db.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.v("xyzy", snapshot.getValue().toString());
                Log.v("xyzyx", snapshot.getValue().toString());
//                users1.add((User) snapshot.getValue());
                //Log.v("xyzyx", users1.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        users.setValue(users1);
        return users;
    }


}
