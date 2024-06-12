package com.eleven.finalweb.controller;

import com.eleven.finalweb.model.GameData;
import com.eleven.finalweb.service.GameDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gamesavedata")
public class GameDataController {
    private final GameDataService service;
    private final ObjectMapper objectMapper;

    @Autowired
    public GameDataController(GameDataService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/user/{userNumber}")
    public List<GameData> getGameSaveDataByUserNumber(@PathVariable Long userNumber) {
        return service.findByUserNumber(userNumber);
    }

    @PostMapping
    public GameData createGameSaveData(@RequestBody GameData gameSaveData) {
        return service.save(gameSaveData);
    }

    @PatchMapping("/{id}")
    public GameData updateGameSaveData(@PathVariable Long id, @RequestBody GameData gameSaveData) {
        gameSaveData.setId(id);
        return service.update(gameSaveData);
    }

    @PatchMapping("/{id}/gamedata")
    public ResponseEntity<GameData> updateGameData(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // Extract the gameData field from the updates map
        Object gameDataObj = updates.get("gameData");
        String gameDataJson = null;
        if (gameDataObj instanceof String) {
            gameDataJson = (String) gameDataObj;
        } else {
            try {
                gameDataJson = objectMapper.writeValueAsString(gameDataObj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }

        System.out.println("Received gameDataJson: " + gameDataJson);

        // Update gameData field
        GameData updatedGameData = service.updateGameData(id, gameDataJson);
        return ResponseEntity.ok(updatedGameData);
    }

    @PatchMapping("/{id}/playtime")
    public ResponseEntity<Void> updatePlayTime(@PathVariable Long id, @RequestBody Map<String, Integer> playTimeMap) {
        int playTime = playTimeMap.get("playtime");
        service.updatePlayTime(id, playTime);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/playtime")
    public int getPlayTime(@PathVariable Long id) {
        return service.getPlayTime(id);
    }
}
