package com.paad.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class ToDoListActivity extends Activity implements NewItemFragment.OnNewItemAddedListener, NewDateFragment.OnNewDateAddedListener {
  
  private ArrayAdapter<String> aa;
  private ArrayList<String> todoItems;
  private String itemS = "";
  private String dateS = "";


  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Inflate your view
    setContentView(R.layout.main);
      
    // Get references to the Fragments
    FragmentManager fm = getFragmentManager();
    ToDoListFragment todoListFragment = 
      (ToDoListFragment)fm.findFragmentById(R.id.TodoListFragment);

    // Create the array list of to do items
    todoItems = new ArrayList<String>();
     
    // Create the array adapter to bind the array to the listview
    aa = new ArrayAdapter<String>(this,
                                  android.R.layout.simple_list_item_1,
                                  todoItems);
     
    // Bind the array adapter to the listview.
    todoListFragment.setListAdapter(aa);
  }

  public void onNewItemAdded(String newItem) {
    itemS = newItem;
    if(!itemS.equals("") && !dateS.equals(""))
    {
      todoItems.add(itemS + " @ " + dateS);
      aa.notifyDataSetChanged();
      itemS = "";
      dateS = "";

    }
    else if(!itemS.equals("")){
      todoItems.add(itemS);
      aa.notifyDataSetChanged();
      itemS = "";
    }

   // Log.e("ITEM", itemS);
  }

  public boolean onNewDateAdded(String newDate)
  {
    //Log.e("date", "inside is "+itemS);
    dateS = newDate;
    if(!itemS.equals("")) {
      todoItems.add(itemS + " @ " + dateS);
      aa.notifyDataSetChanged();
      itemS = "";
      dateS = "";
      return true;
    }
    return false;
  }





}