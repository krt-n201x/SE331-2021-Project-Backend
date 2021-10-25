package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.dao.UserDao;
import se331.lab.rest.entity.User;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.UserService;
import se331.lab.rest.util.LabMapper;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @GetMapping("users")
    public ResponseEntity<?> getUserLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "name", required = false) String title) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Users> pageOutput;
        if (title == null) {
            pageOutput = userService.getUsers(perPage, page);
        } else {
            pageOutput = userService.getUsers(title, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getUserDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);

    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

        Users output = userService.getUser(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}
