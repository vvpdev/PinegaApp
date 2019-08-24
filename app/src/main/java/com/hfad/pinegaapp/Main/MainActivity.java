package com.hfad.pinegaapp.Main;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hfad.pinegaapp.NewsVK.AdapterRecyclerViewNews;
import com.hfad.pinegaapp.Attractions.AttractionsFragment;
import com.hfad.pinegaapp.DateBase.DateBasePinega;
import com.hfad.pinegaapp.NewsVK.News;
import com.hfad.pinegaapp.NewsVK.NewsFragment;
import com.hfad.pinegaapp.R;
import com.hfad.pinegaapp.Shops.ShopsListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import static android.view.View.GONE;


                // иконка приложения задается в манифесте
             // добавлять через image asset

            // добавить метод проверки интернета

            // доделать проверку с инетом - прогресс бар

          // переделать достопримечательности



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    // поставщик действия - для кнопки поделиться
    public ShareActionProvider shareActionProvider;

    // тулбар
    public Toolbar toolbar_main;

    // DrawerLayout макет
    DrawerLayout drawerLayout;

    // переменная для проверки запуска афинхронного потока
    public boolean onStartAsyncTask = false;



    //______________________________________________________________________________________________
    //  проверка интернета приемником вещания
    //______________________________________________________________________________________________
    final String BROADCAST_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            switch (action) {
                case BROADCAST_ACTION: onCheckInternet();   // запускаем поток для проверки инета
                    break;
            }
        }
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // создаем объект для создания и управления версиями БД
      //  dateBasePinega_obj = new DateBasePinega(this);




        // Находим тулбар
        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        // добавляем поддержку ActionBar
        setSupportActionBar(toolbar_main);

        // объект созданного нами класса SectionPagerAdapter -  sectionPagerAdapter
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        //находим ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        // присоединяем адаптер к ViewPager
        viewPager.setAdapter(sectionPagerAdapter);

        // находим TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // присоединяем TabLayout к ViewPager
        tabLayout.setupWithViewPager(viewPager);


        //__________________________________________________________________________________________
        // скроем, т.к. вкладки отображает PagerTabStrip
        tabLayout.setVisibility(GONE);
        //__________________________________________________________________________________________


        // DrawerLayout - макет
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // для боковой шторки
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //NavigationView - боковая шторка
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //__________________________________________________________________________________________
        navigationView.bringToFront();  // вывести вперед / на передний план
        // если не работает слушатель для боковой шторки - то использовать этот метод
        //__________________________________________________________________________________________

        // слушатель для боковой шторки
        navigationView.setNavigationItemSelectedListener(this);


        // регистрируем ресивер
        registerReceiver(receiver,intentFilter);
    }



    // переопределяем регистрацию слушателя в супер методах
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }





        // метод для проверки инета
        public void onCheckInternet (){

            // запускаем проверку интернета и запуск AsyncTask
            if (isInternetAvailable(getApplicationContext()) == false){
                Toast toast = Toast.makeText(getApplicationContext(), "проверьте соединение с интернетом", Toast.LENGTH_LONG);
                toast.show();
            }

            else {

                //______________________________________________________________________________________
                // проверка - запускался ли уже асинхронный метод
                if (onStartAsyncTask == false) {    // если еще не запускался, то
                 //   newsAsyncTask_obj.();
                    onStartAsyncTask = true;        // ставим true - уже запускался
                }
                else {
                    // ничего не делать
                }
            }

        }

        // метод для проверки интернета
        public boolean isInternetAvailable(Context context) {
            ConnectivityManager conMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo i = conMgr.getActiveNetworkInfo();
            if (i == null)  // если вообще ничего нет
                return false;
            if (!i.isConnected())   // если не соединяется
                return false;
            if (!i.isAvailable())   // если не доступно
                return false;
            return true;
        }






    // метод для скрытия и открытия шторки
    @Override
    public void onBackPressed (){

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    // БОКОВОЕ МЕНЮ
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item){

            int id = item.getItemId();

            if (id == R.id.shops_menu){

                // переход на активити списка магазинов
                Intent intentShopsList  = new Intent(this, ShopsListActivity.class);
                startActivity(intentShopsList);
            }

            else if (id == R.id.streets_menu){

            }

            else if (id == R.id.gallery_menu){

            }

            else if (id == R.id.about_app_menu){
                    Intent intentAboutApp = new Intent(this, ActivityAboutApp.class);
                    startActivity(intentAboutApp);
            }

            drawerLayout.closeDrawer(GravityCompat.START);   // закрытие боковой шторки после нажатия

        return true;
    }



    //  метод для меню в тулбаре

    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        // Inflater - наполнитель, "раздуватель"

        // получаем меню для раздувателя - метод раздуть - в параметрах ссылка на макет меню
        // и объект класса меню в параметрах самого метода
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem_Share = menu.findItem(R.id.share_app);

        // задаем кнопке Провайдера действия
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem_Share);

        // передаем в метод текст для неявного интента
        setShareActionIntent ("Поделиться новым приложением Пинега");

        // метод boolean - значит возвращаем - метод создать опции меню
        return super.onCreateOptionsMenu(menu);
    }



    // метод для неявного интента кнопки поделиться
    public void setShareActionIntent (String text){

        Intent neyav_intent = new Intent(Intent.ACTION_SEND); // действие отправить
        neyav_intent.setType("text / plain");   // тип данных - текст / простой
        neyav_intent.putExtra(Intent.EXTRA_TEXT, text); // поместить объект text - объявлен в параметрах метода
        shareActionProvider.setShareIntent(neyav_intent);       // провайдер действия поделиться - задаем наш интент
    }



    // метод для поиска выбранного айтема меню тулбара
    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()){

            case R.id.dev_activity: // переход на страницу разработчика

                Intent developer_intent = new Intent(this, DeveloperActivity.class);
                startActivity(developer_intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



  // класс для ViewPager  - его АДАПТЕР
    private class SectionPagerAdapter extends FragmentPagerAdapter {

        // конструктор для возврата объекта класса FragmentManager - fm
        SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        // тут определяем какие фрагменты и как должны выводиться
      @Override
      public Fragment getItem(int i) {

            switch (i){

                case 0:
                    return new MainFragment();

                case 1:
                    return new NewsFragment();

                case 2:
                    return new AttractionsFragment();
            }
            return null;
      }


        // общее количество фрагментов
      @Override
      public int getCount() {
          return 3;
      }





      // метод для TabLayout  - отображение названия вкладок
      @Override
      public CharSequence getPageTitle (int i){

          switch (i){

              case 0:
                  return getResources().getString(R.string.main_fragment);

              case 1:
                  return getResources().getString(R.string.news_fragment);

              case 2:
                  return getResources().getString(R.string.attractions_fragment);
          }
          return null;

      }


  }





}
