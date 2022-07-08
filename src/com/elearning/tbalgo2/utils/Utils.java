package com.elearning.tbalgo2.utils;

import com.elearning.tbalgo2.barang.Barang;
import com.elearning.tbalgo2.barangKeluar.BarangKeluar;
import com.elearning.tbalgo2.barangMasuk.BarangMasuk;
import com.elearning.tbalgo2.jenisBarang.Jenis;
import com.elearning.tbalgo2.satuanBarang.Satuan;
import com.elearning.tbalgo2.supplier.Supplier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Utils {
    public static String generateSupplierId(List<Supplier> listSupplier) {
        int length = listSupplier.size();
        return "SUP" + String.valueOf(length + 1);
    }

    public static String generateSatuanId(List<Satuan> listSatuan) {
        int length = listSatuan.size();
        return "SA" + String.valueOf(length + 1);
    }

    public static String generateJenisId(List<Jenis> listJenis) {
        int length = listJenis.size();
        return "JI" + String.valueOf(length + 1);
    }

    public static String generateBarangId(List<Barang> listBarang) {
        int length = listBarang.size();
        return "BRG" + String.valueOf(length + 1);
    }

    public static String generateBarangMasukId(List<BarangMasuk> listBarangMasuk) {
        int length = listBarangMasuk.size();
        return "IN" + String.valueOf(length + 1);
    }

    public static String generateBarangKeluarId(List<BarangKeluar> listBarangKeluar) {
        int length = listBarangKeluar.size();
        return "OUT" + String.valueOf(length + 1);
    }

    public static Function<Scanner, String> scanLine = (scan -> {
        String s = scan.nextLine();
        return (s.length() == 0 ? scan.nextLine() : s);
    });

    public static String getDateTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    //isValidNumber
    public static boolean isValidNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
