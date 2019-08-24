package com.hfad.pinegaapp.NewsVK;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.pinegaapp.POJO.Item;
import com.hfad.pinegaapp.POJO.POJO;
import com.hfad.pinegaapp.R;
import com.hfad.pinegaapp.Retrofit.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class NewsFragment extends Fragment {


    RecyclerView recyclerViewNews;

    List<Item> arrayItems = new ArrayList<>();     // для items новостей из JSON

    static ArrayList <News> arrayAllNews = new ArrayList<>();

    LinearLayoutManager linearLayoutManager;
    AdapterRecyclerViewNews adapterRecyclerViewNews;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerViewNews = (RecyclerView) view.findViewById(R.id.recyclerVewNews);

        adapterRecyclerViewNews = new AdapterRecyclerViewNews(getActivity(), arrayAllNews);

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewNews.setLayoutManager(linearLayoutManager);


            NetworkService.getInstance()
                    .getJSON_Create()
                    .getAllPosts()
                    .enqueue(new Callback<POJO>() {

                        @Override
                        public void onResponse(Call<POJO> call, Response<POJO> response) {

                            arrayItems = response.body().getResponse().getItems();  // массив items

                            String textNews = "";
                            String textTitle = "";
                            String imageURL = "";   // url картинки
                            int owner_id = 0;       // id паблика
                            int id = 0;             // id поста


                            for (int i = 0; i < arrayItems.size(); i++){

                                if (arrayItems.get(i).getCopyHistory() == null){    // если нет репоста


                                    if (arrayItems.get(i).getText() != null){
                                        textNews = arrayItems.get(i).getText();

                                        if (textNews.contains("\n")){
                                            String[] title_news = textNews.split("\n");
                                            textTitle = title_news[0];
                                        }
                                        else {
                                            textTitle = textNews;
                                        }
                                     }

                                    else {
                                        // нет текста
                                    }






                                    if (arrayItems.get(i).getAttachments() != null){

                                        if (arrayItems.get(i).getAttachments().get(0).getPhoto() != null){
                                            imageURL = arrayItems.get(i).getAttachments().get(0).getPhoto().getSizes().get(3).getUrl();
                                        }

                                        if (arrayItems.get(i).getAttachments().get(0).getVideo() != null){

                                            imageURL = arrayItems.get(i).getAttachments().get(0).getVideo().getImage().get(3).getUrl();
                                        }


                                        if (arrayItems.get(i).getAttachments().get(0).getAlbum() != null){

                                            imageURL = arrayItems.get(i).getAttachments().get(0).getAlbum().getThumb().getSizes().get(4).getUrl();
                                        }

                                    }

                                    owner_id = arrayItems.get(i).getOwnerId();
                                    id = arrayItems.get(i).getId();

                                }




                                else {                                              // если репост


                                    if (arrayItems.get(i).getCopyHistory().get(0).getText() != null){

                                        textNews = arrayItems.get(i).getCopyHistory().get(0).getText();

                                        if (textNews.contains("\n")){

                                            String[] title_news = textNews.split("\n");
                                            textTitle = title_news[0];
                                        }
                                        else {
                                            textTitle = textNews;
                                        }
                                    }
                                    else {
                                       // нет текста
                                    }



                                    if (arrayItems.get(i).getCopyHistory().get(0).getAttachments() != null){

                                        if (arrayItems.get(i).getCopyHistory().get(0).getAttachments().get(0).getPhoto() != null){
                                            imageURL = arrayItems.get(i).getCopyHistory().get(0).getAttachments().get(0).getPhoto().getSizes().get(3).getUrl();
                                        }

                                        if (arrayItems.get(i).getCopyHistory().get(0).getAttachments().get(0).getVideo() != null){

                                            imageURL = arrayItems.get(i).getAttachments().get(0).getVideo().getImage().get(3).getUrl();
                                        }

                                        if (arrayItems.get(i).getCopyHistory().get(0).getAttachments().get(0).getAlbum() != null){

                                            imageURL = arrayItems.get(i).getAttachments().get(0).getAlbum().getThumb().getSizes().get(4).getUrl();
                                        }

                                    }

                                    owner_id = arrayItems.get(i).getCopyHistory().get(0).getOwnerId();
                                    id = arrayItems.get(i).getCopyHistory().get(0).getId();

                                }

                                arrayAllNews.add(new News(imageURL, textTitle, textNews, owner_id, id));

                                textNews = "";
                                textTitle = "";
                                imageURL = "";
                                owner_id = 0;
                                id = 0;


                            }



                            recyclerViewNews.setAdapter(adapterRecyclerViewNews);

                            getArrayAllNews();  // возвращаем заполненный массив
                        }

                        @Override
                        public void onFailure(Call<POJO> call, Throwable t) {

                        }
                    });

        }


    public ArrayList <News> getArrayAllNews (){
        return arrayAllNews;
    }



}
