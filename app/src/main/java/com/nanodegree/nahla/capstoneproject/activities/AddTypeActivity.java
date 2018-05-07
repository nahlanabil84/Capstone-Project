package com.nanodegree.nahla.capstoneproject.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.models.Type;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTypeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.typeET)
    EditText typeET;
    @BindView(R.id.colorET)
    EditText colorET;
    @BindView(R.id.addTypeB)
    Button addTypeB;
    private Type type;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, AddTypeActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type);
        ButterKnife.bind(this);

        type = new Type();

        setToolbar();

    }

    private void setToolbar() {
        setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(this);
    }

    @OnClick(R.id.colorET)
    public void onColorETClicked() {
        showColorPickerDialog();
    }

    private void showColorPickerDialog() {
        ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("ColorPicker Dialog");
        builder.setPreferenceName("MyColorPickerDialog");
        builder.setPositiveButton(getString(R.string.ok), new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                colorET.setText("#" + colorEnvelope.getColorHtml());

                Drawable drawable = DrawableCompat.wrap(getDrawable(R.drawable.shape_circle));
                DrawableCompat.setTint(drawable, Color.parseColor("#" + colorEnvelope.getColorHtml()));
                colorET.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @OnClick(R.id.addTypeB)
    public void onAddTypeBClicked() {
        if (!typeET.getText().toString().isEmpty())
            type.setTypeTitle(typeET.getText().toString());
        else
            typeET.setError(getString(R.string.empty_));
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
