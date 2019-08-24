package com.hfad.pinegaapp.Shops;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfad.pinegaapp.DateBase.DateBasePinega;
import com.hfad.pinegaapp.R;


// фрагмент для деталировки магазина


    // добавить часы работы!!!!

public class FragmentDetailsShop extends Fragment {



    // для заполнения фрагмента
    String title_shop;
    int resource_image_shop;
    String description_shop;


    // элементы макета
    TextView textTitleShop;
    ImageView imageInDetailsShop;
    TextView textDescriptionShop;



    FragmentListShops fragmentListShops_obj = new FragmentListShops();



    // добавляем, чтоб во фрагменте можно было работать с элементами View
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_shop, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        // получаем элементы View
        textTitleShop = (TextView) view.findViewById(R.id.textTitleShop);
        imageInDetailsShop = (ImageView) view.findViewById(R.id.imageInDetailsShop);
        textDescriptionShop = (TextView) view.findViewById(R.id.textDescriptionShop);


        SelectDetailsFromDB selectDetailsFromDB = new SelectDetailsFromDB();

        // запускаем отдельный поток
        selectDetailsFromDB.start();
    }






    // класс для запроса к БД - в отдельном потоке
    public class SelectDetailsFromDB extends Thread {


        // переопределяем метод run
       @Override
       public void run (){

           // позиция выбранного магазина из ListView во фрагменте
           int position_shop = fragmentListShops_obj.getPosition_shop_from_selection();

           System.out.println("позиция магазина из списка = " + position_shop);


           // id выбранного магазина из БД
           int id_selected_shop = fragmentListShops_obj.arrayIdShops.get(position_shop);

           System.out.println("id магазина из БД = " + id_selected_shop);


           // помощник открытия БД
           SQLiteOpenHelper sqLiteOpenHelper_obj = new DateBasePinega(getActivity());

           // оъект для БД
           SQLiteDatabase db = sqLiteOpenHelper_obj.getReadableDatabase();


           // курсор - запрос
           Cursor cursor_obj = db.query(

                   // таблица
                   "SHOPS",

                   null,

                   null,

                   null,

                   null, null, null );



           try {

               cursor_obj.moveToFirst();    // перейти к первой строке

               while (!cursor_obj.isAfterLast()){   // пока не дошел до конца таблицы

                   // для выборки по подкатегории
                   int id_shop = cursor_obj.getInt(0);

                   if (id_shop == id_selected_shop){

                       title_shop = cursor_obj.getString(2);
                       resource_image_shop = cursor_obj.getInt(3);
                       description_shop = cursor_obj.getString(4);

                       break;  // закончить цикл, если получены данные
                   }

                   else {
                       cursor_obj.moveToNext();    // к следующей строке
                   }

               }
           }
           // в конце закрываем подключение к БД
           finally {
               db.close();
               cursor_obj.close();
           }


           // запускаем метод для обновления UI
           onFillLayout(title_shop, resource_image_shop, description_shop);
       }



       //___________________________________________________________________________________________
       // чтоб заполнить элементы интерфейса в главном потоке - определим отдельный метод onFillLayout
       //___________________________________________________________________________________________

    public void onFillLayout (final String title_shop, final int resource_image_shop, final String description_shop) {

           // получаем главную активити для фрагмента - вызываем метод runOnUiThread - запустить в User Interface потоке
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {

                textTitleShop.setText(title_shop);

                // загрузка через Glide
                Glide.with(getActivity())
                        .load(resource_image_shop)
                        .centerCrop()   // масштабирует изображение равномерно, чтоб заполняло область
                        .into(imageInDetailsShop);

                textDescriptionShop.setText(description_shop);
            }

        });

    }

    }

}