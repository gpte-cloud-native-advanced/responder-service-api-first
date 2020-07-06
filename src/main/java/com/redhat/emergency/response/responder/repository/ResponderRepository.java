package com.redhat.emergency.response.responder.repository;

import java.math.BigDecimal;
import javax.enterprise.context.ApplicationScoped;

import com.redhat.emergency.response.responder.model.Responder;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ResponderRepository implements PanacheRepository<ResponderEntity> {


    public Responder fromEntity(ResponderEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Responder().id(String.valueOf(entity.getId()))
                .name(entity.getName()).phoneNumber(entity.getPhoneNumber())
                .latitude(entity.getCurrentPositionLatitude().doubleValue())
                .longitude(entity.getCurrentPositionLongitude().doubleValue())
                .boatCapacity(entity.getBoatCapacity())
                .medicalKit(entity.getMedicalKit())
                .available(entity.isAvailable());

    }

    public ResponderEntity toEntity(Responder responder) {

        if (responder == null) {
            return null;
        }

        return ResponderEntity.builder(responder.getId() == null ? 0 : Long.parseLong(responder.getId()))
                .name(responder.getName()).phoneNumber(responder.getPhoneNumber())
                .currentPositionLatitude(BigDecimal.valueOf(responder.getLatitude()))
                .currentPositionLongitude(BigDecimal.valueOf(responder.getLongitude()))
                .boatCapacity(responder.getBoatCapacity())
                .medicalKit(responder.getMedicalKit())
                .available(responder.getAvailable()).build();

    }

}
