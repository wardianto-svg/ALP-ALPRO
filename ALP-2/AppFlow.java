import java.util.ArrayList;
import java.util.Scanner;
public class AppFlow {
    private ArrayList<User> userList;
    private Scanner s = new Scanner(System.in);
    private Admin currentUserAdmin;
    private Petani currentUserPetani;
    private Pembeli currentUserPembeli;

    public void start(){
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
                System.out.print("Pilih: ");
                int pilihRegister = s.nextInt();
                s.nextLine();
                if (pilihRegister ==1 || pilihRegister ==2) {
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

                            System.out.print("No. Handphone: ");
                            String noHP = s.nextLine();

                            if (nama.isBlank() || lokasi.isBlank() || noHP.isBlank()) {
                                System.out.println("Nama, Lokasi atau No. Handphone Tidak Boleh Kosong");
                            } else {
                                if (pilihRegister == 1) {
                                    registerUser= new Petani(nama, pass, lokasi, noHP);
                                    System.out.println("Berhasil Register Sebagai Petani");
                                    userList.add(registerUser);
                                } else if (pilihRegister == 2) {
                                    registerUser= new Pembeli(nama, pass, lokasi, noHP);
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

                    if (u.login(nama, pass)){
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
                        } else if (u instanceof Pembeli){
                            Pembeli pembeli = (Pembeli) u;
                            if (!pembeli.getStatus()) {
                                System.out.println("Akun Anda telah diblokir.");
                                break;
                            }
                            currentUserPembeli = (Pembeli) u;   
                            menuPembeli ();
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

        } while (pilih !=3);

    }
    public void menuPembeli () {
        boolean loggedin = true;
        boolean gantiNama = true;
        do {
            System.out.println("=== MENU PEMBELI ===");
            System.out.println("1. Setting");
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
                            System.out.println("Nama Lama: "+ currentUserPetani.getNama());
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
                            String passwordlama =s.nextLine();
                            if (passwordlama.equals(currentUserPembeli.getPassword())) {
                                System.out.print("Password Baru: ");
                                String passwordBaru = s.nextLine();
                                if (passwordBaru.isBlank()) {
                                    System.out.println("Password tidak boleh kosong");
                                } else {
                                    if (passwordBaru.length() >7 && passwordBaru.length() < 13) {
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
                default:
                    System.out.println("Tidak ada dalam pilihan");
            }   
        } while (loggedin);
        
    }

    public void menuPetani () {
        boolean loggedin = true;
        do {
            System.out.println("=== MENU PETANI ===");
            System.out.println("1. Setting");
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
                            System.out.println("Nama Lama: "+ currentUserPetani.getNama());
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
                            String passwordlama =s.nextLine();
                            if (passwordlama.equals(currentUserPetani.getPassword())) {
                                System.out.print("Password Baru: ");
                                String passwordBaru = s.nextLine();
                                if (passwordBaru.isBlank()) {
                                    System.out.println("Password tidak boleh kosong");
                                } else {
                                    if (passwordBaru.length() >7 && passwordBaru.length() < 13) {
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
                        System.out.println(i +". " + user.getNama());
                        i++;
                    }
                    break;
                default:
                    System.out.println("Tidak ada dalam pilihan");
            }
        }while (pilih != 2);
        
    }
    
}
