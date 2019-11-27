package com.conviva.app.Repository;

import com.conviva.app.Model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<EventModel, Long> {

    @Query(value = "SELECT * FROM findEventsInRegion(:profileLongitude , :profileLatitude, :profileRadius) ORDER BY date;",
            nativeQuery = true)
    public List<EventModel> findEventByRegion(@Param("profileLongitude") Double longitude,
                                              @Param("profileLatitude") Double latitude,
                                              @Param("profileRadius") Double radius);

    @Query(value = "SELECT * FROM event_model WHERE adm = :adm",
            nativeQuery = true)
    public List<EventModel> findEventsWithAdm(@Param("adm") String adm);

}


// Tentei colocar o  código completo da função na @Query, mas não deu certo
// Logo, o banco de dados precisa ter a função abaixo gravada
// findEventsInRegion(profileLongitude double precision, profileLatitude double precision, profileRadius double precision)
//
//    String queryFunction = "create extension if not exists cube; " +
//            "create extension if not exists earthdistance; " +
//            "\n" +
//            "CREATE OR REPLACE FUNCTION findEventsInRegion(profileLongitude double precision, " +
//            "profileLatitude double precision, " +
//            "profileRadius double precision) " +
//            "\n" +
//            "RETURNS TABLE (ID bigint, \n" +
//            "\t\t\t  ADDRESS text, \n" +
//            "\t\t\t  ADM text, \n" +
//            "\t\t\t  COMPLAINT integer, \n" +
//            "\t\t\t  COST integer, \n" +
//            "\t\t\t  DATE timestamp without time zone, \n" +
//            "\t\t\t  DESCRIPTION text, \n" +
//            "\t\t\t  JUSTIFICATION text, \n" +
//            "\t\t\t  LATITUDE double precision, \n" +
//            "\t\t\t  LONGITUDE double precision, \n" +
//            "\t\t\t  NAME text) \n" +
//            "AS \n" +
//            "\t $$ \n" +
//            "\t SELECT ID, ADDRESS, ADM, COMPLAINT, COST, DATE, \n" +
//            "\t\t\t\t DESCRIPTION, JUSTIFICATION, LATITUDE, LONGITUDE, NAME \n" +
//            "\t FROM event_model \n" +
//            "\t WHERE ((point(LONGITUDE, LATITUDE) <@> point($1, $2)) * 1.61) < $3 \n" +
//            "\t $$ \n" +
//            "LANGUAGE SQL; ";