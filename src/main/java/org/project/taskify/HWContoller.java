package org.project.taskify;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HWContoller {

    @RequestMapping
    public String helloWorld()
    {
        return "Hello World";
    }

    @RequestMapping("/goodbye")
    public String test()
    {
        return "Goodbye from Spring Boot!";
    }

}
