package org.launchcode.controllers;

import com.sun.org.apache.regexp.internal.RE;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value="results", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(@RequestParam String searchType, @RequestParam String searchTerm, Model model){
        model.addAttribute("columns", ListController.columnChoices);
        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("jobs", jobs);

        System.out.println(JobData.findByColumnAndValue(searchType, searchTerm));

        return "search";
    }

}
