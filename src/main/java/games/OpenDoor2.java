package games;

import creation.GameBuilder;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Element;
import model.ruleExpressions.rules.HasContainerRule;
import model.ruleExpressions.rules.HasStateRule;
import model.ruleExpressions.expressions.IExpression;
import model.ruleExpressions.expressions.RuleExpression;

public final class OpenDoor2 extends GameBuilder {

    public static final String gameDescription = "There is a door on this game. But no key around.";

    public OpenDoor2() {
        gameName = "OpenDoor2";
    }

    @SuppressWarnings("CPD-START")
    public void setElements() {

        //Create states
        Element closedBoxState = new Element("Closed");
        Element closedDoorState = new Element("Closed");
        Element openDoorState = new Element("Open");
        Element openBoxState = new Element("Open");

        //Create and add elements
        ComplexElement room1 = createAndAddElement("room1", null, null);
        ComplexElement room2 = createAndAddElement("room1", null, null);
        ComplexElement door = createAndAddElement("door", room1, closedDoorState);
        ComplexElement box = createAndAddElement("box", room1, closedBoxState);
        ComplexElement key = createAndAddElement("key", box, null);
        ComplexElement character = createAndAddElement("character", room1, null);
        game.character = character;

        //Crear reglas para movimientos
        HasContainerRule victoryRule = checkContainerRule(character, room2, "it's a pitty");
        HasStateRule closedBoxRule = checkStateRule(box, closedBoxState, "the box was open");
        HasContainerRule keyIsInRoom1 = checkContainerRule(key, room1, "key is not in room1");
        HasStateRule closedDoorRule = checkStateRule(door, closedDoorState, "the door was open");
        HasContainerRule characterHasKey = checkContainerRule(key, character, "character doesn't have the key");

        //Build Actions
        Action addOpenedStateToBox = buildAddStatesAction(box, openBoxState);
        Action removeOpenedStateToBox = buildRemoveStatesAction(box, closedBoxState);
        Action addKeyToRoom1 = buildChangeContainerAction(key, room1);
        Action addKeyToCharacter = buildChangeContainerAction(key, character);
        Action addOpendStateToDoor = buildAddStatesAction(door, openDoorState);
        Action removeOpenedStateToDoor = buildRemoveStatesAction(door, closedDoorState);
        Action moveCharacterToRoom2 = buildChangeContainerAction(character, room2);

        //Rules to Open Door
        LogicBuilder logicBuilder = new LogicBuilder();
        IExpression openingRules = null;
        try {
            openingRules = logicBuilder.build(closedDoorRule, characterHasKey, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }

        //Create moves
        Move openBox = moveWithActionsAndRules("open", addOpenedStateToBox, closedBoxRule, "The box is opened!");
        Move pickKey = moveWithActionsAndRules("pick", addKeyToCharacter, keyIsInRoom1, "There you go!");
        Move openDoor = moveWithActionsAndRules("open", addOpendStateToDoor, openingRules, "You enter room 2.");

        //Inject Actions to moves
        openBox.addAction(removeOpenedStateToBox);
        openBox.addAction(addKeyToRoom1);

        openDoor.addAction(removeOpenedStateToDoor);
        openDoor.addAction(moveCharacterToRoom2);

        //Inject Moves to Elements
        key.addMove(pickKey);
        door.addMove(openDoor);
        box.addMove(openBox);

        game.setVictoryCondition(victoryRule);
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, "pick");
        createAndAddSuportedAction(1, "open");
    }

}