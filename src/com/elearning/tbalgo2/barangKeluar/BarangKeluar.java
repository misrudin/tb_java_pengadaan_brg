package com.elearning.tbalgo2.barangKeluar;

import com.elearning.tbalgo2.utils.Utils;

public class BarangKeluar {
    private String id;
    private String idBarang;
    private String created_at;
    private int quantity;

    public BarangKeluar(String id, String idBarang, int quantity) {
        this.id = id;
        this.idBarang = idBarang;
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

    public String getCreated_at() {
        return created_at;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
