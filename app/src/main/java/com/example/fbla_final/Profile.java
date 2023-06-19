package com.example.fbla_final;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {
    String id;
    String name;
    String password;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        id = i.getStringExtra("ID");
        if(!(name == null)) {
            ((TextView) findViewById(R.id.fullName)).setText(name);
            ((TextView) findViewById(R.id.firstName)).setText(name.substring(0, name.indexOf(" ")));
            ((TextView) findViewById(R.id.lastName)).setText(name.substring(name.indexOf(" ") + 1));
        }
        else{
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            ImageView imageView = (ImageView)findViewById(R.id.pfp);
            GraphRequest request = GraphRequest.newMeRequest(

                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code

                            try {
                                String fullName = object.getString("name");
                                String url = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                ((TextView) findViewById(R.id.fullName)).setText(fullName);
                                Picasso.get().load(url).into(imageView);
                                ((TextView) findViewById(R.id.firstName)).setText(fullName.substring(0, fullName.indexOf(" ")));
                                ((TextView) findViewById(R.id.lastName)).setText(fullName.substring(fullName.indexOf(" ") + 1));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link, picture.type(large)");
            request.setParameters(parameters);
            request.executeAsync();
        }


    }

    public void calling(View v)
    {
        //gathers the information that the user types


        Intent j  = new Intent(this, studetnPortal.class);
        //packaging information
        String first = ((TextView)findViewById(R.id.firstName)).getText().toString();
        String last = ((TextView)findViewById(R.id.lastName)).getText().toString();
        j.putExtra("FIRST", first);
        j.putExtra("LAST", last);
        j.putExtra("IDNum", id);
        j.putExtra("Password", password);
        System.out.println("fkhgsgjsdfklgfd");

        startActivity(j);
    }
}