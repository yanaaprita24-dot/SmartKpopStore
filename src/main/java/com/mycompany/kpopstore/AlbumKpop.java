package com.mycompany.kpopstore;

public class AlbumKpop extends ItemKpop {
    private int jumlahLagu;

    public AlbumKpop(String nama, String grup, int tahunRilis, int harga, int jumlahLagu) {
        super(nama, grup, tahunRilis, harga);
        setJumlahLagu(jumlahLagu);
        catatObjekBaru();
    }

    public int getJumlahLagu() { return this.jumlahLagu; }

    public final void setJumlahLagu(int jumlahLagu) {
        if (jumlahLagu <= 0) {
            throw new IllegalArgumentException("Jumlah lagu harus lebih dari 0!");
        }
        this.jumlahLagu = jumlahLagu;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[Album]      Nama: %-18s | Grup: %-12s | Tahun: %d | Harga: Rp%d | Lagu: %d%n",
                getNama(), getGrup(), getTahunRilis(), getHarga(), this.jumlahLagu);
    }

    @Override
    public void caraBeli() {
        System.out.println("   -> Info Beli: Album dikirim via ekspedisi, sudah termasuk photocard acak.");
    }
}