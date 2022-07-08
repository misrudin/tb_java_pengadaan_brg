package com.elearning.tbalgo2.barangKeluar;

import com.elearning.tbalgo2.barang.Barang;
import com.elearning.tbalgo2.barangMasuk.BarangMasuk;
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

public class ModuleBarangKeluar {
    //init module
    public static void initModule() {
        MainMenu mainMenu = new MainMenu();

        //Module Title
        System.out.print(Color.YELLOW_BOLD);
        System.out.println("Data Barang Keluar");
        System.out.print(Color.RESET);

        do {
            showDataBarangKeluar();
            mainMenu.showMenuBarangKeluar();
        } while (Models.isModuleBarangKeluarRunning);
    }

    //munculkan data barang keluar
    public static void showDataBarangKeluar() {
        Table table = new Table();
        List<String> headers = new ArrayList<>();
        List<BarangKeluar> listBarangKeluar = Models.listBarangKeluar;
        List<Barang> listBarang = Models.listBarang;
        List<Satuan> listSatuan = Models.listSatuan;
        List<Jenis> listJenis = Models.listJenis;

        if (listBarangKeluar.size() != 0) {
            headers.add("Id.");
            headers.add("Tanggal");
            headers.add("Jenis Barang");
            headers.add("Nama Barang");
            headers.add("Jumlah");
            headers.add("Satuan");
            headers.add("Total");

            List<List<String>> rows = new ArrayList<>();

            for (BarangKeluar item : listBarangKeluar) {
                List<String> row = new ArrayList<>();
                row.add(String.valueOf(item.getId()));
                row.add(item.getCreated_at());

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
            System.out.println("Opss, Belum ada data barang keluar!");
            System.out.print(Color.RESET);
        }
    }

    //tambah data barang keluar
    public static void addDataBarangKeluar() {
        boolean isValidBarang;
        boolean isValidJumlah;

        System.out.println("Silahkan masukan data, tekan '-' untuk batal.");

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
                if (barang.getStok() == 0) {
                    System.out.print(Color.RED_BOLD);
                    System.out.println("Stok barang tidak tersedia!");
                    System.out.print(Color.RESET);
                    isValidBarang = false;
                } else {
                    kodeBarang = id;
                    System.out.print(Color.GREEN_BOLD);
                    Satuan satuan = Models.listSatuan.stream().filter(s -> Objects.equals(s.getId(), barang.getIdSatuan())).findFirst().orElse(null);
                    assert satuan != null;
                    System.out.println("Barang ditemukan [" + barang.getNama() + "(" + barang.getStok() + " " + satuan.getNama() + ")" + "]");
                    System.out.print(Color.RESET);
                }
            }
        } while (!isValidBarang);

        String jumlah = "";
        do {
            String finalKodeBarang = kodeBarang;
            Barang barang = Models.listBarang.stream().filter(x -> x.getKode().equals(finalKodeBarang)).findFirst().orElse(null);

            System.out.print("Jumlah: ");
            jumlah = Utils.scanLine.apply(Models.input);
            if (jumlah != null && jumlah.equals("-")) {
                return;
            }
            assert barang != null;
            assert jumlah != null;
            isValidJumlah = Utils.isValidNumber(jumlah) && Integer.parseInt(jumlah) <= barang.getStok();
            if (!isValidJumlah) {
                System.out.print(Color.RED_BOLD);
                System.out.println("Stock tidak cukup!");
                System.out.print(Color.RESET);
            }
        } while (!isValidJumlah);

        String idBarangKeluar = Utils.generateBarangKeluarId(Models.listBarangKeluar);
        BarangKeluar barangKeluar = new BarangKeluar(idBarangKeluar, kodeBarang, Integer.parseInt(jumlah));

        Barang barang = Models.listBarang.stream().filter(x -> x.getKode().equals(barangKeluar.getIdBarang())).findFirst().orElse(null);
        assert barang != null;
        barang.setStok(barang.getStok() - barangKeluar.getQuantity());

        Models.listBarangKeluar.add(barangKeluar);
        System.out.print(Color.GREEN_BOLD);
        System.out.println("Data berhasil ditambahkan!");
        System.out.print(Color.RESET);
    }

    //update data barang keluar
    public static void updateDataBarangKeluar() {
        boolean isValidJumlah;

        System.out.print("Masukan id yang akan diedit [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        BarangKeluar barangKeluar = Models.listBarangKeluar.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if (barangKeluar != null) {
            System.out.println("Data ditemukan, silahkan masukan data yang akan di edit.");
            System.out.println("Masukan '0' untuk skip atau '-' untuk batal.");

            do {
                Barang barang = Models.listBarang.stream().filter(x -> x.getKode().equals(barangKeluar.getIdBarang())).findFirst().orElse(null);

                System.out.print("Jumlah: ");
                String jumlah = Utils.scanLine.apply(Models.input);
                if (jumlah != null && jumlah.equals("-")) {
                    return;
                }
                assert barang != null;
                assert jumlah != null;
                isValidJumlah = Utils.isValidNumber(jumlah) && (Integer.parseInt(jumlah) + barangKeluar.getQuantity()) <= barang.getStok();
                if (!isValidJumlah) {
                    System.out.print(Color.RED_BOLD);
                    System.out.println("Stock tidak cukup!");
                    System.out.print(Color.RESET);
                } else {
                    barang.setStok(barang.getStok() + barangKeluar.getQuantity());
                    barangKeluar.setQuantity(Integer.parseInt(jumlah));
                    barang.setStok(barang.getStok() - barangKeluar.getQuantity());
                }
            } while (!isValidJumlah);

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
