package com.example.kyotonagoya.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kyotonagoya.todolist.TodoDatabase.TodoDatabaseAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    TodoDatabaseAdapter dbAdapter;
    ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new TodoDatabaseAdapter(getApplicationContext());
        taskList = dbAdapter.selectTaskAll();
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String deleteData = adapter.getItem(position).toString();

                dbAdapter.deleteRecord(deleteData);
                adapter.remove(deleteData);
                return false;
            }
        });
    }

    public void addData(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String task = editText.getText().toString();
        dbAdapter.addRecord(task);
        adapter.add(task);
    }
}
