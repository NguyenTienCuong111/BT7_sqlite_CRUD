package com.example.bt_sqlite_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bt_sqlite_crud.adapter.EmployeeAdapter;
import com.example.bt_sqlite_crud.model.Employee;
import com.example.bt_sqlite_crud.sqlite.DBHelper;
import com.example.bt_sqlite_crud.sqlite.EmployeeDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EmployeeAdapter employeeAdapter;
    private ListView lvEmployees;
    private String employeeId;
    private List<Employee> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DbHelper dbHelper=new DbHelper(this);
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        database.close();
        findViewById(R.id.buttonInsert).setOnClickListener(this);
        findViewById(R.id.buttonEdit).setOnClickListener(this);
        findViewById(R.id.buttonDelete).setOnClickListener(this);

        lvEmployees=findViewById(R.id.lvEmployees);
        EmployeeDao dao= new EmployeeDao(this);
        list= dao.getAll();
        employeeAdapter=new EmployeeAdapter(this, list);
        lvEmployees.setAdapter(employeeAdapter);
        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee emp = list.get(i);
                employeeId=emp.getId();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        EmployeeDao dao= new EmployeeDao(this);
        List<Employee> updatedList = dao.getAll();
        updatedList.forEach(item->list.add(item));
        list.clear();
        employeeAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,AddOrEditEmployeeActivity.class);

        switch (view.getId()){
            case R.id.buttonInsert:
                startActivity(intent);
                break;
            case R.id.buttonEdit:
                if(employeeId==null){
                    Toast.makeText(this, "Employee ID must be selected", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle=new Bundle();
                bundle.putString("id",employeeId);
                intent.putExtra("data",bundle);
                startActivity(intent);

                break;
            case R.id.buttonDelete:
                if(employeeId==null){
                    Toast.makeText(this, "Employee ID must be selected", Toast.LENGTH_SHORT).show();
                    return;
                }

                EmployeeDao dao= new EmployeeDao(this);
                dao.delete(employeeId);
                employeeId=null;
                onResume();



                Toast.makeText(this, "Game was deleted", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}