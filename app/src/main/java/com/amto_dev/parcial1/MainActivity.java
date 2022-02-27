package com.amto_dev.parcial1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.herecomethesun, R.drawable.letitbe, R.drawable.obladi, R.drawable.sunshine, R.drawable.vivalasvegas, R.drawable.whatawonderfulword};
    int audio[] = {R.raw.herecomethesun, R.raw.letitbe, R.raw.ullala, R.raw.sunshinelollipop, R.raw.vivalasvegas, R.raw.whatawonderfulword};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvListMusic);

        s1 = getResources().getStringArray(R.array.music_name);
        s2 = getResources().getStringArray(R.array.music_album);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}