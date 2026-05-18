import java.util.ArrayList;

public class Pembeli extends User{
    private boolean status;
    private ArrayList<ItemKeranjang> keranjang;
    private String noHP;
    public Pembeli(String nama, String password, String lokasi, String noHP) {
        super(nama, password);
        this.lokasi = lokasi;
        this.noHP = noHP;
        this.status = true;
    }

    @Override
    public boolean login(String nama, String password) {
        return this.nama.equals(nama) && this.password.equals(password);
    }

    public void tambahProduk() {
        
    }

    public void displayProduk() {
        
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus() {
        return status;
    }
}
