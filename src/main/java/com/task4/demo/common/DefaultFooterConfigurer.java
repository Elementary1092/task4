package com.task4.demo.common;

import org.springframework.ui.Model;

public class DefaultFooterConfigurer implements FooterConfigurer {
    @Override
    public void populateFooter(Model model) {
        model.addAttribute("email", "mailto: sardorjuraboev1092gmail.com");
        model.addAttribute("github", "https://github.com/Elementary1092/task4");
    }
}
