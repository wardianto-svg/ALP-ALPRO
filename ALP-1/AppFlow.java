import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AppFlow {
    private ArrayList<User> userList;
    private Scanner s = new Scanner(System.in);
    private Admin currentUserAdmin;
    private Petani currentUserPetani;
    private Pembeli currentUserPembeli;

    public void start() {
        userList = new ArrayList<>();
        User user = new Pembeli("budi", "password123", "Bandung", "08123");
        userList.add(user);

        user = new Petani("andi", "password123", "Jakarta", "08234");
        userList.add(user);
        User admin = new Admin("Admin", "Admin123");
        userList.add(admin);

        int pilih;
        do {

            System.out.println("=== SISTEM MARKETPLACE ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            pilih = s.nextInt();
            boolean register = true;
            s.nextLine();
            User registerUser = null;
            if (pilih == 1) {
                System.out.println("Register Sebagai:");
                System.out.println("1. Petani");
                System.out.println("2. Pembeli");
                System.out.println("3. Kembali");
                System.out.print("Pilih: ");
                int pilihRegister = s.nextInt();
                s.nextLine();
                if (pilihRegister == 1 || pilihRegister == 2) {
                    System.out.print("Nama: ");
                    String nama = s.nextLine();
                    for (User user1 : userList) {
                        if (user1.getNama().equals(nama)) {
                            register = false;
                            System.out.println("Nama tidak tersedia");
                        }
                    }

                    if (register) {
                        System.out.print("Password(8-12 Karakter): ");
                        String pass = s.nextLine();
                        if (pass.length() < 8 || pass.length() > 12) {
                            System.out.println("Password Harus 8-12 Karakter");
                        } else {
                            System.out.print("Lokasi: ");
                            String lokasi = s.nextLine();
                            if (lokasi.isEmpty()) {
                                System.out.println("Lokasi Tidak Boleh Kosong");
                            } else if (lokasi.matches(".*\\d+.*")) {
                                System.out.println("Lokasi Tidak Boleh berupa angka");

                            }
                            System.out.print("No. Handphone: ");
                            String noHP = s.nextLine();

                            if (nama.isBlank() || lokasi.isBlank() || noHP.isBlank()) {
                                System.out.println("Nama, Lokasi atau No. Handphone Tidak Boleh Kosong");
                            } else {
                                if (pilihRegister == 1) {
                                    registerUser = new Petani(nama, pass, lokasi, noHP);
                                    System.out.println("Berhasil Register Sebagai Petani");
                                    userList.add(registerUser);
                                } else if (pilihRegister == 2) {
                                    registerUser = new Pembeli(nama, pass, lokasi, noHP);
                                    System.out.println("Register Berhasil Sebagai Pembeli");
                                    userList.add(registerUser);
                                }
                            }
                        }
                    }

                } else {
                    System.out.println("Tidak ada dalam pilihan");
                }

            } else if (pilih == 2) {
                boolean foundUser = false;
                System.out.print("Nama: ");
                String nama = s.nextLine();

                System.out.print("Password: ");
                String pass = s.nextLine();

                for (User u : userList) {

                    if (u.login(nama, pass)) {
                        foundUser = true;
                        if (u instanceof Petani) {
                            Petani petani = (Petani) u;
                            if (!petani.getStatus()) {
                                System.out.println("Akun Anda telah diblokir.");
                                break;
                            }
                            currentUserPetani = (Petani) u;
                            menuPetani();
                            break;
                        } else if (u instanceof Pembeli) {
                            Pembeli pembeli = (Pembeli) u;
                            if (!pembeli.getStatus()) {
                                System.out.println("Akun Anda telah diblokir.");
                                break;
                            }
                            currentUserPembeli = (Pembeli) u;
                            menuPembeli();
                            break;
                        } else {
                            currentUserAdmin = (Admin) u;
                            menuAdmin();
                            break;
                        }
                    }
                }
                if (!foundUser) {
                    System.out.println("Nama Atau Password salah");
                }
            }

        } while (pilih != 3);

    }

    public void menuPembeli() {
        boolean loggedin = true;
        boolean gantiNama = true;
        do {
            System.out.println("=== MENU PEMBELI ===");
            System.out.println("1. Setting");
            System.out.println("2. Marketplace");
            System.out.println("3. Keranjang");
            System.out.println("4. saldo");
            System.out.println("Pilihan: ");
            int pilihan = s.nextInt();
            switch (pilihan) {
                case 1:
                    System.out.println("=== SETTING ===");
                    System.out.println("1. Ganti Nama");
                    System.out.println("2. Ganti Password");
                    System.out.println("3. Log Out");
                    System.out.print("Pilihan: ");
                    int pilihanSetting = s.nextInt();
                    s.nextLine();
                    switch (pilihanSetting) {
                        case 1:
                            System.out.println("Nama Lama: " + currentUserPetani.getNama());
                            System.out.print("Nama Baru: ");
                            String namaBaru = s.nextLine();
                            if (namaBaru.isBlank()) {

                            } else {
                                for (User user : userList) {
                                    if (user.getNama().equals(namaBaru)) {
                                        gantiNama = false;
                                        System.out.println("Nama Tidak Tersedia");
                                        break;
                                    }
                                }
                                if (gantiNama) {
                                    System.out.println("Nama Berhasil di Ganti");
                                    currentUserPembeli.setNama(namaBaru);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Masukkan Password Lama: ");
                            String passwordlama = s.nextLine();
                            if (passwordlama.equals(currentUserPembeli.getPassword())) {
                                System.out.print("Password Baru: ");
                                String passwordBaru = s.nextLine();
                                if (passwordBaru.isBlank()) {
                                    System.out.println("Password tidak boleh kosong");
                                } else {
                                    if (passwordBaru.length() > 7 && passwordBaru.length() < 13) {
                                        System.out.println("Nama Berhasil di Ganti");
                                        currentUserPembeli.setPassword(passwordBaru);
                                    } else {
                                        System.out.println("Password Harus 8-12 Karakter");
                                    }
                                }
                            } else {
                                System.out.println("Password Salah");
                            }

                            break;
                        case 3:
                            loggedin = false;
                            break;
                        default:
                            System.out.println("Tidak ada dalam pilihan");
                    }
                    break;
                case 2:
                    marketplaceMenu();
                    break;
                default:
                    System.out.println("Tidak ada dalam pilihan");

            }
        } while (loggedin);

    }

    private void marketplaceMenu() {
    boolean diMarketplace = true;

    while (diMarketplace) {
        System.out.println("\n=== MARKETPLACE ===");
        System.out.println("1. Lihat Semua Katalog Produk");
        System.out.println("2. Cari Produk");
        System.out.println("3. Lihat Keranjang");
        System.out.println("4. Kembali ke Menu Utama");
        System.out.print("Pilihan: ");

        int pilihan = s.nextInt();
        s.nextLine(); // membersihkan buffer

        switch (pilihan) {
            case 1:
                tampilkanKatalogProduk();
                break;
            case 2:
                cariProduk();
                break;
            case 3:
                // lihatKeranjang();   // buat method ini nanti
                System.out.println("Fitur Keranjang belum diimplementasikan.");
                break;
            case 4:
                diMarketplace = false;
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
}

private void tampilkanKatalogProduk() {
    System.out.println("\n=== KATALOG PRODUK ===");
    
    System.out.printf("%-4s %-20s %-15s %-12s %-8s %-10s\n",
            "No.", "Nama Produk", "Petani", "Kategori", "Harga", "Stok");
    System.out.println("---------------------------------------------------------------------");

    int nomor = 1;
    boolean adaProduk = false;

    for (User user : userList) {
        if (user instanceof Petani) {
            Petani petani = (Petani) user;
            for (Produk produk : petani.getProdukList()) {
                System.out.printf("%-4d %-20s %-15s %-12s Rp%,-8d %-8d\n",
                        nomor++,
                        produk.getNama(),
                        petani.getNama(),
                        produk.getKategori(),
                        produk.getHarga(),
                        produk.getStok());
                adaProduk = true;
            }
        }
    }

    if (!adaProduk) {
        System.out.println("Belum ada produk yang dijual.");
        return;
    }
}

private void cariProduk() {
    System.out.print("\nMasukkan kata kunci (nama produk atau kategori): ");
    String keyword = s.nextLine().trim().toLowerCase();

    System.out.println("\n=== HASIL PENCARIAN ===");
    
    System.out.printf("%-4s %-20s %-15s %-12s %-8s %-10s\n",
            "No.", "Nama Produk", "Petani", "Kategori", "Harga", "Stok");
    System.out.println("---------------------------------------------------------------------");

    int nomor = 1;
    boolean ditemukan = false;

    for (User user : userList) {
        if (user instanceof Petani) {
            Petani petani = (Petani) user;
            for (Produk produk : petani.getProdukList()) {
                if (produk.getNama().toLowerCase().contains(keyword) ||
                    produk.getKategori().toLowerCase().contains(keyword) ||
                    petani.getNama().toLowerCase().contains(keyword)) {
                    
                    System.out.printf("%-4d %-20s %-15s %-12s Rp%,-8d %-8d\n",
                            nomor++,
                            produk.getNama(),
                            petani.getNama(),
                            produk.getKategori(),
                            produk.getHarga(),
                            produk.getStok());
                    ditemukan = true;
                }
            }
        }
    }

    if (!ditemukan) {
        System.out.println("Tidak ada produk yang cocok dengan kata kunci tersebut.");
        return;
    }
}
    public void menuPetani() {
        boolean loggedin = true;
        do {
            System.out.println("=== MENU PETANI ===");
            System.out.println("1. Setting");
            System.out.println("2. Kelola Produk");
            System.out.println("3. Lihat Pesanan");
            System.out.print("Pilihan: ");
            int pilihan = s.nextInt();
            switch (pilihan) {
                case 1:
                    boolean gantiNama = true;
                    System.out.println("=== SETTING ===");
                    System.out.println("1. Ganti Nama");
                    System.out.println("2. Ganti Password");
                    System.out.println("3. Log Out");
                    System.out.print("Pilihan: ");
                    int pilihanSetting = s.nextInt();
                    s.nextLine();
                    switch (pilihanSetting) {
                        case 1:
                            System.out.println("Nama Lama: " + currentUserPetani.getNama());
                            System.out.print("Nama Baru: ");
                            String namaBaru = s.nextLine();
                            if (namaBaru.isBlank()) {

                            } else {
                                for (User user : userList) {
                                    if (user.getNama().equals(namaBaru)) {
                                        gantiNama = false;
                                        System.out.println("Nama Tidak Tersedia");
                                        break;
                                    }
                                }
                                if (gantiNama) {
                                    System.out.println("Nama Berhasil di Ganti");
                                    currentUserPetani.setNama(namaBaru);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Masukkan Password Lama: ");
                            String passwordlama = s.nextLine();
                            if (passwordlama.equals(currentUserPetani.getPassword())) {
                                System.out.print("Password Baru: ");
                                String passwordBaru = s.nextLine();
                                if (passwordBaru.isBlank()) {
                                    System.out.println("Password tidak boleh kosong");
                                } else {
                                    if (passwordBaru.length() > 7 && passwordBaru.length() < 13) {
                                        System.out.println("Nama Berhasil di Ganti");
                                        currentUserPetani.setPassword(passwordBaru);
                                    } else {
                                        System.out.println("Password Harus 8-12 Karakter");
                                    }
                                }
                            } else {
                                System.out.println("Password Salah");
                            }
                            break;
                        case 3:
                            loggedin = false;
                            break;
                        default:
                            System.out.println("Tidak ada dalam pilihan");
                    }
                    break;
                case 2:
                    System.out.println("=== KELOLA PRODUK ===");
                    System.out.println("1. Tambah Produk");
                    System.out.println("2. Edit Produk");
                    System.out.println("3. Hapus Produk");
                    System.out.print("Pilihan: ");
                    int pilihanProduk = s.nextInt();
                    s.nextLine();
                    switch (pilihanProduk) {
                        case 1:
                            System.out.println("1. Sayur");
                            System.out.println("2. Buah");
                            System.out.println("3. Daging");
                            System.out.print("Pilihan: ");
                            int pilihanKategori = s.nextInt();
                            s.nextLine();
                            switch (pilihanKategori) {
                                case 1:
                                    System.out.println("=== TAMBAH PRODUK SAYUR ===");
                                    System.out.println("Nama Produk: ");
                                    String namaSayur = s.nextLine();
                                    System.out.println("Harga: " + "Rp. ");
                                    int hargaSayur = s.nextInt();
                                    s.nextLine();
                                    System.out.println("Stok: ");
                                    int stokSayur = s.nextInt();
                                    int expiredDay, expiredMonth, expiredYear;
                                    System.out.println("Tanggal Expired (DD MM YYYY): ");
                                    expiredDay = s.nextInt();
                                    expiredMonth = s.nextInt();
                                    expiredYear = s.nextInt();
                                    LocalDate expiredDate = LocalDate.of(expiredYear, expiredMonth, expiredDay);                                    
                                    s.nextLine();
                                    Produk produk = new Produk(namaSayur, "Sayur", hargaSayur, stokSayur, expiredDate);
                                    currentUserPetani.tambahProduk(produk);
                                    break;
                                case 2:
                                    System.out.println("=== TAMBAH PRODUK BUAH ===");
                                    System.out.println("Nama Produk: ");
                                    String namaBuah = s.nextLine();
                                    System.out.println("Harga: " + "Rp. ");
                                    int hargaBuah = s.nextInt();
                                    System.out.println("Stok: ");
                                    int stokBuah = s.nextInt();
                                    s.nextLine();
                                    int expiredDay1, expiredMonth1, expiredYear1;
                                    System.out.println("Tanggal Expired (DD MM YYYY): ");
                                    expiredDay1 = s.nextInt();
                                    expiredMonth1 = s.nextInt();
                                    expiredYear1 = s.nextInt();
                                    expiredDate = LocalDate.of(expiredYear1, expiredMonth1, expiredDay1);
                                    s.nextLine();
                                    produk = new Produk(namaBuah, "Buah", hargaBuah, stokBuah, expiredDate);
                                    currentUserPetani.tambahProduk(produk);
                                    break;
                                case 3:
                                    System.out.println("=== TAMBAH PRODUK DAGING ===");
                                    System.out.println("Nama Produk: ");
                                    String namaDaging = s.nextLine();
                                    System.out.println("Harga: " + "Rp. ");
                                    int hargaDaging = s.nextInt();
                                    System.out.println("Stok: ");
                                    int stokDaging = s.nextInt();
                                    s.nextLine();
                                    int expiredDay2, expiredMonth2, expiredYear2;
                                    System.out.println("Tanggal Expired (DD MM YYYY): ");
                                    expiredDay2 = s.nextInt();
                                    expiredMonth2 = s.nextInt();
                                    expiredYear2 = s.nextInt();
                                    expiredDate = LocalDate.of(expiredYear2, expiredMonth2, expiredDay2);
                                    s.nextLine();
                                    produk = new Produk(namaDaging, "Buah", hargaDaging, stokDaging, expiredDate);
                                    currentUserPetani.tambahProduk(produk);
                                    break;
                                default:
                                    System.out.println("Tidak ada dalam pilihan");
                            }
                            break;
                        case 2:
                            if (currentUserPetani.getProdukList().size() != 0) {
                                int nomor =1;
                                System.out.println("=== EDIT PRODUK ===");
                                System.out.println("Nama | Harga | Stok | Expired Date");
                                for (Produk produk : currentUserPetani.getProdukList()) {
                                    System.out.println(nomor + ". " + produk.getNama() + " - " + produk.getHarga() + " - " + produk.getStok() + " - " + produk.getExpiredDate());
                                }
                                System.out.print("Pilihan: ");
                                int pilihanpProduk = s.nextInt() - 1;
                                currentUserPetani.editProduk(currentUserPetani.getProdukList().get(pilihanpProduk));
                            } else {
                                System.out.println("Produk anda kosong");
                            }
                            
                            break;
                        case 3:
                            if (currentUserPetani.getProdukList().size() != 0) {
                                int nomor =1;
                                System.out.println("=== HAPUS PRODUK ===");
                                for (Produk produk : currentUserPetani.getProdukList()) {
                                    System.out.println(nomor + ". " + produk.getNama() + " - " + produk.getHarga() + " - " + produk.getStok() + " - " + produk.getExpiredDate());
                                }
                                System.out.print("Pilihan: ");
                                int pilihanHapus = s.nextInt() - 1;
                                currentUserPetani.hapusProduk(pilihanHapus);   
                            } else {
                                System.out.println("Produk anda kosong");
                            }

                            break;
                        default:
                            System.out.println("Tidak ada dalam pilihan");
                    }
                case 3:// Lihat Pesanan
                    break;
                default:
                    System.out.println("Tidak ada dalam pilihan");
            }
        } while (loggedin);
    }

    public void menuAdmin() {
        int pilih;
        do {
            System.out.println("=== MENU ADMIN ===");
            System.out.println("1. List Semua User");
            System.out.println("2. Log Out");
            System.out.print("Pilihan: ");
            pilih = s.nextInt();
            switch (pilih) {
                case 1:
                    int i = 1;
                    for (User user : userList) {
                        System.out.println(i + ". " + user.getNama());
                        i++;
                    }
                    break;
                default:
                    System.out.println("Tidak ada dalam pilihan");
            }
        } while (pilih != 2);

    }

}
