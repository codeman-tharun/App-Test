package com.example.calculatorapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CalListAdapter extends ArrayList<CalList> {
    private List<String> list;
    private int resource;

    public CalListAdapter(@NonNull Context context, int resource, List<String> list) {
        //super(context,resource,list);
        this.list=list;
        this.resource=resource;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        String item=list.get(position);
        View view=convertView;
        if(view==null){
            view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        }

        TextView history=view.findViewById(R.id.HistoryText);

        //history.setText(getvalue());

        return view;

    }
}
