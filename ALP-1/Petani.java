import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Petani extends User {
    private boolean status;
    private String noHP;
    private ArrayList<Produk> daftarProduk = new ArrayList<>();

    public Petani(String nama, String password, String lokasi, String noHP) {
        super(nama, password);
        this.lokasi = lokasi;
        this.noHP = noHP;
        this.status = true;
    }
    public ArrayList<Produk> getProdukList() {
        return daftarProduk;
    }

    public void tambahProduk(Produk produk) {
        daftarProduk.add(produk);
    }
    public boolean login(String nama, String password) {
        return this.nama.equals(nama) && this.password.equals(password);
    }
    public boolean getStatus () {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void editProduk (Produk produk) {
        Scanner s = new Scanner(System.in);
        System.out.println("Nama Baru: ");
        String nama = s.nextLine();
        System.out.println("Harga: " + "Rp. ");
        int hargaDaging = s.nextInt();
        s.nextLine();
        System.out.println("Stok: ");
        int stokDaging = s.nextInt();
        s.nextLine();
        int expiredDay2, expiredMonth2, expiredYear2;
        System.out.println("Tanggal Expired (DD MM YYYY): ");
        expiredDay2 = s.nextInt();
        expiredMonth2 = s.nextInt();
        expiredYear2 = s.nextInt();
        LocalDate expiredDate = LocalDate.of(expiredYear2, expiredMonth2, expiredDay2);
        produk.setNama(nama);
        produk.setExpiredDate(expiredDate);
        produk.setStok(stokDaging);
        produk.setHarga(hargaDaging);
        
    }

    public void hapusProduk (int i) {
        daftarProduk.remove(i);
        System.out.println("Berhasil Di Hapus");
    }
    
}