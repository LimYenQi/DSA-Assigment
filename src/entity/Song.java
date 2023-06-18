/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Song implements Comparable<Song>{
    private String songID;
    private String songName;

    public Song(String songID, String songName) {
        this.songID = songID;
        this.songName = songName;

    }

    public Song() {
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public String toString() {
        return songID + "   " + songName + "\n";
    }

    @Override
    public int compareTo(Song s) {
        return this.songID.compareTo(s.songID);
    }  
}
