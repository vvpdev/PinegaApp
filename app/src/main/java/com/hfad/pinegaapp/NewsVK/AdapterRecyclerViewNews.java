package com.hfad.pinegaapp.NewsVK;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hfad.pinegaapp.R;

import java.util.List;



        // адаптер для RecyclerView Новости


    public class AdapterRecyclerViewNews extends RecyclerView.Adapter <AdapterRecyclerViewNews.ViewHolder> {

    // раздуватель макета
    private LayoutInflater layoutInflater_objNews;

    // массив с элементами класса News
    private List <News> newsList;

    // переменная, через которую будет раздуваться макет и к которой привязан слушатель
    public View view;




    // конструктор адаптера
    public AdapterRecyclerViewNews(Context context, List<News> newsList){

        // переопределяем
        this.newsList = newsList;

        // передаем раздувателю макета контекст
        this.layoutInflater_objNews = LayoutInflater.from(context);
    }


    // создаем Владельца Вида - ViewHolder
    @NonNull
    @Override
    public AdapterRecyclerViewNews.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        view = layoutInflater_objNews.inflate(R.layout.card_layout_news, parent, false);
        return new AdapterRecyclerViewNews.ViewHolder(view);
    }


    // привязываем владельца вида ViewHolder к объекту класса Attractions
    @Override
    public void onBindViewHolder (final AdapterRecyclerViewNews.ViewHolder holder, final int position){

        // через объект news получаем элемент массива newsList
        News news = newsList.get(position);

        // дальше передаем данные держателю вида через геттеры
        holder.textViewNews.setText(news.getTitleNews());

        // привязываем imageViewCard через holder (владельца вида)
        ImageView imageViewCardNews = holder.imageViewCardNews;

            // загрузка через Glide
            Glide
                    .with(view)
                    .load(news.getImageNews())
                 //   .centerCrop()   // масштабирует изображение равномерно, чтоб заполняло область
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(imageViewCardNews);

        //__________________________________________________________________________________________
        // слушатель нажатий на карточку
        holder.cardViewNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // передаем через интент
                Intent intent = new Intent(view.getContext(), DetailsNews.class);

                // через позицию ключ - "selected_id_news"
                intent.putExtra("selected_item", (int) position);

                //запускаем интент через получение контекста элементом view
                view.getContext().startActivity(intent);

            }
        });


    }


    // класс держателя вида ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageViewCardNews;
        final TextView textViewNews;
        final CardView cardViewNews;


        ViewHolder(View view){
            super(view);

            // находим элементы на макете
            imageViewCardNews = (ImageView) view.findViewById(R.id.imageViewNews);
            textViewNews = (TextView) view.findViewById(R.id.textViewNews);
            cardViewNews = (CardView) view.findViewById(R.id.CardViewNews);
        }
    }


    // возвращаем количество элементов  - привязано к размеру массива
    @Override
    public int getItemCount() {

        return  newsList.size();
    }
}
