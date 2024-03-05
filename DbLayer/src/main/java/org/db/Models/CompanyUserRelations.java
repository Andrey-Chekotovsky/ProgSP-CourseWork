package org.db.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_company", uniqueConstraints={
        @UniqueConstraint(columnNames = {"user_id", "company_id"})
})
public class CompanyUserRelations {
    @EmbeddedId
    private CompanyUserId companyUserId;
}
