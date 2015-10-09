package com.paad.todolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Guarionex on 10/9/2015.
 */
public class NewDateFragment extends Fragment {

    EditText myEditTextDate;
    DatePickerDialog.OnDateSetListener date;
    Calendar myCalendar;
    Context context;

    private OnNewDateAddedListener onNewDateAddedListener;

    public interface OnNewDateAddedListener {
        public boolean onNewDateAdded(String newItem);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.new_date_fragment, container, false);
        myEditTextDate = (EditText) view.findViewById(R.id.myEditTextDate);
        myEditTextDate.setFocusableInTouchMode(false);
        myCalendar = Calendar.getInstance();


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        myEditTextDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog();

            }
        });



        return view;
    }

    private Dialog showDialog() {
        final Calendar now = Calendar.getInstance();

        DatePickerDialog _date = new DatePickerDialog(context, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        _date.show();
        return _date;



    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; // In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        //Log.d("Date value ", "===" + sdf.format(myCalendar.getTime()));

        myEditTextDate.setText(sdf.format(myCalendar.getTime()));
        boolean erase = onNewDateAddedListener.onNewDateAdded(myEditTextDate.getText().toString());
        if(erase) myEditTextDate.setText("");

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onNewDateAddedListener = (OnNewDateAddedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    " must implement OnNewDateAddedListener");
        }
    }


}

