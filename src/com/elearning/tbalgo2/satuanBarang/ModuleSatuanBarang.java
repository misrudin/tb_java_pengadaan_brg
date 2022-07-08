package com.elearning.tbalgo2.satuanBarang;

import com.elearning.tbalgo2.color.Color;
import com.elearning.tbalgo2.mainMenu.MainMenu;
import com.elearning.tbalgo2.models.Models;
import com.elearning.tbalgo2.table.Table;
import com.elearning.tbalgo2.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ModuleSatuanBarang {
    //init module
    public static void initModule() {
        MainMenu mainMenu = new MainMenu();

        //Module Title
        System.out.print(Color.YELLOW_BOLD);
        System.out.println("Satuan Barang");
        System.out.print(Color.RESET);

        do {
            showDataSatuanBarang();
            mainMenu.showMenuSatuanBarang();
        } while (Models.isModuleSatuanRunning);
    }

    //munculkan data satuan barang
    public static void showDataSatuanBarang() {
        Table table = new Table();
        List<String> headers = new ArrayList<>();
        List<Satuan> listSatuan = Models.listSatuan.stream().filter(satuan -> !satuan.isDeleted()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        if (listSatuan.size() != 0) {
            headers.add("Id.");
            headers.add("Satuan");

            List<List<String>> rows = new ArrayList<>();

            for (Satuan item : listSatuan) {
                List<String> row = new ArrayList<>();
                row.add(String.valueOf(item.getId()));
                row.add(item.getNama());
                rows.add(row);
            }

            System.out.println(table.generateTable(headers, rows));
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Belum ada data satuan barang!");
            System.out.print(Color.RESET);
        }
    }

    //tambah data satuan barang
    public static void addDataSatuanBarang() {
        System.out.println("Silahkan masukan data satuan barang yang ingin anda tambahkan [-]-> Batal.");

        System.out.print("Satuan: ");
        String nama = Utils.scanLine.apply(Models.input);
        if (nama != null && nama.equals("-")) {
            return;
        }

        String idSatuan = Utils.generateSatuanId(Models.listSatuan);
        Satuan satuan = new Satuan(idSatuan, nama);
        Models.listSatuan.add(satuan);

        System.out.print(Color.GREEN_BOLD);
        System.out.println("Data supplier berhasil ditambahkan!");
        System.out.print(Color.RESET);
    }

    //edit data satuan barang
    public static void updateDataSatuanBarang() {
        System.out.print("Masukan id satuan barang yang akan diedit [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        Satuan satuan = Models.listSatuan.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
        if (satuan != null) {
            System.out.println("Jenis barand ditemukan, silahkan masukan data yang akan di edit.");
            System.out.println("Masukan '0' untuk skip atau '-' untuk batal.");

            System.out.print("Jenis Barang " + "[" + satuan.getNama() + "]" + ": ");
            String nama = Utils.scanLine.apply(Models.input);
            if (nama != null && !nama.equals("0")) {
                satuan.setNama(nama);
            }
            if (nama != null && nama.equals("-")) {
                return;
            }

            System.out.print(Color.GREEN_BOLD);
            System.out.println("Data supplier berhasil diedit!");
            System.out.print(Color.RESET);
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Data supplier tidak ditemukan!");
            System.out.print(Color.RESET);
        }
    }

    //hapus data satuan barang
    public static void deleteDataSatuanBarang() {
        System.out.print("Masukan id satuan barang yang akan dihapus [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        Satuan satuan = Models.listSatuan.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
        if (satuan != null) {
            satuan.setDeleted(true);
            System.out.print(Color.GREEN_BOLD);
            System.out.println("Data satuan barang berhasil dihapus!");
            System.out.print(Color.RESET);
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Data satuan barang tidak ditemukan!");
            System.out.print(Color.RESET);
        }
    }
}
