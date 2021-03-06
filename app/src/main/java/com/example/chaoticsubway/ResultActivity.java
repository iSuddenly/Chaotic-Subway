package com.example.chaoticsubway;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    static Boolean isTouched = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LocalDateTime tempTime = java.time.LocalDateTime.now();

        //Data
        ArrayList<Pathway> data = new ArrayList<>();
        data.add(new Pathway("상도", "고속터미널", LocalDateTime.parse("2018-09-18T16:26:00")));
        data.add(new Pathway("고속터미널", "종로3가", LocalDateTime.parse("2018-09-18T16:42:00")));
        data.add(new Pathway("종로3가", "종로5가",  LocalDateTime.parse("2018-09-18T17:03:00")));

        //Adapter
        PathwayAdapter adapter = new PathwayAdapter(data);

        //Link View and Adapter
//        ListView listView = (ListView)findViewById(R.id.list_view);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ResultActivity.this, position + " 번째 구간", Toast.LENGTH_SHORT).show();
//            }
//        });

        //Choose current route Button
        Button chooseRouteButton = (Button)findViewById(R.id.realTime);
        //If not real-time search, make it gone
        chooseRouteButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent congestionIntent = new Intent(view.getContext(), CongestionActivity.class);
//                congestionIntent.putExtra()
                startActivity(congestionIntent);
            }
        });

        //Offer container
        final LinearLayout offerContainer = (LinearLayout)findViewById(R.id.offerContainer);

        //Congestion Search Switch
        SwitchCompat switchButton = (SwitchCompat)findViewById(R.id.congestionSwitch);
        switchButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isTouched = true;
                return false;
            }
        });
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
            if (isTouched) {
                isTouched = false;
                if (isChecked) {
                    Toast.makeText(ResultActivity.this, "혼잡도 고려", Toast.LENGTH_SHORT).show();
                    offerContainer.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(ResultActivity.this, "혼잡도 고려 취소", Toast.LENGTH_SHORT).show();
                    offerContainer.setVisibility(View.GONE);
                }
            }
            }
        });

    }
}
