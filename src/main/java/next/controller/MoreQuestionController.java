package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.QuestionDao;
import next.model.Question;

public class MoreQuestionController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionDao questionDao = QuestionDao.getInstance();
		List<Question> questions = questionDao
				.findAllByPage(ServletRequestUtils.getRequiredIntParameter(request, "pageNumber"));

		ModelAndView mav = jsonView();
		mav.addObject("questions", questions);

		return mav;
	}
}
