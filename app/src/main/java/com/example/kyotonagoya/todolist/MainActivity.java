package com.example.kyotonagoya.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kyotonagoya.todolist.TodoDatabase.TodoDatabaseAdapter;
import com.example.kyotonagoya.todolist.TodoDatabase.TodoDatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String[] countries = {
            "America",
            "Japan",
    };

    TodoDatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new TodoDatabaseAdapter(getApplicationContext());

        ArrayList<String> dbDatas = dbAdapter.selectTaskAll();


        ListView listView = (ListView) findViewById(R.id.listView);
        TodoViewAdaptor adapter = new TodoViewAdaptor(getApplicationContext(),R.layout.todo_row_layout, dbDatas);
        listView.setAdapter(adapter);

        // TODO: 18/12/12 削除機能の実装

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
