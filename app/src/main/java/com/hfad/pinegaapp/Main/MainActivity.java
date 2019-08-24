package com.hfad.pinegaapp.Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hfad.pinegaapp.Attractions.AttractionsFragment;
import com.hfad.pinegaapp.NewsVK.NewsFragment;
import com.hfad.pinegaapp.R;
import com.hfad.pinegaapp.Shops.ShopsListActivity;


import static android.view.View.GONE;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {




    public ShareActionProvider shareActionProvider;

    public Toolbar toolbar_main;

    DrawerLayout drawerLayout;

    public boolean onStartAsyncTask = false;




    //  проверка интернета
    final String BROADCAST_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            switch (action) {
                case BROADCAST_ACTION: onCheckInternet();
                    break;
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar_main);

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.setAdapter(sectionPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);



        tabLayout.setVisibility(GONE);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(this);

        registerReceiver(receiver,intentFilter);
    }



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


            if (isInternetAvailable(getApplicationContext()) == false){
                Toast toast = Toast.makeText(getApplicationContext(), "проверьте соединение с интернетом", Toast.LENGTH_LONG);
                toast.show();
            }

            else {


                if (onStartAsyncTask == false) {
                    onStartAsyncTask = true;
                }
                else {

                }
            }

        }

        public boolean isInternetAvailable(Context context) {
            ConnectivityManager conMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo i = conMgr.getActiveNetworkInfo();
            if (i == null)
                return false;
            if (!i.isConnected())
                return false;
            if (!i.isAvailable())
                return false;
            return true;
        }


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

            drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }




    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem_Share = menu.findItem(R.id.share_app);

        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem_Share);

        setShareActionIntent ("Поделиться новым приложением Пинега");

        return super.onCreateOptionsMenu(menu);
    }


    public void setShareActionIntent (String text){

        Intent neyav_intent = new Intent(Intent.ACTION_SEND);
        neyav_intent.setType("text / plain");
        neyav_intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(neyav_intent);
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()){

            case R.id.dev_activity:

                Intent developer_intent = new Intent(this, DeveloperActivity.class);
                startActivity(developer_intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private class SectionPagerAdapter extends FragmentPagerAdapter {

        SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }


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


      @Override
      public int getCount() {
          return 3;
      }



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
