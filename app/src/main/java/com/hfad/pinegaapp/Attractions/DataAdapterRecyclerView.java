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


// внизу прописан интерфейс для обработчика нажатий RecyclerView

   public class DataAdapterRecyclerView extends RecyclerView.Adapter <DataAdapterRecyclerView.ViewHolder> {


        // раздуватель макета
    private LayoutInflater layoutInflater_obj;

    // массив с элементами класса Attactions
    private List <Attractions> attractionsList;

    // переменная, через которую бедт раздуваться макет и к которой привязан слущатель
    public  View view;


    // конструктор адаптера
    public DataAdapterRecyclerView(Context context, List<Attractions> attractions){

        // переопределяем
        this.attractionsList = attractions;

        // передаем раздувателю макета контекст
        this.layoutInflater_obj = LayoutInflater.from(context);
    }


    // создаем Владельца Вида - ViewHolder
    @Override
    public DataAdapterRecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

            view = layoutInflater_obj.inflate(R.layout.card_layout, parent, false);
            return new ViewHolder(view);
    }



    // привязываем владельца вида ViewHolder к объекту класса Attractions
    @Override
    public void onBindViewHolder (final ViewHolder holder, final int position){

        // через объект attractions получаем элемент массива attractionsList
        Attractions attractions = attractionsList.get(position);

        // дальше передаем данные держателю вида через геттеры
        holder.textViewTitleCard.setText(attractions.getAttract_title());
        holder.textViewSubTitleCard.setText(attractions.getAttract_sub_title());


        // привязываем imageViewCard через holder (владельца вида)
        ImageView imageViewCard = holder.imageViewCard;

        // загрузка через Glide
        Glide.with(view)
                .load(attractions.getAttract_image())
                .centerCrop()   // масштабирует изображение равномерно, чтоб заполняло область
                .into(imageViewCard);



        //__________________________________________________________________________________________
        // слушатель нажатий на карточку
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // передаем через интент
                Intent intent = new Intent(view.getContext(), DetailsAttractions.class);

                                // через позицию ключ - "selected_id"
                intent.putExtra("selected_id", (int) position);

                //запускаем интент через получение контекста элементом view
                view.getContext().startActivity(intent);
            }
        });
    }




            // класс держателя вида ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageViewCard;
        final TextView textViewTitleCard, textViewSubTitleCard;
        final CardView cardView;

        ViewHolder(View view){
        super(view);

                // находим элементы на макете
                imageViewCard = (ImageView) view.findViewById(R.id.imageViewCard);
                textViewTitleCard = (TextView) view.findViewById(R.id.textViewCardTitle);
                textViewSubTitleCard = (TextView) view.findViewById(R.id.textViewCardSubTitle);
                cardView = (CardView) view.findViewById(R.id.CardViewAttractions);
        }
    }


    // возвращаем количество элементов  - привязано к размеру массива
    @Override
    public int getItemCount() {

        return  attractionsList.size();
    }



}
