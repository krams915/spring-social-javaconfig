package org.krams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/fb")
public class FacebookController {
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@ModelAttribute("source")
	public String source() {
		return "fb";
	}
	
	@RequestMapping(value="/profile")
	public String getProfile(ModelMap model) {
		try {
			Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();		
			model.addAttribute("profileLink", facebook.userOperations().getUserProfile().getLink());
			model.addAttribute("profileInfo", facebook.userOperations().getUserProfile());
			return "facebook/profile";
		}  catch (NotConnectedException e) {
			return "facebook/connect";
		}
	}
	
	@RequestMapping(value="/post", method=RequestMethod.GET)
	public String composer(ModelMap model) {
		try {
			connectionRepository.getPrimaryConnection(Facebook.class).getApi();
		} catch (NotConnectedException e) {
			return "facebook/connect";
		}
		return "post";
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String post(String message, ModelMap model) {
		try {
			Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();
			facebook.feedOperations().updateStatus(message);
			model.addAttribute("status", "success");
			model.addAttribute("message", message);
			return "posted";
		} catch (Exception e) {
			model.addAttribute("status", "failure");
			return "posted";
		}
	}
}
