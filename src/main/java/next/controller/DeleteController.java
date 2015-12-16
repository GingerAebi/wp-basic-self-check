package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class DeleteController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionDao questionDao = QuestionDao.getInstance();
		AnswerDao answerDao = AnswerDao.getInstance();

		int questionId = ServletRequestUtils.getIntParameter(request, "questionId", -1);
		if (questionId == -1) {
			throw new RuntimeException();
		}

		Question question = questionDao.findById(questionId);
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);

		if (question.getCountOfComment() == 0) {
			questionDao.delete(questionId);
		} else if (isAllWriterSame(answers, question.getWriter())) {
			questionDao.delete(questionId);
		} else {
			//삭제가 안될 경우 처리 
		}

		ModelAndView mav = jsonView();

		return mav;

	}

	private boolean isAllWriterSame(List<Answer> answers, String writer) {
		for (Answer answer : answers) {
			if (!answer.getWriter().equals(writer)) {
				return false;
			}
		}
		return true;
	}

}
