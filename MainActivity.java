package com.brahmsmart.jsonplaceholder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.textview_result);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        Call<List<PostClass>> call = jsonPlaceholderApi.getposts();

        call.enqueue(new Callback<List<PostClass>>() {
            @Override
            public void onResponse(Call<List<PostClass>> call, Response<List<PostClass>> response) {


                if (!response.isSuccessful()) {

                    textViewResult.setText("Code" + response.code());
                    return;
                }

                List<PostClass> postClasses = response.body();


                for (PostClass postresult : postClasses){

                    String content ="";

                    content +=  "ID :" +postresult.getId() + "\n" ;
                            content += "Userid" +postresult.getUserId()+"\n";
                            content += "Title" +postresult.getTitle()+"\n";
                            content += "Body" + postresult.getText()+"\n\n";

                            textViewResult.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<PostClass>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

}
