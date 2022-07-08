package com.elearning.tbalgo2.jenisBarang;

import com.elearning.tbalgo2.color.Color;
import com.elearning.tbalgo2.mainMenu.MainMenu;
import com.elearning.tbalgo2.models.Models;
import com.elearning.tbalgo2.table.Table;
import com.elearning.tbalgo2.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ModuleJenisBarang {
    //init module
    public static void initModule() {
        MainMenu mainMenu = new MainMenu();

        //Module Title
        System.out.print(Color.YELLOW_BOLD);
        System.out.println("Jenis Barang");
        System.out.print(Color.RESET);

        do {
            showDataJenisBarang();
            mainMenu.showMenuJenisBarang();
        } while (Models.isModuleJenisRunning);
    }

    //munculkan data jenis barang
    public static void showDataJenisBarang() {
        Table table = new Table();
        List<String> headers = new ArrayList<>();
        List<Jenis> listJenis = Models.listJenis.stream().filter(jenis -> !jenis.isDeleted()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        if (listJenis.size() != 0) {
            headers.add("Id.");
            headers.add("Jenis");

            List<List<String>> rows = new ArrayList<>();

            for (Jenis item : listJenis) {
                List<String> row = new ArrayList<>();
                row.add(String.valueOf(item.getId()));
                row.add(item.getNama());
                rows.add(row);
            }

            System.out.println(table.generateTable(headers, rows));
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Belum ada data jenis barang!");
            System.out.print(Color.RESET);
        }
    }

    //tambah data jenis barang
    public static void addDataJenisBarang() {
        System.out.println("Silahkan masukan data jenis barang [-]-> Batal.");
        System.out.print("Jenis: ");
        String nama = Utils.scanLine.apply(Models.input);
        if (nama != null && nama.equals("-")) {
            return;
        }

        String idJenisBarang = Utils.generateJenisId(Models.listJenis);
        Jenis jenis = new Jenis(idJenisBarang, nama);
        Models.listJenis.add(jenis);
        System.out.print(Color.GREEN_BOLD);
        System.out.println("Data jenis barang berhasil ditambahkan!");
        System.out.print(Color.RESET);
    }

    //edit data jenis barang
    public static void updateDataJenisBarang() {
        System.out.print("Masukan id jenis barang yang akan diedit [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        Jenis jenis = Models.listJenis.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
        if (jenis != null) {
            System.out.println("Jenis barand ditemukan, silahkan masukan data yang akan di edit.");
            System.out.println("Masukan '0' untuk skip atau '-' untuk batal.");

            System.out.print("Jenis Barang " + "[" + jenis.getNama() + "]" + ": ");
            String nama = Utils.scanLine.apply(Models.input);
            if (nama != null && !nama.equals("0")) {
                jenis.setNama(nama);
            }
            if (nama != null && nama.equals("-")) {
                return;
            }

            System.out.print(Color.GREEN_BOLD);
            System.out.println("Data jenis barang berhasil diedit!");
            System.out.print(Color.RESET);
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Data jenis barang tidak ditemukan!");
            System.out.print(Color.RESET);
        }
    }

    //hapus data jenis barang
    public static void deleteDataJenisBarang() {
        System.out.print("Masukan id jenis barang yang akan dihapus [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        Jenis jenis = Models.listJenis.stream().filter(jenisBarang -> jenisBarang.getId().equals(id)).findFirst().orElse(null);
        if (jenis != null) {
            jenis.setDeleted(true);
            System.out.print(Color.GREEN_BOLD);
            System.out.println("Data jenis barang berhasil dihapus!");
            System.out.print(Color.RESET);
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Data jenis barang tidak ditemukan!");
            System.out.print(Color.RESET);
        }
    }
}
