package com.teknofest.turizm.controller;
import com.teknofest.turizm.model.User;
import com.teknofest.turizm.response.ApiResponse;
import com.teknofest.turizm.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<ApiResponse<User>> findByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        var response = new ApiResponse.Builder<User>()
                .success(true)
                .message("Kullanıcı emaile göre bulundu")
                .data(user)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<ApiResponse<User>> findByUserName(@PathVariable String username) {
        User user = userService.findByUserName(username);
        var response = new ApiResponse.Builder<User>()
                .success(true)
                .message("Kullanacı adına göre bulundu")
                .data(user)
                .build();
    return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ApiResponse<User>> findById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            var response = new ApiResponse.Builder<User>()
                    .success(true)
                    .message("Kullanıcı bulundu")
                    .data(user.get())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse.Builder<User>().success(false).message("Kullanıcı bulunamadı").build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        var response = new ApiResponse.Builder<User>()
                .success(true)
                .message("Kullanıcı güncellendi")
                .data(updatedUser)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        var response = new ApiResponse.Builder<Void>()
                .success(true)
                .message("Kullanıcı silindi")
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
