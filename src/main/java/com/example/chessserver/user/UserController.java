package com.example.chessserver.user;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Operation(summary = "닉네임 입력", description = "닉네임 입력시 해당 유저의 고유 아이디 리턴하는 api")
    @PostMapping("/users")
    public ResponseEntity<UserDto> responseUserId(@RequestBody String nickName) {
        String id = userService.generateUserId();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(UserDto.builder()
                    .userId(id).build());
    }
}
