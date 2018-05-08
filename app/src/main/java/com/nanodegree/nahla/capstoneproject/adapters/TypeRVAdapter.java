package com.nanodegree.nahla.capstoneproject.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.models.Type;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeRVAdapter extends RecyclerView.Adapter<TypeRVAdapter.TypeViewHolder> {

    View itemView;
    ArrayList<Type> types;

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
                types.remove(types.get(position));
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
