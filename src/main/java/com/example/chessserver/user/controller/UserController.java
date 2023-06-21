package com.example.chessserver.user.controller;

import com.example.chessserver.match.service.MatchServiceRouter;
import com.example.chessserver.user.data.NickNameDto;
import com.example.chessserver.user.data.UserDto;
import com.example.chessserver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final MatchServiceRouter matchServiceRouter;

    @Operation(summary = "닉네임 입력", description = "닉네임 입력시 해당 유저의 고유 아이디 리턴하는 api")
    @PostMapping("/chess")
    public ResponseEntity<UserDto> responseChessUserId(@RequestBody NickNameDto nickNameDto) {
        String uuid = userService.saveUser(nickNameDto.getName());
        matchServiceRouter.getMatchServiceImpl("Chess").insertWaitingList(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UserDto(uuid));
    }

    @Operation(summary = "닉네임 입력", description = "닉네임 입력시 해당 유저의 고유 아이디 리턴하는 api")
    @PostMapping("/number")
    public ResponseEntity<UserDto> responseNumberUserId(@RequestBody NickNameDto nickNameDto) {
        String uuid = userService.saveUser(nickNameDto.getName());
        matchServiceRouter.getMatchServiceImpl("Number").insertWaitingList(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UserDto(uuid));
    }
}
