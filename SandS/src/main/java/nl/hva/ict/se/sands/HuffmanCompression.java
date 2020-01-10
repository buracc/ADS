package nl.hva.ict.se.sands;

import java.io.InputStream;
import java.util.*;

public class HuffmanCompression {

    public static void main(String[] args) {
        HuffmanCompression huffmanCompression = new HuffmanCompression("cacbcac");
//        System.out.println(huffmanCompression.compress());
        System.out.println(huffmanCompression.getCompressionTree());
//        System.out.println(huffmanCompression.getCodes());
    }

    private static final int ASCII_TABLE_SIZE = 128;

    private final String text;
    private Node huffmanTree;

    public HuffmanCompression(String text) {
        this.text = text;
        huffmanTree = buildTree();
    }

    public HuffmanCompression(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        text = sc.next();
        huffmanTree = buildTree();
    }

    /**
     * Returns the compression ratio assuming that every characters in the text uses 8 bits.
     *
     * @return the compression ratio.
     */
    public double getCompressionRatio() {
        double bits = text.length() * 8;
        double compressedBits = compress().length();

        return compressedBits / bits;
    }

    /**
     * Compresses the text that was provided to the constructor.
     *
     * @return
     */
    public String compress() {
        Map<Character, String> codes = getCodes();
        StringBuilder compressed = new StringBuilder();

        // Write Huffman compressed text
        for (char c : text.toCharArray()) {
            String code = codes.get(c);
            compressed.append(code);
        }

        return compressed.toString();
    }

    /**
     * Returns the root of the compression tree.
     *
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return huffmanTree;
    }

    private Node buildTree() {
        char[] text = this.text.toCharArray();
        int[] freq = new int[ASCII_TABLE_SIZE];
        Set<Character> uniqueChars = new HashSet<>();

        for (char c : text) {
            freq[c]++;
            uniqueChars.add(c);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (char c : uniqueChars) {
            Node node = new Node(freq[c], c);
            pq.add(node);
        }

        // special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) {
            if (freq['\0'] == 0) pq.add(new Node(0, '\0'));
            else pq.add(new Node(0, '\1'));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            System.out.println(node.getWeight());
//            Node left = pq.delMin();
//            Node right = pq.delMin();
//            Node parent = new Node(left, right);
//            pq.insert(parent);
        }

//        huffmanTree = pq.delMin();

//        return huffmanTree;
        System.out.println();
        return null;
    }

    /**
     * Returns a Map<Character, String> with the character and the code that is used to encode it.
     * For "aba" this would result in: ['b' -> "0", 'a' -> "1"]
     * And for "cacbcac" this would result in: ['b' -> "00", 'a' -> "01", 'c' -> "1"]
     *
     * @return the Huffman codes
     */
    Map<Character, String> getCodes() {
        Map<Character, String> codes = new HashMap<>();
        buildCode(codes, huffmanTree, "");
        return codes;
    }

    private void buildCode(Map<Character, String> codes, Node node, String s) {
        if (!node.isLeaf()) {
            buildCode(codes, node.getLeft(), s + '0');
            buildCode(codes, node.getRight(), s + '1');
            return;
        }

        codes.put(node.getCharacter(), s);
    }
}
