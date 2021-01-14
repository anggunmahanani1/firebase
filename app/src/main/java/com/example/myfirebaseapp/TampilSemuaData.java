package com.example.myfirebaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TampilSemuaData extends AppCompatActivity {
    ListView listView;
    ArrayAdapter arrayAdapter;

    ArrayList<String> arraytampil = new ArrayList<>();
    ArrayList<String> arrayKunci=new ArrayList<>();
    ArrayList<String> arrayHapus=new ArrayList<>();
    ArrayList<String> arrayNama = new ArrayList<>();
    ArrayList<String> arrayLulusan = new ArrayList<>();
    ArrayList<String> arrayMK  = new ArrayList<>();
    ArrayList<String> arrayKode  = new ArrayList<>();
    ArrayList<String> arrayJK  = new ArrayList<>();
    Button tbl;
    Intent pindah;

    com.example.myfirebaseapp.dosen dosen;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_data);

        tbl = (Button) findViewById(R.id.tambahmhs);

        tbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pindah = new Intent(TampilSemuaData.this , MainActivity.class);
                startActivity(pindah);
            }
        });

        listView = findViewById(R.id.listdataku);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("dosen");
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arraytampil);
        listView.setAdapter(arrayAdapter);




        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                String hasil = dataSnapshot.getValue(com.example.myfirebaseapp.dosen.class).toPrint();
                String key =dataSnapshot.getKey();
                String nama = dataSnapshot.getValue(com.example.myfirebaseapp.dosen.class).getNama();
                String jeniskelamin =dataSnapshot.getValue(com.example.myfirebaseapp.dosen.class).getJeniskelamin();
                String kode = dataSnapshot.getValue(com.example.myfirebaseapp.dosen.class).getKode();
                String lulusan = dataSnapshot.getValue(com.example.myfirebaseapp.dosen.class).getLulusan();
                String maker = dataSnapshot.getValue(com.example.myfirebaseapp.dosen.class).getMasakerja();
                arraytampil.add(hasil);
                arrayKunci.add(key);
                arrayHapus.add(key);
                arrayNama.add(nama);
                arrayJK.add(jeniskelamin);
                arrayKode.add(kode);
                arrayLulusan.add(lulusan);
                arrayMK.add(maker);


                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String hasil=snapshot.getValue(com.example.myfirebaseapp.dosen.class).toPrint();
                String key=snapshot.getKey();
                int noindex=arrayKunci.indexOf(key);
                arraytampil.set(noindex, hasil);
                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                String key=snapshot.getKey();
                int noindex=arrayHapus.indexOf(key);
                arraytampil.remove(noindex);
                arrayHapus.remove(noindex);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                final String selectedFromList=(String) arraytampil.get(position);
                final String selectedFromkunci=(String) arrayKunci.get(position);
                final String selectedFromNama= (String) arrayNama.get(position);
                final String selectedFromjk= (String) arrayJK.get(position);
                final String selectedFromkode= (String) arrayKode.get(position);
                final String selectedFromlulusan= (String) arrayLulusan.get(position);
                final String selectedFrommaker= (String) arrayMK.get(position);




                databaseReference.orderByChild(selectedFromList).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        arrayEdit.clear();
//                        for (DataSnapshot child: dataSnapshot.getChildren()){
//                            ckey=child.getKey();
//                            String ref=child.getRef().toString();
//                            String val=child.getValue().toString();
//                            arrayEdit.add(ckey);
//                            Toast.makeText(tampil_semua_data.this, ""+val, Toast.LENGTH_SHORT).show();
//                        }


//                        String kode=dataSnapshot.child(selectedFromList).getKey().substring(0,5);
//                        String nama=dataSnapshot.child(selectedFromList).getKey().substring(10);
//                        String jk=dataSnapshot.child(selectedFromList).getKey().substring(4);
//                        String lulusan=dataSnapshot.child(selectedFromList).getKey().substring(6);
//                        String maker=dataSnapshot.child(selectedFromList).getKey().substring(6);
//
//                        String key=dataSnapshot.child(selectedFromLis).getKey();


                        Intent intent=new Intent(TampilSemuaData.this, UpdateData.class);
                        intent.putExtra("skey",selectedFromkunci);
                        intent.putExtra("nama", selectedFromNama);
                        intent.putExtra("kode", selectedFromkode);
                        intent.putExtra("jeniskelamin",selectedFromjk );
                        intent.putExtra("lulusan", selectedFromlulusan);
                        intent.putExtra("masakerja", selectedFrommaker);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}