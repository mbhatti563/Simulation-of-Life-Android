package quandoo.test.quandootest.application;

import quandoo.test.quandootest.enums.ItemStateEnum;
import quandoo.test.quandootest.interfaces.IGridItem;

/**
 * Implementation of IGridItem. This calss represents an item of the grid.
 * Created by Haroon on 8/30/2015.
 */
class GridItem implements IGridItem{

//   Represents row index in a 2D array.
    private int row;
//    Represents col in a 2D array.
    private int col;
//    State of the item either dead or alive
    private ItemStateEnum state;
//    Use to calculate the counts of neighbour.
    private int counter;

    public GridItem(int row, int col, ItemStateEnum state){
        this.row = row;
        this.col = col;
        this.state = state;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public ItemStateEnum getState() {
        return state;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getCount() {
        return counter;
    }

    @Override
    public void setCount(int counter) {
        this.counter = counter;
    }

    @Override
    public void setState(ItemStateEnum state) {
        this.state = state;
    }

}
