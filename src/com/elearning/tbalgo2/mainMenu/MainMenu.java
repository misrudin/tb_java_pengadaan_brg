package com.elearning.tbalgo2.mainMenu;

import com.elearning.tbalgo2.barang.ModuleBarang;
import com.elearning.tbalgo2.barangKeluar.ModuleBarangKeluar;
import com.elearning.tbalgo2.barangMasuk.ModuleBarangMasuk;
import com.elearning.tbalgo2.color.Color;
import com.elearning.tbalgo2.jenisBarang.ModuleJenisBarang;
import com.elearning.tbalgo2.models.Models;
import com.elearning.tbalgo2.satuanBarang.ModuleSatuanBarang;
import com.elearning.tbalgo2.supplier.ModuleSupplier;
import com.elearning.tbalgo2.table.Table;
import com.elearning.tbalgo2.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    public void showMainMenu() {
        List<Menu> listMenu = new ArrayList<>();
        Table table = new Table();
        listMenu.add(new Menu(1, "Data Barang"));
        listMenu.add(new Menu(2, "Supplier"));
        listMenu.add(new Menu(3, "Barang Masuk"));
        listMenu.add(new Menu(4, "Barang Keluar"));
        listMenu.add(new Menu(5, "Keluar Aplikasi"));

        List<String> headers = new ArrayList<>();

        for (Menu menu : listMenu) {
            headers.add("[" + menu.getId() + "] " + menu.getNama());
        }

        System.out.print(Color.YELLOW_BOLD);
        System.out.println(table.generateTable(headers, null));
        System.out.print(Color.RESET);

        System.out.print(Color.CYAN_BOLD);
        System.out.print("Masukkan pilihan menu: ");
        System.out.print(Color.RESET);

        String input = Utils.scanLine.apply(Models.input);
        int selectedMenu = Utils.isValidNumber(input) ? Integer.parseInt(input) : 0;
        switch (selectedMenu) {
            case 1:
                Models.isAppRunning = true;
                ModuleBarang.initModule();
                break;
            case 2:
                Models.isAppRunning = true;
                ModuleSupplier.initModule();
                break;
            case 3:
                Models.isAppRunning = true;
                ModuleBarangMasuk.initModule();
                break;
            case 4:
                Models.isAppRunning = true;
                ModuleBarangKeluar.initModule();
                break;
            case 5:
                System.out.print(Color.YELLOW_BOLD);
                System.out.println("SAMPAI JUMPA LAGI!");
                System.out.print(Color.RESET);
                Models.isAppRunning = false;
                break;
            default:
                System.out.print(Color.RED_BOLD);
                System.out.println("Opss, Pilihan menu tidak ada!");
                System.out.print(Color.RESET);
                Models.isAppRunning = true;
                break;
        }
    }

    public void showMenuBarang() {
        List<Menu> listMenu = new ArrayList<>();
        Table table = new Table();
        listMenu.add(new Menu(1, "Satuan"));
        listMenu.add(new Menu(2, "Jenis Barang"));
        listMenu.add(new Menu(3, "Tambah Barang"));
        listMenu.add(new Menu(4, "Edit"));
        listMenu.add(new Menu(5, "Hapus"));
        listMenu.add(new Menu(6, "Home"));

        List<String> headers = new ArrayList<>();

        for (Menu menu : listMenu) {
            headers.add("[" + menu.getId() + "] " + menu.getNama());
        }

        System.out.print(Color.YELLOW_BOLD);
        System.out.println(table.generateTable(headers, null));
        System.out.print(Color.RESET);

        System.out.print(Color.CYAN_BOLD);
        System.out.print("Masukkan pilihan menu: ");
        System.out.print(Color.RESET);

        String input = Utils.scanLine.apply(Models.input);
        int selectedMenu = Utils.isValidNumber(input) ? Integer.parseInt(input) : 0;
        switch (selectedMenu) {
            case 1:
                Models.isModuleBarangRunning = true;
                ModuleSatuanBarang.initModule();
                break;
            case 2:
                Models.isModuleBarangRunning = true;
                ModuleJenisBarang.initModule();
                break;
            case 3:
                Models.isModuleBarangRunning = true;
                ModuleBarang.addDataBarang();
                break;
            case 4:
                Models.isModuleBarangRunning = true;
                ModuleBarang.updateDataBarang();
                break;
            case 5:
                Models.isModuleBarangRunning = true;
                ModuleBarang.deleteDataBarang();
                break;
            case 6:
                Models.isModuleBarangRunning = false;
                break;
            default:
                System.out.print(Color.RED_BOLD);
                System.out.println("Opss, Pilihan menu tidak ada!");
                System.out.print(Color.RESET);
                Models.isModuleBarangRunning = true;
                break;
        }
    }

    public void showMenuSupplier() {
        List<Menu> listMenu = new ArrayList<>();
        Table table = new Table();
        listMenu.add(new Menu(1, "Tambah"));
        listMenu.add(new Menu(2, "Edit"));
        listMenu.add(new Menu(3, "Hapus"));
        listMenu.add(new Menu(4, "Home"));

        List<String> headers = new ArrayList<>();

        for (Menu menu : listMenu) {
            headers.add("[" + menu.getId() + "] " + menu.getNama());
        }

        System.out.print(Color.YELLOW_BOLD);
        System.out.println(table.generateTable(headers, null));
        System.out.print(Color.RESET);

        System.out.print(Color.CYAN_BOLD);
        System.out.print("Masukkan pilihan menu: ");
        System.out.print(Color.RESET);

        String input = Utils.scanLine.apply(Models.input);
        int selectedMenu = Utils.isValidNumber(input) ? Integer.parseInt(input) : 0;
        switch (selectedMenu) {
            case 1:
                Models.isModuleSupplierRunning = true;
                ModuleSupplier.addDataSupplier();
                break;
            case 2:
                Models.isModuleSupplierRunning = true;
                ModuleSupplier.updateDataSupplier();
                break;
            case 3:
                Models.isModuleSupplierRunning = true;
                ModuleSupplier.deleteDataSupplier();
                break;
            case 4:
                Models.isModuleSupplierRunning = false;
                break;
            default:
                System.out.print(Color.RED_BOLD);
                System.out.println("Opss, Pilihan menu tidak ada!");
                System.out.print(Color.RESET);
                Models.isModuleSupplierRunning = true;
                break;
        }
    }

    public void showMenuJenisBarang() {
        List<Menu> listMenu = new ArrayList<>();
        Table table = new Table();
        listMenu.add(new Menu(1, "Tambah"));
        listMenu.add(new Menu(2, "Edit"));
        listMenu.add(new Menu(3, "Hapus"));
        listMenu.add(new Menu(4, "Kembali"));

        List<String> headers = new ArrayList<>();

        for (Menu menu : listMenu) {
            headers.add("[" + menu.getId() + "] " + menu.getNama());
        }

        System.out.print(Color.YELLOW_BOLD);
        System.out.println(table.generateTable(headers, null));
        System.out.print(Color.RESET);

        System.out.print(Color.CYAN_BOLD);
        System.out.print("Masukkan pilihan menu: ");
        System.out.print(Color.RESET);

        String input = Utils.scanLine.apply(Models.input);
        int selectedMenu = Utils.isValidNumber(input) ? Integer.parseInt(input) : 0;
        switch (selectedMenu) {
            case 1:
                Models.isModuleJenisRunning = true;
                ModuleJenisBarang.addDataJenisBarang();
                break;
            case 2:
                Models.isModuleJenisRunning = true;
                ModuleJenisBarang.updateDataJenisBarang();
                break;
            case 3:
                Models.isModuleJenisRunning = true;
                ModuleJenisBarang.deleteDataJenisBarang();
                break;
            case 4:
                Models.isModuleJenisRunning = false;
                break;
            default:
                System.out.print(Color.RED_BOLD);
                System.out.println("Opss, Pilihan menu tidak ada!");
                System.out.print(Color.RESET);
                Models.isModuleJenisRunning = true;
                break;
        }
    }

    public void showMenuSatuanBarang() {
        List<Menu> listMenu = new ArrayList<>();
        Table table = new Table();
        listMenu.add(new Menu(1, "Tambah"));
        listMenu.add(new Menu(2, "Edit"));
        listMenu.add(new Menu(3, "Hapus"));
        listMenu.add(new Menu(4, "Kembali"));

        List<String> headers = new ArrayList<>();

        for (Menu menu : listMenu) {
            headers.add("[" + menu.getId() + "] " + menu.getNama());
        }

        System.out.print(Color.YELLOW_BOLD);
        System.out.println(table.generateTable(headers, null));
        System.out.print(Color.RESET);

        System.out.print(Color.CYAN_BOLD);
        System.out.print("Masukkan pilihan menu: ");
        System.out.print(Color.RESET);

        String input = Utils.scanLine.apply(Models.input);
        int selectedMenu = Utils.isValidNumber(input) ? Integer.parseInt(input) : 0;
        switch (selectedMenu) {
            case 1:
                Models.isModuleSatuanRunning = true;
                ModuleSatuanBarang.addDataSatuanBarang();
                break;
            case 2:
                Models.isModuleSatuanRunning = true;
                ModuleSatuanBarang.updateDataSatuanBarang();
                break;
            case 3:
                Models.isModuleSatuanRunning = true;
                ModuleSatuanBarang.deleteDataSatuanBarang();
                break;
            case 4:
                Models.isModuleSatuanRunning = false;
                break;
            default:
                System.out.print(Color.RED_BOLD);
                System.out.println("Opss, Pilihan menu tidak ada!");
                System.out.print(Color.RESET);
                Models.isModuleSatuanRunning = true;
                break;
        }
    }

    public void showMenuBarangMasuk() {
        List<Menu> listMenu = new ArrayList<>();
        Table table = new Table();
        listMenu.add(new Menu(1, "Tambah"));
        listMenu.add(new Menu(2, "Edit"));
        listMenu.add(new Menu(3, "Home"));

        List<String> headers = new ArrayList<>();

        for (Menu menu : listMenu) {
            headers.add("[" + menu.getId() + "] " + menu.getNama());
        }

        System.out.print(Color.YELLOW_BOLD);
        System.out.println(table.generateTable(headers, null));
        System.out.print(Color.RESET);

        System.out.print(Color.CYAN_BOLD);
        System.out.print("Masukkan pilihan menu: ");
        System.out.print(Color.RESET);

        String input = Utils.scanLine.apply(Models.input);
        int selectedMenu = Utils.isValidNumber(input) ? Integer.parseInt(input) : 0;
        switch (selectedMenu) {
            case 1:
                Models.isModuleBarangMasukRunning = true;
                ModuleBarangMasuk.addDataBarangMasuk();
                break;
            case 2:
                Models.isModuleBarangMasukRunning = true;
                ModuleBarangMasuk.updateDataBarangMasuk();
                break;
            case 3:
                Models.isModuleBarangMasukRunning = false;
                break;
            default:
                System.out.print(Color.RED_BOLD);
                System.out.println("Opss, Pilihan menu tidak ada!");
                System.out.print(Color.RESET);
                Models.isModuleBarangMasukRunning = true;
                break;
        }
    }

    public void showMenuBarangKeluar() {
        List<Menu> listMenu = new ArrayList<>();
        Table table = new Table();
        listMenu.add(new Menu(1, "Tambah"));
        listMenu.add(new Menu(2, "Edit"));
        listMenu.add(new Menu(3, "Home"));

        List<String> headers = new ArrayList<>();

        for (Menu menu : listMenu) {
            headers.add("[" + menu.getId() + "] " + menu.getNama());
        }

        System.out.print(Color.YELLOW_BOLD);
        System.out.println(table.generateTable(headers, null));
        System.out.print(Color.RESET);

        System.out.print(Color.CYAN_BOLD);
        System.out.print("Masukkan pilihan menu: ");
        System.out.print(Color.RESET);

        String input = Utils.scanLine.apply(Models.input);
        int selectedMenu = Utils.isValidNumber(input) ? Integer.parseInt(input) : 0;
        switch (selectedMenu) {
            case 1:
                Models.isModuleBarangKeluarRunning = true;
                ModuleBarangKeluar.addDataBarangKeluar();
                break;
            case 2:
                Models.isModuleBarangKeluarRunning = true;
                ModuleBarangKeluar.updateDataBarangKeluar();
                break;
            case 3:
                Models.isModuleBarangKeluarRunning = false;
                break;
            default:
                System.out.print(Color.RED_BOLD);
                System.out.println("Opss, Pilihan menu tidak ada!");
                System.out.print(Color.RESET);
                Models.isModuleBarangKeluarRunning = true;
                break;
        }
    }
}
