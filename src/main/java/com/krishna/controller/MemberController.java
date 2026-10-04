package com.krishna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krishna.entity.Member;
import com.krishna.service.MemberService;

import jakarta.validation.Valid;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberservice;

    @GetMapping("/member/add")
    public String showAddMemberPage(Model model) {

        model.addAttribute("member", new Member());

        return "add-member";
    }
    
    @GetMapping("/member/edit")
    public String editMember(@RequestParam int id, Model model) {
    	Member member=memberservice.getMemberById(id);
    	
    	if(member==null) {
    		return "redirect:/member/list";
    	}
    	model.addAttribute("member",member);
    	
    	return "edit-member";
    	
    }
    
    @GetMapping("/member/delete")
    public String deleteMember(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
    	
    	memberservice.deleteMember(id);
    	
    	redirectAttributes.addFlashAttribute("successMessage", "Member Deleted Successfully1");
    	
    	return "redirect:/member/list";
    }

    @PostMapping("/member/save")
    public String saveMember(
            @Valid @ModelAttribute("member") Member member,
            BindingResult result, RedirectAttributes redirectAttributes) {

        
        if (result.hasErrors()) {
        	
            return "add-member";
        }


        memberservice.saveMember(member);

        redirectAttributes.addFlashAttribute("successMessage","Member Added Successfully!");
        
        return "redirect:/member/list";
    }

    @GetMapping("/member/list")
    public String showAllMembers( Model model) {
    
        List<Member> members = memberservice.getAllMembers();

        model.addAttribute("members", members);

        return "member-list";
    }

    @GetMapping("/member/search")
    public String searchMember(@RequestParam(value="id", required=false) Integer id, Model model) {

    	
    	if(id==null) {
    		model.addAttribute("searched",false);
    		model.addAttribute("member",null);
    		
    		return "member-search";
    	}

        Member member = memberservice.getMemberById(id);

        model.addAttribute("member", member);
        model.addAttribute("searched", true);

        return "member-search";
    }
    
   }