import java.time.LocalDate;

public class Produk {

    protected String nama, ProdukList, kategori;
    protected int harga;
    protected int stok;
    protected LocalDate expiredDate;

    public Produk(String nama, String kategori, int harga, int stok, LocalDate expiredDate) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = 0;                    
        this.expiredDate = expiredDate;
    }
    public String getNama() {
        return nama;
    }
    public String getKategori() {
        return kategori;
    }
    public int getHarga() {
        return harga;
    }
    public int getStok() {
        return stok;
    }
    public LocalDate getExpiredDate() {
        return expiredDate;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }
    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiredDate);
    }
    public String getstatus(){
        if (isExpired()) {
            return "Kadaluarsa";
        } else {
            return "Masih berlaku";
        }
    }
}
