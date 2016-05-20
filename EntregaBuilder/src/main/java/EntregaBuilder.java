import com.sun.org.apache.xpath.internal.operations.Or;
import creation.GameBuilderImp;
import logic.LogicBuilder;
import model.actions.Action;
import model.actions.Move;
import model.elements.ComplexElement;
import model.rulesexpressions.expressions.AndExpression;
import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.expressions.OrExpression;
import model.rulesexpressions.rules.*;

public final class EntregaBuilder extends GameBuilderImp {

    @SuppressWarnings("CPD-START")

    public EntregaBuilder() {
        gameName = "EntregaBuilder";
        gameDescription = "EjercicioEntrega";
    }

    //Character
    private ComplexElement character;

    //Rooms
    private ComplexElement roomPasillo;
    private ComplexElement roomSalon1;
    private ComplexElement roomSalon2;
    private ComplexElement roomSalon3;
    private ComplexElement roomSotano;
    private ComplexElement roomSubSotano;
    private ComplexElement roomBiblioteca;
    private ComplexElement roomAccesoBiblioteca;
    private ComplexElement roomPatio;

    //Items
    private ComplexElement itemFoto;
    private ComplexElement itemLapicera;
    //Items salon 1
    private ComplexElement itemMesa;
    private ComplexElement itemBotella;
    private ComplexElement itemVaso1;
    private ComplexElement itemVaso2;
    private ComplexElement itemSilla1;
    private ComplexElement itemSilla2;
    private ComplexElement itemCuadroTren;
    private ComplexElement itemCuadroBarco;
    private ComplexElement itemCajaFuerte;
    private ComplexElement itemCredencial;
    //Items salon 2
    private ComplexElement itemMartillo;
    private ComplexElement itemDestornillador1;
    private ComplexElement itemDestornillador2;
    //Items salon 3
    private ComplexElement itemLlave;
    //Items Acceso Bilioteca
    private ComplexElement itemBibliotecario;
    //Items Bilioteca
    private ComplexElement itemLibroViejo;
    private ComplexElement itemEstante;
    private ComplexElement itemLibro1;
    private ComplexElement itemLibro2;
    private ComplexElement itemLibro3;
    private ComplexElement itemLibro4;
    private ComplexElement itemLibro5;
    private ComplexElement itemLibro6;
    private ComplexElement itemLibro7;
    private ComplexElement itemLibro8;
    private ComplexElement itemLibro9;
    //Items Sotano
    private ComplexElement itemEscalera;
    private ComplexElement itemBaranda;

    //Rules
    private LogicBuilder logicBuilder = new LogicBuilder();
    private HasContainerRule ruleTenerLlave;
    private HasStateRule ruleCredencialValida;
    private IExpression ruleParaEmborracharAlBibliotecario;
    private IExpression ruleParaIngresarALaBiblioteca;

    //Item actions
    private Action actionPickKey;
    private Action actionPickMartillo;
    private Action actionPickDestornillador1;
    private Action actionPickDestornillador2;
    private Action actionPickBotella;
    private Action actionPickVaso1;
    private Action actionPickVaso2;
    private Action actionPickCredencial;
    private Action actionSetVisibleCajaFuerte;
    private Action actionSetVisibleCredencial;
    private Action actionChangeToPasillo;
    private Action actionChangeToSalon1;
    private Action actionChangeToSalon2;
    private Action actionChangeToSalon3;
    private Action actionChangeToAccesoBiblioteca;
    private Action actionChangeToBiblioteca;
    private Action actionChangeToSubSotano;
    private Action actionPutFotoEnCredencial;
    private Action actionSetCredencialToValida;
    private Action actionSetCredencialToInvalida;
    private Action actionMakeBibliotecarioFeliz;
    private Action actionMakeBibliotecarioBorracho;

    //Moves
    private Move moveMoverCuadro;
    private Move moveAbrirCajaFuerte;
    private Move moveTomarCredencial;
    private Move moveIrAPasillo;
    private Move moveIrASalon1;
    private Move moveIrASalon2;
    private Move moveIrASalon3;
    private Move moveIrAAccesoBiblioteca;
    private Move moveIrABiblioteca;
    private Move movePonerFotoEnCredencial;
    private Move moveEmborracharAlBibliotecario;
    private Move moveTomarBotella;
    private Move moveTomarLlave;
    private Move moveTomarMartillo;
    private Move moveTomarDestornillador1;
    private Move moveTomarDestornillador2;
    private Move moveTomarVaso1;
    private Move moveTomarVaso2;

    //Doors
    private ComplexElement doorPasilloToSalon1;
    private ComplexElement doorPasilloToSalon2;
    private ComplexElement doorPasilloToSalon3;
    private ComplexElement doorPasilloToAccesoBiblioteca;
    private ComplexElement doorSalon1ToPasillo;
    private ComplexElement doorSalon2ToPasillo;
    private ComplexElement doorSalon3ToPasillo;
    private ComplexElement doorAccesoBibliotecaToPasillo;
    private ComplexElement doorAccesoBibliotecaToBiblioteca;
    private ComplexElement doorBibliotecaToAccesoBiblioteca;

    //States
    private ComplexElement stateValido;
    private ComplexElement stateInvalido;
    private ComplexElement stateOpen;
    private ComplexElement stateFeliz;
    private ComplexElement stateBorracho;

    protected void setActions() {}

    public void setElements() {
        createRooms();
        defineCharacter();
        createStates();
        createDoors();
        createItems();
        createRoomTransitionActions();
        createDoorActions();
        createItemActions();
        createRules();
        defineVictoryRule();
        createMoves();
        addMoves();
    }

    private void createRoomTransitionActions() {}
    private void createDoorActions() {}
    private void defineVictoryRule() {}


    private void defineCharacter() {
        character = createAndAddElement(EntregaConstants.character, roomPasillo, null);
        game.character = character;
    }

    private void createRooms() {
        roomPasillo = createAndAddElement(EntregaConstants.roomPasillo, null, null);
        roomSalon1 = createAndAddElement(EntregaConstants.roomSalon1, null, null);
        roomSalon2 = createAndAddElement(EntregaConstants.roomSalon2, null, null);
        roomSalon3 = createAndAddElement(EntregaConstants.roomSalon3, null, null);
        roomSotano = createAndAddElement(EntregaConstants.roomSotano, null, null);
        roomSubSotano = createAndAddElement(EntregaConstants.roomSubSotano, null, null);
        roomBiblioteca = createAndAddElement(EntregaConstants.roomBiblioteca, null, null);
        roomAccesoBiblioteca = createAndAddElement(EntregaConstants.roomAccesoBiblioteca, null, null);
        roomPatio = createAndAddElement(EntregaConstants.roomPatio, null, null);
    }

    private void createStates() {
        stateOpen = new ComplexElement(EntregaConstants.abierta);
        stateInvalido = new ComplexElement(EntregaConstants.invalido);
        stateValido = new ComplexElement(EntregaConstants.valido);
        stateFeliz = new ComplexElement(EntregaConstants.feliz);
        stateBorracho = new ComplexElement(EntregaConstants.borracho);
    }

    private void createDoors() {
        //Doors from Pasillo to another one
        doorPasilloToSalon1 = createAndAddElement(EntregaConstants.doorPasilloToSalon1, roomPasillo, stateOpen);
        doorPasilloToSalon2 = createAndAddElement(EntregaConstants.doorPasilloToSalon2, roomPasillo, stateOpen);
        doorPasilloToSalon3 = createAndAddElement(EntregaConstants.doorPasilloToSalon3, roomPasillo, stateOpen);
        doorPasilloToAccesoBiblioteca = createAndAddElement(EntregaConstants.doorAccesoBiblioteca, roomPasillo, stateOpen);

        //Doors to Pasillo from another one
        doorSalon1ToPasillo = createAndAddElement(EntregaConstants.doorPasillo, roomSalon1, stateOpen);
        doorSalon2ToPasillo = createAndAddElement(EntregaConstants.doorPasillo, roomSalon2, stateOpen);
        doorSalon3ToPasillo = createAndAddElement(EntregaConstants.doorPasillo, roomSalon3, stateOpen);
        doorAccesoBibliotecaToPasillo = createAndAddElement(EntregaConstants.doorPasillo, roomAccesoBiblioteca, stateOpen);

        //Others doors
        doorAccesoBibliotecaToBiblioteca = createAndAddElement(EntregaConstants.doorBiblioteca, roomAccesoBiblioteca, stateOpen);
        doorBibliotecaToAccesoBiblioteca = createAndAddElement(EntregaConstants.doorAccesoBiblioteca, roomBiblioteca, stateOpen);
    }

    private void createPickItemsAction() {
        actionPickKey = buildChangeContainerAction(itemLlave, character);
        actionPickMartillo = buildChangeContainerAction(itemMartillo, character);
        actionPickDestornillador1 = buildChangeContainerAction(itemDestornillador1, character);
        actionPickDestornillador2 = buildChangeContainerAction(itemDestornillador2, character);
        actionPickBotella = buildChangeContainerAction(itemBotella, character);
        actionPickVaso1 = buildChangeContainerAction(itemVaso1, character);
        actionPickVaso2 = buildChangeContainerAction(itemVaso2, character);
        actionPickCredencial = buildChangeContainerAction(itemCredencial, character);
    }

    private void createChangeRoomItemsAction() {
        actionChangeToPasillo = buildChangeContainerAction(character, roomPasillo);
        actionChangeToSalon1 = buildChangeContainerAction(character,roomSalon1);
        actionChangeToSalon2 = buildChangeContainerAction(character,roomSalon2);
        actionChangeToSalon3 = buildChangeContainerAction(character,roomSalon3);
        actionChangeToAccesoBiblioteca = buildChangeContainerAction(character, roomAccesoBiblioteca);
        actionChangeToBiblioteca = buildChangeContainerAction(character, roomBiblioteca);
        actionChangeToSubSotano = buildChangeContainerAction(character, roomSubSotano);
    }

    private void createItemActions() {
        createPickItemsAction();
        createChangeRoomItemsAction();

        actionSetVisibleCajaFuerte = buildChangeContainerAction(itemCajaFuerte,roomSalon1);
        actionSetVisibleCredencial = buildChangeContainerAction(itemCredencial,roomSalon1);

        actionPutFotoEnCredencial = buildChangeContainerAction(itemFoto, itemCredencial);
        actionSetCredencialToValida = buildAddStatesAction(itemCredencial, stateValido);
        actionSetCredencialToInvalida = buildAddStatesAction(itemCredencial, stateInvalido);

        actionMakeBibliotecarioFeliz = buildAddStatesAction(itemBibliotecario, stateFeliz);
        actionMakeBibliotecarioFeliz.setRules(ruleCredencialValida);
        actionMakeBibliotecarioBorracho = buildAddStatesAction(itemBibliotecario, stateBorracho);
    }

    private void createRules() {
        ruleTenerLlave = checkContainerRule(itemLlave,character,EntregaConstants.necesitaTenerLlaveSalon3);
        ruleCredencialValida = checkStateRule(itemCredencial, stateValido, EntregaConstants.necesitaSerValida);

        //Reglas para poder emborrachar al bibliotecario
        HasContainerRule ruleTieneBotella = checkContainerRule(itemBotella, character, EntregaConstants.necesitaLaBotella);
        HasContainerRule ruleTieneVaso1 = checkContainerRule(itemVaso1, character, EntregaConstants.necesitaElVaso);
        HasContainerRule ruleTieneVaso2 = checkContainerRule(itemVaso2, character, EntregaConstants.necesitaElVaso);
        OrExpression orExpressionParaVasos = new OrExpression();
        orExpressionParaVasos.setLeftExpression(ruleTieneVaso1);
        orExpressionParaVasos.setRightExpression(ruleTieneVaso2);
        orExpressionParaVasos.setFailMessage(EntregaConstants.noTieneVasos);
        AndExpression andExpressionParaEmborrachar = new AndExpression();
        andExpressionParaEmborrachar.setLeftExpression(ruleTieneBotella);
        andExpressionParaEmborrachar.setRightExpression(orExpressionParaVasos);
        andExpressionParaEmborrachar.setFailMessage(EntregaConstants.noSePuedeEmborrachar);
        ruleParaEmborracharAlBibliotecario = andExpressionParaEmborrachar;

        //Reglas para poder pasar a la biblioteca
        OrExpression orExpressionParaPasarABiblioteca = new OrExpression();
        HasStateRule ruleBibliotecarioFeliz = checkStateRule(itemBibliotecario, stateFeliz,EntregaConstants.noEstaFeliz);
        HasStateRule ruleBibliotecarioBorracho = checkStateRule(itemBibliotecario, stateBorracho,EntregaConstants.noEstaBorracho);
        orExpressionParaPasarABiblioteca.setLeftExpression(ruleBibliotecarioFeliz);
        orExpressionParaPasarABiblioteca.setRightExpression(ruleBibliotecarioBorracho);
        orExpressionParaPasarABiblioteca.setFailMessage(EntregaConstants.noSePuedePasarALaBiblioteca);
        ruleParaIngresarALaBiblioteca = orExpressionParaPasarABiblioteca;

    }

    private void createMoves() {
        moveMoverCuadro = moveWithActionsAndRules(EntregaConstants.moveCuadro, actionSetVisibleCajaFuerte,
                null, EntregaConstants.movedCuadroBarco);
        moveAbrirCajaFuerte = moveWithActionsAndRules(EntregaConstants.moveAbrirCajaFuerte, actionSetVisibleCredencial,
                ruleTenerLlave, EntregaConstants.abiertaCajaFuerte);
        moveTomarCredencial = moveWithActionsAndRules(EntregaConstants.movePick, actionPickCredencial,
                null, EntregaConstants.tomadoCredencial);

        moveIrAPasillo = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToPasillo,
                null, EntregaConstants.cambiadoAPasillo);
        moveIrASalon1 = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToSalon1,
                null, EntregaConstants.cambiadoASalon1);
        moveIrASalon2 = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToSalon2,
                null, EntregaConstants.cambiadoASalon2);
        moveIrASalon3 = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToSalon3,
                null, EntregaConstants.cambiadoASalon3);
        moveIrAAccesoBiblioteca = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToAccesoBiblioteca,
                null, EntregaConstants.cambiadoAAccesoBiblioteca);
        moveIrABiblioteca = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToBiblioteca,
                ruleParaIngresarALaBiblioteca, EntregaConstants.cambiadoAAccesoBiblioteca);


        movePonerFotoEnCredencial = moveWithActionsAndRules(EntregaConstants.movePutFoto, actionPutFotoEnCredencial,
                null, EntregaConstants.cambiadoFotoDeCredencial);
        movePonerFotoEnCredencial.addAction(actionSetCredencialToValida);

        moveEmborracharAlBibliotecario = moveWithActionsAndRules(EntregaConstants.moveEmborrachar, actionMakeBibliotecarioBorracho, ruleParaEmborracharAlBibliotecario, EntregaConstants.bibliotecarioBorracho);


        //Moves for pick items
        moveTomarBotella = moveWithActionsAndRules(EntregaConstants.movePick, actionPickBotella, null,
                EntregaConstants.tomadaBotella);
        moveTomarLlave = moveWithActionsAndRules(EntregaConstants.movePick, actionPickKey, null,
                EntregaConstants.tomadaLlave);
        moveTomarMartillo = moveWithActionsAndRules(EntregaConstants.movePick, actionPickMartillo, null,
                EntregaConstants.tomadoMartillo);
        moveTomarDestornillador1 = moveWithActionsAndRules(EntregaConstants.movePick, actionPickDestornillador1,
                null, EntregaConstants.tomadoDestornillador);
        moveTomarDestornillador2 = moveWithActionsAndRules(EntregaConstants.movePick, actionPickDestornillador2,
                null, EntregaConstants.tomadoDestornillador);
        moveTomarVaso1 = moveWithActionsAndRules(EntregaConstants.movePick, actionPickVaso1, null,
                EntregaConstants.tomadoVaso);
        moveTomarVaso2 = moveWithActionsAndRules(EntregaConstants.movePick, actionPickVaso2, null,
                EntregaConstants.tomadoVaso);

    }

    private void addMovesItemsInSalon1() {
        itemCuadroBarco.addMove(moveMoverCuadro);
        itemCajaFuerte.addMove(moveAbrirCajaFuerte);

        itemCredencial.addMove(moveTomarCredencial);
        itemCredencial.addMove(movePonerFotoEnCredencial);

        itemBotella.addMove(moveTomarBotella);
        itemVaso1.addMove(moveTomarVaso1);
        itemVaso2.addMove(moveTomarVaso2);
    }

    private void addMovesItemsInSalon2() {
        itemMartillo.addMove(moveTomarMartillo);
        itemDestornillador1.addMove(moveTomarDestornillador1);
        itemDestornillador2.addMove(moveTomarDestornillador2);
    }

    private void addMovesItemsInSalon3() {
        itemLlave.addMove(moveTomarLlave);
    }

    private void addMovesItemsInAccesoBiblioteca() {
        itemBibliotecario.addMove(moveEmborracharAlBibliotecario);
    }

    private void addMoves() {
        addMovesItemsInSalon1();
        addMovesItemsInSalon2();
        addMovesItemsInSalon3();
        addMovesItemsInAccesoBiblioteca();

        doorAccesoBibliotecaToPasillo.addMove(moveIrAPasillo);
        doorSalon1ToPasillo.addMove(moveIrAPasillo);
        doorSalon2ToPasillo.addMove(moveIrAPasillo);
        doorSalon3ToPasillo.addMove(moveIrAPasillo);

        doorPasilloToAccesoBiblioteca.addMove(moveIrAAccesoBiblioteca);
        doorBibliotecaToAccesoBiblioteca.addMove(moveIrAAccesoBiblioteca);
        doorPasilloToSalon1.addMove(moveIrASalon1);
        doorPasilloToSalon2.addMove(moveIrASalon2);
        doorPasilloToSalon3.addMove(moveIrASalon3);

        itemCredencial.addMove(movePonerFotoEnCredencial);
        itemBibliotecario.addMove(moveEmborracharAlBibliotecario);

        doorAccesoBibliotecaToBiblioteca.addMove(moveIrABiblioteca);

    }

    private void createItems() {
        // Items from character
        itemFoto = createAndAddElement(EntregaConstants.photo, character,null);
        itemLapicera = createAndAddElement(EntregaConstants.pen, character,null);

        createItemsSalon1();
        createItemsSalon2();
        createItemsSalon3();
        createItemsAccesoBiblioteca();
        createItemsBiblioteca();
        createItemsSotano();
    }

    private void createItemsSalon1() {
        itemMesa = createAndAddElement(EntregaConstants.tableSalon1, roomSalon1,null);
        itemBotella = createAndAddElement(EntregaConstants.bottleSalon1, roomSalon1,null);
        itemVaso1 = createAndAddElement(EntregaConstants.glass1Salon1, roomSalon1,null);
        itemVaso2 = createAndAddElement(EntregaConstants.glass2Salon1, roomSalon1,null);
        itemSilla1 = createAndAddElement(EntregaConstants.chair1Salon1, roomSalon1,null);
        itemSilla2 = createAndAddElement(EntregaConstants.chair2Salon1, roomSalon1,null);
        itemCuadroTren = createAndAddElement(EntregaConstants.cuadroTrenSalon1, roomSalon1,null);

        itemCuadroBarco = createAndAddElement(EntregaConstants.cuadroBarcoSalon1, roomSalon1,null);
        itemCajaFuerte = createAndAddElement(EntregaConstants.cajaFuerteSalon1, itemCuadroBarco,null);
        itemCredencial = createAndAddElement(EntregaConstants.credencialSalon1, itemCajaFuerte,null);
    }

    private void createItemsSalon2() {
        itemMartillo = createAndAddElement(EntregaConstants.martilloSalon2, roomSalon2,null);
        itemDestornillador1 = createAndAddElement(EntregaConstants.destornillador1Salon2, roomSalon2,null);
        itemDestornillador2 = createAndAddElement(EntregaConstants.destornillador2Salon2, roomSalon2,null);
    }

    private void createItemsSalon3() {
        itemLlave = createAndAddElement(EntregaConstants.keySalon3, roomSalon3,null);
    }

    private void createItemsAccesoBiblioteca() {
        itemBibliotecario = createAndAddElement(EntregaConstants.bibliotecario, roomAccesoBiblioteca,null);
    }

    private void createItemsBiblioteca() {
        itemLibroViejo = createAndAddElement(EntregaConstants.libroViejo, roomBiblioteca,null);
        itemEstante = createAndAddElement(EntregaConstants.estante, roomBiblioteca,null);
        itemLibro1 = createAndAddElement(EntregaConstants.libro1, roomBiblioteca,null);
        itemLibro2 = createAndAddElement(EntregaConstants.libro2, roomBiblioteca,null);
        itemLibro3 = createAndAddElement(EntregaConstants.libro3, roomBiblioteca,null);
        itemLibro4 = createAndAddElement(EntregaConstants.libro4, roomBiblioteca,null);
        itemLibro5 = createAndAddElement(EntregaConstants.libro5, roomBiblioteca,null);
        itemLibro6 = createAndAddElement(EntregaConstants.libro6, roomBiblioteca,null);
        itemLibro7 = createAndAddElement(EntregaConstants.libro7, roomBiblioteca,null);
        itemLibro8 = createAndAddElement(EntregaConstants.libro8, roomBiblioteca,null);
        itemLibro9 = createAndAddElement(EntregaConstants.libro9, roomBiblioteca,null);
    }

    private void createItemsSotano() {
        itemBibliotecario = createAndAddElement(EntregaConstants.baranda, roomSotano, null);
        itemBibliotecario = createAndAddElement(EntregaConstants.escaleraOxidada, roomSotano, null);
    }
}
