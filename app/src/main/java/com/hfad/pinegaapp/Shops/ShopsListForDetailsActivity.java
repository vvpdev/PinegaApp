package com.hfad.pinegaapp.Shops;

import android.annotation.SuppressLint;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hfad.pinegaapp.R;
import com.hfad.pinegaapp.Shops.FragmentDetailsShop;
import com.hfad.pinegaapp.Shops.FragmentListShops;
import com.hfad.pinegaapp.Shops.ShopsListActivity;

import java.util.ArrayList;

public class ShopsListForDetailsActivity extends AppCompatActivity {


    public Toolbar toolbar_main;


    TextView indikatorListShop;
    TextView indikatorDetailsShop ;

    Fragment fragmentListShops;
    Fragment fragmentDetailsShop;

    FragmentTransaction fragmentTransaction;





    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shops_for_details);



        ArrayList<String> shopsCategoryList = new ArrayList<>();

        shopsCategoryList.add("Продовольственные");
        shopsCategoryList.add("Автозапчасти");
        shopsCategoryList.add("Рыбалка / Туризм");
        shopsCategoryList.add("Компьютеры / Телефоны");
        shopsCategoryList.add("Хоз. товары");


        ShopsListActivity shopsListActivity = new ShopsListActivity();

        setTitle(shopsCategoryList.get(shopsListActivity.getSelect_category()));


        fragmentDetailsShop = new FragmentDetailsShop();
        fragmentListShops = new FragmentListShops();


        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar_main);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        indikatorListShop = (TextView) findViewById(R.id.indikatorListShop);
        indikatorDetailsShop = (TextView) findViewById(R.id.indikatorDetailsShop);

        indikatorListShop.setTypeface(null, Typeface.BOLD);
        indikatorListShop.setBackgroundResource(R.color.colorIndicatorTRUE);



        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_DetailsShops, fragmentListShops);
        fragmentTransaction.commit();   // совершить тразакцию
    }



    @SuppressLint("ResourceAsColor")
    public void onForIdentText (View view){



        view.getId();

        switch (view.getId()){

            case R.id.indikatorListShop:
                onGoToFragment(1);
                indikatorListShop.setTypeface(null, Typeface.BOLD);
                indikatorListShop.setBackgroundResource(R.color.colorIndicatorTRUE);

                indikatorDetailsShop.setTypeface(null, Typeface.NORMAL);
                indikatorDetailsShop.setBackgroundResource(R.color.colorIndicatorFALSE);
                break;

            case R.id.indikatorDetailsShop:
                onGoToFragment(2);
                indikatorDetailsShop.setTypeface(null, Typeface.BOLD);
                indikatorDetailsShop.setBackgroundResource(R.color.colorIndicatorTRUE);

                indikatorListShop.setTypeface(null, Typeface.NORMAL);
                indikatorListShop.setBackgroundResource(R.color.colorIndicatorFALSE);
                break;
            }

    }




    @SuppressLint("ResourceAsColor")
    public void onGoToFragment (int item){

        if (item == 1){

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_DetailsShops, fragmentListShops);
            fragmentTransaction.addToBackStack("");     // стек переходов
            fragmentTransaction.commit();


            indikatorListShop.setTypeface(null, Typeface.BOLD); // жирный текст
            indikatorListShop.setBackgroundResource(R.color.colorIndicatorTRUE);

            indikatorDetailsShop.setTypeface(null, Typeface.NORMAL);
            indikatorDetailsShop.setBackgroundResource(R.color.colorIndicatorFALSE);
        }

        if (item == 2){

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_DetailsShops, fragmentDetailsShop);
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();


            indikatorDetailsShop.setTypeface(null, Typeface.BOLD);
            indikatorDetailsShop.setBackgroundResource(R.color.colorIndicatorTRUE);

            indikatorListShop.setTypeface(null, Typeface.NORMAL);
            indikatorListShop.setBackgroundResource(R.color.colorIndicatorFALSE);
        }
    }


}
