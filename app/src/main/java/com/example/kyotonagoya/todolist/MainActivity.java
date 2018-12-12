package com.example.kyotonagoya.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kyotonagoya.todolist.TodoDatabase.TodoDatabaseAdapter;
import com.example.kyotonagoya.todolist.TodoDatabase.TodoDatabaseOpenHelper;
import com.example.kyotonagoya.todolist.TodoDatabase.TodoProperty;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String[] countries = {
            "America",
            "Japan",
    };

    ArrayAdapter adapter;
    TodoDatabaseAdapter dbAdapter;
    ArrayList<String> taskList;
    ArrayList<TodoProperty> dbDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new TodoDatabaseAdapter(getApplicationContext());
        dataFetch();

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);

        // TODO: 18/12/12 削除機能の実装

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TodoProperty deleteDbDatas = dbDatas.get(position);
                int deleteId = deleteDbDatas.id;
                String deleteString = deleteDbDatas.task;

                dbAdapter.deleteRecord(deleteId);
                dataFetch();

                adapter.remove(deleteString);
                return false;
            }
        });
    }

    public void addData(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String task = editText.getText().toString();
        dbAdapter.addRecord(task);

        dataFetch();
        adapter.add(taskList.get(taskList.size() - 1));
    }

    private void dataFetch() {
        dbDatas = dbAdapter.selectTaskAll();
        taskList = new ArrayList<String>();

        for (int i = 0; i < dbDatas.size(); i++) {
            TodoProperty taskData = dbDatas.get(i);
            taskList.add(taskData.task);
        }
    }
}
