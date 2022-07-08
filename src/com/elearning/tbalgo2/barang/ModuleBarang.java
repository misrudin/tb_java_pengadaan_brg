package com.elearning.tbalgo2.barang;

import com.elearning.tbalgo2.color.Color;
import com.elearning.tbalgo2.jenisBarang.Jenis;
import com.elearning.tbalgo2.mainMenu.MainMenu;
import com.elearning.tbalgo2.models.Models;
import com.elearning.tbalgo2.satuanBarang.Satuan;
import com.elearning.tbalgo2.table.Table;
import com.elearning.tbalgo2.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleBarang {
    //init module
    public static void initModule() {
        MainMenu mainMenu = new MainMenu();

        //Module Title
        System.out.print(Color.YELLOW_BOLD);
        System.out.println("Daftar Barang");
        System.out.print(Color.RESET);

        do {
            showDataBarang();
            mainMenu.showMenuBarang();
        } while (Models.isModuleBarangRunning);
    }


    //munculkan data barang
    public static void showDataBarang() {
        Table table = new Table();
        List<String> headers = new ArrayList<>();
        List<Barang> listBarang = Models.listBarang.stream().filter(barang -> !barang.isDeleted()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        List<Satuan> listSatuan = Models.listSatuan;
        List<Jenis> listJenis = Models.listJenis;

        if (listBarang.size() != 0) {
            headers.add("Id.");
            headers.add("Nama");
            headers.add("Jenis");
            headers.add("Harga");
            headers.add("Stock");
            headers.add("Satuan");

            List<List<String>> rows = new ArrayList<>();

            for (Barang item : listBarang) {
                List<String> row = new ArrayList<>();
                row.add(String.valueOf(item.getKode()));
                row.add(item.getNama());

                Jenis jenis = listJenis.stream().filter(j -> Objects.equals(j.getId(), item.getIdJenis())).findFirst().orElse(null);
                assert jenis != null;
                row.add(jenis.getNama());

                row.add(String.format("Rp. %,.0f", item.getHarga()));

                row.add(String.valueOf(item.getStok()));

                Satuan satuan = listSatuan.stream().filter(s -> Objects.equals(s.getId(), item.getIdSatuan())).findFirst().orElse(null);
                assert satuan != null;
                row.add(satuan.getNama());

                rows.add(row);
            }

            System.out.println(table.generateTable(headers, rows));
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Belum ada data barang!");
            System.out.print(Color.RESET);
        }
    }

    //tambah data barang
    public static void addDataBarang() {
        boolean isValidJenis;
        boolean isValidSatuan;

        System.out.println("Silahkan masukan data barang, tekan '-' untuk batal.");

        System.out.print("Nama Barang: ");
        String nama = Utils.scanLine.apply(Models.input);
        if (nama != null && nama.equals("-")) {
            return;
        }

        String idSatuan = "";
        do {
            System.out.print("ID Satuan: ");
            String id = Utils.scanLine.apply(Models.input);
            if (id != null && id.equals("-")) {
                return;
            }
            Satuan satuan = Models.listSatuan.stream().filter(s -> Objects.equals(s.getId(), id)).findFirst().orElse(null);
            isValidSatuan = satuan != null;
            if (!isValidSatuan) {
                System.out.print(Color.RED_BOLD);
                System.out.println("Satuan barang tidak ditemukan!");
                System.out.print(Color.RESET);
            } else {
                idSatuan = id;
                System.out.print(Color.GREEN_BOLD);
                System.out.println("Satuan batang ditemukan [" + satuan.getNama() + "]");
                System.out.print(Color.RESET);
            }
        } while (!isValidSatuan);

        String idJenis = "";
        do {
            System.out.print("ID Jenis: ");
            String id = Utils.scanLine.apply(Models.input);
            if (id != null && id.equals("-")) {
                return;
            }
            Jenis jenis = Models.listJenis.stream().filter(j -> Objects.equals(j.getId(), id)).findFirst().orElse(null);
            isValidJenis = jenis != null;
            if (!isValidJenis) {
                System.out.print(Color.RED_BOLD);
                System.out.println("Jenis barang tidak ditemukan!");
                System.out.print(Color.RESET);
            } else {
                idJenis = id;
                System.out.print(Color.GREEN_BOLD);
                System.out.println("Jenis barang ditemukan [" + jenis.getNama() + "]");
                System.out.print(Color.RESET);
            }
        } while (!isValidJenis);

        System.out.print("Harga: ");
        String harga = Utils.scanLine.apply(Models.input);
        if (harga != null && harga.equals("-")) {
            return;
        }
        String idBarang = Utils.generateBarangId(Models.listBarang);

        assert harga != null;
        Barang barang = new Barang(idBarang, nama, idSatuan, idJenis, Float.parseFloat(harga));
        Models.listBarang.add(barang);
        System.out.print(Color.GREEN_BOLD);
        System.out.println("Data barang berhasil ditambahkan!");
        System.out.print(Color.RESET);
    }

    //edit data barang
    public static void updateDataBarang() {
        boolean isValidJenis;
        boolean isValidSatuan;

        System.out.print("Masukan id barang yang akan diedit [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        Barang barang = Models.listBarang.stream().filter(b -> b.getKode().equals(id)).findFirst().orElse(null);
        if (barang != null) {
            System.out.println("Barang ditemukan, silahkan masukan data yang akan di edit.");
            System.out.println("Masukan '0' untuk skip atau '-' untuk batal.");

            System.out.print("Nama Barang " + "[" + barang.getNama() + "]" + ": ");
            String nama = Utils.scanLine.apply(Models.input);
            if (nama != null && !nama.equals("0")) {
                barang.setNama(nama);
            }
            if (nama != null && nama.equals("-")) {
                return;
            }

            do {
                System.out.print("ID Satuan " + "[" + barang.getIdSatuan() + "]" + ": ");
                String idSatuan = Utils.scanLine.apply(Models.input);
                if (idSatuan != null && !idSatuan.equals("0")) {
                    if (idSatuan.equals("-")) {
                        return;
                    } else {
                        Satuan satuan = Models.listSatuan.stream().filter(s -> Objects.equals(s.getId(), idSatuan)).findFirst().orElse(null);
                        isValidSatuan = satuan != null;
                        if (!isValidSatuan) {
                            System.out.print(Color.RED_BOLD);
                            System.out.println("Satuan barang tidak ditemukan!");
                            System.out.print(Color.RESET);
                        } else {
                            barang.setIdSatuan(idSatuan);
                            System.out.print(Color.GREEN_BOLD);
                            System.out.println("Satuan batang ditemukan [" + satuan.getNama() + "]");
                            System.out.print(Color.RESET);
                        }
                    }
                } else {
                    isValidSatuan = true;
                }
            } while (!isValidSatuan);

            do {
                System.out.print("ID Jenis " + "[" + barang.getIdJenis() + "]" + ": ");
                String idJenis = Utils.scanLine.apply(Models.input);
                if (idJenis != null && !idJenis.equals("0")) {
                    if (idJenis.equals("-")) {
                        return;
                    } else {
                        Jenis jenis = Models.listJenis.stream().filter(s -> Objects.equals(s.getId(), idJenis)).findFirst().orElse(null);
                        isValidJenis = jenis != null;
                        if (!isValidJenis) {
                            System.out.print(Color.RED_BOLD);
                            System.out.println("Jenis barang tidak ditemukan!");
                            System.out.print(Color.RESET);
                        } else {
                            barang.setIdJenis(idJenis);
                            System.out.print(Color.GREEN_BOLD);
                            System.out.println("Jenis barang ditemukan [" + jenis.getNama() + "]");
                            System.out.print(Color.RESET);
                        }
                    }
                } else {
                    isValidJenis = true;
                }
            } while (!isValidJenis);

            System.out.print("Harga " + "[" + String.format("Rp. %,.0f", barang.getHarga()) + "]" + ": ");
            String harga = Utils.scanLine.apply(Models.input);
            if (harga != null && !harga.equals("0")) {
                barang.setHarga(Float.parseFloat(harga));
            }
            if (harga != null && harga.equals("-")) {
                return;
            }

            System.out.print(Color.GREEN_BOLD);
            System.out.println("Data barang berhasil diedit!");
            System.out.print(Color.RESET);
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Data barang tidak ditemukan!");
            System.out.print(Color.RESET);
        }
    }

    //hapus data barang
    public static void deleteDataBarang() {
        System.out.print("Masukan id barand yang akan dihapus [-]-> Batal: ");
        String id = Utils.scanLine.apply(Models.input);
        if (id != null && id.equals("-")) {
            return;
        }
        Barang barang = Models.listBarang.stream().filter(b -> b.getKode().equals(id)).findFirst().orElse(null);
        if (barang != null) {
            barang.setDeleted(true);
            System.out.print(Color.GREEN_BOLD);
            System.out.println("Data barang berhasil dihapus!");
            System.out.print(Color.RESET);
        } else {
            System.out.print(Color.RED_BOLD);
            System.out.println("Opss, Data barang tidak ditemukan!");
            System.out.print(Color.RESET);
        }
    }
}
