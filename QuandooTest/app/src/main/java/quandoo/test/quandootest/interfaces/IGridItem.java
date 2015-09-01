package quandoo.test.quandootest.interfaces;

import quandoo.test.quandootest.enums.ItemStateEnum;

/**
 * Interface to handle Gird Items.
 * Each Item in the grid is handled by this interface.
 * Created by Haroon on 8/30/2015.
 */
public interface IGridItem {

    /**
     * Returns Current row
     * @return
     */
    public int getRow();

    /**
     * Returns Current state. Either dead or alive
     * @return
     */
    public ItemStateEnum getState();

    /**
     * Returns Current col
     * @return
     */
    public int getCol();

    /**
     * Returns the count of neighbours
     * @return
     */
    public int getCount();

    /**
     * Sets the count of neighbour
     * @param counter
     */
    public void setCount(int counter);

    /**
     * Sets the state of the item.
     * @param state
     */
    public void setState(ItemStateEnum state);

}
