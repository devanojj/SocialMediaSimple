package com.example.ca3.Adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ca3.Entity.Animal;

import com.example.ca3.R;

import java.util.ArrayList;

public class newAdapter extends RecyclerView.Adapter<newAdapter.MyAdapterViewHolder> {

    public Context c;
    public ArrayList<Animal> arrayList;

    public newAdapter(Context c, ArrayList<Animal> arrayList) {
        this.c=c;
        this.arrayList=arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.animal_lookup,parent,false);

        return new MyAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {
        Animal tlist1 = arrayList.get(position);
        holder.t1.setText(tlist1.getName());
        holder.t2.setText(tlist1.getBreed());
        holder.t3.setText(tlist1.getAge());
        holder.t4.setText(tlist1.getlifestyle());
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView t1;
        public TextView t2;
        public TextView t3;
        public TextView t4;

        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.nameTV);
            t2 = (TextView) itemView.findViewById(R.id.BreedTV);
            t3 = (TextView) itemView.findViewById(R.id.AgeTV);
            t4 = (TextView) itemView.findViewById(R.id.LifestyleTV);
        }
    }

}
