package com.example.ingfrantzy.todoappfrantz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayAdapter<String> itemsAdapter;
    ArrayList<String> items;
    ListView lvItems;
    EditText etNewItem;


    protected void onCreate(Bundle savedInstanceState) {
        readItems();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        itemsAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems=(ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);


        items.add("First item");
        items.add("second items");

        Button add = (Button) findViewById(R.id.btnAddItems);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNewItem = (EditText) findViewById(R.id.etNewItem);
                String itemText = etNewItem.getText().toString();
                items.add(itemText);
                etNewItem.setText("");
                //writeItems();
            }
        });

        setupListViewListener();

    }





    private  void setupListViewListener(){

    lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long ld) {
            items.remove(pos);
            itemsAdapter.notifyDataSetChanged();


            return  true;
        }
    });
    }
    private void readItems(){

    File filesDir=getFilesDir();
    File todofile=new File(filesDir,"todo.txt");
    try{
        items=new ArrayList<String>(FileUtils.readLines(todofile));


    }catch (IOException e){items=new ArrayList<String>();}

}
    private void writeItems()
    {

        File filesDir=getFilesDir();
        File todofile=new File(filesDir,"todo.txt");
        try{
            FileUtils.writeLines(todofile, items);


        }catch (IOException e){e.printStackTrace();}
    }


}
