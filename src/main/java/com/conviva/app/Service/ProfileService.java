package com.conviva.app.Service;

import com.conviva.app.Exceptions.InvalidInputException;
import com.conviva.app.Model.ProfileModel;
import com.conviva.app.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    // Find profile through id
    public Optional<ProfileModel> findProfileById(long id) {
        Optional<ProfileModel> profileOptional = profileRepository.findById(id);
        if (profileOptional.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID not found"
            );
        }
        return profileOptional;
    }

    // Find profile through email
    public Optional<ProfileModel> findProfileByEmail(String email) {
        Optional<ProfileModel> profileOptional = profileRepository.findOneByEmail(email);
        if (profileOptional.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Email not found"
            );
        }
        return profileOptional;
    }

    // Find all profiles
    public List<ProfileModel> listAllProfiles() {
        return profileRepository.findAll();
    }

    // Create profile
    @Transactional
    public void createProfile(ProfileModel profile) {
        String email = profile.getEmail();
        Optional<ProfileModel> profileOptional = profileRepository.findOneByEmail(email);

        if (profile.getId() != 0) { //check if profile already exists so the new one doesn't override it
            throw new InvalidInputException("Invalid input id!");
        }
        if (profileOptional.isPresent()){ //check if email is already registered
            throw new InvalidInputException("Invalid input email!");
        }

        profileRepository.save(profile);
    }

    // Login
    @Transactional
    public Optional<ProfileModel> login(String email, String password) {
        Optional<ProfileModel> profileOptional = profileRepository.findOneByEmail(email);

        if (profileOptional != null){
            if (!profileOptional.isPresent()){ //check if email is already registered
                throw new InvalidInputException("Invalid input email!");
            }
            if (!profileOptional.get().getPassword().equals(password)){ //check if the password is valid
                throw new InvalidInputException("Invalid input password!");
            }
        }

        return profileOptional;
    }

    // Edit profile
    @Transactional
    public void deleteProfileById(long id) {
        Optional<ProfileModel> profileOptional = profileRepository.findById(id);

        if (!profileOptional.isPresent()) { // check if profile exists in repository
            throw new InvalidInputException("This id does not exist. It can not be deleted");
        }
        try {
            profileRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Profile could not be deleted because it will affect Data Integrity in DB.");
        }
    }

    // Requires you to pass the whole json again, not just the category to be changed
    public void editProfile(long id, ProfileModel profile) {
        Optional<ProfileModel> profileOptional = profileRepository.findById(id);

        if (!profileOptional.isPresent()) { // check if profile exists in repository
            throw new InvalidInputException("This id does not exist. It can not be edited");
        }
        profile.setId(id);
        profileRepository.save(profile);
    }
}
