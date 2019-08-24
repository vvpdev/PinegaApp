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



public class FragmentListShops extends Fragment {


    ListView listShopsInCategory;

    ArrayList <String> arrayListShopsInCategory = new ArrayList<>();

    static ArrayList <Integer> arrayIdShops = new ArrayList<>();

    int selected_category;

    static int shop_from_selection;

    ShopsListActivity shopsListActivity = new ShopsListActivity();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_shops, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listShopsInCategory = (ListView) view.findViewById(R.id.listShopsInCategory);


        onSelectListFromDB onSelectListFromDB = new onSelectListFromDB();
        onSelectListFromDB.start();
    }


    public int getPosition_shop_from_selection (){
        return shop_from_selection;
    }



    public class onSelectListFromDB extends Thread {



        @Override
        public void run () {


            arrayIdShops.clear();
            arrayListShopsInCategory.clear();

            selected_category = shopsListActivity.getSelect_category();

            SQLiteOpenHelper sqLiteOpenHelper_obj = new DateBasePinega(getActivity());

            SQLiteDatabase db = sqLiteOpenHelper_obj.getReadableDatabase();

            final Cursor cursor_obj = db.query(

                    // таблица
                    "SHOPS",

                    null,

                    null,

                    null,

                    null, null, null );


            try {
                cursor_obj.moveToFirst();

                while (!cursor_obj.isAfterLast()){

                    // для выборки по подкатегории
                    int select_id_podcategory_fromDB = cursor_obj.getInt(1);

                    if (select_id_podcategory_fromDB == selected_category){

                        arrayIdShops.add(cursor_obj.getInt(0));

                        arrayListShopsInCategory.add(cursor_obj.getString(2));

                        cursor_obj.moveToNext();

                    }

                    else {
                        cursor_obj.moveToNext();
                    }

                }
            }

            finally {
                db.close();
                cursor_obj.close();
            }


            onSetDataToListView (arrayListShopsInCategory);
        }




        public  void onSetDataToListView (final ArrayList arrayListShopsInCategory) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<>(

                                getActivity(),

                                android.R.layout.simple_list_item_1,

                                arrayListShopsInCategory
                        );

                        listShopsInCategory.setAdapter(arrayAdapter);

                        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                shop_from_selection = position;

                                ((ShopsListForDetailsActivity)getActivity()).onGoToFragment(2);

                            }
                        };

                        listShopsInCategory.setOnItemClickListener(listener);

                    }
                });
        }
    }
}