//package com.example.jacobdexter_milling.mytodolistapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class Detail_activity extends AppCompatActivity {
//
//    ArrayList<String> detailList;
//    ArrayAdapter<String> detailListAdapter;
//
//    TextView detailTitle;
//    EditText editTextDetail;
//    ListView detailListView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail_activity);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        TextView detailTitle = (TextView) findViewById(R.id.detailTitle);
//        EditText editTextDetail = (EditText) findViewById(R.id.editTextDetail);
//        ListView detailListView = (ListView) findViewById(R.id.detailList);
//
//        detailList = new ArrayList<String>();
//        detailListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
//
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//                String listItem = editTextDetail.getText().toString();
//                detailList.add(listItem);
//                detailListAdapter.notifyDataSetChanged();
//
//
//            }
//        });
//
//    }
//}
