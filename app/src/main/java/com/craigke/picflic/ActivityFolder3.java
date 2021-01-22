package com.craigke.picflic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;

public class ActivityFolder3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder3);
        ButterKnife.bind(this);

        Intent intent = getIntent();
    }
}