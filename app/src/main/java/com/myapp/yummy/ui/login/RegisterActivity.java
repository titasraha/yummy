package com.myapp.yummy.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myapp.yummy.R;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_register);

        Button cmdRegister = findViewById(R.id.register);

        cmdRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView txtName = findViewById(R.id.name);
                TextView txtMobileNumber = findViewById(R.id.mobile_number);
                TextView txtPassword = findViewById(R.id.password);
                TextView txtAddress = findViewById(R.id.address);
                TextView txtEmail = findViewById(R.id.email);

                // Validation -- TO DO

                String name = txtName.getText().toString();
                String mobileNumber = txtMobileNumber.getText().toString();
                String password = txtPassword.getText().toString();
                String address = txtAddress.getText().toString();
                String email = txtEmail.getText().toString();


                try {


                    URL url = new URL("http://13.235.250.119/v2/register/fetch_result");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("name", name);
                    jsonParam.put("mobile_number", mobileNumber);
                    jsonParam.put("password", password);
                    jsonParam.put("address", address);
                    jsonParam.put("email", email);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
