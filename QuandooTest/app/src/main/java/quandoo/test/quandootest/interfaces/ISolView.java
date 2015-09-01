package quandoo.test.quandootest.interfaces;

import android.view.View;

import quandoo.test.quandootest.enums.ActionEnum;

/**
 * Interface used to handle View interaction.
 * It is added to extend the view for any type like Android or Swing.
 * Created by Haroon on 8/30/2015.
 */
public interface ISolView {

    /**
     * Function used ot initialize view and SOL
     */
    public void initializeView();

    /**
     * Function used to initialize grid for the first time
     * @param items
     */
    public void initializeGrid(IGridItem[][] items);

    /**
     * Function used to update the grid after update.
     * @param items
     */
    public void updateGrid(IGridItem[][] items);

    /**
     * Function used to update a specific item in the grid
     * @param item
     */
    public void updateGridItem(IGridItem item);

    /**
     * Function used to display error or warning messages
     * @param message
     */
    public void onError(String message);

    /**
     * Function used to start the simulation of the grid
     */
    public void startSimulation();

    /**
     * Function used to stop the simulation
     */
    public void stopSimulation();

    /**
     * Function used to update the state of a view.
     * @param v
     */
    public void updateViewState(View v);

}
