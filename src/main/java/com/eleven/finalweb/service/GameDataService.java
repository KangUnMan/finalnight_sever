package com.eleven.finalweb.service;

import com.eleven.finalweb.model.GameData;
import com.eleven.finalweb.repository.GameDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameDataService {
    private final GameDataRepository repository;

    @Autowired
    public GameDataService(GameDataRepository repository) {
        this.repository = repository;
    }

    public List<GameData> findByUserNumber(Long userNumber) {
        return repository.findByUser_UserNumber(userNumber);
    }

    public GameData save(GameData gameSaveData) {
        return repository.save(gameSaveData);
    }

    public GameData update(GameData gameSaveData) {
        return repository.save(gameSaveData);
    }

    public void updatePlayTime(Long id, int playTime) {
        Optional<GameData> existingGameData = repository.findById(id);
        if (existingGameData.isPresent()) {
            GameData gameData = existingGameData.get();
            gameData.setPlaytime(playTime);
            repository.save(gameData);
        } else {
            throw new ResourceNotFoundException("GameData not found with id " + id);
        }
    }

    public int getPlayTime(Long id) {
        Optional<GameData> existingGameData = repository.findById(id);
        if (existingGameData.isPresent()) {
            return existingGameData.get().getPlaytime();
        } else {
            throw new ResourceNotFoundException("GameData not found with id " + id);
        }
    }

    public GameData updateGameData(Long id, String gameDataJson) {
        Optional<GameData> optionalGameData = repository.findById(id);
        if (optionalGameData.isPresent()) {
            GameData existingGameData = optionalGameData.get();
            existingGameData.setGameData(gameDataJson); // Update gameData field
            return repository.save(existingGameData);
        } else {
            throw new ResourceNotFoundException("GameData not found with id " + id);
        }
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }


}
