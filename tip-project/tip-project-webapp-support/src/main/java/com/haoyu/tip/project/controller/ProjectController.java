package com.haoyu.tip.project.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.tip.project.entity.Project;
import com.haoyu.tip.project.service.IProjectService;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;

@Controller
@RequestMapping("project")
public class ProjectController extends AbstractBaseController{
	
	@Resource
	private IProjectService projectService;
	
	@RequestMapping(value="create",method = RequestMethod.GET)
	public String create(){
		return "project/edit_project";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response save(Project project){
		return projectService.createProject(project);
	}
	
	@RequestMapping(value="{id}/edit",method = RequestMethod.GET)
	public String edit(Project project,Model model){
		model.addAttribute("project", projectService.findProjectById(project.getId()));
		return "project/edit_project";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Response update(Project project){
		return projectService.updateProject(project);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model,Project project){
		model.addAttribute("project", project);
		model.addAttribute("pageBounds", getPageBounds(10, true));
		return "project/list_project";
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Project project){
		return projectService.deleteProject(project);
	}
	
	@RequestMapping(value="test")
	public String test(Model model,Project project){
		model.addAttribute("project", project);
		model.addAttribute("pageBounds", getPageBounds(10, true));
		return "list_project_test";
	}
	
	@RequestMapping(value="a")
	public String a(){
		return "a";
	}
	
	public static void main(String[] args) {
		TemplateLoader templateLoader = new ClassTemplateLoader( ProjectController.class, "/templates" ); 
	}
	

}
