package com.hfad.pinegaapp.Attractions;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfad.pinegaapp.R;

import java.util.List;



   public class DataAdapterRecyclerView extends RecyclerView.Adapter <DataAdapterRecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater_obj;

    private List <Attractions> attractionsList;

    public  View view;


    // конструктор класса
    public DataAdapterRecyclerView(Context context, List<Attractions> attractions){

        this.attractionsList = attractions;

        this.layoutInflater_obj = LayoutInflater.from(context);
    }


    @Override
    public DataAdapterRecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

            view = layoutInflater_obj.inflate(R.layout.card_layout, parent, false);
            return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder (final ViewHolder holder, final int position){

        Attractions attractions = attractionsList.get(position);


        holder.textViewTitleCard.setText(attractions.getAttract_title());
        holder.textViewSubTitleCard.setText(attractions.getAttract_sub_title());

        ImageView imageViewCard = holder.imageViewCard;


        Glide.with(view)
                .load(attractions.getAttract_image())
                .centerCrop()
                .into(imageViewCard);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), DetailsAttractions.class);

                intent.putExtra("selected_id", (int) position);

                view.getContext().startActivity(intent);
            }
        });
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageViewCard;
        final TextView textViewTitleCard, textViewSubTitleCard;
        final CardView cardView;

        ViewHolder(View view){
        super(view);

                imageViewCard = (ImageView) view.findViewById(R.id.imageViewCard);
                textViewTitleCard = (TextView) view.findViewById(R.id.textViewCardTitle);
                textViewSubTitleCard = (TextView) view.findViewById(R.id.textViewCardSubTitle);
                cardView = (CardView) view.findViewById(R.id.CardViewAttractions);
        }
    }


    @Override
    public int getItemCount() {

        return  attractionsList.size();
    }



}
