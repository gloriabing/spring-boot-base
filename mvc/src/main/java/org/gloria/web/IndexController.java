package org.gloria.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Create on 2016/12/8 15:19.
 *
 * @author : gloria.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index/hello";
    }
}
