# 1번 , 2번 

####로컬 개발 환경에 Tomcat 서버를 시작하면 Servlet Container의 초기화 과정이 진행된다. 현재 소스 코드에서 초기화되는 과정에 대해 설명해라
<br>

1)

 웹서버런쳐가 실행되면 ServletContextListner를 구현하고있는 리스너들에게 알림이 가 실행된다.(종료될 때도 알려줌)

-> 현재 프로젝트 같은 경우에는 ContextLoaderListner가 실행되어 jwp.sql 파일을 DB에 실행시킨다. 

 그 후 서블릿이 loadOnStartup 속성이 있는 DispatcherServlet에 들어가 init과정을 실행한다. 이 때 init에서는 RequestMapping 클래스를 통해 각 컨트롤러를 알맞은 주소에 등록한다. 

이후에 유저에게 *.next 에 해당하는 url들에 요청이 들어오면 해당하는 컨트롤러를 RequestMapper에 등록되어 있는 맵에서 찾는다.

-> 이 과정에서 urlExceptParameter를 통해 ? 즉 쿼리스트링이 있는지 체크하고 있다면 앞부분만을 잘라서 controller를 검색한다. 

찾은 controller의 excute메서드를 실행하여 mav객체에 리턴값을 넣어준다.


-> excute시 ModelAndView라는 view 객체와 model에 대한 map을 가지고 있는 클래스에 각 Controller가 상속받고 있는 AbstractController에 구현되어 있는 메서드인 jstlView 혹은 jsonView를 실행시켜 ModelAndView에 View 객체를 받아온다. 그 후 mav의 addObject메서드를 실행시켜 dao에서 가져온 데이터를 object의 형태로 필요한 정보를 mav의 model맵에 등록한다.  

--> jstlView 와 jsonView 클래스는 각각 View를 상속받는 클래스이며 각각이 render메서드를 구현하고 있다.

 excute가 끝나면 가져와진 mav객체에 view를 getView하여 render메서드를 실행시키는데 이 때 mav에서 이전 과정(excute) 등록한 model과 req,resp를 넘겨주어 화면에 해당하는 jsp나 화면을 뿌려준다. 

<br><br><br>
2) 질문 목록을 보여줄 때에는 QuestionDao에서 findAllPage()를 통해 질문 목록을 가져오는데 이 때 jdbcTemplete에 있는 query 메서드를 사용한다. 