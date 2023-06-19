package com.example.fbla_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;

public class studetnPortal extends login {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studetn_portal);
        Intent i = getIntent();
        first = i.getStringExtra("FIRST");
        last = i.getStringExtra("LAST");
        name = first + " " + last;
        System.out.println(name);
        ((Button)findViewById(R.id.button12)).setText("Welcome " + name);
        id = i.getStringExtra("IDNum");
        password = i.getStringExtra("Password");

    }
    String first;
    String last;
    String name;
    String id;
    String password;

    public void openCalandar(View v) { //opens calandar activity
        Intent i = new Intent(this, Calandar.class);
        startActivity(i);
    }

    public void openProfile(View v) {//opens profile activity

        AccessToken accessToken = AccessToken.getCurrentAccessToken(); //put this code at beggining of app after create sign out option only
        if(accessToken!= null && accessToken.isExpired() == false)
        {
            startActivity(new Intent(studetnPortal.this, Profile.class));
            finish();
        }
        else{
            Intent i = new Intent(this, Profile.class);
            i.putExtra("NAME", name);
            i.putExtra("ID", id);
            startActivity(i);
        }


    }
    public void openImages(View v) {//opens openImage activity
        Intent i = new Intent(this, scrollingPhotos.class);
        startActivity(i);
    }

    public void openFeedback(View v)//opens opens a doc with copyright and a feedback form activity
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/1u-wumDBcXXHB5r4oGN3L11P0-aBAzjnPfrxtrrqkszs/edit?usp=sharing"));
        startActivity(i);
    }

    public void Map(View v)//opens png photos of the school's map
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1ufMwxeYJDWOqHcM6G2Tbw__IFOhk1PqT/view?usp=sharing"));
        startActivity(i);
    }
    public void Contacts(View v)//opens faculty web page with links to all school employee's contact
    {
        Intent i = new Intent(this, contact.class);
        i.putExtra("NAME", name);
        startActivity(i);
    }
    public void SharePhoto(View v)//opens faculty web page with links to all school employee's contact
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLScDK1U109TlobG_3qSCw7L78o7VrJotKkK50DyvsxZYa0S90g/viewform?usp=sf_link"));
        startActivity(i);
    }
}