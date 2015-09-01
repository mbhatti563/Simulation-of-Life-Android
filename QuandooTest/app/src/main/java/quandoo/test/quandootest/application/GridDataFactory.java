package quandoo.test.quandootest.application;

import quandoo.test.quandootest.enums.SolTypeEnum;
import quandoo.test.quandootest.interfaces.ISolGrid;

/**
 * Factory class used to ged Grid Data on the basis of Type.
 * This class is used to add different variations of Sol.
 * Created by Haroon on 8/30/2015.
 */
public class GridDataFactory {

//    Static SolType.
    private static ISolGrid solType = null;

//    Private Constructor
    private GridDataFactory(){

    }

    /**
     * Functoin used to get the instance of the class based on type.
     * @param typeEnum
     * @param row
     * @param col
     * @return
     */
    public static ISolGrid getInstance(SolTypeEnum typeEnum, final int row, final int col){
        if(typeEnum == SolTypeEnum.TYPE1){
            solType = new SolGridDataType1(row, col);
        }
        return solType;
    }


}
