package quandoo.test.quandootest.application;

import java.lang.reflect.Array;
import java.util.ArrayList;

import quandoo.test.quandootest.enums.ItemStateEnum;
import quandoo.test.quandootest.interfaces.IGridItem;

/**
 * Actual implementation of SolGridData.
 * This class is handling basic type of SOL algo.
 *
 * Created by Haroon on 8/30/2015.
 */
class SolGridDataType1 extends SolGridData{

//    ArrayList to keep the record of current selected items and perform next iteration
//    on these items only. This is added for the better performnace.
    private ArrayList<IGridItem> currentItems = null;

    public SolGridDataType1(final int row, final int col){
        super(row, col);
        currentItems = new ArrayList<IGridItem>(1);
    }

    @Override
    public boolean calculateSimulation() {
        boolean status = false;

        ArrayList<IGridItem> tmp = new ArrayList<>(1);
        ArrayList<IGridItem> live = new ArrayList<>(1);
        if(null == currentItems || currentItems.size() == 0){
            status = false;
        }else {
//       Adding new items.
            for (IGridItem item : currentItems) {
                setNewItem(item.getRow() - 1, item.getCol() - 1, tmp);
                setNewItem(item.getRow() - 1, item.getCol(), tmp);
                setNewItem(item.getRow() - 1, item.getCol() + 1, tmp);
                setNewItem(item.getRow(), item.getCol() - 1, tmp);
                setNewItem(item.getRow(), item.getCol() + 1, tmp);
                setNewItem(item.getRow() + 1, item.getCol() - 1, tmp);
                setNewItem(item.getRow() + 1, item.getCol(), tmp);
                setNewItem(item.getRow() + 1, item.getCol() + 1, tmp);
            }
//        Removing dead items
            for (IGridItem item : currentItems) {
                if (item.getCount() != 3 && item.getCount() != 2) {
                    item.setState(ItemStateEnum.DEAD);
                } else {
                    live.add(item);
                }
            }
//        Keeping only live item in currentItems list.
            currentItems = live;

//        Adding new items in currentItem
            for (IGridItem item : tmp) {
                if (item.getCount() == 3) {
                    item.setState(ItemStateEnum.ALIVE);
                    if (!currentItems.contains(item)) {
                        currentItems.add(item);
                    }
                }
            }

            status = true;
        }
        return status;

    }

    @Override
    public void addGridItem(IGridItem item) {
        if(currentItems.contains(item)){
            if(item.getState() == ItemStateEnum.DEAD)
                currentItems.remove(item);
        }else{
            if(item.getState() == ItemStateEnum.ALIVE)
                currentItems.add(item);
        }
    }


    private void setNewItem(final int row, final int col, final ArrayList<IGridItem> tmp){
        try {
            IGridItem newItem = gridItems[row][col];
            if(tmp.contains(newItem)){
                newItem.setCount(newItem.getCount() + 1);
            }else {
                newItem.setCount(1);
                tmp.add(newItem);
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

    }

    @Override
    public boolean isItemSelected() {
        boolean status;
        if(null != currentItems && currentItems.size() > 0){
            status = true;
        }else{
            status = false;
        }
        return status;
    }

    @Override
    public void clearData() {
        for(IGridItem item: currentItems){
            item.setState(ItemStateEnum.DEAD);
        }
        currentItems.clear();
    }
}
