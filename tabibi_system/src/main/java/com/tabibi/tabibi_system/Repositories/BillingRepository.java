package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Billing;

public interface BillingRepository extends JpaRepository<Billing,Long> {
    
}
