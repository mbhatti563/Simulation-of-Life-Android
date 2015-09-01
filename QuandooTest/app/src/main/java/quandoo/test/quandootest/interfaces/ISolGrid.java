package quandoo.test.quandootest.interfaces;

/**
 * This interface is used to manage the grid of Simulation of life.
 * Created by Haroon on 8/30/2015.
 */
public interface ISolGrid {

    /**
     *  Function to initialize grid data.
     * @return
     * true if initialized successfully else false
     */
    public boolean initializeGrid();


    /**
     * Function to calculate grid data.
     * @return
     * true if calculation successful else false
     */
    public boolean calculateGridData();


    /**
     * Function used to get Grid
     * @return
     * Current grid
     */
    public IGridItem[][] getGrid();

    /**
     * Function used to set item in the grid.
     * @param item
     */
    public void setGridItem(IGridItem item);

    /**
     * Functoin used to check either any itme selected or not.
     * @return
     * true if selected else false
     */
    public boolean itemSelected();

    /**
     * Functoin used to reset gridData
     */
    public void resetGrid();

}
