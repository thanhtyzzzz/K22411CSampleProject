package com.thanhty.k22411csampleproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.thanhty.utils.BMIResult;
import com.thanhty.utils.Healthcare;


public class EmployeeHealthcareActivity extends AppCompatActivity {

    EditText edtHeight;
    EditText edtWeight;
    Button btnCalculate;
    Button btnClear;
    Button btnClose;
    TextView txtResult;

    View.OnClickListener myClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_healthcare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvent();
    }

    private void addEvent() {
        myClick=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Kiểm tra các View s dụng biến sự kiện ở đây
                if(view.equals(btnCalculate))
                {
                    //View Calculate đang sử dụng sự kiên này
                    double h=Double.parseDouble(edtHeight.getText().toString());
                    double w=Double.parseDouble(edtWeight.getText().toString());
                    BMIResult result= Healthcare.calculate(h,w, EmployeeHealthcareActivity.this);
                    txtResult.setText(result.getBMI()+"=>"+result.getDescription());

                }
                else if(view.equals(btnClear))
                {
                    //view Clear đang sử dụng sự kiện này
                    edtHeight.setText("");
                    edtWeight.setText("");
                    txtResult.setText("");
                    edtHeight.requestFocus();
                }
                else if(view.equals(btnClose))
                {
                    //view Close đang sử dụng sự kiện này
                    finish();
                }
            }
        };
        //gán biến sự kiện cho các view (sharing events)
        btnCalculate.setOnClickListener(myClick);
        btnClear.setOnClickListener(myClick);
        btnClose.setOnClickListener(myClick);
    }

    private void addViews() {
        edtHeight=findViewById(R.id.edtHeight);
        edtWeight=findViewById(R.id.edtWeight);

        btnCalculate=findViewById(R.id.btnCalculate);
        btnClear=findViewById(R.id.btnClear);
        btnClose=findViewById(R.id.btnClose);

        txtResult=findViewById(R.id.txtResult);
    }
}