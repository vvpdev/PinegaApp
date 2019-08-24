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



public class FragmentDetailsShop extends Fragment {



    String title_shop;
    int resource_image_shop;
    String description_shop;


    TextView textTitleShop;
    ImageView imageInDetailsShop;
    TextView textDescriptionShop;

    FragmentListShops fragmentListShops_obj = new FragmentListShops();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_shop, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textTitleShop = (TextView) view.findViewById(R.id.textTitleShop);
        imageInDetailsShop = (ImageView) view.findViewById(R.id.imageInDetailsShop);
        textDescriptionShop = (TextView) view.findViewById(R.id.textDescriptionShop);

        SelectDetailsFromDB selectDetailsFromDB = new SelectDetailsFromDB();

        selectDetailsFromDB.start();
    }




    public class SelectDetailsFromDB extends Thread {

       @Override
       public void run (){


           int position_shop = fragmentListShops_obj.getPosition_shop_from_selection();

           System.out.println("позиция магазина из списка = " + position_shop);

           int id_selected_shop = fragmentListShops_obj.arrayIdShops.get(position_shop);

           System.out.println("id магазина из БД = " + id_selected_shop);

           SQLiteOpenHelper sqLiteOpenHelper_obj = new DateBasePinega(getActivity());

           SQLiteDatabase db = sqLiteOpenHelper_obj.getReadableDatabase();


           Cursor cursor_obj = db.query(


                   "SHOPS",

                   null,

                   null,

                   null,

                   null, null, null );



           try {

               cursor_obj.moveToFirst();

               while (!cursor_obj.isAfterLast()){


                   int id_shop = cursor_obj.getInt(0);

                   if (id_shop == id_selected_shop){

                       title_shop = cursor_obj.getString(2);
                       resource_image_shop = cursor_obj.getInt(3);
                       description_shop = cursor_obj.getString(4);

                       break;
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



           onFillLayout(title_shop, resource_image_shop, description_shop);
       }



    public void onFillLayout (final String title_shop, final int resource_image_shop, final String description_shop) {

        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {

                textTitleShop.setText(title_shop);


                Glide.with(getActivity())
                        .load(resource_image_shop)
                        .centerCrop()
                        .into(imageInDetailsShop);

                textDescriptionShop.setText(description_shop);
            }
        });
    }
    }
}