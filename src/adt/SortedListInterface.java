/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Yap
 */
public interface SortedListInterface<T extends Comparable<T>> {
    
    public boolean add(T newEntry); // To add new entry
    
    public boolean remove(T entry); // To remove entry choose
    
    public void clearAll(); // To clear all the entry
    
    public boolean isEmpty(); // To check if the list is empty or not
    
    public T getEntry(int givenPosition); // To get the entry of that position

    public int getNumberOfEntries(); // To get total number of entries

}
