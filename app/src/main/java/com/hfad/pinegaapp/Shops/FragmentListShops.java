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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hfad.pinegaapp.DateBase.DateBasePinega;
import com.hfad.pinegaapp.R;

import java.util.ArrayList;


        // фрагмент для списка магазинов в категории


public class FragmentListShops extends Fragment {



    // ListView для списка магазинов в категории
    ListView listShopsInCategory;

    // для заголовков магазинов в категории
    ArrayList <String> arrayListShopsInCategory = new ArrayList<>();

    // для id магазинов в категории
    static ArrayList <Integer> arrayIdShops = new ArrayList<>();

    // выбранная категория из активити со списком
    int selected_category;

    // выбранный магазин из listener списка
    static int shop_from_selection;                            // static  !!!!!!!!!!!!!!!


    // объект активити со списком категорий
    ShopsListActivity shopsListActivity = new ShopsListActivity();


    // добавляем, чтоб во фрагменте можно было работать с элементами View
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_shops, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // находим ListView на макете
        listShopsInCategory = (ListView) view.findViewById(R.id.listShopsInCategory);


        // запускаем дополнительный поток для запроса к БД и обновления UI
        onSelectListFromDB onSelectListFromDB = new onSelectListFromDB();
        onSelectListFromDB.start();
    }



    // вернуть позицию выбранного магазина из выборки в ListView во фрагменте
    public int getPosition_shop_from_selection (){
        return shop_from_selection;
    }





    // класс для запроса к БД - в отдельном потоке
    public class onSelectListFromDB extends Thread {


        // переопределяем метод run
        @Override
        public void run () {


            // очистить данные
            arrayIdShops.clear();
            arrayListShopsInCategory.clear();


            // выбранная категория  из ShopListActivity
            selected_category = shopsListActivity.getSelect_category();

            // помощник открытия БД
            SQLiteOpenHelper sqLiteOpenHelper_obj = new DateBasePinega(getActivity());

            // оъект для БД
            SQLiteDatabase db = sqLiteOpenHelper_obj.getReadableDatabase();


            // курсор - запрос
            final Cursor cursor_obj = db.query(

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
                    int select_id_podcategory_fromDB = cursor_obj.getInt(1);

                    if (select_id_podcategory_fromDB == selected_category){

                        arrayIdShops.add(cursor_obj.getInt(0));                     // получаем id магазинов

                        arrayListShopsInCategory.add(cursor_obj.getString(2));   // получаем заголовки магазинов из выборки

                        cursor_obj.moveToNext();    // к следующей трочке

                    }

                    else {
                        cursor_obj.moveToNext();    // к следующей трочке
                    }

                }
            }

            finally {
                db.close();
                cursor_obj.close();
            }



            // запускаем метод для обновления UI
            onSetDataToListView (arrayListShopsInCategory);
        }




        //____________________________________________________________________________________________________
        // чтоб заполнить элементы интерфейса в главном потоке - определим отдельный метод onSetDataToListView
        //____________________________________________________________________________________________________

        public  void onSetDataToListView (final ArrayList arrayListShopsInCategory) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // адаптер для ListView
                        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<>(

                                getActivity(),

                                android.R.layout.simple_list_item_1,

                                arrayListShopsInCategory
                        );

                        listShopsInCategory.setAdapter(arrayAdapter);


                        // слушатель
                        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                shop_from_selection = position;     // выбранный магазин

                                // перейти к фрагменту с деталями
                                ((ShopsListForDetailsActivity)getActivity()).onGoToFragment(2);

                            }
                        };

                        listShopsInCategory.setOnItemClickListener(listener);

                    }
                });

        }

    }


}