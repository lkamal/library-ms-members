package com.digizol.library.ms.members.librarymsmembers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    @GetMapping ("")
    public List<Member> list() {
        logger.debug("Start method list()");
        logger.info("Returning members, size = 2");
        logger.debug("End method list()");
        return List.of(
                new Member(1, "Rashmitha"),
                new Member(2, "Dewmi")
        );
    }
}
