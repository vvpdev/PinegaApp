package com.hfad.pinegaapp.Shops;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hfad.pinegaapp.R;

import java.util.ArrayList;

public class ShopsListActivity extends AppCompatActivity {


    // тулбар
    public Toolbar toolbar_main;

    // для слушателя - выбранная категория      ОБЯЗАТЕЛЬНО static !!!!!!!!!!!!!!!!!!
    public static int select_category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);

        // Находим тулбар
        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        // добавляем поддержку ActionBar
        setSupportActionBar(toolbar_main);

        // кнопка назад в тулбаре
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // списковый массив для категорий
        ArrayList<String> shopsCategoryList = new ArrayList<>();

        shopsCategoryList.add("Продовольственные");
        shopsCategoryList.add("Автозапчасти");
        shopsCategoryList.add("Рыбалка / Туризм");
        shopsCategoryList.add("Компьютеры / Телефоны");
        shopsCategoryList.add("Хоз. товары");


        // находим ListView
        ListView listViewShopsCategory = (ListView) findViewById(R.id.shopsCategoryListView);

        // адаптер
        ArrayAdapter<String> adapterViewShopsCategory = new ArrayAdapter<>(

                getApplicationContext(),

                android.R.layout.simple_expandable_list_item_1,

                shopsCategoryList
        );


        listViewShopsCategory.setAdapter(adapterViewShopsCategory);

        AdapterView.OnItemClickListener onSelectedCategory = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                setSelect_category(position);

                Intent intent = new Intent(ShopsListActivity.this, ShopsListForDetailsActivity.class);
                startActivity(intent);
            }
        };

        listViewShopsCategory.setOnItemClickListener(onSelectedCategory);

}


        // переопределяем выбранную подкатегорию
    public void setSelect_category (int position){
        select_category = position;
    }


        //  возвращаем полученную подкатегорию
    public int getSelect_category() {
        return select_category;
    }
}
