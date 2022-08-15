package com.example.practica_almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExernoStorage extends AppCompatActivity {
    Button guardare,leere;
    TextView contenidoe;
    EditText capturae;
    String nombreArchivo="",rutaSdcard="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerno_storage);
        guardare=(Button)findViewById(R.id.guardare);
        leere=(Button)findViewById(R.id.leere);
        capturae=(EditText)findViewById(R.id.capturae);
        contenidoe=(TextView)findViewById(R.id.contenidoe);
        nombreArchivo="archivo.txt";
        guardare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               guardarEnExterna();
            }
        });
       leere.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             leerArchivoExterno();
           }
       });
    }

    private void leerArchivoExterno() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            rutaSdcard=getExternalFilesDir(null).getAbsolutePath();
        }else{
            Toast.makeText(getApplicationContext(), "No se puede acceder a la SD card",Toast.LENGTH_LONG).show();
        }
        try{
            File file = new File(rutaSdcard+"/"+nombreArchivo);
            InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferReader = new BufferedReader(inputStream);
            String content=bufferReader.readLine();
            bufferReader.close();
            contenidoe.setText(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarEnExterna() {
         String texto= capturae.getText().toString();
         if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
             rutaSdcard=getExternalFilesDir(null).getAbsolutePath();
         }else{
             Toast.makeText(getApplicationContext(), "No se puede acceder a la SD card",Toast.LENGTH_LONG).show();
         }
         File file= new File(rutaSdcard+"/"+nombreArchivo);
         FileOutputStream fos = null;
         try{
             fos=new FileOutputStream(file);
             if(fos!=null){
                 fos.write(texto.getBytes());
             }
             if(fos!=null){
                 fos.close();
             }
             Toast.makeText(getApplicationContext(), "Texto Guardado con EXITO!!!",Toast.LENGTH_LONG).show();
         } catch (FileNotFoundException e) {
             Toast.makeText(getApplicationContext(), "Error"+e.getMessage(),Toast.LENGTH_LONG).show();
             e.printStackTrace();
         }catch(IOException e){
             Toast.makeText(getApplicationContext(), "Error"+e.getMessage(),Toast.LENGTH_LONG).show();
             e.printStackTrace();
         }
    }
}