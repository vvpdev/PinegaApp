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



    public Toolbar toolbar_main;

    public static int select_category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);

        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ArrayList<String> shopsCategoryList = new ArrayList<>();

        shopsCategoryList.add("Продовольственные");
        shopsCategoryList.add("Автозапчасти");
        shopsCategoryList.add("Рыбалка / Туризм");
        shopsCategoryList.add("Компьютеры / Телефоны");
        shopsCategoryList.add("Хоз. товары");


        ListView listViewShopsCategory = (ListView) findViewById(R.id.shopsCategoryListView);

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



    public void setSelect_category (int position){
        select_category = position;
    }

    public int getSelect_category() {
        return select_category;
    }
}
