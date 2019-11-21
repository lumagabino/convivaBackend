package com.conviva.app.Controller;

import com.conviva.app.Model.ProfileModel;
import com.conviva.app.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
        ProfileService profileService;

    // GET all profiles
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<ProfileModel> getAllProfiles() {
        return profileService.listAllProfiles();
    }

    // GET profile by email
    @RequestMapping(method = RequestMethod.GET, path = {"/{email}"})
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public Optional<ProfileModel> getProfileByEmail(@PathVariable("email") String email) {
        return profileService.findProfileByEmail(email);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK, reason = "Profile created")
    public void createProfile(@Valid @RequestBody ProfileModel profile) {
        profileService.createProfile(profile);
    }

    //GET - Login method
    @RequestMapping(method = RequestMethod.GET, path = {"/{email}/{password}"})
    @ResponseStatus(code = HttpStatus.OK, reason = "User logged")
    public void login(@PathVariable("email") String email, @PathVariable("password") String password) {
        profileService.login(email, password);
    }

    // PUT
    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK, reason = "Profile edited")
    public void editProfile (@PathVariable("id") long id, @Valid @RequestBody ProfileModel profile) {
        profileService.editProfile(id, profile); }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK, reason = "Profile deleted")
    public void deleteProfile(@PathVariable("id") long id) {
        profileService.deleteProfileById(id);
    }
}