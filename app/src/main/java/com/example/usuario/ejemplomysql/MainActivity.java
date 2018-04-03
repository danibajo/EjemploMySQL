package com.example.usuario.ejemplomysql;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComunicationTask ct=new ComunicationTask();
                ct.execute();
            }
        });
    }

    private class ComunicationTask extends AsyncTask<Void, Void, ResultSet> {
        protected void onPostExecute(ResultSet resultSet) {
            super.onPostExecute( resultSet);
            if(resultSet!=null){
                try{
                    resultSet.next();
                    editText.setText(resultSet.getString(2));
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        protected ResultSet doInBackground(Void... voids) {
            String url = "jdbc:mysql://10.225.92.134:3306/sga?useSSL=false";
            String sql = "select id_persona,nombre,apellido from persona";
            ResultSet result=null;
            try {
                    Connection conexion = DriverManager.getConnection(url, "test", "cartagena2018");
                    Statement stmt = conexion.createStatement();
                    result = stmt.executeQuery(sql);


                //	Connection conexion=DriverManager.getConnection(url,"root","#ff0016#");
                //	Statement stmt=conexion.createStatement();

                //	ResultSet result=stmt.executeQuery(sql);

               // while (result.next()) {
               //     System.out.print("Id:" + result.getInt(1));
               //     System.out.print(" nombre:" + result.getString(2));
               //     System.out.println(" apellido:" + result.getString(3));


                //result.close(); etc..

            } catch (Exception e) {
                // TODO: handle exception
                System.err.println("no es posible connectar");
            }
            return result;
        }
    }
}
