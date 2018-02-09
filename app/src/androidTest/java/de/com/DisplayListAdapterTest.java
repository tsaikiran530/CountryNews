package de.com;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;

import de.com.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DisplayListAdapterTest {

    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testShouldLaunchTheMainActivityAndFindItemsInTheList() throws Exception {
        RecyclerView recyclerView = (RecyclerView) main.getActivity().findViewById(R.id.my_recycler_view);

        Thread.sleep(5000);
        assert recyclerView != null;
        assertThat(recyclerView.getAdapter().getItemCount(), is(14));
    }

    @Test
    public void testShouldTestTheItemNameInTheList() throws Exception {
        onView(withText("Beavers")).check(matches(isDisplayed()));
    }

    @Test
    public void testShouldTestTheItemNameIfError() throws Exception {
        Thread.sleep(5000);
        TextView textView = (TextView) main.getActivity().findViewById(R.id.empty_view);
        assertThat(textView.getVisibility(), is(0x00000008));
    }


}