package com.company;

import java.util.ArrayList;

public class MyTree {

    private Node root;

    public ArrayList<com.company.ValueContainer> solution(int[] array, int s) {
        for (int i = 0; i < array.length; i++) {
            addNode(array[i], i);
        }

        ArrayList<com.company.ValueContainer> result = new ArrayList<>();

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int delta = s - array[i] - array[j];

                Node app = Search(delta);
                if (app.getValue() == delta && app.getIndex() != i && app.getIndex() != j) {
                    com.company.ValueContainer addCondidat = new com.company.ValueContainer(array[i], array[j], delta);
                    boolean flag = true;
                    for (int m = 0; m < result.size(); m++) {
                        flag = result.get(m).compare(addCondidat);
                        if (!flag)
                            break;
                    }
                    if (flag)
                        result.add(addCondidat);
                }
            }
        }

        return result;

    }

    private void addNode(int value, int index) {
        if (index == 0)
            root = new Node(value, index);
        else {
            Node parent = Search(value);
            if (value > parent.getValue()) {
                parent.setRight(new Node(value, index));
                balance(parent);
            }
            if (value < parent.getValue()) {
                parent.setLeft(new Node(value, index));
                balance(parent);
            }

        }
    }

    private Node Search(int value) {
        Node temp = root;
        while (temp.getValue() != value) {
            if (temp.getValue() > value)
                if (temp.getLeft() != null)
                    temp = temp.getLeft();
                else
                    break;
            else if (temp.getRight() != null)
                temp = temp.getRight();
            else
                break;
        }
        return temp;
    }

    private void balance(Node leaf) {
        do {
            int a = (leaf.getRight() != null) ? leaf.getRight().getHeight() : -1;
            int b = (leaf.getLeft() != null) ? leaf.getLeft().getHeight() : -1;
            if (a - b == 2) {
                if (((leaf.getRight().getRight() != null) ? leaf.getRight().getRight().getHeight() : -1) <
                        ((leaf.getRight().getLeft() != null) ? leaf.getRight().getLeft().getHeight() : -1))
                    rightRotation(leaf.getRight());
                leftRotation(leaf);
            }
            if (a - b == -2) {
                if (((leaf.getLeft().getLeft() != null) ? leaf.getLeft().getLeft().getHeight() : -1) <
                        ((leaf.getLeft().getRight() != null) ? leaf.getLeft().getRight().getHeight() : -1))
                    leftRotation(leaf.getLeft());
                rightRotation(leaf);
            }
            leaf = leaf.getParent();
        } while (leaf != null);

    }

    private void rightRotation(Node root) {
        Node left = root.getLeft(), parent = root.getParent();
        left.setParent(null);
        root.setLeft(left.getRight());
        left.setRight(root);
        if (parent == null) {
            this.root = left;
        } else {
            if (parent.getValue() > root.getValue())
                parent.setLeft(left);
            else
                parent.setRight(left);
        }
    }

    private void leftRotation(Node root) {
        Node right = root.getRight(), parent = root.getParent();
        right.setParent(null);
        root.setRight(right.getLeft());
        right.setLeft(root);
        if (parent == null) {
            this.root = right;
            right.setParent(null);
        } else {
            if (parent.getValue() > root.getValue())
                parent.setLeft(right);
            else
                parent.setRight(right);
        }
    }


    private class Node {
        private int index, height, value;
        private Node left;
        private Node right;
        private Node parent;

        int getValue() {
            return value;
        }

        int getHeight() {
            return height;
        }

        int getIndex() {
            return index;
        }

        Node getRight() {
            return right;
        }

        Node getLeft() {
            return left;
        }

        Node getParent() {
            return parent;
        }

        void setHeight(int height) {
            this.height = height;
        }

        void setLeft(Node node) {
            left = node;
            if (node != null) {
                node.setParent(this);
            }
            updateParameters();
        }

        void setRight(Node node) {
            right = node;
            if (node != null) {
                node.setParent(this);
            }
            updateParameters();
        }

        void setParent(Node node) {
            parent = node;
        }

        private void updateParameters() {
            Node parent = this;
            do {
                int a = parent.getLeft() != null ? parent.getLeft().getHeight() : -1;
                int b = parent.getRight() != null ? parent.getRight().getHeight() : -1;
                parent.setHeight(Math.max(a, b) + 1);
                parent = parent.getParent();
            } while (parent != null);
        }

        Node(int value, int index) {
            this.index = index;
            this.value = value;
            height = 0;
        }
    }
}