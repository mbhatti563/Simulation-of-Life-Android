package quandoo.test.quandootest.interfaces;

/**
 * Interface for Simulation of life and manage all the operations.
 * Created by Haroon on 8/30/2015.
 */
public interface ISolController {

    /**
     * Function to initialize the grid and items.
     */
    public void initializeSol();

    /**
     * Function to calculate next iteration of the gird items
     * @return
     * updated IGridItem[][]
     */
    public IGridItem[][] calculateSol();

    /**
     * Function to return the current grid.
     * @return
     * current state of the grid.
     */
    public IGridItem[][] getGrid();

    /**
     * Selects specific item in the grid.
     * @param item
     * item to be modified. Only state is changed.
     */
    public void selectGridItem(IGridItem item);

    /**
     * Function to check if grid has any selection to process next calculation.
     * @return
     * true if has selection else false
     */
    public boolean hasSelection();

    /**
     * Function to reset the grid to its default state
     */
    public void reset();
}
