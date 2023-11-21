package com.example.galleryshibed01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends BaseAdapter {

    private List<Animal> dataSource;
    private Context context;
    private LayoutInflater layoutInflater;

    public Adapter(List<Animal> dataSource, Context context) {
        this.dataSource = dataSource;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = layoutInflater
                .inflate(R.layout.item_animal, parent, false);
        Animal animal = dataSource.get(position);
        // Bind Id
        TextView tvName = view.findViewById(R.id.tvAnimalName);
        ImageView imgAnimal = view.findViewById(R.id.imgAnimal);
        // Bind data
        tvName.setText(animal.getName());
        Glide.with(context).load(animal.getImage()).into(imgAnimal);

        return view;
    }
}
