package com.example.ca3.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ca3.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView n, b, a, l;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        n = itemView.findViewById(R.id.nameTV);
        b = itemView.findViewById(R.id.BreedTV);
        a = itemView.findViewById(R.id.AgeTV);
        l = itemView.findViewById(R.id.LifestyleTV);


    }
}
