package com.lopezharomr.pokedex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lopezharomr.pokedex.models.Pokemon;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private final ArrayList<Pokemon> dataset;
    private final Context context;

    public ListaPokemonAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView fotoImageView;
        private final TextView nombreTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoImageView = itemView.findViewById(R.id.fotoImageView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
        }
    }
}
