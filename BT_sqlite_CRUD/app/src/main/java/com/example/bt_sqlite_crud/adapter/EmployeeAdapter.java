package com.example.bt_sqlite_crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bt_sqlite_crud.R;
import com.example.bt_sqlite_crud.model.Employee;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {
    private Context context;
    private List<Employee> list;

    public EmployeeAdapter(Context context, List<Employee> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int iPosition) {
        return list.get(iPosition);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view== null){
            view= LayoutInflater.from(context).inflate(R.layout.layout_employee_item, null);
        }
        TextView tvName=view.findViewById(R.id.tvName);
        TextView tvClass=view.findViewById(R.id.tvClass);

        Employee emp =list.get(i);
        tvName.setText(emp.getName());
        tvClass.setText("" + emp.getClasss());
        return view;
    }
}
