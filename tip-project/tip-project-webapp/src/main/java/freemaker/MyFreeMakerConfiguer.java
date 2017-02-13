package freemaker;

import java.util.List;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.haoyu.tip.project.controller.ProjectController;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;

public class MyFreeMakerConfiguer extends FreeMarkerConfigurer{
	
	@Override
	protected void postProcessTemplateLoaders(List<TemplateLoader> templateLoaders) {
		templateLoaders.add(new ClassTemplateLoader(ProjectController.class, "/templates"));
		logger.info("ClassTemplateLoader for Spring macros added to FreeMarker configuration");
	}
}
