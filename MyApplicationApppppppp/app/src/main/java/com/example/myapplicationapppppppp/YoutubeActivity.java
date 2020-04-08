package com.example.myapplicationapppppppp;

import android.os.Bundle;
import com.example.myapplicationapppppppp.R;
import com.example.myapplicationapppppppp.YoutubeConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import androidx.annotation.Nullable;

public class YoutubeActivity extends YouTubeBaseActivity {

    protected YouTubePlayerView youtubePlay_view;
    protected YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView( R.layout.item_youtube_view1 );

        youtubePlay_view = findViewById( R.id.youtubePlay_view );

        youTubeData();

        youtubePlay_view.initialize( YoutubeConfig.getApiKey(),onInitializedListener);
    }

    private void youTubeData() {

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadPlaylist( "W4hTJybfU7s" );
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
    }


}
