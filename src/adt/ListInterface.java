/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Lim Yen Qi
 */
public interface ListInterface<T> {
    /**
     * Description: Inserts newEntry at the end of the list.
     * 
     * @param newEntry, the object to be added into the list
     * @return true if newEntry is successfully added to the list; if not, false.
     */
    public boolean add(T newEntry);
    
    /**
     * Description: Inserts newEntry at position pos to the list. 
     * The position of the first entry of the list is 1. 
     * 
     * @param newEntry, the object to be added to the list
     * @param pos, the desire position for the newEntry
     * @return true if newEntry is successfully added to the list; if not, false.
     */
    boolean add(T newEntry, Integer pos);
    
    /**
     * Description: Removes all the entries from the list.
     * 
     * @return true if all entries are successfully removed from the list; if not, false.
     */
    boolean removeAll();
    
    /**
     * Description: Removes the entry at position pos from the list. 
     * 
     * @param pos. the position of the entry to be removed
     * @return true if the entry at position pos is successfully removed from the list; 
     *      if not, false.
     */
    boolean remove(Integer pos);
    
    /**
     * Description: Retrieves the entry at position pos in the list.
     * 
     * @param pos, the position of the desired entry
     * @return The entry at position pos in the list.
     */
    T getEntry(Integer pos);
    
    /**
     * Description: Replaces the entry at position pos with newEntry.
     * 
     * @param pos, the position of the entry to be replaced 
     * @param newEntry, the object that will replace the entry at position pos
     * @return true if the entry at position pos is successfully replaced by newEntry from the list; 
     *      if not, false. 
     */
    boolean replace(Integer pos, T newEntry);
    
    /**
     * Description: Prints out all of the entries in the list in which order 1 represents ascending order and order 2 represents descending order.
     * 
     * @param order, the order of the entry in the list, either 1 or 2 
     * @return The string of all entries in the list in ascending order or descending order.
     */
    String display(int order);
    
    /**
     * Description: Get the total number of entries in the list.
     * 
     * @return The total number of entries in the list.
     */
    int getSize();
    
    /**
     * Description: To check if anEntry is in the list.
     * 
     * @param anEntry, the desired object
     * @return true if the list contains anEntry; if not, false.
     */
    int contains(T anEntry);
    
    /**
     * Description: To determine whether the list is empty.
     * 
     * @return true if the list is empty; if not, false.
     */
    boolean isEmpty();
}