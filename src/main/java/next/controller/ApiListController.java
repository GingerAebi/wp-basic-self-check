package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

public class ApiListController extends AbstractController {
	private QuestionDao questionDao;

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		questionDao = QuestionDao.getInstance();
		List<Question> questions = questionDao.findAllByPage(1);

		ModelAndView mav = jsonView();
		mav.addObject("questions", questions);

		return mav;
	}

}
