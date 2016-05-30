import creation.GameBuilderImp;
import model.actions.*;
import model.elements.*;
import model.rulesexpressions.expressions.*;
import model.rulesexpressions.rules.*;

import java.util.ArrayList;

@SuppressWarnings("CPD-START")
public final class CursedObjectBuilder extends GameBuilderImp {

    private CursedObjectConstants constants = new CursedObjectConstants();
    private ComplexElement room0;
    private ComplexElement room1;
    private ComplexElement room2;
    private ComplexElement door0To1;
    private ComplexElement door1To2;
    private ComplexElement cursedObject;
    private ComplexElement thief;
    private ArrayList<Player> characters;
    private ComplexElement notVisibleObjectState;
    private Action changeRoom0ForRoom1;
    private Action changeRoom1ForRoom2;
    private Action stolenObject;
    private Action pickedObject;
    private Action makeNotVisibleObject;
    private HasContainerRule victoryRule;
    private HasContainerRule thiefHaveCursedObject;
    private HasContainerRule characterHasCursedObject;
    private HasContainerRule objectIsInTheRoom;
    private HasContainerRule characterIsInRoom1;
    private HasContainerRule characterIsInRoom0;
    private AndExpression conditionToPickObject;
    private AndExpression conditionsToTalkWithThief;
    private Move goToRoom1;
    private Move goToRoom2;
    private Move talkThief;
    private Move pickObject;
    private Element openState;

    public CursedObjectBuilder() {
        gameName = "CursedObject";
        gameDescription = "There is a cursed object on this game. And the thief...";
        characters = new ArrayList<>();
    }

    public void setElements() {
        createElements();
        loadPlayers();
        createActions();
        createRules();
        createComplexRules();
        createMoves();
        addMovesToElements();
    }

    private void loadPlayers() {
        for (int i = 0; i < constants.numberOfPlayers; i++) {
            characters.add(createAndAddPlayer("character" + i, room0, null));
        }
        game.currentPlayer = characters.get(0);
        game.characters = characters;
    }

    private void addMovesToElements() {
        door0To1.addMove(goToRoom1);
        door1To2.addMove(goToRoom2);
        cursedObject.addMove(pickObject);
        thief.addMove(talkThief);
    }

    private void createMoves() {
        goToRoom1 = moveWithActionsAndRules(constants.open, changeRoom0ForRoom1, characterHasCursedObject, constants.goToRoom1);
        goToRoom2 = moveWithActionsAndRules(constants.open, changeRoom1ForRoom2, thiefHaveCursedObject, constants.goToRoom2);
        talkThief = moveWithActionsAndRules(constants.talkTo, stolenObject, conditionsToTalkWithThief, constants.talkThief);
        talkThief.addAction(makeNotVisibleObject);
        pickObject = moveWithActionsAndRules(constants.pick, pickedObject, conditionToPickObject, constants.pickObject);
    }

    private void createComplexRules() {
        conditionToPickObject = new AndExpression();
        conditionToPickObject.setLeftExpression(objectIsInTheRoom);
        conditionToPickObject.setRightExpression(characterIsInRoom0);

        conditionsToTalkWithThief = new AndExpression();
        conditionsToTalkWithThief.setLeftExpression(characterIsInRoom1);
        conditionsToTalkWithThief.setRightExpression(characterHasCursedObject);

    }

    private void createRules() {
        Player character = characters.get(0);
        victoryRule = checkContainerRule(character, room2, constants.notWon);
        thiefHaveCursedObject = checkContainerRule(cursedObject, thief, constants.thiefNeedsObject);
        characterHasCursedObject = checkContainerRule(cursedObject, character, constants.missingObject);
        objectIsInTheRoom = checkContainerRule(cursedObject, room0, constants.hasObject);
        characterIsInRoom1 = checkContainerRule(character, room1, constants.wrongRoom);
        characterIsInRoom0 = checkContainerRule(character, room0, constants.wrongRoom);
        character.setVictoryCondition(victoryRule);
    }

    private void createActions() {
        Player character = characters.get(0);
        changeRoom0ForRoom1 = buildChangeContainerAction(character, room1);
        changeRoom1ForRoom2 = buildChangeContainerAction(character, room2);
        stolenObject = buildChangeContainerAction(cursedObject, thief);
        pickedObject = buildChangeContainerAction(cursedObject, character);
        makeNotVisibleObject = new ChangeVisibleAction();
        addElementsToAction(makeNotVisibleObject, cursedObject, notVisibleObjectState);
    }

    private void createElements() {
        openState = new Element(constants.opened);
        notVisibleObjectState = new ComplexElement(constants.notVisible);
        notVisibleObjectState.setVisible(false);
        room0 = createAndAddElement(constants.room0, null, null);
        room1 = createAndAddElement(constants.room1, null, null);
        room2 = createAndAddElement(constants.room2, null, null);
        door0To1 = createAndAddElement(constants.door0to1, room0, openState);
        door1To2 = createAndAddElement(constants.door1to2, room1, openState);
        cursedObject = createAndAddElement(constants.cursedObject, room0, null);
        thief = createAndAddElement(constants.thief, room1, null);
    }

    @SuppressWarnings("CPD-END")


    public void setActions() {
        createAndAddSuportedAction(1, constants.pick);
        createAndAddSuportedAction(1, constants.open);
        createAndAddSuportedAction(1, constants.talkTo);
    }
}