package com.mycompany.kpopstore;

import java.util.Scanner;

public class SmartKpopStore {

    public static void cariItem(String kataKunci, ItemKpop[] daftar, int jumlah) {
        System.out.println("Mencari item dengan nama/grup: " + kataKunci);
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getNama().toLowerCase().contains(kataKunci.toLowerCase())
                    || daftar[i].getGrup().toLowerCase().contains(kataKunci.toLowerCase())) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Item tidak ditemukan.");
    }

    public static void cariItem(int tahun, ItemKpop[] daftar, int jumlah) {
        System.out.println("Mencari item dengan tahun rilis: " + tahun);
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getTahunRilis() == tahun) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Item tidak ditemukan.");
    }

    public static void cariItem(String grup, int tahun, ItemKpop[] daftar, int jumlah) {
        System.out.println("Mencari item grup " + grup + " tahun " + tahun);
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getGrup().equalsIgnoreCase(grup) && daftar[i].getTahunRilis() == tahun) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Item tidak ditemukan.");
    }

    private static int bacaAngka(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ItemKpop[] daftarItem = new ItemKpop[10];
            int jumlahItem = 0;
            boolean isRunning = true;

            daftarItem[jumlahItem++] = new AlbumKpop("Get Up", "NewJeans", 2023, 250000, 6);
            daftarItem[jumlahItem++] = new AlbumKpop("MAXIDENT", "Stray Kids", 2022, 280000, 7);
            daftarItem[jumlahItem++] = new Lightstick("Carat Bong", "SEVENTEEN", 2023, 450000, "Ver.3");
            daftarItem[jumlahItem++] = new Lightstick("Hammer Bong", "BLACKPINK", 2020, 500000, "Ver.2");
            daftarItem[jumlahItem++] = new AlbumKpop("Born Pink", "BLACKPINK", 2022, 300000, 8);

            System.out.println("=========================================");
            System.out.println("     SELAMAT DATANG DI K-POP STORE!      ");
            System.out.println("=========================================");

            while (isRunning) {
                System.out.println("\nMenu Utama:");
                System.out.println("1. Tambah Item K-Pop");
                System.out.println("2. Lihat Daftar Item");
                System.out.println("3. Cari Item");
                System.out.println("4. Keluar");
                int pilihan = bacaAngka(scanner, "Pilih Menu (1-4): ");

                switch (pilihan) {
                    case 1 -> {
                        if (jumlahItem < daftarItem.length) {
                            System.out.println("\n-- Pilih Jenis Item --");
                            System.out.println("1. Album");
                            System.out.println("2. Lightstick");
                            int jenis = bacaAngka(scanner, "Pilihan (1/2): ");

                            if (jenis != 1 && jenis != 2) {
                                System.out.println("Jenis tidak valid!");
                            } else {
                                System.out.print("Masukkan Nama Item: ");
                                String nama = scanner.nextLine();
                                System.out.print("Masukkan Grup/Artis: ");
                                String grup = scanner.nextLine();
                                int tahun = bacaAngka(scanner, "Masukkan Tahun Rilis: ");
                                int harga = bacaAngka(scanner, "Masukkan Harga (Rp): ");

                                try {
                                    if (jenis == 1) {
                                        int lagu = bacaAngka(scanner, "Masukkan Jumlah Lagu: ");
                                        daftarItem[jumlahItem] = new AlbumKpop(nama, grup, tahun, harga, lagu);
                                    } else {
                                        System.out.print("Masukkan Versi Lightstick: ");
                                        String versi = scanner.nextLine();
                                        daftarItem[jumlahItem] = new Lightstick(nama, grup, tahun, harga, versi);
                                    }
                                    jumlahItem++;
                                    System.out.println("Sukses! Item berhasil ditambahkan.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Gagal: " + e.getMessage());
                                }
                            }
                        } else {
                            System.out.println("Maaf, kapasitas toko penuh!");
                        }
                    }
                    case 2 -> {
                        System.out.println("\n--- Daftar Item di K-Pop Store ---");
                        if (jumlahItem == 0) {
                            System.out.println("Belum ada item yang tersimpan.");
                        } else {
                            for (int i = 0; i < jumlahItem; i++) {
                                System.out.print((i + 1) + ". ");
                                daftarItem[i].tampilkanInfo();  
                                daftarItem[i].caraBeli();       
                                System.out.println();
                            }
                            System.out.println("* Total Item Berhasil Dibuat: "
                                    + ItemKpop.getTotalItemBerhasilDibuat());
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                    }
                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Item --");
                        System.out.println("1. Cari berdasarkan Nama/Grup (String)");
                        System.out.println("2. Cari berdasarkan Tahun (Integer)");
                        System.out.println("3. Cari berdasarkan Grup + Tahun");
                        int mode = bacaAngka(scanner, "Pilih (1-3): ");

                        if (mode == 1) {
                            System.out.print("Masukkan Nama/Grup: ");
                            cariItem(scanner.nextLine(), daftarItem, jumlahItem);
                        } else if (mode == 2) {
                            int tahun = bacaAngka(scanner, "Masukkan Tahun: ");
                            cariItem(tahun, daftarItem, jumlahItem);
                        } else if (mode == 3) {
                            System.out.print("Masukkan Grup: ");
                            String grup = scanner.nextLine();
                            int tahun = bacaAngka(scanner, "Masukkan Tahun: ");
                            cariItem(grup, tahun, daftarItem, jumlahItem);
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                    }
                    case 4 -> {
                        System.out.println("Terima kasih telah berkunjung ke K-Pop Store!");
                        isRunning = false;
                    }
                    default -> System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
                }
            }
        }
    }
}