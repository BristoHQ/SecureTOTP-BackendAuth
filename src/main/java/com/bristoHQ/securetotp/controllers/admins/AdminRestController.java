package com.bristoHQ.securetotp.controllers.admins;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bristoHQ.securetotp.dto.user.UserDTO;
import com.bristoHQ.securetotp.models.role.Role;
import com.bristoHQ.securetotp.models.role.RoleName;
import com.bristoHQ.securetotp.security.jwt.JwtUtilities;
import com.bristoHQ.securetotp.services.role.RoleService;
import com.bristoHQ.securetotp.services.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/admins")
@AllArgsConstructor
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;
    private final JwtUtilities jwtUtilities;

    // RessourceEndPoint:http://localhost:8087/api/admin/hello
    @GetMapping
    public String sayHello() {
        return "admin supported";
    }

    @GetMapping("/getAllUsers")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getAllUsers/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/getAllUsers/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/getAllUsers/username/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/getAllUsers/role/{role}")
    public List<Role> getUsersByRole(@PathVariable String role) {
        if (role.equals("ADMIN")) {
            return roleService.getRoleByRoleName(RoleName.ADMIN);
        } else if (role.equals("USER")) {
            return roleService.getRoleByRoleName(RoleName.USER);
        } else if (role.equals("SUPER_ADMIN")) {
            return roleService.getRoleByRoleName(RoleName.SUPERADMIN);
        }
        return List.of(new Role(null, RoleName.USER, null));
    }

    @PostMapping("/generatejwtToken/{emailOrUsername}")
    public String generateJwtTokenForUser(@PathVariable String emailOrUsername) {
        return jwtUtilities.generateToken(emailOrUsername, List.of("USER"));
    }
}
