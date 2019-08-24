package com.hfad.pinegaapp.NewsVK;


public class News {

    private String imageNews;
    private String titleNews;
    String textNews;
    private int id;
    private int owner_id;




    // конструктор
    public News(String imageNews, String titleNews, String textNews, int owner_id, int id){
        this.imageNews = imageNews;
        this.titleNews = titleNews;
        this.textNews = textNews;
        this.owner_id = owner_id;
        this.id = id;
    }



    public String getImageNews() {
        return imageNews;
    }

    public String getTextNews() {
        return textNews;
    }

    public int getId (){
        return id;
    }

    public int getOwner_id(){
        return owner_id;
    }

    public String getTitleNews(){
        return titleNews;
    }


}
