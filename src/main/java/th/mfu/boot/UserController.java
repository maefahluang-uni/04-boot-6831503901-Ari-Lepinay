package th.mfu.boot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public static Map<String, User> users = new HashMap<String, User>();

   
    public ResponseEntity<String> registerUser( User user) {
      if (user == null || user.getUsername() == null || user.getUsername().isBlank()) {
        return new ResponseEntity<>("Invalid user", HttpStatus.BAD_REQUEST);
    }

    if (users.containsKey(user.getUsername())) {
        return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
    }

    users.put(user.getUsername(), user);
    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    public ResponseEntity<Collection<User>> list() {
        //TODO
        return new ResponseEntity<>(users.values(), HttpStatus.OK);
    }

    public ResponseEntity<User> getUser(String username) {
        //TODO
        User u = users.get(username);
        if (u == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    
}
