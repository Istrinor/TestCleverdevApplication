package org.cleverdevtest.test.patient.repository;

import org.cleverdevtest.test.patient.model.OldClientGuIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldClientGuIdRepository extends JpaRepository<OldClientGuIdEntity, Long> {

    @Query(value = "select ocg.* from old_client_guid ocg" +
            " left join patient_profile pe on ocg.patient_id = pe.id" +
            " inner join patient_status pse on pe.patient_status_id = pse.id" +
            " inner join second_level_status sls on sls.id = pse.second_level_status_id" +
            " where ocg.old_client_guid in :oldClientGuIds and sls.id = :secondLevelStatusId", nativeQuery = true)
    List<OldClientGuIdEntity> getClientGuidsOfActivePatientsByAllClientGuids(Integer secondLevelStatusId,
                                                                             List<String> oldClientGuIds);
}
