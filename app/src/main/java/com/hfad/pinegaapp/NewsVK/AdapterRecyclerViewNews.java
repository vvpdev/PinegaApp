package com.hfad.pinegaapp.NewsVK;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hfad.pinegaapp.R;

import java.util.List;


    public class AdapterRecyclerViewNews extends RecyclerView.Adapter <AdapterRecyclerViewNews.ViewHolder> {

    private LayoutInflater layoutInflater_objNews;

    private List <News> newsList;

    public View view;




    // конструктор адаптера
    public AdapterRecyclerViewNews(Context context, List<News> newsList){

        this.newsList = newsList;

        this.layoutInflater_objNews = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public AdapterRecyclerViewNews.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        view = layoutInflater_objNews.inflate(R.layout.card_layout_news, parent, false);
        return new AdapterRecyclerViewNews.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder (final AdapterRecyclerViewNews.ViewHolder holder, final int position){

        News news = newsList.get(position);

        holder.textViewNews.setText(news.getTitleNews());

        ImageView imageViewCardNews = holder.imageViewCardNews;


            Glide
                    .with(view)
                    .load(news.getImageNews())
                 //   .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(imageViewCardNews);


        // слушатель нажатий на карточку
        holder.cardViewNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), DetailsNews.class);

                intent.putExtra("selected_item", (int) position);

                view.getContext().startActivity(intent);

            }
        });


    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageViewCardNews;
        final TextView textViewNews;
        final CardView cardViewNews;


        ViewHolder(View view){
            super(view);

            imageViewCardNews = (ImageView) view.findViewById(R.id.imageViewNews);
            textViewNews = (TextView) view.findViewById(R.id.textViewNews);
            cardViewNews = (CardView) view.findViewById(R.id.CardViewNews);
        }
    }


    @Override
    public int getItemCount() {

        return  newsList.size();
    }
}
