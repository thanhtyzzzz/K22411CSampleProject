package com.thanhty.k22411csampleproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
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
import com.thanhty.connectors.SQLiteConnector;
import com.thanhty.models.Employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity {


    EditText edtUserName;
    EditText edtPassword;
    CheckBox chkSaveLogin;
    private long lastBackPressedTime = 0; // Biến lưu thời gian nhấn nút back lần trước
    private static final long DOUBLE_BACK_PRESS_INTERVAL = 500; // 0.5 giây (500ms)

    String DATABASE_NAME="SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;

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
        processCopy();
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

//        SQLiteConnector sqLiteConnector=new SQLiteConnector(this);
//        sqLiteConnector.openDatabase();


//        Employee emp = ec.login(sqLiteConnector.getDatabase(),usr,pwd);
        Employee emp = ec.login(new SQLiteConnector(this).openDatabase(),usr,pwd);
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
//        Intent intent=new Intent(this, MainActivity.class);
//        startActivity(intent);
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
    public void saveLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        String usr=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();
        boolean isSave=chkSaveLogin.isChecked();
        editor.putString("USERNAME", usr);
        editor.putString("PASSWORD",pwd);
        editor.putBoolean("SAVED", isSave);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInformation();
    }

    public void restoreLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        String usr=preferences.getString("USERNAME", "");
        String pwd=preferences.getString("PASSWORD","");
        boolean isSave=preferences.getBoolean("SAVED", true);
        if(isSave)
        {
            edtUserName.setText(usr);
            edtPassword.setText(pwd);
            chkSaveLogin.setChecked(isSave);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();
    }

    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
    public void CopyDataBaseFromAsset()
    {
        try {
            InputStream myInput;

            myInput = getAssets().open(DATABASE_NAME);


            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}