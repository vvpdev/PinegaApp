package com.hfad.pinegaapp.DateBase;




        // клас для Базы Данных


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hfad.pinegaapp.R;

public class DateBasePinega extends  SQLiteOpenHelper{



        // имя БД
    public static final String DB_NAME = "PinegaDateBase";

    // версия БД
    public static final int DB_VERSION = 1;



    // конструктор БД
    public DateBasePinega(Context context){
         super(context, DB_NAME, null,  DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        // первоначальные параметры
         updateDateBase(db, 0, DB_VERSION);



        // таблица - данные для магазинов
        db.execSQL("CREATE TABLE SHOPS ("
                                                                                            //_________________________
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT," // ключевое поле по дефолту      счет идет с 1 !!!!!!!!!!!!!
                + " CATEGORY_SHOP INTEGER,"     // категория (группа) магазина
                + " TITLE_SHOP TEXT,"           // текст поста
                + " IMAGE_RESOURCE_SHOP INTEGER,"     // url для картинки
                + " DESCRIPTION_SHOP TEXT)"  );      // id паблика



        // вставляем через метод

        insertTableShops(db, 0, "ПОСПО", R.drawable.rybalka, "продукты, бакалея, кондитерские изделия");
        insertTableShops(db, 0, "Кубик", R.drawable.kubik, "продукты, бакалея, кондитерские изделия");
        insertTableShops(db, 1, "Запчасти", R.drawable.rybalka, "запчасти для авто");
        insertTableShops(db, 2, "Рыбацкое подворье", R.drawable.rybalka, "товары для отдыха, охоты и рыбалки");
        insertTableShops(db, 3, "Мобильный бум", R.drawable.m_bum, "телефоны и компьютеры, оргтехника");
        insertTableShops(db, 4, "Хозтовары ПОСПО", R.drawable.hoz_tov, "хозяйственные и строительные товары, все для дома");
        insertTableShops(db, 0, "Девятка", R.drawable.rybalka, "продукты, бакалея, кондитерские изделия");
    }






    // метод для заполнения БД
    public static void insertTableShops (SQLiteDatabase db, int category, String title, int resource_image, String description){

        ContentValues values = new ContentValues();

        values.put("CATEGORY_SHOP", category);
        values.put("TITLE_SHOP", title);
        values.put("IMAGE_RESOURCE_SHOP", resource_image);
        values.put("DESCRIPTION_SHOP", description);

        db.insert("SHOPS", null, values);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         updateDateBase(db, oldVersion, newVersion);
    }



    private void updateDateBase (SQLiteDatabase db, int OldVersion, int newVersion) {

    }





}
