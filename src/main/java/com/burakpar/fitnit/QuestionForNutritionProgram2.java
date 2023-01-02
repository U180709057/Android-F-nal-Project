package com.burakpar.fitnit;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class QuestionForNutritionProgram2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_question_for_nutrition_program2,container,false);

        Button toLogin = viewGroup.findViewById(R.id.submitNutritionQuestions2) ;
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Informations Saved",Toast.LENGTH_SHORT).show();
            }
        });
        return viewGroup;
    }




}