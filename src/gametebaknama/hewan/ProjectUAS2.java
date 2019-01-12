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

class Node {

    Node parent;
    Node left;
    Node right;
    int indeksHuruf;

    Node(int new_indeks) { //constructur //parameter
        this.indeksHuruf = new_indeks; //indeksHuruf di isi new_indeks
        this.parent = null; //parent di isi null
        this.left = null;
        this.right = null;
    }

    void set_parent(Node other) { //method //parameter
        this.parent = other;
        if (other != null) {
            if (other.indeksHuruf > this.indeksHuruf) {
                other = left;
            } else {
                other = right;
            }
        }
    }

    void set_left(Node other) {
        left = other;
        if (other != null) {
            other = parent;
        }
    }

    void set_right(Node other) {
        right = other;
        if (other != null) {
            other = parent;
        }
    }
}

class BinaryTree {

    Node root;

    BinaryTree() { //constructur di set null
        this.root = null;
    }

    void push(Node new_node) {
        if (root == null) {
            root = new_node;
        } else {
            Node current = root;
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
        Node current = root;
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
                        System.out.println("\nHuruf ada.");
                        kataTebakan[p] = hurufTebakan;
                        counter += 1;
                    }
                }
            }
            if (cekSlotPenuh()) {
                tampilSlot();
                System.out.println("Kamu menang.\n");
                reset();
                menuUtama();
            }
        } else {
            System.out.println("\nHuruf tidak ada. Nyawa dikurangi.");
            nyawa -= 1;
            if (nyawa == 0) {
                System.out.println("Kamu kalah. Coba lagi.");
                System.out.println("Jawaban: " + kataAcak + "\n");
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
        ProjectUAS2 p = new ProjectUAS2();
        p.menuUtama();
    }


}
