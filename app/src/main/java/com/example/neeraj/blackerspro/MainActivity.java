package com.example.neeraj.blackerspro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trial);
        webView = findViewById(R.id.webView);
        String layout = getIntent().getStringExtra("layout");
        if (layout.equalsIgnoreCase("govt")) {
            setTitle("Government Polytechnic Dehradun ");
            setContentView(R.layout.govt_poly_tech);

        }
        else if(layout.equalsIgnoreCase("principal")){
            setTitle("Principal Sir");
            setContentView(R.layout.principal);

        }

        else if(layout.equalsIgnoreCase("facilities")){
            setTitle("Facilities ");
            setContentView(R.layout.facilities);

        }

        else if(layout.equalsIgnoreCase("course")){
            setTitle("Courses");
            setContentView(R.layout.course);

        }
        else if(layout.equalsIgnoreCase("syllabus")){
            setTitle("Syllabus");
            setContentView(R.layout.trial);
         //  progressDialog   = ProgressDialog.show(getApplicationContext(), "Loading","Please wait...", true);
          //  progressDialog.setCancelable(false);

            webView.setWebViewClient(new WebViewClient());

            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setDomStorageEnabled(true);

            webView.loadUrl("http://www.gpdehradun.org.in/download.html");


        }

        else if(layout.equalsIgnoreCase("about")){
            setTitle("About Us");
            setContentView(R.layout.about_us);

        }
        else
        {
            Toast.makeText(this, "Some Error occur...", Toast.LENGTH_SHORT).show();
        }
    }


}



