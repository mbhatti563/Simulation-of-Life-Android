package quandoo.test.quandootest.application;

import quandoo.test.quandootest.enums.ItemStateEnum;
import quandoo.test.quandootest.interfaces.IGridItem;
import quandoo.test.quandootest.interfaces.ISolGrid;

/**
 * Implementation of ISolGrid. This class is representing complete grid.
 * It is kept Abstract so that it can easily handle different types.
 * Created by Haroon on 8/30/2015.
 */
 abstract class SolGridData implements ISolGrid{

    //  Two dimensional array of IGridItem to represent the grid of SOL.
    protected IGridItem[][] gridItems = null;

//    Constructor to initialize the item.
    public SolGridData(final int rows, final int columns){
        gridItems = new GridItem[rows][columns];
    }

    @Override
    public boolean initializeGrid() {
        boolean status;

        if(null == gridItems){
            status = false;
        }else {

            for(int i = 0; i < gridItems.length; i++){

                for(int j=0; j < gridItems[0].length; j++){
                    gridItems[i][j] = new GridItem(i,j, ItemStateEnum.DEAD);
                }

            }

            status = true;
        }
        return status;
    }

    @Override
    public void setGridItem(IGridItem item) {
        if(item.getState() == ItemStateEnum.DEAD) {
            gridItems[item.getRow()][item.getCol()].setState(ItemStateEnum.ALIVE);
        }else{
            gridItems[item.getRow()][item.getCol()].setState(ItemStateEnum.DEAD);
        }
        addGridItem(gridItems[item.getRow()][item.getCol()]);
    }

    @Override
    public boolean calculateGridData(){
        return calculateSimulation();
    }

    @Override
    public IGridItem[][] getGrid() {
        return gridItems;
    }

    @Override
    public boolean itemSelected() {
        return isItemSelected();
    }

    @Override
    public void resetGrid() {
        clearData();
    }

    /**
     * Abstract function to calcuate next simulation data.

     * @return
     */
    public abstract boolean calculateSimulation();

    /**
     * Abstract function used to add a grid item.
     * @param item
     */
    public abstract void addGridItem(IGridItem item);

    /**
     * Function used to check either any item in the grid is selected
     * @return
     */
    public abstract boolean isItemSelected();

    /**
     * Function used to clear the date of the grid
     */
    public abstract void clearData();
}
