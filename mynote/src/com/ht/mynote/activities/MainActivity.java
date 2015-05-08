package com.ht.mynote.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.ht.mynote.adapters.MyAdapter;
import com.ht.mynote.dao.DbManager;
import com.ht.mynote.pojo.Notes;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Project: com.ht.mynote.activities
 * Author: 安诺爱成长
 * Email: 1399487511@qq.com
 * Date: 2015/5/1
 */
public class MainActivity extends Activity {

    private ListView listView;
    private ImageButton imageButton;
    private List<Notes> list;
    private MyAdapter adapter;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        imageButton = (ImageButton) findViewById(R.id.add);
        registerForContextMenu(listView);

        //单例是自己实例化自己
        DbManager.createInstance(getApplicationContext());
        list = DbManager.getInstance().getList();


        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("notes", list.get(i));
                startActivity(intent);
            }
        });
   }

    //这样就可以保证每次返回的时候，更新我们的主界面的listview
    @Override
    protected void onResume() {
        super.onResume();
        list = DbManager.getInstance().getList();
        adapter = new MyAdapter(this, list);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        position = 0;
        if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            position = info.position;
        }
        menu.add("删除：" + list.get(position).getTitle());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Notes notes = list.get(position);
        DbManager.getInstance().delete(notes);
        list = DbManager.getInstance().getList();
        adapter = new MyAdapter(this, list);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        return true;
    }
}