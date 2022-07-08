package com.elearning.tbalgo2.barang;

public class Barang {
    private String kode;
    private String nama;
    private String idSatuan;
    private String idJenis;
    private float harga;
    private int stok;
    private boolean isDeleted;

    public Barang(String kode, String nama, String idSatuan, String idJenis, float harga) {
        this.kode = kode;
        this.nama = nama;
        this.idSatuan = idSatuan;
        this.idJenis = idJenis;
        this.harga = harga;
    }

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

    public String getIdSatuan() {
        return idSatuan;
    }

    public void setIdSatuan(String idSatuan) {
        this.idSatuan = idSatuan;
    }

    public String getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(String idJenis) {
        this.idJenis = idJenis;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
