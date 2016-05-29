import creation.GameBuilderImp;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Element;
import model.rulesexpressions.expressions.*;
import model.rulesexpressions.rules.*;

import java.util.ArrayList;

@SuppressWarnings("CPD-START")
public final class OpenDoorBuilder extends GameBuilderImp {

    private OpenDoorConstants constants = new OpenDoorConstants();
    private Element closedDoor;
    private Element openedDoor;
    private ComplexElement room1;
    private ComplexElement room2;
    private ComplexElement door;
    private ComplexElement key;
    private ArrayList<ComplexElement> characters;
    private HasContainerRule roomHasKey;
    private HasContainerRule characterHasKey;
    private HasStateRule doorIsClosed;
    private HasContainerRule victoryCondition;
    private LogicBuilder logicBuilder;
    private IExpression conditionsToOpenDoor;
    private Action addOpenedDoorState;
    private Action removeClosedDoorState;
    private Action addKeyToCharacter;
    private Action addCharacterToRoom2;
    private Move pickKey;
    private Move openDoor;

    public OpenDoorBuilder() {
        gameName = "OpenDoor";
        gameDescription = "There is a door on this game. Also, it is locked.";
        characters = new ArrayList<>();
    }

    public void setElements() {
        createElements();
        loadPlayers();
        createRules();
        createActions();
        createComplexRules();
        createMoves();
    }

    private void loadPlayers() {
        for (int i = 0; i < constants.numberOfPlayers; i++) {
            characters.add(createAndAddElement("character" + i, room1, null));
        }
        game.currentPlayer = characters.get(0);
        game.characters = characters;
    }

    private void createMoves() {
        //Create moves
        pickKey = moveWithActionsAndRules(constants.pick, addKeyToCharacter, roomHasKey, constants.pickKey);
        openDoor = moveWithActionsAndRules(constants.open, addOpenedDoorState, conditionsToOpenDoor, constants.openDoor);

        //Inject further actions
        openDoor.addAction(removeClosedDoorState);
        openDoor.addAction(addCharacterToRoom2);

        //Inject Moves a Elements
        key.addMove(pickKey);
        door.addMove(openDoor);
    }

    private void createComplexRules() {
        //Rules to open door
        logicBuilder = new LogicBuilder();
        conditionsToOpenDoor = null;
        try {
            conditionsToOpenDoor = logicBuilder.build(doorIsClosed, characterHasKey, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }
    }

    private void createActions() {
        ComplexElement character = characters.get(0);
        addOpenedDoorState = buildAddStatesAction(door, openedDoor);
        removeClosedDoorState = buildRemoveStatesAction(door, closedDoor);
        addKeyToCharacter = buildChangeContainerAction(key, character);
        addCharacterToRoom2 = buildChangeContainerAction(character, room2);
    }

    private void createRules() {
        ComplexElement character = characters.get(0);
        roomHasKey = checkContainerRule(key, room1, constants.keyNotInRoom1);
        characterHasKey = checkContainerRule(key, character, constants.room2Locked);
        doorIsClosed = checkStateRule(door, closedDoor, constants.doorOpened);
        victoryCondition = checkContainerRule(character, room2, constants.notWon);
        game.setVictoryCondition(victoryCondition);
    }

    private void createElements() {
        closedDoor = new Element(constants.closed);
        openedDoor = new Element(constants.opened);
        room1 = createAndAddElement(constants.room1, null, null);
        room2 = createAndAddElement(constants.room2, null, null);
        door = createAndAddElement(constants.door, room1, closedDoor);
        key = createAndAddElement(constants.key, room1, null);
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, constants.pick);
        createAndAddSuportedAction(1, constants.open);
    }
}
