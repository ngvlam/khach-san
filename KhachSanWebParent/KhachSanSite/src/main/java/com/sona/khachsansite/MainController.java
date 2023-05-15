package com.sona.khachsansite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = {"/", "/home"})
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/blog")
    public String viewBlog() {
        return "blog";
    }

    @GetMapping("/about-us")
    public String viewAboutUs() {
        return "about-us";
    }

    @GetMapping("/contact")
    public String viewContact() {
        return "contact";
    }

    @GetMapping("/blog-detail")
    public String viewBlogDetail() {
        return "blog-details";
    }

}
