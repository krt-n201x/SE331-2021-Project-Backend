package se331.lab.rest.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import se331.lab.rest.security.JwtTokenUtil;
import se331.lab.rest.security.entity.JwtUser;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.util.LabMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        Map result = new HashMap();
        result.put("token", token); //this 2.2.1
        User user = userRepository.findById(((JwtUser) userDetails).getId()).orElse(null);
        if(user.getPatient() != null) {
            result.put("user", LabMapper.INSTANCE.getPatientAuthDTO(user.getPatient()));
        }else if(user.getDoctor() != null) {
            result.put("user", LabMapper.INSTANCE.getDoctorAuthDTO(user.getDoctor()));
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setEnabled(true);
        User output = userRepository.save(user);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDto(output));


    }


}
