package com.example.myfirebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText kodedosen,namadosen,lulusandosen,masakerjadosen;
    Button tblsv,tblvw;
    RadioButton laki,perempuan,rbjk;
    RadioGroup pilihanjeniskelamin;
    DatabaseReference reference;
    com.example.myfirebaseapp.dosen dosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kodedosen = findViewById(R.id.kodedsn);
        namadosen = findViewById(R.id.namadsn);
//        jeniskelamindosen = findViewById(R.id.jkdsn);
        laki = findViewById(R.id.radioMale);
        perempuan = findViewById(R.id.radioFemale);
        pilihanjeniskelamin = findViewById(R.id.pilihanjk);


        lulusandosen = findViewById(R.id.lldsn);
        masakerjadosen = findViewById(R.id.mkdsn);
        tblsv = findViewById(R.id.idbtsimpan);
        tblvw = findViewById(R.id.idbttampil);


        dosen = new dosen();

        reference = FirebaseDatabase.getInstance().getReference().child("dosen");

        tblsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dosen.setKode(kodedosen.getText().toString().trim());
                dosen.setNama(namadosen.getText().toString().trim());
                dosen.setMasakerja(masakerjadosen.getText().toString().trim());
                Integer radioid = pilihanjeniskelamin.getCheckedRadioButtonId();
                rbjk = findViewById(radioid);
                dosen.setJeniskelamin(rbjk.getText().toString().trim());
                dosen.setLulusan(lulusandosen.getText().toString().trim());






                reference.push().setValue(dosen);
                Toast.makeText(MainActivity.this,"Data Tersimpan",Toast.LENGTH_LONG).show();
            }
        });



        tblvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TampilSemuaData.class);
                startActivity(intent);
            }
        });


    }

    public void radioclick (View view )
    {
        int radioid = pilihanjeniskelamin.getCheckedRadioButtonId();
        rbjk = findViewById(radioid);
    }
}