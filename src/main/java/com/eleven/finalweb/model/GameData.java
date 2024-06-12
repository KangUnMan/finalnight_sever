package com.eleven.finalweb.model;

import  jakarta.persistence.*;

@Entity
public class GameData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_number", nullable = false)
    private User user;

    private int playtime;
    private int clearCount;

    @Column(name = "game_data", columnDefinition = "LONGTEXT")
    private String gameData;  // Assuming game_data is a large object (LOB)

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public int getClearCount() {
        return clearCount;
    }

    public void setClearCount(int clearCount) {
        this.clearCount = clearCount;
    }

    public String getGameData() {
        return gameData;
    }

    public void setGameData(String gameData) {
        this.gameData = gameData;
    }
}