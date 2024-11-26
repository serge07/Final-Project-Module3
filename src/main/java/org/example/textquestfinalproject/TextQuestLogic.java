package org.example.textquestfinalproject;

import java.util.HashMap;
import java.util.Map;

/*
 * MENTOR COMMENT
 * In general, not that much code here.
 * Way more code in the controller.
 * That is a smell that the controller is doing too much, and the model, too little.
 * It should be the other way around; as the controller should be a component that simply
 * translates input from the view to the model, in general the model should contain way more lines
 * of code that the controller. Because what the controller should do is really simple in essence.
 */
public class TextQuestLogic {

  private int step = 0;
  private Map<Integer, String> questions = new HashMap<>();
  private Map<Integer, String> answers = new HashMap<>();

  public TextQuestLogic() {

    questions.put(0, "You lost your memory.Accept the UFO Challenge?");
    questions.put(1, "You have accepted the challenge.Are you going up to the captain's bridge?");
    questions.put(2, "You have gone up to the bridge. Who are you?");

    answers.put(0, "Accept");
    answers.put(1, "Yes");
    answers.put(2, "Elon Musk");
  }

  public String getQuestion(int step) {
    return questions.getOrDefault(step, "End of the quest");
  }

  public boolean checkAnswer(String answer) {
    if (answers.get(step).equalsIgnoreCase(answer)) {
      step++;
      return true;
    }
    return false;
  }

  /*
   * MENTOR COMMENT
   * Some methods here are not used.
   * That is a smell that we have either skipped that part of the business logic,
   * or, most likely, that it has been implemented somewhere else, probably in the controller.
   */
  public boolean isQuestWon() {
    return step >= questions.size();
  }

  /*
   * MENTOR COMMENT
   * We should be careful how we use this in the controller.
   * It is part of the internal state of the business logic; so it might be tempting to use it to implement part
   * of the business logic in the controller itself.
   * It is ok though to use it in the controller for example to decide what view to show next.
   * But even so, there's some coupling between the model, controller and view if we depend on this internal state for that.
   * There is a more elegant way to model this using union types and type guard functions.
   * That is advanced programming stuff that might be out of the scope of a beginners program like this,
   * but we can discuss it at the end of the module if you guys are interested.
   */
  public int getCurrentStep() {
    return step;
  }

  public void resetQuest() {
    step = 0;
  }
}
