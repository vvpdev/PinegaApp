package com.hfad.pinegaapp.Attractions;

import com.hfad.pinegaapp.R;



public class Attractions {



        private String attract_title;
        private String attract_sub_title;
        private int attract_image;


        private int text_article1;
        private int text_article2;
        private int text_article3;
        private int text_article4;
        private int text_article5;


          private int image_in_text1;
          private int image_in_text2;
          private int image_in_text3;
          private int image_in_text4;
          private int image_in_text5;



    public Attractions (

            String attract_title_constr,
            String attract_sub_title_constr,
            int attract_image_constr,


            int text_article1,
            int text_article2,
            int text_article3,
            int text_article4,
            int text_article5,

            int image_in_text1,
            int image_in_text2,
            int image_in_text3,
            int image_in_text4,
            int image_in_text5    )


         {

        this.attract_title = attract_title_constr;
        this.attract_sub_title = attract_sub_title_constr;
        this.attract_image = attract_image_constr;




        this.text_article1 = text_article1;
        this.text_article2 = text_article2;
        this.text_article3 = text_article3;
        this.text_article4 = text_article4;
        this.text_article5 = text_article5;

        this.image_in_text1 = image_in_text1;
        this.image_in_text2 = image_in_text2;
        this.image_in_text3 = image_in_text3;
        this.image_in_text4 = image_in_text4;
        this.image_in_text5 = image_in_text5;

             }




    public String getAttract_title (){
        return this.attract_title; }

    public String getAttract_sub_title (){
        return attract_sub_title; }

    public int getAttract_image () {
        return attract_image; }



    public int getText_article1 (){
        return text_article1; }
    public int getText_article2 (){
        return text_article2; }
    public int getText_article3 (){
        return text_article3; }
    public int getText_article4 (){
        return text_article4; }
    public int getText_article5 (){
        return text_article5; }

    public int getImage_in_text1() {
        return image_in_text1; }
    public int getImage_in_text2() {
        return image_in_text2; }
    public int getImage_in_text3() {
        return image_in_text3; }
    public int getImage_in_text4() {
        return image_in_text4; }
    public int getImage_in_text5() {
        return image_in_text5; }





    public static final Attractions[] attractionsList ={


            // музей
        new Attractions("Краеведческий музей",
                         "погружение в прошлое поселка",
                        R.drawable.museum_header,

                        R.string.article_museum1,
                        R.string.article_museum2,
                        R.string.article_museum3,
                        R.string.article_museum4,
                        R.string.article_museum5,
                        R.drawable.museum1,
                        R.drawable.museum2,
                        R.drawable.museum3,
                        R.drawable.museum4,
                        R.drawable.museum5),

            // заповедник
            new Attractions("Пинежский заповедник",
                    "уникальная флора и фауна",
                    R.drawable.zapovednik_header,
                    R.string.article_zapovednik1,
                    R.string.article_zapovednik2,
                    R.string.article_zapovednik3,
                    R.string.article_zapovednik4,
                    R.string.article_zapovednik5,
                    R.drawable.zapovednik1,
                    R.drawable.zapovednik2,
                    R.drawable.zapovednik3,
                    R.drawable.zapovednik4,
                    R.drawable.zapovednik5),

            // водопад
            new Attractions("Водопад Святой источник",
                    "интересные места",
                    R.drawable.vodopad1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0),

            // тараканий лог
            new Attractions("Тараканий лог",
                    "интересные места",
                    R.drawable.tarak_log1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0),

            // пещеры
            new Attractions("Карстовые пещеры",
                    "интересные места",
                    R.drawable.karst_peshchera1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0),

            // монастырь
            new Attractions("Красногорский монастырь",
                    "исторический памятник",
                    R.drawable.monastyr1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0),

            // голубино
            new Attractions("Голубино",
                    "база отдыха",
                    R.drawable.golubino1,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0),
        };




}
