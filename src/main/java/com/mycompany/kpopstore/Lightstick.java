package com.mycompany.kpopstore;

public class Lightstick extends ItemKpop {
    private String versi;

    public Lightstick(String nama, String grup, int tahunRilis, int harga, String versi) {
        super(nama, grup, tahunRilis, harga);
        setVersi(versi);
        catatObjekBaru();
    }

    public String getVersi() { return this.versi; }

    public final void setVersi(String versi) {
        if (versi == null || versi.trim().isEmpty()) {
            throw new IllegalArgumentException("Versi lightstick tidak boleh kosong!");
        }
        this.versi = versi.trim();
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[Lightstick] Nama: %-18s | Grup: %-12s | Tahun: %d | Harga: Rp%d | Versi: %s%n",
                getNama(), getGrup(), getTahunRilis(), getHarga(), this.versi);
    }

    @Override
    public void caraBeli() {
        System.out.println("   -> Info Beli: Lightstick wajib pre-order & aktivasi Bluetooth di aplikasi resmi.");
    }
}