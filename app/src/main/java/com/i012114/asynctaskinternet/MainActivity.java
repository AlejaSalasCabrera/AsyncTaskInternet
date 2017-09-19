package com.i012114.asynctaskinternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_1);
        button = (Button) findViewById(R.id.btn_1);
        textView = (TextView) findViewById(R.id.id_tv_1);
    }

    public Boolean isOnLine(){
        //VALIDAR SI SE TIENE INTERNET

        //Objeto Connectivity para manejar las conectividades (HADWARE)
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //Validar el estado de la RED

        if (networkInfo != null){
            return true;
        }else{
            return false;
        }

    }


    public void loadData(View view){
        //

        if (isOnLine()){

            MyTask task = new MyTask();
            task.execute();

            //Toast.makeText(this, "Cargar datos", Toast.LENGTH_SHORT).show();

        }else {

            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();

        }

    }



    public void processData(String s){
        //Procesar todos los datos

        textView.append("Item: " +s + "\n");


    }


    public class MyTask extends AsyncTask<String, String, String>{
        //


        @Override
        protected void onPreExecute() {

            progressBar.setVisibility(View.VISIBLE);

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            for (int i=1; i<50; i++){
                //
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(String.valueOf(i));

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            processData(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressBar.setVisibility(View.GONE);

        }
    }

}
