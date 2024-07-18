import org.example.CustomArrayList;
import org.example.DefaultCustomArrayList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

    CustomArrayList<String> stringCustomArrayList = new DefaultCustomArrayList<>();

    @Test
    void addTest() {
        stringCustomArrayList.add("one");
        assertEquals("one", stringCustomArrayList.get(0));
    }

    @Test
    void addWithIndexTest() {
        stringCustomArrayList.add("one");
        stringCustomArrayList.add("two");
        stringCustomArrayList.add("three");

        stringCustomArrayList.add(0, "four");
        assertEquals("four", stringCustomArrayList.get(0));

    }

    @Test
    void addAllTest() {
        List<String> list = new ArrayList<>() {{
            add("one");
            add("two");
        }};
        stringCustomArrayList.addAll(list);

        assertEquals(2, stringCustomArrayList.size());
    }


    @Test
    void isEmptyTest() {
        assertTrue(stringCustomArrayList.isEmpty());

    }

    @Test
    void clearTest() {
        stringCustomArrayList.add("one");
        stringCustomArrayList.clear();
        assertEquals(0, stringCustomArrayList.size());

    }


}


