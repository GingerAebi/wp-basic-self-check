package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.QuestionDao;
import next.model.Question;

public class SaveQuestionController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionDao questionDao = QuestionDao.getInstance();
		
		questionDao.insert(new Question(
				ServletRequestUtils.getStringParameter(request, "writer"),
				ServletRequestUtils.getStringParameter(request, "title"),
				ServletRequestUtils.getStringParameter(request, "contents")));

		return jstlView("redirect:list.next");
	}

}
