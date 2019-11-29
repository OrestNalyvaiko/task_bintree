package com.nalyvaiko;

import java.util.Map;

public class BinTree<K extends Comparable, V> {

  private int size;
  private Node<K, V> root;

  public int size() {
    return size;
  }

  public V get(K key) {
    Node<K, V> current = root;
    while (current.key != key) {
      int cmp = key.compareTo(current.key);
      if (cmp < 0) {
        current = current.left;
      } else {
        current = current.right;
      }
      if (current == null) {
        return null;
      }
    }
    return current.value;
  }

  public V put(K key, V value) {
    Node<K, V> current = root;
    if (current == null) {
      root = new Node<>(key, value);
      size++;
      return null;
    } else {
      Node<K, V> parent;
      int cmp;
      do {
        parent = current;
        cmp = key.compareTo(current.key);
        if (cmp < 0) {
          current = current.left;
          if (current == null) {
            parent.left = new Node<>(key, value);
          }
        } else if (cmp > 0) {
          current = current.right;
          if (current == null) {
            parent.right = new Node<>(key, value);
          }
        } else {
          return current.setValue(value);
        }
      } while (current != null);
    }
    size++;
    return null;
  }

  public boolean remove(K key) {
    Node<K, V> current = root;
    Node<K, V> parent = root;
    boolean isLeftChild = true;
    while (current.key != key) {
      parent = current;
      int cmp = key.compareTo(current.key);
      if (cmp < 0) {
        isLeftChild = true;
        current = current.left;
      } else {
        isLeftChild = false;
        current = current.right;
      }
      if (current == null) {
        return false;
      }
    }

    if (current.left == null && current.right == null) {
      removeWithNoChildren(current, parent, isLeftChild);
    } else if (current.right == null) {
      removeWithLeftChildren(current, parent, isLeftChild);
    } else if (current.left == null) {
      removeWithRightChildren(current, parent, isLeftChild);
    } else {
      removeWithTwoChildren(current, parent, isLeftChild);
    }
    size--;
    return true;
  }

  private void removeWithNoChildren(Node<K, V> current, Node<K, V> parent,
      boolean isLeftChild) {
    if (current == root) {
      root = null;
    } else if (isLeftChild) {
      parent.left = null;
    } else {
      parent.right = null;
    }
  }

  private void removeWithLeftChildren(Node<K, V> current, Node<K, V> parent,
      boolean isLeftChild) {
    if (current == root) {
      root = current.left;
    } else if (isLeftChild) {
      parent.left = current.left;
    } else {
      parent.right = current.left;
    }
  }

  private void removeWithRightChildren(Node<K, V> current, Node<K, V> parent,
      boolean isLeftChild) {
    if (current == root) {
      root = current.right;
    } else if (isLeftChild) {
      parent.left = current.right;
    } else {
      parent.right = current.right;
    }
  }

  private void removeWithTwoChildren(Node<K, V> current, Node<K, V> parent,
      boolean isLeftChild) {
    Node<K, V> successor = getSuccessor(current);
    if (current == root) {
      root = successor;
      successor.left = current.left;
    } else if (isLeftChild) {
      parent.left = successor;
    } else {
      parent.right = successor;
    }
  }

  private Node<K, V> getSuccessor(Node<K, V> delNode) {
    Node<K, V> successorParent = delNode;
    Node<K, V> successor = delNode;
    Node<K, V> current = delNode.right;
    while (current != null) {
      successorParent = successor;
      successor = current;
      current = current.left;
    }
    if (successor != delNode.right) {
      successorParent.left = successor.right;
      successor.right = delNode.right;
    }
    return successor;
  }

  static final class Node<K, V> implements Map.Entry<K, V> {

    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;

    Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public V setValue(V value) {
      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }
  }

  public void showMap() {
    inOrder(root);
  }

  private void inOrder(Node localRoot) {
    if (localRoot != null) {
      inOrder(localRoot.left);
      System.out
          .println("Key: " + localRoot.key + " Value: " + localRoot.value);
      inOrder(localRoot.right);
    }
  }
}
