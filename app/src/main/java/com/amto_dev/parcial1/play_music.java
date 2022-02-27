package com.amto_dev.parcial1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class play_music extends AppCompatActivity {

    ImageView ivMusic;
    TextView nombre, description;

    ImageView play, prev, next, imageView;
    TextView songTitle;
    SeekBar mSeekBarTime, mSeekBarVol;
    static MediaPlayer mMediaPlayer;
    private Runnable runnable;
    private AudioManager mAudioManager;
    int currentIndex = 0;

    String data1, data2;
    int myImage, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }

        // initializing views
        play = findViewById(R.id.play);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        imageView = findViewById(R.id.imageView);
        mSeekBarTime = findViewById(R.id.seekBarTime);
        mSeekBarVol = findViewById(R.id.seekBarVol);

        ivMusic = findViewById(R.id.ivMusic );
        nombre = findViewById(R.id.nombre);
        description = findViewById(R.id.description);
        getData();
        setData();

        currentIndex = number;

        // creating an ArrayList to store our songs
        final ArrayList<Integer> songs = new ArrayList<>();

        songs.add(0, R.raw.herecomethesun);
        songs.add(1, R.raw.letitbe);
        songs.add(2, R.raw.ullala);
        songs.add(3, R.raw.sunshinelollipop);
        songs.add(4, R.raw.vivalasvegas);
        songs.add(5, R.raw.whatawonderfulword);


        // intializing mediaplayer

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));

        // seekbar volume

        int maxV = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curV = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mSeekBarVol.setMax(maxV);
        mSeekBarVol.setProgress(curV);

        mSeekBarVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Toast.makeText(this, "No Data " + currentIndex, Toast.LENGTH_LONG).show();


        //above seekbar volume
        //

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBarTime.setMax(mMediaPlayer.getDuration());
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    play.setImageResource(R.drawable.ic_play_info);
                } else {
                    mMediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause);
                }

                songNames();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) {
                    play.setImageResource(R.drawable.ic_pause);
                }

                if (currentIndex < songs.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }

                mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));

                mMediaPlayer.start();
                songNames();

            }
        });


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mMediaPlayer != null) {
                    play.setImageResource(R.drawable.ic_pause);
                }

                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = songs.size() - 1;
                }
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }

                mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                mMediaPlayer.start();
                songNames();

            }
        });





    }

    @SuppressLint("SetTextI18n")
    private void songNames() {
        if (currentIndex == 0) {
            nombre.setText("Here Come the Sun");
            description.setText("Abbey Road - The Beatles");
            ivMusic.setImageResource(R.drawable.herecomethesun);
        }
        if (currentIndex == 1) {
            nombre.setText("Let It Be");
            description.setText("Let It Be - The Beatles");
            ivMusic.setImageResource(R.drawable.letitbe);
        }
        if (currentIndex == 2) {
            nombre.setText("Ob La Di, Ob La Da");
            description.setText("The Beatles - The Beatles");
            ivMusic.setImageResource(R.drawable.obladi);
        }
        if (currentIndex == 3) {
            nombre.setText("Sunshine, Lollipops");
            description.setText("Mixed-Up Hearts - Lesley Gore");
            ivMusic.setImageResource(R.drawable.sunshine);
        }
        if (currentIndex == 4) {
            nombre.setText("Viva las Vegas");
            description.setText("Viva las Vegas - Elvis Presley");
            ivMusic.setImageResource(R.drawable.vivalasvegas);
        }if (currentIndex == 5) {
            nombre.setText("What a Wonderful World");
            description.setText("what a wonderful world - Louis Armstrong");
            ivMusic.setImageResource(R.drawable.whatawonderfulword);
        }


        // seekbar duration
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mSeekBarTime.setMax(mMediaPlayer.getDuration());
                mMediaPlayer.start();
            }
        });

        mSeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                    mSeekBarTime.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mMediaPlayer != null) {
                    try {
                        if (mMediaPlayer.isPlaying()) {
                            Message message = new Message();
                            message.what = mMediaPlayer.getCurrentPosition();
                            handler.sendMessage(message);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @SuppressLint("Handler Leak")
    Handler handler = new Handler () {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage  (Message msg) {
            mSeekBarTime.setProgress(msg.what);
        }
    };



    private void getData(){
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            myImage = getIntent().getIntExtra("myImage", 1);
            number = getIntent().getIntExtra("number", 0);


        }else{
            Toast.makeText(this, "No Data ", Toast.LENGTH_LONG).show();
        }
    }

    private void setData(){
        nombre.setText(data1);
        description.setText(data2);
        ivMusic.setImageResource(myImage);
    }

}