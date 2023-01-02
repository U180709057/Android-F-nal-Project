package com.burakpar.fitnit;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.burakpar.fitnit.databinding.ActivityMySportProgramBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MySportProgramActivity extends AppCompatActivity {
    private ActivityMySportProgramBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityMySportProgramBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        YouTubePlayerView youTubePlayerView = binding.video1;
        youTubePlayerView.enterFullScreen();
    }

    public void toHome(View view){
        Intent intent = new Intent(MySportProgramActivity.this, HomeActivity.class);
        startActivity(intent);
    }



}