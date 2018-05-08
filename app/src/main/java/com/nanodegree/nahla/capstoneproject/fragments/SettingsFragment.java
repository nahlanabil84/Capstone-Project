package com.nanodegree.nahla.capstoneproject.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.nanodegree.nahla.capstoneproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingsFragment extends Fragment {

    @BindView(R.id.nameTV)
    TextView nameTV;
    @BindView(R.id.pwTV)
    TextView pwTV;
    @BindView(R.id.avatarTV)
    TextView avatarTV;
    @BindView(R.id.alarmSwitch)
    Switch alarmSwitch;
    @BindView(R.id.reminderTV)
    TextView reminderTV;
    @BindView(R.id.snoozeTV)
    TextView snoozeTV;
    @BindView(R.id.highlightDelayedCB)
    CheckBox highlightDelayedCB;
    @BindView(R.id.redCB)
    TextView redCB;
    @BindView(R.id.highlightDelayedLL)
    LinearLayout highlightDelayedLL;
    Unbinder unbinder;
    private TextView titleTV;
    private ImageView sortIV;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setUpToolbar();

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void setUpToolbar() {
        titleTV = getActivity().findViewById(R.id.titleTV);
        titleTV.setText(getString(R.string.settings));

        sortIV = getActivity().findViewById(R.id.sortIV);
        sortIV.setVisibility(View.GONE);
    }

    private void showNameDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.change_name));

        final EditText input = new EditText(getActivity().getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(32, 0, 32, 0);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (!input.getText().toString().isEmpty())
                    updateDisplayName(input.getText().toString());
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void showPasswordDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.change_name));

        final EditText input = new EditText(getActivity().getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(32, 0, 32, 0);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (!input.getText().toString().isEmpty())
                    updateDisplayName(input.getText().toString());
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void showAvatarDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.change_name));

        final EditText input = new EditText(getActivity().getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(32, 0, 32, 0);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (!input.getText().toString().isEmpty())
                    updateDisplayName(input.getText().toString());
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void showReminderDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.change_name));

        final EditText input = new EditText(getActivity().getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(32, 0, 32, 0);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (!input.getText().toString().isEmpty())
                    updateDisplayName(input.getText().toString());
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void showSnoozeDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.change_name));

        final EditText input = new EditText(getActivity().getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(32, 0, 32, 0);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (!input.getText().toString().isEmpty())
                    updateDisplayName(input.getText().toString());
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void updateDisplayName(String newName) {
        Toast.makeText(getContext(), newName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.nameTV)
    public void onNameTVClicked() {
        showNameDialog();
    }

    @OnClick(R.id.pwTV)
    public void onPwTVClicked() {
        showPasswordDialog();
    }

    @OnClick(R.id.avatarTV)
    public void onAvatarTVClicked() {
        showAvatarDialog();
    }

    @OnClick(R.id.reminderTV)
    public void onReminderTVClicked() {
        showReminderDialog();
    }

    @OnClick(R.id.snoozeTV)
    public void onSnoozeTVClicked() {
        showSnoozeDialog();
    }

    @OnClick({R.id.redCB, R.id.highlightDelayedLL})
    public void onViewClicked(View view) {
        if (highlightDelayedCB.isChecked())
            highlightDelayedCB.setChecked(false);
        else
            highlightDelayedCB.setChecked(true);
    }
}
