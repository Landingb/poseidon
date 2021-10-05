package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class BidListController {
    // TODO: Inject Bid service

    private static Logger log = LoggerFactory.getLogger(BidListController.class);

    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        log.info("GET Request to /bid/list");

        model.addAttribute("bids", bidListService.findAll());

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        log.info("GET Request to :bid/add");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        log.info("Post Request to /bid/validate");

        if(result.hasErrors()){
            return "bidList/add";
        }
        else {
            bidListService.save(bid);
        }

        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        log.info("GET Request to /bid/update/" +id);

        BidList bid = bidListService.findById(id);
        if(bid != null){
            model.addAttribute("bid", bid);
        }

        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        log.info("POST Request to /bid/update/" +id);
        if( !result.hasErrors()){
            bidListService.save(bidList);
        }
        else {
            return "bid/update";
        }

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        log.info("Get Request to /bid/delete/" +id);

        bidListService.delete(id);
        return "redirect:/bidList/list";
    }
}
