package org.db.JpaRepositories;

import org.db.Models.CompanyUserId;
import org.db.Models.CompanyUserRelations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyUserRelationsRepository extends JpaRepository<CompanyUserRelations, CompanyUserId> {

}
