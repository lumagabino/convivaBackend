package com.conviva.app.Repository;

import com.conviva.app.Model.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileModel, Long> {

    /**
     * Method responsible for finding an user by its email.
     *
     * @param email email to be used as a filter
     * @return null or a user in according to the filter
     */
    Optional<ProfileModel> findOneByEmail(String email);

}