package org.example;

import org.example.menu.MenuGestor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuGestor menu = new MenuGestor(sc);

        menu.mostrarMenuPrincipal();
    }
}