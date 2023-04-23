package com.kns.tenquest.repository;

import com.kns.tenquest.entity.Preset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PresetRepository extends JpaRepository<Preset , String> {
    Optional<Preset> findByPresetName(String presetName);
}
