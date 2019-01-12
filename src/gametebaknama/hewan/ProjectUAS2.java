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
}
