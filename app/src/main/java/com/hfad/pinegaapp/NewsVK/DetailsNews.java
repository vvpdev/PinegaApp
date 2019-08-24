package com.hfad.pinegaapp.NewsVK;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfad.pinegaapp.DateBase.DateBasePinega;
import com.hfad.pinegaapp.R;

import java.util.ArrayList;

public class DetailsNews extends AppCompatActivity {


    // тулбар
    public Toolbar toolbar_main;

    // для id поста
    String id_post_from_DB = "";


    int selected_item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);


        // Находим тулбар
        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        // добавляем поддержку ActionBar
        setSupportActionBar(toolbar_main);

        // кнопка назад в тулбаре
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // получаем интент
        selected_item = (Integer) getIntent().getExtras().get("selected_item");

          System.out.println("выбранный элемент = " + selected_item);


          // элементы с макета
        ImageView imageViewDetailsNews = (ImageView) findViewById(R.id.imageViewDetailsNews);
        TextView textViewDetailsNews = (TextView) findViewById(R.id.textViewDetailsNews);



        //    NewsFragment.arrayAllNews;      // статический массив  !!!!!!!!!


        Glide
                .with(getActivity())
                .load( NewsFragment.arrayAllNews.get(selected_item).getImageNews())
                .into(imageViewDetailsNews);

        textViewDetailsNews.setText( NewsFragment.arrayAllNews.get(selected_item).getTextNews());

    }


    // метод для кнопки - открыть оригинал поста
    public void onClickPost(View view){


        // url
        String full_url = "https://vk.com/public" +  NewsFragment.arrayAllNews.get(selected_item).getOwner_id()
                                                    + "?w=wall"
                                                    +  NewsFragment.arrayAllNews.get(selected_item).getOwner_id()
                                                    + "_" + NewsFragment.arrayAllNews.get(selected_item).getId();

        Intent intent_open_post = new Intent(Intent.ACTION_VIEW);   // неявый интент
        intent_open_post.setData(Uri.parse(full_url));
        startActivity(intent_open_post);
    }



    // переопределенный метод для возврата назад к RecyclerView
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // используется для возврата к RecyclerView
    @Override
    public void onBackPressed() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0)    // стек переходов больше 0, то ....
            fm.popBackStack();  // воврат к предыдущему фрагменту
        else
            finish();
    }


    // возвращает текущую активити для метода onBackPressed
    private FragmentActivity getActivity() {
        return this;
    }







}
