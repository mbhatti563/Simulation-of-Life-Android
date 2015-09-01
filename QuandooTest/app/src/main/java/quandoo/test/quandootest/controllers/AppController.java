package quandoo.test.quandootest.controllers;

import android.view.View;

import quandoo.test.quandootest.enums.ActionEnum;
import quandoo.test.quandootest.interfaces.IAppController;
import quandoo.test.quandootest.interfaces.IGridItem;
import quandoo.test.quandootest.interfaces.ISolController;
import quandoo.test.quandootest.interfaces.ISolView;

/**
 * Implementation of IAppController. Used to manage the app and user interaction.
 * Created by Haroon on 8/30/2015.
 */
public class AppController implements IAppController{

//    Sol Controller for communicating with Sol logic.
    private ISolController solController = null;
//    View controller used to handle view.
    private ISolView viewController = null;

    public AppController(final ISolController solController, final ISolView viewController){
        this.solController = solController;
        this.viewController = viewController;
    }


    @Override
    public void initializeApp() {
        if(null != solController){
            solController.initializeSol();
        }

        if(null != viewController){
            viewController.initializeView();
            viewController.initializeGrid(solController.getGrid());
        }
    }

    @Override
    public void onAction(ActionEnum action, View view) {
        if(action == ActionEnum.ACTION_CHANGE){
            IGridItem item = (IGridItem)view.getTag();
            solController.selectGridItem(item);
            item = solController.getGrid()[item.getRow()][item.getCol()];
            viewController.updateGridItem(item);
        }else if(action == ActionEnum.ACTION_START){
            if(solController.hasSelection()) {
                viewController.updateViewState(view);
                viewController.startSimulation();
            }else{
                viewController.onError("No selected item.");
            }
        }else if(action == ActionEnum.ACTION_STOP){
            viewController.updateViewState(view);
            viewController.stopSimulation();
        }else if(action == ActionEnum.ACTION_RESET){
            solController.reset();
            viewController.updateGrid(solController.getGrid());
        }else{
            viewController.onError("Wrong action called.");
        }
    }

    @Override
    public IGridItem[][] startCalculation() {
       return solController.calculateSol();
    }
}
