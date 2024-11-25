package org.example.textquestfinalproject;

import java.util.HashMap;
import java.util.Map;

public class TextQuestLogic {

    private int step=0;
    private Map<Integer,String> questions=new HashMap<>();
    private Map<Integer,String> answers = new HashMap<>();


    public TextQuestLogic(){

        questions.put(0,"You lost your memory.Accept the UFO Challenge?");
        questions.put(1,"You have accepted the challenge.Are you going up to the captain's bridge?");
        questions.put(2,"You have gone up to the bridge. Who are you?");


        answers.put(0,"Accept");
        answers.put(1,"Yes");
        answers.put(2,"Elon Musk");
    }

    public String getQuestion(int step){
        return questions.getOrDefault(step,"End of the quest");
    }

    public boolean checkAnswer(String answer){
        if(answers.get(step).equalsIgnoreCase(answer)){
             step++;
             return true;
        }
        return false;
    }

    public  boolean isQuestWon(){
        return step>=questions.size();
    }


    public int getCurrentStep(){
        return step;
    }

    public void resetQuest(){
        step=0;
    }



}
