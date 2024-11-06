package com.s11160663.prototype_v3.Repository;

import com.s11160663.prototype_v3.Model.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long> {
}
