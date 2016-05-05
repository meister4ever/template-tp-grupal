package gameCreation;

import GameParser.GameParser;
import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.ruleExpressions.expressions.IExpression;
import GameParser.GameAction;

import java.util.List;

public class Game {
    private String gameName;
    public ComplexElement character;
    public List<Element> elementList;
    public IExpression rules;
    public GameParser parser;
    public IExpression victoryCondition;

    Game(){

    }

    public void setName(String gameName) {
        this.gameName = gameName;
    }

    public void reset() {
        System.out.println(gameName + " reset.");
    }

    private boolean checkVictory(){
        return victoryCondition.interpret();
    }

    public String receiveCommands(String command) {
        String sendCommand="";
        GameAction actionToExecute = parser.parseInstruction(command);
        sendCommand = actionToExecute.getMessage();
        if (actionToExecute.isASupportedAction()) {
            sendCommand = "object not found";
            for (Element anElement : elementList)  {
                for (String itemsID : actionToExecute.getItemsID()) {
                    if(anElement.getName().equals(itemsID)) {
                        sendCommand = ((ComplexElement)anElement).execute(actionToExecute.getActionID());
                    }
                }
            }
        }

        if (checkVictory())
            sendCommand = "You won the game!";
        return sendCommand;
    }


    public String getName(){return gameName;}

    public void setVictoryCondition(IExpression condition){
        victoryCondition= condition;
    }

    public void setParser(GameParser parser) {
        this.parser = parser;
    }
    public void setElements( List<Element> elementList) {
        this.elementList = elementList;
    }
}
