package es.kamikaze.app.ui.social;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.kamikaze.app.R;
import es.kamikaze.app.databinding.FragmentSocialBinding;
import es.kamikaze.app.model.User;

public class SocialFragment extends Fragment {

    private SocialViewModel socialViewModel;
    private FragmentSocialBinding binding;


    private final String TAG = "DEBUG";
    private final String TAGLOC = "Social";
    private final int RC_SIGN_IN = 9001;
    private String url;
    private View view;
    private GoogleSignInClient mSignInClient;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        socialViewModel =
                new ViewModelProvider(this).get(SocialViewModel.class);

        binding = FragmentSocialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT);
                signIn();
            }
        });

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();
        view = binding.getRoot();

        try {
            // Listeners de botones
            /** Configura sign-in para solicitar el user's ID, email, y perfil
             básico. ID y el perfil básico están incluidos en DEFAULT_SIGN_IN.*/
            GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            mSignInClient = GoogleSignIn.getClient(requireActivity(), options);
            mAuth = FirebaseAuth.getInstance();


            /** Customiza el botón sign in. Puede ser mostrado con3
             *  Scopes.PLUS_LOGIN scope hacia GoogleSignInOptions
             *  para ver la diferencia.

            SignInButton signInButton = view.findViewById(R.id.sign_in_button);
            signInButton.setSize(SignInButton.SIZE_WIDE);*/


        } catch (InflateException e) {
            /* El mapa ya está aquí, return de la view como está. */
        }
        super.onCreate(savedInstanceState);

        return view;

        //return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
        if (mAuth.getCurrentUser() != null) {
            user = mAuth.getCurrentUser();
            writeNewUser(user.getUid(), user.getDisplayName(), user.getEmail());
            Log.d(TAG + TAGLOC, " -> onStart-> Usuario con sesión activa: " + user.getDisplayName() + "\n[" + mAuth.getUid() + "]");
        } else {
            Log.d(TAG + TAGLOC, " -> onStart-> Ningún usuario logeado");
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Resultado devuelto de lanzar el Intent desde GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In ha sido un éxito, autentificación con Firebase,
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null)
                    Log.d(TAG + TAGLOC, " -> onActivityResult -> firebaseAuthWithGoogle: Login request de " + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {
                // Google Sign In ha fallado, update UI acordemente.
                Log.w(TAG + TAGLOC, " -> onActivityResult -> Google sign in failed", e);
                updateUI();
            }
        }
    }


    private void firebaseAuthWithGoogle(String idToken) {

        showProgressBar();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            user = mAuth.getCurrentUser();
                            Log.d(TAG + TAGLOC, " -> firebaseAuthWithGoogle -> Authentication Succesfull de : " + user.getDisplayName() + "\n[" + mAuth.getUid() + "]");
                            writeNewUser(user.getUid(), user.getDisplayName(), user.getEmail());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG + TAGLOC, " -> firebaseAuthWithGoogle -> onComplete Error -> " + task.getException());
                            Toast.makeText(getContext(), "Authentication Failed.", Toast.LENGTH_SHORT);
                        }

                        updateUI();
                        hideProgressBar();
                    }
                });
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = User.getInstancia();
        mDatabase.child("users").child(userId).setValue(user);
    }

    private void signIn() {
        Intent signInIntent = mSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mSignInClient.signOut().addOnCompleteListener(requireActivity(),
                task -> updateUI());
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();
        // Google revoke access
        mSignInClient.revokeAccess().addOnCompleteListener(requireActivity(),
                task -> updateUI());
    }

    private void showProgressBar() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(requireActivity());
            // mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressBar() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

   private void updateUI() {
        FirebaseUser user = mAuth.getCurrentUser();
         /*if (user != null) {
            binding.pNombreTv.setText(user.getDisplayName());
            //Picasso.get().load(user.getPhotoUrl()).resize(200, 200).into(binding.fotoPerfilCV);
            binding.emailCv.setText(user.getEmail());
            binding.signInButton.setVisibility(View.GONE);
            binding.botonesDeslogeo.setVisibility(View.VISIBLE);
            binding.fotoPerfilCV.setVisibility(View.VISIBLE);
            binding.perfilCardView.setVisibility(View.VISIBLE);
            binding.pNombreTv.setVisibility(View.VISIBLE);
            binding.emailCv.setVisibility(View.VISIBLE);

        } else {
            //mStatusTextView.setText(R.string.signed_out);

            binding.signInButton.setVisibility(View.VISIBLE);
            binding.pNombreTv.setVisibility(View.GONE);
            binding.fotoPerfilCV.setVisibility(View.GONE);
            binding.emailCv.setVisibility(View.GONE);
            binding.perfilCardView.setVisibility(View.GONE);
            binding.botonesDeslogeo.setVisibility(View.GONE);

        }*/
    }


}