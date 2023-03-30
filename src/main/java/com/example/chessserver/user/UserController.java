package com.example.chessserver.user;

import com.example.chessserver.multi.service.MatchService;
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
    private final MatchService matchService;

    @Operation(summary = "닉네임 입력", description = "닉네임 입력시 해당 유저의 고유 아이디 리턴하는 api")
    @PostMapping("/users")
    public ResponseEntity<UserDto> responseUserId(@RequestBody String nickName) {
        String uuid = userService.saveUser(nickName);
        matchService.insertWaitingList(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(UserDto.builder()
                        .uuid(uuid).build());
    }
}
