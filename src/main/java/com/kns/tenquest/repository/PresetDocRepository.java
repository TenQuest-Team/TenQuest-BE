package com.kns.tenquest.repository;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.entity.PresetDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresetDocRepository extends JpaRepository<PresetDoc , Long> {
    DtoList<PresetDoc> findAllByPresetId(Long presetId);
}
