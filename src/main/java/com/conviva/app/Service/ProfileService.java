package com.conviva.app.Service;

import com.conviva.app.Exceptions.InvalidInputException;
import com.conviva.app.Model.ProfileModel;
import com.conviva.app.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    // Find profile through id
    public ProfileModel findSectorById(long id) {
        return profileRepository.getOne(id);
    }

    // Find all profiles
    public List<ProfileModel> listAllProfiles() {
        return profileRepository.findAll();
    }

    // Create profile
    @Transactional
    public void createProfile(ProfileModel profile) {
        if (profile.getId() != 0) { // check if profile already exists so the new one doesn't override it
            throw new InvalidInputException("Invalid input id!");
        }
        profileRepository.save(profile);
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
