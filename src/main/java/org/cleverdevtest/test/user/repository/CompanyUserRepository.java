package org.cleverdevtest.test.user.repository;

import org.cleverdevtest.test.user.model.CompanyUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CompanyUserRepository extends JpaRepository<CompanyUserEntity, Long> {

    List<CompanyUserEntity> findAllByLoginIn(Collection<String> logins);

}
