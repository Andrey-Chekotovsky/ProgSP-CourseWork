package org.db.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUserId implements Serializable {

    @Column(name = "company_id", updatable = true)
    private int companyId;
    @Column(name = "user_id", updatable = true)
    private int userId;


}
