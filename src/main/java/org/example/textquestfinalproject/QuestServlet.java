package org.example.textquestfinalproject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {

  private static final Logger logger = LogManager.getLogger(QuestServlet.class);

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    HttpSession session = request.getSession();
    TextQuestLogic quest = (TextQuestLogic) session.getAttribute("quest");

    String action = request.getParameter("action");
    logger.info("Received action: " + action);

    /*
     * MENTOR COMMENT
     * Two levels of nested if/else code blocks. Not incorrect, but hard to read.
     * We can bring this to the 2 classes at the end of the module to explain ways to improve it.
     */
    if ("accept".equals(action)) {
      /*
       * MENTOR COMMENT
       * To understand the previous if condition we need to:
       * 1. See that we are in the doPost method.
       * 2. Find the JSP that is performing a post request to this servlet sending the action attribute with a value of "accept".
       * That might imply reading several files top to bottom, just to know when we are entering this code block.
       * That happens because view and controller here are tightly coupled.
       * We need to read in depth the internals of the view to understand the controller and make it work.
       * This is true for all high level if statements in this doPost method, as we use strings for actions.
       * Those strings don't tell us clearly about where that action might come from.
       * This is not a problem for the developer that has written the code; he remembers it because he implemented it.
       * However, it is problematic for other developers in the team; also for the author in maybe a couple of months time.
       */
      logger.info("Player accepted the challenge");
      quest = new TextQuestLogic(); // New instance for the quest logic
      session.setAttribute("quest", quest);
      // quest.checkAnswer("Accept"); // Directly trigger the first question logic
      // doGet(request, response);

      // increment attempts
      /*
       * MENTOR COMMENT
       * We are leaking some business logic here: keeping track of the number of attempts.
       * It might feel natural to do it here; but if we want to honor the MVC approach, ir should go in the TextQuestLogic.
       */
      Integer attempts = (Integer) session.getAttribute("attempts");
      if (attempts == null) {
        attempts = 1; // initial first attempt
      } else {
        attempts++;
      }
      session.setAttribute("attempts", attempts);
      /*
       * MENTOR COMMENT
       * Using Strings in parameters to convey actions is not usually a good practice.
       * Doing so couples the controller to the logic in unwanted ways.
       * This is a good point to elaborate on more the next occasion in class, at the end of the current module.
       */
      quest.checkAnswer("Accept");
      doGet(request, response); // automatically trigger the first question
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
        /*
         * MENTOR COMMENT
         * Business logic leaked here.
         * The decision that this is the final question is something that should be up to the TextQuestLogic component.
         * The decision about what view to redirect to if it is the final question though is for the controller to be made.
         * Bottom line: the if is correct; but the condition check (currentStep >= 3) is not.
         * It should be something like `if (quest.isFinalQuestion())` instead.
         */
        if (currentStep >= 3) { // check if it's the final question
          response.sendRedirect("result.jsp"); // Redirect to the result page
        } else {
          doGet(request, response); // Proceed to the next question
        }

      } else {
        logger.info("Player answered the question incorrectly");
        session.setAttribute("message", "Game over! Wrong choice");
        response.sendRedirect("incomplete.jsp"); // Redirect to the incomplete page
      }
    }
  }

  /*
   * MENTOR COMMENT
   * Using doGet here for the game flow is not recommended for several reasons:
   *
   * Idempotence: GET requests are idempotent, meaning that repeated requests should return the same result
   * and not modify the server state. That makes GET a natural choice when requesting static information
   * that doesn't change based on user input. However, it’s not ideal if the state changes as the user progresses
   * through the game.
   *
   * Data Visibility: GET requests send data via the URL (query parameters), so the user's responses would be visible
   * in the address bar. This could expose game logic or make the URL unnecessarily cluttered. It might also allow
   * users to manipulate the URL and skip ahead or change answers.
   *
   * Caching: GET requests can be cached by browsers, so depending on how you're managing session tracking,
   * this could lead to undesired behavior where the user sees stale game states due to caching.
   *
   * Bookmarking: GET allows for easy bookmarking of pages, but in a game, this may be undesirable because users
   * might bookmark mid-game and disrupt the flow.
   *
   * Bottom line; we should use doPost for all the controller logic in the game.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    HttpSession session = request.getSession();

    // Initialize quest logic if not already done
    TextQuestLogic quest = (TextQuestLogic) session.getAttribute("quest");
    if (quest == null) {
      quest = new TextQuestLogic();
      session.setAttribute("quest", quest);
    }

    // Track player details and attempts
    /*
     * MENTOR COMMENT
     * I tend to see this as something that should be done by the model, not the controller.
     * In the case of the player name, is not that big of a deal because is just immutable data,
     * not some state as the number of attempts is.
     */
    String playerName = (String) session.getAttribute("playerName");

    if (playerName == null) {
      playerName = request.getParameter("playerName");
      if (playerName == null || playerName.isEmpty()) {
        playerName = "Anonymous";
      }
      session.setAttribute("playerName", playerName);
    }

    /*
     * MENTOR COMMENT
     * Same as before, business logic leaked here.
     * Not only leaked, but now duplicated. Similar code snippet in 2 different methods.
     * When this happens, it is a good idea to outsource the logic to a separate private method in the class.
     */
    Integer attempts = (Integer) session.getAttribute("attempts");
    if (attempts == null) {
      attempts = 0;
    }

    session.setAttribute("attempts", attempts);

    /*
     * MENTOR COMMENT
     * Same as before, we probably want to keep track of this in the model
     * instead of directly in the session using controller code.
     */
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
