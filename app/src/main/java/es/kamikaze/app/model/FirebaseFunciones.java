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

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class FirebaseFunciones {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection = db.collection("user");
    private MutableLiveData<List<User>> users = new MutableLiveData<>();

    public void addUser(User user){
        collection.add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.v("xyz", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz", e.toString());
                    }
                });
    }

    public MutableLiveData<List<User>> readUser(){
        collection.get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            users.setValue(task.getResult().toObjects(User.class));
                            /*for(Telefono c: telefonos){
                                Log.v("xyz", c.toString());
                            }*/
                            for(QueryDocumentSnapshot document: task.getResult()){
                                Log.v("xyz", document.getId()+" =>"+document.getData());
                                Log.v("xyz", document.getData().getClass().getCanonicalName());
                            }
                        }else{
                            Log.v("xyz", task.getException().toString());
                        }
                    }
                });
        return users;
    }

    public void editUser(User user){
        collection.document(user.getDni()).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.v("xyz", aVoid.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("xyz", e.toString());
                    }
                });
    }

}
