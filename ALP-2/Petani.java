import java.util.ArrayList;

public class Petani extends User {
    private boolean status;
    private String noHP;
    private ArrayList<Produk> daftarProduk;

    public Petani(String nama, String password, String lokasi, String noHP) {
        super(nama, password);
        this.lokasi = lokasi;
        this.noHP = noHP;
        this.status = true;
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
    
}