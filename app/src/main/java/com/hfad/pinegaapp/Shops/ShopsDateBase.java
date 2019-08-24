package com.hfad.pinegaapp.Shops;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hfad.pinegaapp.R;


// база данных для деталировки магазинов

    public class ShopsDateBase extends SQLiteOpenHelper {



        // имя БД
        private static final String DB_NAME = "DATE_BASE_PINEGA";

        // версия БД
        private static final int DB_VERSION = 1;



        // конструктор БД
        ShopsDateBase (Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }



        //  ОБЯЗАТЕЛЬНО должен переопределять методы OnCreate и OnUpdate
        @Override
        public void  onCreate (SQLiteDatabase db){

            updateMyDateBase(db, 0, DB_VERSION);

            //______________________________________________________________________________________
            // создаем таблицы



            db.execSQL("CREATE TABLE  SHOPS ("

                    // поля - столбцы
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "     // ключевое поле с автоинкрементом
                    + "ID_SHOP INTEGER, "                        // задаем свой ID для каждого магазина
                    + "TITLE_SHOP TEXT, "                           // название магазина
                    + "IMAGE_RESOURCE_SHOP INTEGER)" );             // адрес для картинки


                // вставляем новые строки
                insertToTableShops(db, 1, "", R.drawable.pospo);
        }




        //метод для заполнения таблицы SHOPS
        public static void insertToTableShops (SQLiteDatabase db, int id_shop, String title_shop, int image_resource_shop){

            // новый объект класса ContentValues (значения контента)
            ContentValues value = new ContentValues();

            value.put("ID_SHOP", id_shop);
            value.put("TITLE_SHOP", title_shop);
            value.put("IMAGE_RESOURCE_SHOP", image_resource_shop);

            // вставляем в БД - в параметрах нужная таблица, хз что, объект value
            db.insert("SHOPS", null, value);
        }



        // апгрейт БД
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            updateMyDateBase (db, oldVersion, newVersion);
        }




        // метод updateMyDateBase  - для обновления БД
        private void updateMyDateBase (SQLiteDatabase db, int oldVersion, int newVersion) {

        }









    }
