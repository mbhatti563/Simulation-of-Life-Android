import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import quandoo.test.quandootest.application.GridDataFactory;
import quandoo.test.quandootest.enums.ItemStateEnum;
import quandoo.test.quandootest.enums.SolTypeEnum;
import quandoo.test.quandootest.interfaces.IGridItem;
import quandoo.test.quandootest.interfaces.ISolGrid;
import quandoo.test.quandootest.utils.Constants;

/**
 * Created by Haroon on 8/31/2015.
 */

public class UnitTest {

    private static ISolGrid grid;


    @BeforeClass
    public static void init(){
        grid = GridDataFactory.getInstance(SolTypeEnum.TYPE1, Constants.GRID_WIDTH, Constants.GRID_HEIGHT);
    }

    @Test
    public void testGridItemExists(){
        Assert.assertEquals(false, grid.itemSelected());
    }

    @Test
    public void testAddItem(){
        IGridItem[][] items = grid.getGrid();
        Assert.assertNotNull(items);

    }

    @AfterClass
    public static void clear(){
        grid = null;
    }

}
