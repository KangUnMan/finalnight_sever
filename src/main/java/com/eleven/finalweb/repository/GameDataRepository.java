package com.eleven.finalweb.repository;

import com.eleven.finalweb.model.GameData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameDataRepository extends JpaRepository<GameData, Long> {
    List<GameData> findByUser_UserNumber(Long userNumber);
}
