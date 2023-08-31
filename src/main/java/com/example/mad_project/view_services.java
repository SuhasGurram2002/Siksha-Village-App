package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class view_services extends AppCompatActivity implements View.OnClickListener {

    TextView scheme1,scheme2,scheme3,scheme4,scheme5;
    Button apply3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_services);
        //setContentView(R.layout.services_information);

        scheme1 = (TextView) findViewById(R.id.scheme1);
        scheme1.setOnClickListener(this);
        scheme2 = (TextView) findViewById(R.id.scheme2);
        scheme2.setOnClickListener(this);
        scheme3 = (TextView) findViewById(R.id.scheme3);
        scheme3.setOnClickListener(this);
        scheme4 = (TextView) findViewById(R.id.scheme4);
        scheme4.setOnClickListener(this);
        scheme5 = (TextView) findViewById(R.id.scheme5);
        scheme5.setOnClickListener(this);

        apply3 = (Button) findViewById(R.id.apply3);
        apply3.setOnClickListener(this);

    }

    public void onClick(View v)
    {
        Bundle bundle = new Bundle();
        if(v.equals(scheme1)){
            String scheme_name1 = scheme1.getText().toString();
            String scheme_info1=  "Its strategy aims to : Set rural areas at the core of national and international development policies " +
                    ", support a human resource-based rural development ." +
                    "Promote productive enterprises and employment opportunities in farm and non-farm activities." +
                    "Improve working conditions in rural workplaces." +
                    "Extend social protection and labour standard coverage to rural areas.";
            bundle.putString("scheme_name",scheme_name1);
            bundle.putString("scheme_info",scheme_info1);
            Intent intent = new Intent(view_services.this,services_information.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(v.equals(scheme2)){
            String scheme_name2 = scheme2.getText().toString();
            String scheme_info2 = "This is a registration available for entrepreneurs who want to start and operate a small business - micro , small and medium enterprises." +
                    "The eligibility criteria for obtaining Udyog Aadhaar registration is based ont the investment in plant & machinery made by a manufacturing concern or investment in equipment made by a service provider." +
                    "Once, Udyog Aadhaar registration is obtained for a business, it can enjoy various subsidies and schemes specially provided by the govt. for helping small businesses in India.";
            bundle.putString("scheme_name",scheme_name2);
            bundle.putString("scheme_info",scheme_info2);
            Intent intent = new Intent(view_services.this,services_information.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(v.equals(scheme3)){
            String scheme_name3 = scheme3.getText().toString();
            String scheme_info3 = "Under this scheme, the farmers will get the subsidy amount directly in their bank accounts for the purchase of fertilizers." +
                    "This is truly an excellent initiative taken for farmers by the govt. The central govt. has come up with this initiative in order to help farmers and in order to cut costs on fertilizers as well." +
                    "The fertilizer subsidy scheme is still in consideration and can be announced by the govt. in the next budget by sure." +
                    "between this pandemic , this will actually prove to be quit helpful";
            bundle.putString("scheme_name",scheme_name3);
            bundle.putString("scheme_info",scheme_info3);
            Intent intent = new Intent(view_services.this,services_information.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(v.equals(scheme4)){
            String scheme_name4 = scheme4.getText().toString();
            String scheme_info4 = "Reading is the \n primary avenue \nto all knowledge. It offers access to the information, aspirations and happenings of both the past and the present.\n" +
                    "Reading aids character formations and widens horizons . Intellectual development is possible only through the cultivation of regular reading habit,hence reading has become one of the most important factors of success.\n" +
                    "To enable children to become motivated and independent reader, to possess sustainable reading and writing skills to achieve age appropriate learning levels.";
            bundle.putString("scheme_name",scheme_name4);
            bundle.putString("scheme_info",scheme_info4);
            Intent intent = new Intent(view_services.this,services_information.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(v.equals(scheme5)){
            String scheme_name5 = scheme5.getText().toString();
            String scheme_info5 = "The central govt. assistance to states for rural water supply began in 1972 with the launch of Accelerated Rural Water Supply Programme." +
                    "It was renamed as National Rural Drinking Water Programme in 2009, which is a centrally sponsored scheme with fund sharing between the Centre and the states. Under NRDWP, one the objectives was to " +
                    "enable all households to have access to and use sage & adequate drinking water within premises to the extent possible.";
            bundle.putString("scheme_name",scheme_name5);
            bundle.putString("scheme_info",scheme_info5);
            Intent intent = new Intent(view_services.this,services_information.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(v.equals(apply3)){
            Intent i = new Intent(view_services.this,Application.class);
            startActivity(i);
            finish();
        }
    }
}