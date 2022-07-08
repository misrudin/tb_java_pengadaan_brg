package com.elearning.tbalgo2.models;

import com.elearning.tbalgo2.barang.Barang;
import com.elearning.tbalgo2.barangKeluar.BarangKeluar;
import com.elearning.tbalgo2.barangMasuk.BarangMasuk;
import com.elearning.tbalgo2.jenisBarang.Jenis;
import com.elearning.tbalgo2.satuanBarang.Satuan;
import com.elearning.tbalgo2.supplier.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Models {
    public static Scanner input = new Scanner(System.in);
    public static boolean isAppRunning = false;
    public static boolean isModuleBarangRunning = false;
    public static boolean isModuleSupplierRunning = false;
    public static boolean isModuleSatuanRunning = false;
    public static boolean isModuleJenisRunning = false;
    public static boolean isModuleBarangMasukRunning = false;
    public static boolean isModuleBarangKeluarRunning = false;
    public static List<Barang> listBarang = new ArrayList<>();
    public static List<Jenis> listJenis = new ArrayList<>();
    public static List<Satuan> listSatuan = new ArrayList<>();
    public static List<BarangKeluar> listBarangKeluar = new ArrayList<>();
    public static List<BarangMasuk> listBarangMasuk = new ArrayList<>();
    public static List<Supplier> listSupplier = new ArrayList<>();
}
