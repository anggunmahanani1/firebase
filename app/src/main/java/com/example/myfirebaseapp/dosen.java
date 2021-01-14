package com.example.myfirebaseapp;

public class dosen {
    private String kode;
    private  String nama;
    private  String jeniskelamin;
    private String masakerja;
    private String lulusan;
    private String key;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }


    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getMasakerja() {
        return masakerja;
    }

    public void setMasakerja(String masakerja) {
        this.masakerja = masakerja;
    }

    public String getLulusan() {
        return lulusan;
    }

    public void setLulusan(String lulusan) {
        this.lulusan = lulusan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String toPrint() {
        return this.kode+ " "+nama+ " "+jeniskelamin+ " "+lulusan+ " "+masakerja+ " ";
    }
}

