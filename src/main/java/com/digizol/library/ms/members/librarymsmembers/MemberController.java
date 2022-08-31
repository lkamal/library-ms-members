package com.digizol.library.ms.members.librarymsmembers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @GetMapping ("")
    public List<Member> list() {
        return List.of(
                new Member(1, "Rashmitha"),
                new Member(2, "Dewmi")
        );
    }
}
