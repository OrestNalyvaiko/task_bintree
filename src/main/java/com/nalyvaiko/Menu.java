package com.nalyvaiko;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

  private BinTree<Integer, String> binTree = new BinTree<>();
  private Map<String, String> menu;
  private Map<String, Command> menuMethods;

  public Menu() {
    menu = new LinkedHashMap<>();
    menuMethods = new HashMap<>();
    menu.put("1", "1 - Show sorted tree");
    menu.put("2", "2 - Put element");
    menu.put("3", "3 - Get element");
    menu.put("4", "4 - Remove element");
    menu.put("Q", "Q - exit");

    menuMethods.put("1", this::showSortedTree);
    menuMethods.put("2", this::put);
    menuMethods.put("3", this::get);
    menuMethods.put("4", this::remove);

    binTree.put(1, "Orest");
    binTree.put(-1, "Andrii");
    binTree.put(4, "Dima");
    binTree.put(0, "Danylo");
  }

  private void showMenu() {
    System.out.println("Menu:");
    for (String str : menu.values()) {
      System.out.println(str);
    }
  }

  public void start() {
    String keyMenu;
    do {
      showMenu();
      System.out.print("Select action: ");
      Scanner scanner = new Scanner(System.in);
      keyMenu = scanner.nextLine().toUpperCase();
      System.out.println();
      try {
        menuMethods.get(keyMenu).command();
      } catch (Exception e) {

      }
    } while (!keyMenu.equals("Q"));
  }

  private void put() {
    System.out.print("Enter Integer key: ");
    Scanner scanner = new Scanner(System.in);
    Integer key = scanner.nextInt();
    System.out.print("Enter String value: ");
    scanner = new Scanner(System.in);
    String value = scanner.nextLine();
    binTree.put(key, value);
    System.out.println();
  }

  private void showSortedTree() {
    binTree.showMap();
    System.out.println();
  }

  private void get() {
    System.out.print("Enter Integer key: ");
    Scanner scanner = new Scanner(System.in);
    Integer key = scanner.nextInt();
    String value = binTree.get(key);
    if (value == null) {
      System.out.println("No such key exist");
    } else {
      System.out.println("Value of node " + value);
    }
    System.out.println();
  }

  private void remove() {
    System.out.print("Enter Integer key: ");
    Scanner scanner = new Scanner(System.in);
    Integer key = scanner.nextInt();
    boolean successful = binTree.remove(key);
    if (!successful) {
      System.out.println("No such key exist");
    } else {
      System.out.println("Node successfully deleted");
    }
    System.out.println();
  }
}
