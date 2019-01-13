/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametebaknama.hewan;

/**
 *
 * @author User
 */
import java.util.*;

class BNode {

    BNode parent;
    BNode left;
    BNode right;
    int indeksHuruf;

    BNode(int new_indeks) { //constructur //parameter
        this.indeksHuruf = new_indeks; //indeksHuruf di isi new_indeks
        this.parent = null; //parent di isi null
        this.left = null;
        this.right = null;
    }

    void set_parent(BNode other) { //method //parameter
        this.parent = other;
        if (other != null) {
            if (other.indeksHuruf > this.indeksHuruf) {
                other = left;
            } else {
                other = right;
            }
        }
    }

    void set_left(BNode other) {
        left = other;
        if (other != null) {
            other = parent;
        }
    }

    void set_right(BNode other) {
        right = other;
        if (other != null) {
            other = parent;
        }
    }
}

class bBinaryTree {

    BNode root;

    bBinaryTree() { //constructur di set null
        this.root = null;
    }

    void push(BNode new_node) {
        if (root == null) {
            root = new_node;
        } else {
            BNode current = root;
            while (current != null) {
                if (new_node.indeksHuruf > current.indeksHuruf) {
                    if (current.right == null) {
                        current.set_right(new_node);
                        break;
                    } else {
                        current = current.right;
                    }
                } else {
                    if (current.left == null) {
                        current.set_left(new_node);
                        break;
                    } else {
                        current = current.left;
                    }
                }
            }
        }
    }

    boolean cari(int indeks) { //proses bst
        BNode current = root;
        while (current != null) {
            if (current.indeksHuruf == indeks) {
                return true;
            } else if (current.indeksHuruf > indeks) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }
}

public class ProjectUAS {

    static ArrayList<String> kata = new ArrayList<>();

    String[] bankKata = {"Anjing", "Banteng", "Lebah", "Belalang", "Kalajengking", "Sapi", "Zebra", "Tongkol", "Lebah", "Nyamuk", "Paus", "Kadal", "Badak", "Kuda", "Tikus", "Rusa", "Gajah", "Ular", "Macan", "Kadal", "Unta", "Semut", "Katak", "Harimau", "Buaya", "Babi"};
    static String kataAcak = ""; //diinisialisasi
    char[] arrayKata;
    char[] abjad = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    int[] indeksHuruf;

    int nyawa = 3;

    char[] kataTebakan;
    String tebakanFinal = "";
    static bBinaryTree bt = new bBinaryTree();
    int counter = 0; //menghitung banyak percobaan

    void rubahHurufkeIndeks(String kata) {
        char[] huruf = new char[kata.length()];
        indeksHuruf = new int[huruf.length];

        for (int i = 0; i < kata.length(); i++) {
            huruf[i] = kata.toUpperCase().charAt(i); //memecah huruf
            //System.out.println(huruf[i]);
        }

        for (int j = 0; j < huruf.length; j++) {
            for (int k = 0; k < abjad.length; k++) {
                if (huruf[j] == abjad[k]) {
                    indeksHuruf[j] = k + 1;
                    //System.out.println(indeksHuruf[j]);
                }
            }
        }
    }

    void pushHurufkeBinaryTree() {
        for (int i = 0; i < indeksHuruf.length; i++) {
            bt.push(new BNode(indeksHuruf[i]));
        }
    }

    void isiList() {
        for (int i = 0; i < bankKata.length; i++) {
            kata.add(bankKata[i]);
        }
    }

    void acakKata() {
        Random rd = new Random();

        int acak = rd.nextInt(kata.size()) + 1;
        kataAcak = kata.get(acak - 1);

    }

    boolean cekSlotPenuh() {
        return counter == kataAcak.length();
    }
void petunjuk() {
    System.out.println(" petunjuk permainan ");
    System.out.println(" Game tebak nama hewan ini adalah game yang berhubungan seputar nama - nama hewan yang ada di dunia.\n"
            +" dimana anda semua yang bermain anda disuruh untuk menebak huruf per huruf yang ada di dalam slot yang sudah kami sediakan\n"
            + "nuntuk cara bermainya : 1. silahkan pilih 1 untuk main\n"
            + "                        2. pilih 2 petunjuk\n"
            + "                        3. pilih 3 untuk keluar dari perimainan\n"
            + "                       jika kalian pilih main disitu sudah di siapkan slot tugas anda :\n"
            + "                       pertama hitung jumlah slot tersebut dan mulai berfikir nama hewan apa yang sama dengan jumlah slot tersebut\n"
            + "                       kedua pilihlah huruf vokal dulu agar kalian mudah untuk menebak nama hewan itu apa\n"
            + "Terima Kasih Selamat Bermain");
}
    void mulaiTebakan() {
        isiList();
        acakKata();
        //System.out.println(kata);
        //System.out.println("acak: " + kataAcak);
        rubahHurufkeIndeks(kataAcak);
        pushHurufkeBinaryTree();
        kataTebakan = new char[kataAcak.length()];
        arrayKata = new char[kataAcak.length()];
        for (int i = 0; i < arrayKata.length; i++) {
            arrayKata[i] = kataAcak.toUpperCase().charAt(i);
        }
    }

    int getInput() {
        Scanner in = new Scanner(System.in);
        int choice = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print("PILIH:");
                choice = in.nextInt();
                System.out.println("");
            } catch (Exception e) {
                System.out.println("Salah memasukkan, coba lagi.\n");
            }
        } while (validInput);
        return choice;
    }

    void mainMenu() {
        Scanner ini = new Scanner(System.in);
        tampilSlot();
        System.out.println("Nyawa tersedia: " + nyawa);
        System.out.print("Masukan huruf: ");
        String tebakan = ini.nextLine();
        char hurufTebakan = ' ';
        if (tebakan.isEmpty()) {
            System.out.println("Tebakan tidak bisa kosong.\n");
            mainMenu();
        } else if (tebakan.length() > 1) {
            System.out.println("Masukan 1 huruf saja. Coba lagi.\n");
            mainMenu();
        } else {
            hurufTebakan = tebakan.toUpperCase().charAt(0);
        }

        int indeksTebakan = 0;
        for (int k = 0; k < abjad.length; k++) {
            if (hurufTebakan == abjad[k]) {
                indeksTebakan = k + 1;
            }
        }

        if (bt.cari(indeksTebakan)) {
            for (int p = 0; p < kataTebakan.length; p++) {
                if (hurufTebakan == kataTebakan[p]) {
                    System.out.println("\nHuruf sudah ada dalam slot.");
                } else {
                    if (hurufTebakan == arrayKata[p]) {
                        System.out.println("\nYeeeyy huruf kamu tebak ada .");
                        kataTebakan[p] = hurufTebakan;
                        counter += 1;
                    }
                }
            }
            if (cekSlotPenuh()) {
                tampilSlot();
                System.out.println("yeeeeeyyy selamat ya kamu menang .\n");
                reset();
                menuUtama();
            }
        } else {
            System.out.println("\nyaahhh payah huruf yang kamu tebak tidak ada. Nyawa kamu berkurang.");
            nyawa -= 1;
            if (nyawa == 0) {
                System.out.println("payahhh sekali pengetahuanmu tentang nama hewan kamu kalah. Coba lagi.");
                System.out.println("Padahal Jawabanya: " + kataAcak + "\n");
                reset();
                menuUtama();
            }
        }
        mainMenu();
    }

    void reset() {
        counter = 0;
        nyawa = 3;
        kata.removeAll(kata);
    }

    void menuUtama() {
        System.out.println("1. Main");
        System.out.println("2. Petunjuk");
        System.out.println("3. Keluar");
        int pilihan = getInput();
        switch (pilihan) {
            case 1:
                mulaiTebakan();
                mainMenu();
                break;
            case 2:
                petunjuk();
                menuUtama();
                break;
            case 3:
                System.out.println("Terima kasih sudah bermain.");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak tersedia.");
                menuUtama();
                break;
        }
    }

    void tampilSlot() {
        try {
            System.out.print("| ");
            for (int l = 0; l < kataTebakan.length; l++) {
                System.out.print(kataTebakan[l] + " | ");
            }
            System.out.println("");
        } catch (Exception e) {
            for (int l = 0; l < kataAcak.length(); l++) {
                System.out.print("- | ");
            }
            System.out.println("");
        }

    }

    public static void main(String[] args) {
        ProjectUAS p = new ProjectUAS();
        p.menuUtama();
    }

}
