package edu.pnu.pnumenuselector.domain.data.entity.listener;

import edu.pnu.pnumenuselector.domain.data.entity.BaseEntity;
import jakarta.persistence.PrePersist;


public class BaseEntityListener {

    @PrePersist
    public void prePersist(BaseEntity baseEntity){
        if (baseEntity.getCreatedBy() == null){
            baseEntity.setCreatedBy("Member registration by Sign Up");
        }
        if (baseEntity.getLastModifiedBy() == null){
            baseEntity.setLastModifiedBy("N/A");
        }
    }

}
