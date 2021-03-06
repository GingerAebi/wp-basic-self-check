package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.AddAnswerController;
import next.controller.ApiListController;
import next.controller.DeleteController;
import next.controller.ListController;
import next.controller.MoreQuestionController;
import next.controller.SaveQuestionController;
import next.controller.ShowController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		mappings.put("/list.next", new ListController());
		mappings.put("/show.next", new ShowController());
		mappings.put("/form.next", new ForwardController("form.jsp"));
		mappings.put("/save.next", new SaveQuestionController());
		mappings.put("/api/addanswer.next", new AddAnswerController());
		mappings.put("/api/list.next", new ApiListController());
		mappings.put("/seeMore.next", new MoreQuestionController());
		mappings.put("/delete.next", new DeleteController());
		
		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
