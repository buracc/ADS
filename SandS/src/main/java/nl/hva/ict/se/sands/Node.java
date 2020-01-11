package nl.hva.ict.se.sands;

/**
 * NOTE: You are NOT allowed to implement the Serializable interface!!
 */
public class Node implements Comparable<Node> {
    private Node left;
    private Node right;
    private int weight;
    private Character character;

    public Node(int weight, Character c) {
        this.weight = weight;
        this.character = c;
    }

    public Node(Node left, Node right) {
        this.weight = left.weight + right.weight;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return getWeight() - o.getWeight();
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getWeight() {
        return weight;
    }

    public Character getCharacter() {
        return character;
    }

    /**
     * Taken from https://algs4.cs.princeton.edu/55compression/Huffman.java.html
     * @return boolean value whether Node is a Leaf
     */
    public boolean isLeaf() {
        assert ((left == null) && (right == null)) || ((left != null) && (right != null));
        return (left == null) && (right == null);
    }

    @Override
    public String toString() {
        return "Node{" +
                (left != null ? "left=" + left : "") +
                (right != null ? ", right=" + right + ", " : "") +
                "weight=" + weight +
                (character != null ? ", character=" + character : "") +
                '}';
    }
}
