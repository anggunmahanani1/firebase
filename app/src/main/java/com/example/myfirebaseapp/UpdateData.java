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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateData extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText kodedosen,namadosen,lulusandosen,masakerjadosen;
    RadioButton laki,perempuan,rbkel;
    RadioGroup rgkel;
    Button tblsv,tblvw,tbldel;

    String Snama, Skode, Sjk, Slulusan,Smaker, Skey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("dosen");
        namadosen=findViewById(R.id.namadsn);
        kodedosen=findViewById(R.id.kodedsn);

        laki = findViewById(R.id.radioMale);
        perempuan = findViewById(R.id.radioFemale);
        rgkel = findViewById(R.id.pilihanjk);

        lulusandosen=findViewById(R.id.lldsn);
        masakerjadosen=findViewById(R.id.mkdsn);


        tblvw=findViewById(R.id.idbttampil);
        tblsv=findViewById(R.id.idbtsimpan);
        tbldel=findViewById(R.id.idbthapus);

        Intent intent=getIntent();

        Skey=intent.getStringExtra("skey");
        Snama=intent.getStringExtra("nama");
        Skode=intent.getStringExtra("kode");
        Sjk=intent.getStringExtra("jeniskelamin");
        Slulusan=intent.getStringExtra("lulusan");
        Smaker=intent.getStringExtra("masakerja");


        namadosen.setText(Snama);
        kodedosen.setText(Skode);
        String jklaki = "laki-laki";
        String jkperempuan = "perempuan";

        if (Sjk.equals(jklaki)){
            laki.setChecked(true);

            int radioid = rgkel.getCheckedRadioButtonId();
            rbkel = findViewById(radioid);
        }
        else if (Sjk.equals(jkperempuan)){
            perempuan.setChecked(true);

            int radioid = rgkel.getCheckedRadioButtonId();
            rbkel = findViewById(radioid);
        }


//        jeniskelamindosen.setText(Sjk);
        lulusandosen.setText(Slulusan);
        masakerjadosen.setText(Smaker);

        tblsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData();
            }
        });

        tbldel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HapusData();
            }
        });

        tblvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UpdateData.this, TampilSemuaData.class);
                startActivity(intent);
            }
        });
    }

    private void UpdateData(){
        String nama=namadosen.getText().toString();
        String kode=kodedosen.getText().toString();
        String Jeniskelamin= rbkel.getText().toString();
        String maker = masakerjadosen.getText().toString();
        String lulusan =lulusandosen.getText().toString();
        HashMap hashMap=new HashMap();


        hashMap.put("nama", nama);
        hashMap.put("kode", kode);
        hashMap.put("jeniskelamin",Jeniskelamin);
        hashMap.put("lulusan", lulusan);
        hashMap.put("masakerja", maker);

        databaseReference.child(Skey).updateChildren(hashMap);
        Toast.makeText(UpdateData.this, "Data Sudah diPerbarui", Toast.LENGTH_SHORT).show();

    }

    private void HapusData(){
        databaseReference.child(Skey).removeValue();
        Toast.makeText(UpdateData.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(UpdateData.this, TampilSemuaData.class);
        startActivity(intent);
    }

    public void radioclick (View view )
    {
        int radioid = rgkel.getCheckedRadioButtonId();
        rbkel = findViewById(radioid);
    }


}
