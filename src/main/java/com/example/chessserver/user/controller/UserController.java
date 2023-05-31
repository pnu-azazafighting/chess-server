package com.example.chessserver.user.controller;

import com.example.chessserver.multi.service.MatchService;
import com.example.chessserver.user.data.NickNameDto;
import com.example.chessserver.user.data.UserDto;
import com.example.chessserver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final MatchService matchService;

    @Operation(summary = "닉네임 입력", description = "닉네임 입력시 해당 유저의 고유 아이디 리턴하는 api")
    @PostMapping("/users")
    public ResponseEntity<UserDto> responseUserId(@RequestBody NickNameDto nickNameDto) {
        String uuid = userService.saveUser(nickNameDto.getName());
        matchService.insertWaitingList(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UserDto(uuid));
    }
}
