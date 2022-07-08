package com.elearning.tbalgo2.barangMasuk;

import com.elearning.tbalgo2.utils.Utils;

public class BarangMasuk {
    private String id;
    private String idBarang;
    private String idSupplier;
    private String created_at;
    private int quantity;

    public BarangMasuk(String id, String idBarang, String idSupplier, int quantity) {
        this.id = id;
        this.idBarang = idBarang;
        this.idSupplier = idSupplier;
        this.created_at = Utils.getDateTimeNow();
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreated_at() {
        return created_at;
    }
}
