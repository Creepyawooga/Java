package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Flux<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .map(user -> TaskMapper.INSTANCE.userToUserDTO(user));
    }

    @GetMapping("/{id}")
    public Mono<UserDTO> getUserById(@PathVariable String id) {
        return userRepository.findById(id)
                .map(user -> TaskMapper.INSTANCE.userToUserDTO(user));
    }

    @PostMapping
    public Mono<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = TaskMapper.INSTANCE.userDTOToUser(userDTO);
        return userRepository.save(user)
                .map(savedUser -> TaskMapper.INSTANCE.userToUserDTO(savedUser));
    }

    @PutMapping("/{id}")
    public Mono<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setUsername(userDTO.getUsername());
                    existingUser.setEmail(userDTO.getEmail());
                    return userRepository.save(existingUser);
                })
                .map(updatedUser -> TaskMapper.INSTANCE.userToUserDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return userRepository.deleteById(id);
    }
}
