package com.harsh.lessonswithai.Core.commonlist.repository;

import com.harsh.lessonswithai.Core.commonlist.domain.Model;
import com.harsh.lessonswithai.Core.commonlist.model.ModelDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, ModelDto> {
}
