package com.thanhty.k22411csampleproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.thanhty.connectors.EmployeeConnector;
import com.thanhty.models.Employee;

public class LoginActivity extends AppCompatActivity {


    EditText edtUserName;
    EditText edtPassword;
    CheckBox chkSaveLogin;
    private long lastBackPressedTime = 0; // Biến lưu thời gian nhấn nút back lần trước
    private static final long DOUBLE_BACK_PRESS_INTERVAL = 500; // 0.5 giây (500ms)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        //các biến giải thuật liên quan giao diện phải viết sau contentView
        addView(); // này mới thêm nè
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addView() {
        edtUserName=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassword);
        chkSaveLogin=findViewById(R.id.chkSaveLoginInfor);
    }

    public void do_login(View view) {

        String usr=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();
        EmployeeConnector ec=new EmployeeConnector();

        Employee emp = ec.login(usr,pwd);
        if(emp!=null)
        {

            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,
                    "Login failed - please check your account again!",
                    Toast.LENGTH_LONG).show();

        }
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void do_exit(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        Resources res=getResources();
        //Thiết lập tiêu đề
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        //Nội dung cửa sổ
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        //Biểu tuong
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //Thiết lập tương tác YES:
        builder.setPositiveButton(res.getText(R.string.confirm_exit_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //System.exit();
                finish();
            }
        });
        builder.setNegativeButton(res.getText(R.string.confirm_exit_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


    }
}