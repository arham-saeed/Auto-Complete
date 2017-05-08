/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocomplete;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arham Saeed
 */
public class TrieNode {
    TrieNode[] children;
    Boolean complete;
    char c;
   public   TrieNode(){
        children = new TrieNode[26];
        complete = false;
    }
    public void addWord(TrieNode node, String word){
        if(word.equals("")){
            node.complete = true;
            return;
        }
        char first = word.charAt(0);
        word = word.substring(1);
        if(node.children[(int)first%32-1] == null){
            node.children[(int)first%32-1] = new TrieNode();
            node.children[(int)first%32-1].c = first;
        }
        addWord(node.children[(int)first%32-1], word);
    }
    public Boolean checkWord(TrieNode node, String word){
        char first;
        while(!word.equals("")){
            first = word.charAt(0);
            word = word.substring(1);
            if(node.children[(int)first%32-1] == null)
                return false;
            else
                node = node.children[(int)first%32-1];
        }
        if(word.equals("")){
            if(node.complete == true){
                return true;
            }else
                return false;
        }
        return true;    //just to remove error, you never reach this part.
    }
    

}
