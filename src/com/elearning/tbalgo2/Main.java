package com.elearning.tbalgo2;

import com.elearning.tbalgo2.barang.Barang;
import com.elearning.tbalgo2.barangMasuk.BarangMasuk;
import com.elearning.tbalgo2.color.Color;
import com.elearning.tbalgo2.jenisBarang.Jenis;
import com.elearning.tbalgo2.mainMenu.MainMenu;
import com.elearning.tbalgo2.models.Models;
import com.elearning.tbalgo2.satuanBarang.Satuan;
import com.elearning.tbalgo2.supplier.Supplier;

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();

        //dummy satuan
        Satuan satuan1 = new Satuan("SA1", "Pcs");
        Satuan satuan2 = new Satuan("SA2", "Kg");
        Models.listSatuan.add(satuan1);
        Models.listSatuan.add(satuan2);
        //dummy jenis
        Jenis jenis1 = new Jenis("JI1", "Makanan");
        Jenis jenis2 = new Jenis("JI2", "Minuman");
        Jenis jenis3 = new Jenis("JI3", "Fashion");
        Models.listJenis.add(jenis1);
        Models.listJenis.add(jenis2);
        Models.listJenis.add(jenis3);
        //dummy barang
        Barang barang1 = new Barang("BRG1", "Mie Goreng", "SA1", "JI1", 2500);
        barang1.setStok(10);
        Barang barang2 = new Barang("BRG2", "Beras", "SA2", "JI1", 10000);
        Models.listBarang.add(barang1);
        Models.listBarang.add(barang2);
        //dummy supplier
        Supplier supplier1 = new Supplier("SUP1", "Supplier 1", "Jl. Raya 1", "081234567891");
        Supplier supplier2 = new Supplier("SUP2", "Supplier 2", "Jl. Raya 2", "081234567892");
        Models.listSupplier.add(supplier1);
        Models.listSupplier.add(supplier2);

        //dummy barang masuk
        BarangMasuk barangMasuk = new BarangMasuk("IN1", "BRG1", "SUP1", 10);
        Models.listBarangMasuk.add(barangMasuk);

        //app title
        System.out.println("");
        System.out.print(Color.YELLOW_BOLD);
        System.out.println("=======================================            MY STOCK             ====================================");
        System.out.print(Color.RESET);
        System.out.println("");

        do {
            mainMenu.showMainMenu();
        } while (Models.isAppRunning);
    }
}
