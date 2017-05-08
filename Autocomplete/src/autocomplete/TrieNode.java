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
    public List findAll(TrieNode node, String prefix){
        List allWords = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append(prefix.substring(0, prefix.length()-1));
        char first;
        while(!prefix.equals("")){
            first = prefix.charAt(0);
            if(node.children[(int)first%32-1] == null)
                return null;
            else
                node = node.children[(int)first%32-1];
            
            prefix = prefix.substring(1);
        }
        dfs(node, sb, allWords);
        return allWords;
    }
    void dfs(TrieNode node, StringBuilder sb, List allWords){
        sb.append(node.c);
    
        if (node.complete == true)
            allWords.add(sb.toString());
        for (TrieNode child : node.children){
            if(child != null)
             dfs(child, sb, allWords);
        }
      //  String temp = sb.substring(0, sb.length()-1);
        sb.setLength(sb.length()-1);
        //sb.length() = sb.length()-1;
    }
}
