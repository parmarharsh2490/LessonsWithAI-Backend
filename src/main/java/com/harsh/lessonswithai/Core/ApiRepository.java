package com.harsh.lessonswithai.Core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ApiRepository<T> extends JpaRepository<T, String> {
}
