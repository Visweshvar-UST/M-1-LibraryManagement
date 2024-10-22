package com.example.SpringBaseStarter.controller;

 
import com.example.SpringBaseStarter.entity.Issue;
import com.example.SpringBaseStarter.entity.User;
import com.example.SpringBaseStarter.repo.IssueRepo;
import com.example.SpringBaseStarter.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.Optional;
 
/**
* REST controller for managing library system process
*/
@RestController
@RequestMapping("/api/v1")
public class LibraryController {
 
    /**
     * {@code POST  /issueBook} : Create a new issue.
     *
     * @param issue the issue to create.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     * the user, or if does not exist, return with status "noContent".
     * If user is not subscribed, throw {@link UserNotSubscribedException}
     */


    @Autowired
    private UserRepo userRepo;
 
    @Autowired
    private IssueRepo issueRepo;
 
    @PostMapping("/issue-book")
    public ResponseEntity<Issue> issueBook(@RequestBody Issue issue) {
        Optional<User> userOp = userRepo.findById(issue.getUser().getId());
        if(userOp.isPresent() && userOp.get().getIsSubscribed()){
            Issue res = issueRepo.save(issue);
            return ResponseEntity.ok(res);
        } else {
            throw new UserNotSubscribedException("user subscription has expired");
        }
    }
 
    /**
     * {@code POST  /user} : Create a new user.
     *
     * @param user the user to create.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the new user
     */
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User res = userRepo.save(user);
        return ResponseEntity.ok(res);
    }
 
    /**
     * {@code GET  /renew-user-subscription/:id} :  Send userId, set user subscription to true
     *
     * @param id the id of the user to renew subscription.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     * the user, or if does not exist, return with status "noContent".
     */
    @GetMapping("renew-user-subscription/{id}")
    public ResponseEntity<User> renewUserSubscription(@PathVariable Long id) {
        Optional<User> userOp = userRepo.findById(id);
        if(userOp.isPresent()){
            User res = userOp.get();
            res.setIsSubscribed(true);
            userRepo.save(res);
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
