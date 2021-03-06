package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;

public class AddAnswerController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AnswerDao answerDao = AnswerDao.getInstance();
		QuestionDao questionDao = QuestionDao.getInstance();

		int questionId = ServletRequestUtils.getIntParameter(request, "questionId");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");
		String writer = ServletRequestUtils.getStringParameter(request, "writer");

		answerDao.insert(new Answer(writer, contents, questionId));

		questionDao.update(questionId);

		ModelAndView mav = jsonView();
		
		return mav;
	}

}
