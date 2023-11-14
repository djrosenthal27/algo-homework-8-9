package org.example;

import LinkedList.LinkedList;
import LinkedList.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable {
    LinkedList[] vals;

    HashTable(int m, String text) {
        vals = new LinkedList[m];
        for (int i = 0; i < m; i++) {
            vals[i] = new LinkedList<String, Integer>();
        }

        try {
            File textfile = new File(text);
            Scanner filereader = new Scanner(textfile);
            while (filereader.hasNext()) {
                String word = removeSpecialChars(filereader.next().toLowerCase());
                if (find(word) == null) {
                    insert(word, 1);
                } else {
                    increase(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name");
            File textfile = new File(text);
            System.out.println(textfile.exists());
            //throw new RuntimeException(e);
        }


    }

    // Removes all special characters at the beginning and end of a string, keeping
    // dashes and apostrophes for contraction words.
    private static String removeSpecialChars(String word) {
        String newWord = word;
        while (newWord.toCharArray()[0] < 97 || newWord.toCharArray()[0] > 122) {
            newWord = newWord.substring(1);
        }
        while (newWord.toCharArray()[newWord.length() - 1] < 97 || newWord.toCharArray()[newWord.length() - 1] > 122) {
            newWord = newWord.substring(0, newWord.length() - 2);
        }
        return newWord;
    }

    int hashFunction(String key) {
        return 1;
    }

    Node<String, Integer> find(String key) {
        return vals[hashFunction(key)].search(vals[hashFunction(key)], key);
    }
    void insert(String key, int val) {
        Node<String, Integer> x = find(key);
        if (x == null) {
            vals[hashFunction(key)].insert(new Node<>(key, val), vals[hashFunction(key)].nil);
        }
    }

    void delete(String key) {
        Node<String, Integer> x = find(key);
        if (x != null) {
            vals[hashFunction(key)].delete(x);
        }
    }

    void increase(String key) {
        Node<String, Integer> x = find(key);
        if (x != null) {
            x.val++;
        }
    }

    void listAllKeys() {
        for (LinkedList<String, Integer> list : vals) {
            Node<String, Integer> cur = list.nil.next;
            while (cur.key != null) {
                System.out.println(cur.key);
            }
        }
    }



}
