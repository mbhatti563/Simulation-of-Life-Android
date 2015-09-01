package quandoo.test.quandootest.interfaces;

import android.view.View;

import quandoo.test.quandootest.enums.ActionEnum;

/**
 * An interface class to handle communication between view and Simulation.
 * Created by Haroon on 8/30/2015.
 */
public interface IAppController {
    /**
     * Function used to initialize the app
     */
    public void initializeApp();

    /**
     * Get the action request from the view.
     * @param action
     * @param view
     */
    public void onAction(ActionEnum action, View view);

    /**
     * Initiate request for next iteration calculation
     * @return
     * Updated IGridITem[][]
     */
    public IGridItem[][] startCalculation();
}
