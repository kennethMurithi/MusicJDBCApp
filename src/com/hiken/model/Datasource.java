package com.hiken.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "Jdbc:sqlite:E:\\Tutorial Materials\\SpringBoot\\MusicJDBCApp\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUMS_ID ="_id";
    public static final String COLUMN_ALBUMS_NAME = "name";
    public static final String COLUMN_ALBUMS_ARTIST = "artist";

    public static final String TABLE_ARTISTS ="artists";
    public static final String COLUMN_ARTISTS_ID ="_id";
    public static final String COLUMN_ARTISTS_NAME ="name";

    public static final String TABLE_SONGS ="songs";
    public static final String COLUMN_SONGS_TRACK ="track";
    public static final String COLUMN_SONGS_TITLE ="title";
    public static final String COLUMN_SONGS_ALBUM ="album";

    private Connection conn;

    public boolean open(){
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try {
            if (conn != null){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close the connection: " + e.getMessage());
        }
    }

    public List<Artist> queryArtists(){
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)){
            List<Artist> artists = new ArrayList<>();
            while (results.next()){
                Artist artist = new Artist();
                artist.setId(results.getInt(COLUMN_ARTISTS_ID));
                artist.setName(results.getString(COLUMN_ARTISTS_NAME));
                artists.add(artist);
            }

            return artists;
        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
