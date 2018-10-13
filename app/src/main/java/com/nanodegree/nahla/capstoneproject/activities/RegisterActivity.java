package com.nanodegree.nahla.capstoneproject.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.Utils.SharedPref;
import com.nanodegree.nahla.capstoneproject.models.Type;
import com.nanodegree.nahla.capstoneproject.models.User;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.nanodegree.nahla.capstoneproject.Utils.Const.USERS_TABLE;

public class RegisterActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;
    private static final int REQUEST_MULTI_PERMISSION = 500;

    @BindView(R.id.profileCIV)
    CircleImageView profileCIV;
    @BindView(R.id.addPhotoIV)
    ImageView addPhotoIV;
    @BindView(R.id.nameET)
    EditText nameET;
    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.confirmPasswordET)
    EditText confirmPasswordET;

    FirebaseAuth auth;
    @BindView(R.id.photoCover)
    CircleImageView photoCover;
    @BindView(R.id.registerB)
    Button registerB;
    @BindView(R.id.scrollV)
    ScrollView scrollV;
    private File imageFile;
    private Uri selectedImageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.addPhotoIV)
    public void onAddPhotoIVClicked() {
        checkPermissions();
    }

    @OnClick(R.id.registerB)
    public void onRegisterBClicked() {
        if (nameET.getText().toString().isEmpty()) {
            nameET.setError(getString(R.string.empty_));
            return;
        } else nameET.setError(null);

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

        if (!passwordET.getText().toString().equals(confirmPasswordET.getText().toString())) {
            confirmPasswordET.setError(getString(R.string.unmatch_pw));
            return;
        } else confirmPasswordET.setError(null);

        if (nameET.getError() == null && emailET.getError() == null && passwordET.getError() == null && confirmPasswordET.getError() == null)
            createAccount(emailET.getText().toString(), passwordET.getText().toString());
        else
            Toast.makeText(getApplicationContext(), "Check empty.", Toast.LENGTH_SHORT).show();

    }

    public void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA + Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE + Manifest.permission.CAMERA)) {
                showPhotoDialog();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_MULTI_PERMISSION);
            }
        }
    }

    public void showPhotoDialog() {

        final CharSequence[] items = {getResources().getString(R.string.camera),
                getResources().getString(R.string.gallery),
                getResources().getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.set_photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getResources().getString(R.string.camera))) {

                    if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE) || !hasPermission(Manifest.permission.CAMERA))
                        requestPermissionsSafely(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, REQUEST_MULTI_PERMISSION);
                    else {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        SimpleDateFormat m_sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                        String m_curentDateandTime = m_sdf.format(new Date());

                        imageFile = new File(Environment
                                .getExternalStoragePublicDirectory
                                        (Environment.DIRECTORY_PICTURES), m_curentDateandTime + ".jpg");

                        String authority = getApplicationContext().getPackageName() + ".fileprovider";

                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                            selectedImageURI = Uri.fromFile(imageFile);
                        } else {
                            selectedImageURI = FileProvider.getUriForFile(RegisterActivity.this, authority, imageFile);
                        }

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImageURI);
                        try {
                            startActivityForResult(intent, REQUEST_CAMERA);
                        } catch (SecurityException e) {
                        }
                    }
                } else if (items[item].equals(getResources().getString(R.string.gallery))) {

                    if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE))
                        requestPermissionsSafely(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, REQUEST_MULTI_PERMISSION);
                    else {

                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        startActivityForResult(intent, REQUEST_GALLERY);
                    }
                } else if (items[item].equals(getResources().getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_MULTI_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showPhotoDialog();
            } else {
                Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {

            Glide.with(this)
                    .load(selectedImageURI)
                    .centerCrop()
                    .into(profileCIV);

            photoCover.setVisibility(View.GONE);
            addPhotoIV.setVisibility(View.GONE);

        }
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data != null) {
            selectedImageURI = data.getData();

            Glide.with(this)
                    .load(selectedImageURI)
                    .centerCrop()
                    .into(profileCIV);

            photoCover.setVisibility(View.GONE);
            addPhotoIV.setVisibility(View.GONE);

        }
    }

    private void createAccount(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Authentication failed. " + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            User user = new User();
                            ArrayList<com.nanodegree.nahla.capstoneproject.models.Task> tasks = new ArrayList<>();
                            user.setName(nameET.getText().toString());
                            user.setEmail(firebaseUser.getEmail());
                            user.setuId(firebaseUser.getUid());
                            user.setProfileImg(selectedImageURI + "");
                            user.setTypes(createBasicTypes());
                            user.setTasks(tasks);

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(USERS_TABLE);
                            ref.child(firebaseUser.getUid()).setValue(user);

                            new SharedPref(getApplicationContext()).putUserFBId(FirebaseAuth.getInstance().getUid());
                            new SharedPref(getApplicationContext()).putTaskId(-1);
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();
                        }
                    }
                });
    }

    private ArrayList<Type> createBasicTypes() {
        ArrayList<Type> types = new ArrayList<>();

        Type type = new Type();
        type.setTypeTitle(getString(R.string.meeting));
        type.setTypeColor("#ec4758");
        types.add(type);

        type = new Type();
        type.setTypeTitle(getString(R.string.call));
        type.setTypeColor("#22959b");
        types.add(type);

        type = new Type();
        type.setTypeTitle(getString(R.string.study));
        type.setTypeColor("#7eb233");
        types.add(type);

        return types;
    }

}