package org.example.textquestfinalproject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@WebServlet("/quest")
public class QuestServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(QuestServlet.class);



    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession();
        TextQuestLogic quest = (TextQuestLogic) session.getAttribute("quest");

        String action = request.getParameter("action");
        logger.info("Received action: " + action);

        if ("accept".equals(action)) {
            logger.info("Player accepted the challenge");
            quest = new TextQuestLogic();  // New instance for the quest logic
            session.setAttribute("quest", quest);
            //quest.checkAnswer("Accept"); // Directly trigger the first question logic
            //doGet(request, response);

            //increment attempts
            Integer attempts = (Integer) session.getAttribute("attempts");
            if (attempts == null) {
                attempts = 1;//initial first attempt
            }else {
                attempts++;
            }
            session.setAttribute("attempts", attempts );
            quest.checkAnswer("Accept");
            doGet(request, response);//automatically trigger the first question
        } else if ("reject".equals(action)) {
            logger.info("Player rejected the challenge");
            response.sendRedirect("defeated.jsp");
        } else {
            // Process answer to the question logic
            String answer = request.getParameter("answer");
            boolean correct = quest.checkAnswer(answer);

            if (correct) {

                  logger.info("Player answered the question correctly");
                  int currentStep = quest.getCurrentStep();
                  if (currentStep >= 3) { //check if it's the final question
                     response.sendRedirect("result.jsp"); // Redirect to the result page
                 }else{
                   doGet(request, response); // Proceed to the next question
                 }


            } else {
                logger.info("Player answered the question incorrectly");
                session.setAttribute("message", "Game over! Wrong choice");
                response.sendRedirect("incomplete.jsp"); // Redirect to the incomplete page

            }
        }


    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        // Initialize quest logic if not already done
        TextQuestLogic quest = (TextQuestLogic) session.getAttribute("quest");
        if (quest == null) {
            quest = new TextQuestLogic();
            session.setAttribute("quest", quest);
        }

        // Track player details and attempts
        String playerName = (String) session.getAttribute("playerName");

        if (playerName == null) {
            playerName = request.getParameter("playerName");
            if (playerName == null || playerName.isEmpty()) {
                playerName = "Anonymous";
            }
            session.setAttribute("playerName", playerName);
        }

        Integer attempts = (Integer) session.getAttribute("attempts");
        if (attempts == null) {
            attempts = 0;
        }

        session.setAttribute("attempts", attempts);

        // Set player IP in the session
        String playerIp = request.getRemoteAddr();
        session.setAttribute("playerIp", playerIp);

        // Decide the view based on the current step of the quest
        int step = quest.getCurrentStep();
        logger.info("Current quest step: " + quest.getCurrentStep());

        if (step == 0) {
            logger.info("Redirecting to index.jsp");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            // Display the current question based on the quest step
            logger.info("Fetching question for step: " + step);
            String question = quest.getQuestion(step);
            request.setAttribute("question", question);
            request.getRequestDispatcher("/question" + step + ".jsp").forward(request, response);

        }

    }

}