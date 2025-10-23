package com.smartcity;

class AVLNode {
    String key;
    Location loc;
    AVLNode left, right;
    int height;

    AVLNode(String key, Location loc) {
        this.key = key;
        this.loc = loc;
        this.height = 1;
    }
}

public class AVLTree {
    private AVLNode root;

    private int height(AVLNode n) {
        return n == null ? 0 : n.height;
    }

    private int balanceFactor(AVLNode n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    private AVLNode insertNode(AVLNode node, String key, Location loc) {
        if (node == null) return new AVLNode(key, loc);
        if (key.compareToIgnoreCase(node.key) < 0)
            node.left = insertNode(node.left, key, loc);
        else if (key.compareToIgnoreCase(node.key) > 0)
            node.right = insertNode(node.right, key, loc);
        else
            return node; // duplicate names ignored

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balanceFactor(node);

        // Left Left
        if (balance > 1 && key.compareToIgnoreCase(node.left.key) < 0)
            return rightRotate(node);
        // Right Right
        if (balance < -1 && key.compareToIgnoreCase(node.right.key) > 0)
            return leftRotate(node);
        // Left Right
        if (balance > 1 && key.compareToIgnoreCase(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left
        if (balance < -1 && key.compareToIgnoreCase(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public void insert(String key, Location loc) {
        root = insertNode(root, key, loc);
    }

    private void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.loc);
            inorder(node.right);
        }
    }

    public void printInOrder() {
        System.out.println("Locations (in-order):");
        inorder(root);
    }
}
