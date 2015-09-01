package quandoo.test.quandootest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

import quandoo.test.quandootest.MainActivity;

/**
 * Created by Haroon on 9/1/2015.
 */
public class TestInstrumentation extends ActivityInstrumentationTestCase2<MainActivity> {

    public TestInstrumentation(){
        super(MainActivity.class);
    }



    public void testDefault(){
        MainActivity activity = getActivity();
        assertNotNull(activity);

    }

    public void testFragment(){
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment);
        assertNotNull(fragment);
    }

    public void testButton(){
        MainActivity activity = getActivity();
        Button btnAction = (Button)activity.findViewById(R.id.btnAction);
        assertNotNull(btnAction);
    }

    public void testButtonTitle(){
        MainActivity activity = getActivity();
        Button btnAction = (Button)activity.findViewById(R.id.btnAction);
        assertEquals(getActivity().getResources().getString(R.string.str_start), btnAction.getText().toString());
    }
}
