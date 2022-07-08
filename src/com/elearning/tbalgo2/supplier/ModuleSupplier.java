package com.elearning.tbalgo2.supplier;

import com.elearning.tbalgo2.color.Color;
import com.elearning.tbalgo2.mainMenu.MainMenu;
import com.elearning.tbalgo2.models.Models;
import com.elearning.tbalgo2.table.Table;
import com.elearning.tbalgo2.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ModuleSupplier {
    //init module
    public static void initModule() {
        MainMenu mainMenu = new MainMenu();

        //Module Title
        System.out.print(Color.YELLOW_BOLD);
        System.out.println("Data Supplier");
        System.out.print(Color.RESET);

        do {
            showDataSupplier();
            mainMenu.showMenuSupplier();
        } while (Models.isModuleSupplierRunning);
    }

    //munculkan data supplier
    public static void showDataSupplier() {
        Table table = new Table();
        List<String> headers = new ArrayList<>();
        List<Supplier> listSupplier = Models.listSupplier.stream().filter(supplier -> !supplier.isDeleted()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        if (listSupplier.size() != 0) {
            headers.add("Id");
            headers.add("Nama");
            headers.add("No. Telepon");
            headers.add("Alamat");

            List<List<String>> rows = new ArrayList<>();

            for (Supplier item : listSupplier) {
                List<String> row = new ArrayList<>();
                row.add(item.getId());
                row.add(item.getNama());
                row.add(item.getTelepon());
                row.add(item.getAlamat());
                rows.add(row);
            }

            System.out.println(table.generateTable(headers, rows));
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Belum ada data supplier!");
            System.out.print(Color.RESET);
        }
    }

    //tambah data supplier
    public static void addDataSupplier() {
        System.out.println("Silahkan masukan data supplier [-]-> Batal.");

        System.out.print("Nama Supplier: ");
        String nama = Utils.scanLine.apply(Models.input);
        if (nama != null && nama.equals("-")) {
            return;
        }

        System.out.print("No. Telepon: ");
        String telepon = Utils.scanLine.apply(Models.input);
        if (telepon != null && telepon.equals("-")) {
            return;
        }

        System.out.print("Alamat: ");
        String alamat = Utils.scanLine.apply(Models.input);
        if (alamat != null && alamat.equals("-")) {
            return;
        }

        String idSupplier = Utils.generateSupplierId(Models.listSupplier);
        Supplier supplier = new Supplier(idSupplier, nama, alamat, telepon);
        Models.listSupplier.add(supplier);
        System.out.print(Color.GREEN_BOLD);
        System.out.println("Data supplier berhasil ditambahkan!");
        System.out.print(Color.RESET);
    }

    //edit data supplier
    public static void updateDataSupplier() {
        System.out.print("Masukan id supplier yang akan diedit [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        Supplier supplier = Models.listSupplier.stream().filter(supplier1 -> supplier1.getId().equals(id)).findFirst().orElse(null);
        if (supplier != null) {
            System.out.println("Suplier ditemukan, silahkan masukan data yang akan di edit.");
            System.out.println("Masukan '0' untuk skip atau '-' untuk batal.");

            System.out.print("Nama Supplier " + "[" + supplier.getNama() + "]" + ": ");
            String nama = Utils.scanLine.apply(Models.input);
            if (nama != null && !nama.equals("0")) {
                supplier.setNama(nama);
            }
            if (nama != null && nama.equals("-")) {
                return;
            }

            System.out.print("No. Telepon " + "[" + supplier.getTelepon() + "]" + ": ");
            String telepon = Utils.scanLine.apply(Models.input);
            if (telepon != null && !telepon.equals("0")) {
                supplier.setTelepon(telepon);
            }
            if (telepon != null && telepon.equals("-")) {
                return;
            }

            System.out.print("Alamat " + "[" + supplier.getAlamat() + "]" + ": ");
            String alamat = Utils.scanLine.apply(Models.input);
            if (alamat != null && !alamat.equals("0")) {
                supplier.setAlamat(alamat);
            }
            if (alamat != null && alamat.equals("-")) {
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

    //hapus data supplier
    public static void deleteDataSupplier() {
        System.out.print("Masukan id supplier yang akan dihapus [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        Supplier supplier = Models.listSupplier.stream().filter(supplier1 -> supplier1.getId().equals(id)).findFirst().orElse(null);
        if (supplier != null) {
            supplier.setDeleted(true);
            System.out.print(Color.GREEN_BOLD);
            System.out.println("Data supplier berhasil dihapus!");
            System.out.print(Color.RESET);
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Data supplier tidak ditemukan!");
            System.out.print(Color.RESET);
        }
    }
}
