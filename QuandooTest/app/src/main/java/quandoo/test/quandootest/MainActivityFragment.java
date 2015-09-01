package quandoo.test.quandootest;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import quandoo.test.quandootest.application.GridDataFactory;
import quandoo.test.quandootest.controllers.AppController;
import quandoo.test.quandootest.controllers.SolController;
import quandoo.test.quandootest.enums.ActionEnum;
import quandoo.test.quandootest.enums.ItemStateEnum;
import quandoo.test.quandootest.enums.SolTypeEnum;
import quandoo.test.quandootest.interfaces.IAppController;
import quandoo.test.quandootest.interfaces.IGridItem;
import quandoo.test.quandootest.interfaces.ISolController;
import quandoo.test.quandootest.interfaces.ISolView;
import quandoo.test.quandootest.utils.Constants;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ISolView, View.OnClickListener
        {

    private View rootView = null;


    private GridLayout gridView = null;
    private Button btnAction = null;
    private IAppController controller = null;
    private AsyncCalculator solCalc = null;
    private boolean isStart = false;




    @Override
    public void onResume() {
        controller.initializeApp();
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        ISolController solController = new SolController(GridDataFactory.getInstance(
                SolTypeEnum.TYPE1, Constants.GRID_WIDTH, Constants.GRID_HEIGHT));
        controller = new AppController(solController,this);
        return rootView;
    }

            @Override
            public void initializeGrid(IGridItem[][] items) {
                displayGrid(items);
            }

            @Override
            public void updateGrid(IGridItem[][] items) {
                updateGridData(items);
            }

            @Override
            public void initializeView() {
                if(null != rootView){
                    gridView = (GridLayout) rootView.findViewById(R.id.gridData);
                    btnAction = (Button)rootView.findViewById(R.id.btnAction);
                    btnAction.setOnClickListener(this);

                    ((Button)rootView.findViewById(R.id.btnReset)).setOnClickListener(this);

                    gridView.setColumnCount(Constants.GRID_WIDTH);
                    gridView.setRowCount(Constants.GRID_HEIGHT);

                }
            }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.btnAction:
                String text = ((Button) v).getText().toString();
                if (text.equals(getResources().getString(R.string.str_start))) {
                    controller.onAction(ActionEnum.ACTION_START, v);
                } else {
                    controller.onAction(ActionEnum.ACTION_STOP, v);
                }
                break;
            case R.id.btnReset:
                if(!isStart)
                    controller.onAction(ActionEnum.ACTION_RESET, v);
                else
                    onError("Please stop to reset.");
                break;
            default:
                controller.onAction(ActionEnum.ACTION_CHANGE, v);
                break;

        }

    }

            /**
             * Function used to display grid data
             * @param items
             */
    private void displayGrid(IGridItem[][] items){
        TextView view;
        if(null == items){
            onError("Data not found");
            isStart = false;
            return;
        }
        for(int i = 0; i < items.length; i++){

            for(int j=0; j < items[0].length; j++){
                view = getTextView(items[i][j]);
                view.setTag(items[i][j]);
                gridView.addView(view);

            }

        }
    }

            /**
             * Function used to update grid data
             * @param items
             */
    private void updateGridData(IGridItem[][] items){
        View tmp = null;
        IGridItem item = null;
        if(null == items){
            onError("Data not found");
            isStart = false;
            return;
        }

        for(int i = 0; i < items.length; i++){

            for(int j=0; j < items[0].length; j++){
                item = items[i][j];
                tmp = gridView.getChildAt( (item.getRow() * 20) + item.getCol());
                if (item.getState() == ItemStateEnum.ALIVE) {
                    tmp.setBackgroundResource(R.drawable.border_selected);
                }else {
                    tmp.setBackgroundResource(R.drawable.border);
                }

            }

        }
    }

            /**
             * Function used to get TextView as a grid item.
             * @param item
             * @return
             */
    private TextView getTextView(IGridItem item){
        TextView txt = new TextView(getContext());

        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(Constants.CELL_WIDTH,
                Constants.CELL_HEIGHT);
        txt.setLayoutParams(param);
        if(item.getState() == ItemStateEnum.ALIVE){
            txt.setBackgroundResource(R.drawable.border_selected);
        }else{
            txt.setBackgroundResource(R.drawable.border);
        }
        txt.setOnClickListener(this);
        return txt;
    }

    @Override
    public void updateGridItem(IGridItem item) {
        if(null == item){
            onError("Data not found");
            return;
        }
        View tmp = gridView.getChildAt( (item.getRow() * 20) + item.getCol());
        if(item.getState() == ItemStateEnum.ALIVE){
            tmp.setBackgroundResource(R.drawable.border_selected);
        }else{
            tmp.setBackgroundResource(R.drawable.border);
        }
    }


    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

            @Override
            public void startSimulation() {
               new AsyncCalculator().execute();
            }

            @Override
            public void stopSimulation() {
                isStart = false;
            }

            @Override
            public void updateViewState(View v) {
                String text = ((Button) v).getText().toString();
                if (text.equals(getResources().getString(R.string.str_start))) {
                    ((Button) v).setText(getResources().getString(R.string.str_stop));
                    isStart = true;
                } else {
                    ((Button) v).setText(getResources().getString(R.string.str_start));
                }
            }

            /**
             * Async class to handle calculations on background and then update the view.
             */
            private class AsyncCalculator extends AsyncTask<Void,  Void, IGridItem[][]>  {
         @Override
         protected void onPostExecute(IGridItem[][] iGridItems) {
             super.onPostExecute(iGridItems);
             updateGridData(iGridItems);
             try {
                 Thread.sleep(Constants.THREAD_SLEEP_TIME);
                 if(isStart)
                     startSimulation();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

         }

         @Override
         protected IGridItem[][] doInBackground(Void... params) {
             return controller.startCalculation();
         }


     }
        }
