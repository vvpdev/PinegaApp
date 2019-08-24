package com.hfad.pinegaapp.NewsVK;

import android.content.Intent;
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
import com.hfad.pinegaapp.R;


public class DetailsNews extends AppCompatActivity {



    public Toolbar toolbar_main;

    int selected_item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);


        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        selected_item = (Integer) getIntent().getExtras().get("selected_item");

          System.out.println("выбранный элемент = " + selected_item);


        ImageView imageViewDetailsNews = (ImageView) findViewById(R.id.imageViewDetailsNews);
        TextView textViewDetailsNews = (TextView) findViewById(R.id.textViewDetailsNews);


        Glide
                .with(getActivity())
                .load(NewsFragment.arrayAllNews.get(selected_item).getImageNews())
                .into(imageViewDetailsNews);

        textViewDetailsNews.setText(NewsFragment.arrayAllNews.get(selected_item).getTextNews());

    }


    public void onClickPost(View view){

        // url
        String full_url = "https://vk.com/public" +  NewsFragment.arrayAllNews.get(selected_item).getOwner_id()
                                                    + "?w=wall"
                                                    +  NewsFragment.arrayAllNews.get(selected_item).getOwner_id()
                                                    + "_" + NewsFragment.arrayAllNews.get(selected_item).getId();

        Intent intent_open_post = new Intent(Intent.ACTION_VIEW);
        intent_open_post.setData(Uri.parse(full_url));
        startActivity(intent_open_post);
    }



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


    @Override
    public void onBackPressed() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0)    // стек переходов
            fm.popBackStack();
        else
            finish();
    }



    private FragmentActivity getActivity() {
        return this;
    }







}
