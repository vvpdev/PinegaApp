package com.hfad.pinegaapp.Main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.hfad.pinegaapp.R;



public class MainFragment extends Fragment {



    ViewFlipper viewFlipperMainFrag;


    ImageView imageFlipper1;
    ImageView imageFlipper2;
    ImageView imageFlipper3;
    ImageView imageFlipper4;
    ImageView imageFlipper5;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // флиппер - галерея
        viewFlipperMainFrag = (ViewFlipper) view.findViewById(R.id.ViewFlipperGallery);

        imageFlipper1 = (ImageView) view.findViewById(R.id.imageFlipper1);
        imageFlipper2 = (ImageView) view.findViewById(R.id.imageFlipper2);
        imageFlipper3 = (ImageView) view.findViewById(R.id.imageFlipper3);
        imageFlipper4 = (ImageView) view.findViewById(R.id.imageFlipper4);
        imageFlipper5 = (ImageView) view.findViewById(R.id.imageFlipper5);

        LoadingDataToMainFragment loadingDataToMainFragment = new LoadingDataToMainFragment();
        loadingDataToMainFragment.start();
    }




    public class LoadingDataToMainFragment extends Thread{

        @Override
        public void run (){

            viewFlipperMainFrag.setAutoStart(true);

            viewFlipperMainFrag.setFlipInterval(4500);

            viewFlipperMainFrag.setInAnimation(getActivity(), R.anim.anim_in);
            viewFlipperMainFrag.setOutAnimation(getActivity(), R.anim.anim_out);


            onLoadingImage();
        }



        public void onLoadingImage (){

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Glide
                            .with(getActivity())
                            .load(R.drawable.flipper1)
                            .centerCrop()
                            .into(imageFlipper1);


                    Glide
                            .with(getActivity())
                            .load(R.drawable.flipper2)
                            .centerCrop()
                            .into(imageFlipper2);


                    Glide
                            .with(getActivity())
                            .load(R.drawable.flipper3)
                            .centerCrop()
                            .into(imageFlipper3);


                    Glide
                            .with(getActivity())
                            .load(R.drawable.flipper4)
                            .centerCrop()
                            .into(imageFlipper4);

                    Glide
                            .with(getActivity())
                            .load(R.drawable.flipper5)
                            .centerCrop()
                            .into(imageFlipper5);
                }
            });

        }
    }
}