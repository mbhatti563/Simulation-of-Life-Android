package quandoo.test.quandootest.controllers;

import quandoo.test.quandootest.interfaces.IGridItem;
import quandoo.test.quandootest.interfaces.ISolController;
import quandoo.test.quandootest.interfaces.ISolGrid;

/**
 * Implementation of ISolController.
 * This class is responsible for handling core Simulation of Life logic
 * Created by Haroon on 8/30/2015.
 */
public class SolController implements ISolController {

    private ISolGrid solGrid = null;

    public SolController(ISolGrid grid){

        this.solGrid = grid;
    }

    @Override
    public void initializeSol() {
        solGrid.initializeGrid();
    }

    @Override
    public IGridItem[][] calculateSol() {
        if(solGrid.calculateGridData()){
            return solGrid.getGrid();
        }
        return null;
    }

    @Override
    public IGridItem[][] getGrid() {
        return solGrid.getGrid();
    }

    @Override
    public void selectGridItem(IGridItem item) {
        solGrid.setGridItem(item);
    }

    @Override
    public boolean hasSelection() {

        return solGrid.itemSelected();
    }

    @Override
    public void reset() {
        solGrid.resetGrid();
    }
}
