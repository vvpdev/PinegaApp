package com.hfad.pinegaapp.Shops;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hfad.pinegaapp.R;


    public class ShopsDateBase extends SQLiteOpenHelper {


        private static final String DB_NAME = "DATE_BASE_PINEGA";

        private static final int DB_VERSION = 1;


        ShopsDateBase (Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void  onCreate (SQLiteDatabase db){

            updateMyDateBase(db, 0, DB_VERSION);


            db.execSQL("CREATE TABLE  SHOPS ("

                    // поля - столбцы
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ID_SHOP INTEGER, "
                    + "TITLE_SHOP TEXT, "
                    + "IMAGE_RESOURCE_SHOP INTEGER)" );



                insertToTableShops(db, 1, "", R.drawable.pospo);
        }





        public static void insertToTableShops (SQLiteDatabase db, int id_shop, String title_shop, int image_resource_shop){

            ContentValues value = new ContentValues();

            value.put("ID_SHOP", id_shop);
            value.put("TITLE_SHOP", title_shop);
            value.put("IMAGE_RESOURCE_SHOP", image_resource_shop);

            db.insert("SHOPS", null, value);
        }




        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            updateMyDateBase (db, oldVersion, newVersion);
        }



        private void updateMyDateBase (SQLiteDatabase db, int oldVersion, int newVersion) {

        }









    }
