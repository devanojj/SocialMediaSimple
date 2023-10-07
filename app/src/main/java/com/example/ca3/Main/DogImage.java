package com.example.ca3.Main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ca3.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Landing Page
public class DogImage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_image);
        new DogImageAPI().execute();
        new GetFactsTask().execute();

        Button logOut = findViewById(R.id.LogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle2 = new Bundle();
                Intent intent2 = new Intent(DogImage.this, SignIn.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                Toast.makeText(DogImage.this, "Log Out", Toast.LENGTH_SHORT).show();
            }
        });

        Button profileB = findViewById(R.id.profileButton);
        profileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle2 = new Bundle();
                Intent intent2 = new Intent(DogImage.this, AddAnimal.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });

        Button exploreB = findViewById(R.id.exloreButton);
        exploreB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle3 = new Bundle();
                Intent intent3 = new Intent(DogImage.this, Explore.class);
                intent3.putExtras(bundle3);
                startActivity(intent3);
            }
        });

        Button mapButton1 = findViewById(R.id.mapButton);
        mapButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle3 = new Bundle();
                Intent intent3 = new Intent(DogImage.this, MainActivity.class);
                intent3.putExtras(bundle3);
                startActivity(intent3);
            }
        });
    }

    private class GetFactsTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                // Set up the API endpoint URL
                String apiUrl = "https://dog-api.kinduff.com/api/facts";

                // Set up the request
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Read the response
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Parse the response
                JSONObject json = new JSONObject(result.toString());
                JSONArray facts = json.getJSONArray("facts");

                // Build the text
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < facts.length(); i++) {
                    text.append(facts.get(i)).append("\n");
                }
                return text.toString();
            }catch (MalformedURLException e) {
                // Handle invalid URL
                return "Error: Invalid URL";
            } catch (IOException e) {
                // Handle IO error
                return "Error: IO Exception";
            } catch (JSONException e) {
                // Handle JSON parsing error
                return "Error: JSON Exception";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Display the facts
            TextView textView = findViewById(R.id.text_q);
            textView.setText(result);
        }
    }

    private class DogImageAPI extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                // Make the API request
                URL url = new URL("https://dog.ceo/api/breeds/image/random");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Read the response
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Parse the response
                String responseData = result.toString();
                JSONObject json = new JSONObject(responseData);
                final String text = json.getString("message");
                return text;
            } catch (MalformedURLException e) {
                // Handle invalid URL
                return "Error: Invalid URL";
            } catch (IOException e) {
                // Handle IO error
                return "Error: IO Exception";
            } catch (JSONException e) {
                // Handle JSON parsing error
                return "Error: JSON Exception";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            ImageView imageView = findViewById(R.id.Image1);
            Picasso.get().load(result).into(imageView);
        }
    }
}
