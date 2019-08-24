package com.hfad.pinegaapp.Attractions;

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
import com.hfad.pinegaapp.Attractions.Attractions;
import com.hfad.pinegaapp.R;

public class DetailsAttractions extends AppCompatActivity {

    // тулбар
    public Toolbar toolbar_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_recycler_view);

        // Находим тулбар
        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        // добавляем поддержку ActionBar
        setSupportActionBar(toolbar_main);

        // кнопка назад в тулбаре
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);






        //__________________________________________________________________________________________
        // заполняем макет данными


        // получаем интент
        int id_selected_item = (Integer) getIntent().getExtras().get("selected_id");


        // объект массива из класса Attractions
        Attractions attractions_obj = Attractions.attractionsList[id_selected_item];

        // заголовок Активити - выбранный элемент из RecyclerView
        setTitle(attractions_obj.getAttract_title());




        // ссылки на объекты макета
        //__________________________________________________________________________________________

        ImageView imageViewHeader = (ImageView) findViewById(R.id.imageHeaderAttactions);

        TextView textAttraction1 = (TextView) findViewById(R.id.textAttraction1);
        TextView textAttraction2 = (TextView) findViewById(R.id.textAttraction2);
        TextView textAttraction3 = (TextView) findViewById(R.id.textAttraction3);
        TextView textAttraction4 = (TextView) findViewById(R.id.textAttraction4);
        TextView textAttraction5 = (TextView) findViewById(R.id.textAttraction5);


        ImageView imageView1 = (ImageView) findViewById(R.id.imageArticle1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageArticle2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageArticle3);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageArticle4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageArticle5);




        // заполняем макет
        //__________________________________________________________________________________________

        // изображение Header
        Glide
                .with(this) // в этой активности
                .load(attractions_obj.getAttract_image())
                .centerCrop()
                .into(imageViewHeader);

        // первая часть текста
        if (attractions_obj.getText_article1() != 0){
            textAttraction1.setText(attractions_obj.getText_article1());}
        else {
            textAttraction1.setVisibility(View.GONE);   // иначе скрыть
        }

        // вторая часть текста
        if (attractions_obj.getText_article2() != 0){
            textAttraction2.setText(attractions_obj.getText_article2());}
        else {
            textAttraction2.setVisibility(View.GONE);   // иначе скрыть
        }

        // третья часть текста
        if (attractions_obj.getText_article3() != 0){
            textAttraction3.setText(attractions_obj.getText_article3());}
        else {
            textAttraction3.setVisibility(View.GONE);   // иначе скрыть
        }

        // четвертая часть текста
        if (attractions_obj.getText_article4() != 0){
           textAttraction4.setText(attractions_obj.getText_article4());}
         else {
            textAttraction4.setVisibility(View.GONE);   // иначе скрыть
         }

        // пятая часть текста
        if (attractions_obj.getText_article5() != 0){
            textAttraction5.setText(attractions_obj.getText_article5());}
        else {
            textAttraction5.setVisibility(View.GONE);   // иначе скрыть
        }




        //первая картинка
        if (attractions_obj.getImage_in_text1() != 0){
            Glide
                    .with(getApplicationContext())
                    .load(attractions_obj.getImage_in_text1())
                    .centerCrop()
                    .into(imageView1); }
        else {
            imageView1.setVisibility(View.GONE);    // скрыть, если нет картинки
        }


        //вторая картинка
        if (attractions_obj.getImage_in_text2() != 0){
            Glide
                    .with(getApplicationContext())
                    .load(attractions_obj.getImage_in_text2())
                    .centerCrop()
                    .into(imageView2); }
        else {
            imageView2.setVisibility(View.GONE);    // скрыть, если нет картинки
        }


        //третья картинка
        if (attractions_obj.getImage_in_text3() != 0){
            Glide
                    .with(getApplicationContext())
                    .load(attractions_obj.getImage_in_text3())
                    .centerCrop()
                    .into(imageView3); }
        else {
            imageView3.setVisibility(View.GONE);    // скрыть, если нет картинки
        }


        //четвертая картинка
        if (attractions_obj.getImage_in_text4() != 0){
            Glide
                    .with(getApplicationContext())
                    .load(attractions_obj.getImage_in_text4())
                    .centerCrop()
                    .into(imageView4); }

        else {
            imageView4.setVisibility(View.GONE);    // скрыть, если нет картинки
        }

        //пятая картинка
        if (attractions_obj.getImage_in_text5() != 0){
            Glide
                    .with(getApplicationContext())
                    .load(attractions_obj.getImage_in_text5())
                    .centerCrop()
                    .into(imageView5); }

        else {
            imageView5.setVisibility(View.GONE);    // скрыть, если нет картинки
        }

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
