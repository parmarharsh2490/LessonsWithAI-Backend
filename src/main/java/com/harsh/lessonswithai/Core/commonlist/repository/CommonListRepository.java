package com.harsh.lessonswithai.Core.commonlist.repository;

import com.harsh.lessonswithai.Core.commonlist.domain.CommonList;
import com.harsh.lessonswithai.Core.commonlist.model.CommonListDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonListRepository extends JpaRepository<CommonList, CommonListDto> {

    List<CommonList> findByType(String type);
}
