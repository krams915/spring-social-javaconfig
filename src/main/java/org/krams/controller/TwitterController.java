package org.krams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/tw")
public class TwitterController {
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@ModelAttribute("source")
	public String source() {
		return "tw";
	}
	
	@RequestMapping(value="/profile")
	public String getProfile(ModelMap model) {
		try {
			Twitter twitter = connectionRepository.getPrimaryConnection(Twitter.class).getApi();
			model.addAttribute("profileLink", twitter.userOperations().getUserProfile().getUrl());
			model.addAttribute("profileInfo", twitter.userOperations().getUserProfile());
			return "twitter/profile";
		} catch (NotConnectedException e) {
			return "twitter/connect";
		}
	}
	
	@RequestMapping(value="/post", method=RequestMethod.GET)
	public String composer(ModelMap model) {
		try {
			connectionRepository.getPrimaryConnection(Twitter.class).getApi();
		} catch (NotConnectedException e) {
			return "twitter/connect";
		}
		return "post";
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String post(String message, ModelMap model) {
		try {
			Twitter twitter = connectionRepository.getPrimaryConnection(Twitter.class).getApi();
			twitter.timelineOperations().updateStatus(message);
			model.addAttribute("status", "success");
			model.addAttribute("message", message);
			return "posted";
		} catch (Exception e) {
			model.addAttribute("status", "failure");
			return "posted";
		}
	}
}
