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



    // тулбар
    public Toolbar toolbar_main;


    // текстовые индикаторы
    TextView indikatorListShop;
    TextView indikatorDetailsShop ;

    // объекты фрагментов
    Fragment fragmentListShops;
    Fragment fragmentDetailsShop;

    // транзакция
    FragmentTransaction fragmentTransaction;





    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shops_for_details);


        // задать заголовок активити - название категории

        // списковый массив для категорий
        ArrayList<String> shopsCategoryList = new ArrayList<>();

        shopsCategoryList.add("Продовольственные");
        shopsCategoryList.add("Автозапчасти");
        shopsCategoryList.add("Рыбалка / Туризм");
        shopsCategoryList.add("Компьютеры / Телефоны");
        shopsCategoryList.add("Хоз. товары");


        // актитиви со списком категорий
        ShopsListActivity shopsListActivity = new ShopsListActivity();

        // заголок активити - из массива по выбранной категории
        setTitle(shopsCategoryList.get(shopsListActivity.getSelect_category()));




        // переопределяем объекты фрагментов как элементы классов следующих фрагментов
        fragmentDetailsShop = new FragmentDetailsShop();
        fragmentListShops = new FragmentListShops();


        // Находим тулбар
        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        // добавляем поддержку ActionBar
        setSupportActionBar(toolbar_main);

        // кнопка назад в тулбаре
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        // определить начальное отображание текстовых индикаторов
        indikatorListShop = (TextView) findViewById(R.id.indikatorListShop);
        indikatorDetailsShop = (TextView) findViewById(R.id.indikatorDetailsShop);

        indikatorListShop.setTypeface(null, Typeface.BOLD);
        indikatorListShop.setBackgroundResource(R.color.colorIndicatorTRUE);


        // открываем транзакцию для первоначального заполнения макета
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_DetailsShops, fragmentListShops);
        fragmentTransaction.commit();   // совершить тразакцию
    }


        //метод для текстовых идентификаторов
    @SuppressLint("ResourceAsColor")
    public void onForIdentText (View view){


        // поиск ID элементов на макете
        view.getId();

        switch (view.getId()){

            case R.id.indikatorListShop:
                onGoToFragment(1);
                indikatorListShop.setTypeface(null, Typeface.BOLD); // жирный текст
                indikatorListShop.setBackgroundResource(R.color.colorIndicatorTRUE);

                indikatorDetailsShop.setTypeface(null, Typeface.NORMAL);
                indikatorDetailsShop.setBackgroundResource(R.color.colorIndicatorFALSE);
                break;

            case R.id.indikatorDetailsShop:
                onGoToFragment(2);
                indikatorDetailsShop.setTypeface(null, Typeface.BOLD);  // жирный текст
                indikatorDetailsShop.setBackgroundResource(R.color.colorIndicatorTRUE);

                indikatorListShop.setTypeface(null, Typeface.NORMAL);
                indikatorListShop.setBackgroundResource(R.color.colorIndicatorFALSE);
                break;
            }

    }




    // переход к фрагменту с деталями магазина
    @SuppressLint("ResourceAsColor")
    public void onGoToFragment (int item){

        if (item == 1){     // список магазинов в категории

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_DetailsShops, fragmentListShops);
            fragmentTransaction.addToBackStack("");     // стек переходов
            fragmentTransaction.commit();


            indikatorListShop.setTypeface(null, Typeface.BOLD); // жирный текст
            indikatorListShop.setBackgroundResource(R.color.colorIndicatorTRUE);

            indikatorDetailsShop.setTypeface(null, Typeface.NORMAL);
            indikatorDetailsShop.setBackgroundResource(R.color.colorIndicatorFALSE);
        }

        if (item == 2){     // деталировка выбранного магазина

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_DetailsShops, fragmentDetailsShop);
            fragmentTransaction.addToBackStack("");         // стек переходов
            fragmentTransaction.commit();


            indikatorDetailsShop.setTypeface(null, Typeface.BOLD);  // жирный текст
            indikatorDetailsShop.setBackgroundResource(R.color.colorIndicatorTRUE);

            indikatorListShop.setTypeface(null, Typeface.NORMAL);
            indikatorListShop.setBackgroundResource(R.color.colorIndicatorFALSE);
        }
    }


}
