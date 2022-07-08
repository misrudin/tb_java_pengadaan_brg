package com.elearning.tbalgo2.barangMasuk;

import com.elearning.tbalgo2.barang.Barang;
import com.elearning.tbalgo2.color.Color;
import com.elearning.tbalgo2.jenisBarang.Jenis;
import com.elearning.tbalgo2.mainMenu.MainMenu;
import com.elearning.tbalgo2.models.Models;
import com.elearning.tbalgo2.satuanBarang.Satuan;
import com.elearning.tbalgo2.supplier.Supplier;
import com.elearning.tbalgo2.table.Table;
import com.elearning.tbalgo2.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleBarangMasuk {
    //init module
    public static void initModule() {
        MainMenu mainMenu = new MainMenu();

        //Module Title
        System.out.print(Color.YELLOW_BOLD);
        System.out.println("Data Barang Masuk");
        System.out.print(Color.RESET);

        do {
            showDataBarangMasuk();
            mainMenu.showMenuBarangMasuk();
        } while (Models.isModuleBarangMasukRunning);
    }

    //munculkan data barang masuk
    public static void showDataBarangMasuk() {
        Table table = new Table();
        List<String> headers = new ArrayList<>();
        List<BarangMasuk> listBarangMasuk = Models.listBarangMasuk;
        List<Barang> listBarang = Models.listBarang;
        List<Satuan> listSatuan = Models.listSatuan;
        List<Jenis> listJenis = Models.listJenis;

        if (listBarangMasuk.size() != 0) {
            headers.add("Id.");
            headers.add("Tanggal");
            headers.add("Supplier");
            headers.add("Jenis Barang");
            headers.add("Nama Barang");
            headers.add("Jumlah");
            headers.add("Satuan");
            headers.add("Total");

            List<List<String>> rows = new ArrayList<>();

            for (BarangMasuk item : listBarangMasuk) {
                List<String> row = new ArrayList<>();
                row.add(String.valueOf(item.getId()));
                row.add(item.getCreated_at());

                Supplier supplier = Models.listSupplier.stream().filter(s -> s.getId().equals(item.getIdSupplier())).findFirst().orElse(null);
                assert supplier != null;
                row.add(supplier.getNama());

                Barang barang = listBarang.stream().filter(x -> x.getKode().equals(item.getIdBarang())).findFirst().orElse(null);
                assert barang != null;

                Jenis jenis = listJenis.stream().filter(j -> Objects.equals(j.getId(), barang.getIdJenis())).findFirst().orElse(null);
                assert jenis != null;
                row.add(jenis.getNama());

                row.add(barang.getNama());

                row.add(String.valueOf(item.getQuantity()));

                Satuan satuan = listSatuan.stream().filter(s -> Objects.equals(s.getId(), barang.getIdSatuan())).findFirst().orElse(null);
                assert satuan != null;
                row.add(satuan.getNama());

                row.add(String.format("Rp. %,.0f", barang.getHarga() * item.getQuantity()));
                rows.add(row);
            }

            System.out.println(table.generateTable(headers, rows));
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Belum ada data barang masuk!");
            System.out.print(Color.RESET);
        }
    }

    //tambah data barang masuk
    public static void addDataBarangMasuk() {
        boolean isValidSupplier;
        boolean isValidBarang;

        System.out.println("Silahkan masukan data, tekan '-' untuk batal.");

        String idSupplier = "";
        do {
            System.out.print("Id Supplier: ");
            String id = Utils.scanLine.apply(Models.input);
            if (id != null && id.equals("-")) {
                return;
            }
            Supplier supplier = Models.listSupplier.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
            isValidSupplier = supplier != null;
            if (!isValidSupplier) {
                System.out.print(Color.RED_BOLD);
                System.out.println("Supplier tidak ditemukan!");
                System.out.print(Color.RESET);
            } else {
                idSupplier = id;
                System.out.print(Color.GREEN_BOLD);
                System.out.println("Supplier ditemukan [" + supplier.getNama() + "]");
                System.out.print(Color.RESET);
            }
        } while (!isValidSupplier);

        String kodeBarang = "";
        do {
            System.out.print("Kode Barang: ");
            String id = Utils.scanLine.apply(Models.input);
            if (id != null && id.equals("-")) {
                return;
            }
            Barang barang = Models.listBarang.stream().filter(x -> x.getKode().equals(id)).findFirst().orElse(null);
            isValidBarang = barang != null;
            if (!isValidBarang) {
                System.out.print(Color.RED_BOLD);
                System.out.println("Barang tidak ditemukan!");
                System.out.print(Color.RESET);
            } else {
                kodeBarang = id;
                System.out.print(Color.GREEN_BOLD);
                System.out.println("Barang ditemukan [" + barang.getNama() + "]");
                System.out.print(Color.RESET);
            }
        } while (!isValidBarang);

        System.out.print("Jumlah: ");
        String jumlah = Utils.scanLine.apply(Models.input);
        if (jumlah != null && jumlah.equals("-")) {
            return;
        }
        String idBarangMasuk = Utils.generateBarangMasukId(Models.listBarangMasuk);
        assert jumlah != null;
        BarangMasuk barangMasuk = new BarangMasuk(idBarangMasuk, kodeBarang, idSupplier, Integer.parseInt(jumlah));

        Barang barang = Models.listBarang.stream().filter(x -> x.getKode().equals(barangMasuk.getIdBarang())).findFirst().orElse(null);
        assert barang != null;
        barang.setStok(barang.getStok() + barangMasuk.getQuantity());

        Models.listBarangMasuk.add(barangMasuk);
        System.out.print(Color.GREEN_BOLD);
        System.out.println("Data berhasil ditambahkan!");
        System.out.print(Color.RESET);
    }

    //update data barang masuk
    public static void updateDataBarangMasuk() {
        System.out.print("Masukan id yang akan diedit [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        BarangMasuk barangMasuk = Models.listBarangMasuk.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if (barangMasuk != null) {
            System.out.println("Data ditemukan, silahkan masukan data yang akan di edit.");
            System.out.println("Masukan '-' untuk batal.");

            Barang barang = Models.listBarang.stream().filter(x -> x.getKode().equals(barangMasuk.getIdBarang())).findFirst().orElse(null);
            assert barang != null;

            System.out.print("Jumlah " + "[" + barangMasuk.getQuantity() + "]" + ": ");
            String jumlah = Utils.scanLine.apply(Models.input);
            if (jumlah != null && jumlah.equals("-")) {
                return;
            } else {
                barang.setStok(barang.getStok() - barangMasuk.getQuantity());
                barangMasuk.setQuantity(Integer.parseInt(jumlah));
                barang.setStok(barang.getStok() + barangMasuk.getQuantity());
            }

            System.out.print(Color.GREEN_BOLD);
            System.out.println("Data berhasil diedit!");
            System.out.print(Color.RESET);
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Data tidak ditemukan!");
            System.out.print(Color.RESET);
        }
    }
}