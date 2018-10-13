package com.nanodegree.nahla.capstoneproject.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.Utils.SharedPref;
import com.nanodegree.nahla.capstoneproject.models.Type;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nanodegree.nahla.capstoneproject.Utils.Const.USERS_TABLE;
import static com.nanodegree.nahla.capstoneproject.Utils.Const.USERS_TYPES_TABLE;

public class TypeRVAdapter extends RecyclerView.Adapter<TypeRVAdapter.TypeViewHolder> {

    View itemView;
    ArrayList<Type> types;
    private DatabaseReference databaseRef;
    final String TAG = "Database Log";

    public TypeRVAdapter(ArrayList<Type> types) {
        this.types = types;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_type, parent, false);

        return new TypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TypeViewHolder holder, final int position) {
        holder.emptyVH();

        holder.typeTV.setText(types.get(position).getTypeTitle());
        Drawable drawable = DrawableCompat.wrap(holder.itemView.getContext().getResources().getDrawable(R.drawable.shape_circle));
        DrawableCompat.setTint(drawable, Color.parseColor(types.get(position).getTypeColor()));
        holder.typeTV.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

        if (position < 3)
            holder.cancelFAB.setVisibility(View.GONE);

        holder.cancelFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseRef = FirebaseDatabase.getInstance()
                        .getReference()
                        .child(USERS_TABLE + "/" + new SharedPref(holder.itemView.getContext()).getUserFbId() + "/" + USERS_TYPES_TABLE);
                deleteType(position);
            }
        });
    }

    private void deleteType(int position) {
        databaseRef.child(types.get(position).getTypeId()).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cancelFAB)
        ImageView cancelFAB;
        @BindView(R.id.typeTV)
        TextView typeTV;

        public TypeViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        public void emptyVH() {
            cancelFAB.setVisibility(View.VISIBLE);
            typeTV.setText("");
            Drawable drawable = DrawableCompat.wrap(itemView.getContext().getResources().getDrawable(R.drawable.shape_circle));
            DrawableCompat.setTint(drawable, itemView.getContext().getResources().getColor(R.color.colorDefaultGray));
            typeTV.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

        }
    }

}
