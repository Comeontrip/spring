package com.rock.learning.springcloud.springmvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rock.learning.springcloud.springmvc.model.LearnResource;
import com.rock.learning.springcloud.springmvc.util.LearnResourceHelper;

@Controller
public class LearnResourceController {

	@RequestMapping("/jsp/learn/index")
	public ModelAndView index() {
		List<LearnResource> learnResources = LearnResourceHelper.getLearningResources();
		ModelAndView view = new ModelAndView("index");
		view.addObject("learnList", learnResources);
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/jsp/learn/next")
	public ModelAndView next() {
		List<LearnResource> learnResources = LearnResourceHelper.getLearningResources();
		ModelAndView view = new ModelAndView("index");
		view.addObject("learnList", learnResources);
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/xml/learn/getResource", method = RequestMethod.GET, produces = "application/xml")
	public LearnResource getLearnResourceInXml() {
		return LearnResourceHelper.createLearnResource("Rock", "Rock'Blog", "http://www.google.com");
	}

	@ResponseBody
	@RequestMapping(value = "/json/learn/getResource", method = RequestMethod.GET, produces = "application/json")
	public LearnResource getLearnResourceInJSON() {
		return LearnResourceHelper.createLearnResource("Rock", "Rock'Blog", "http://www.google.com");
	}

	@ResponseBody
	@RequestMapping(value = "/both/learn/getResource", method = RequestMethod.GET, produces = {"application/json","application/xml"})
	public LearnResource getLearnResourceInJSONAndXml() {
		return LearnResourceHelper.createLearnResource("Rock", "Rock'Blog", "http://www.google.com");
	}

}
