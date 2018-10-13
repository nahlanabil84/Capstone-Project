package com.nanodegree.nahla.capstoneproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.Utils.SharedPref;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.loginB)
    public void onLoginBClicked() {
        if (emailET.getText().toString().isEmpty()) {
            emailET.setError(getString(R.string.empty_));
            return;
        } else emailET.setError(null);

        if (passwordET.getText().toString().isEmpty()) {
            passwordET.setError(getString(R.string.empty_));
            return;
        } else passwordET.setError(null);

        if (passwordET.getText().toString().length() < 6) {
            passwordET.setError(getString(R.string.short_pw));
            return;
        } else passwordET.setError(null);

        if (emailET.getError() == null && passwordET.getError() == null)
            signIn(emailET.getText().toString(), passwordET.getText().toString());
        else
            Toast.makeText(getApplicationContext(), "Check empty.", Toast.LENGTH_SHORT).show();
    }

    private void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        } else {
                            new SharedPref(getApplicationContext()).putUserFBId(FirebaseAuth.getInstance().getUid());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    @OnClick(R.id.registerTV)
    public void onRegisterTVClicked() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }


    }
}
