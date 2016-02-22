import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import junit.framework.*;

public class ListTest {
    CList<Integer> empty;
    CList<Integer> list;

    @Before
    public void init() {
        list = new CList<Integer>();
        for (int i = 0; i < 10; ++i) {
            list.append(i);
        }
        empty = new CList<Integer>();
    }

    @Test
    public void testString() {
        System.out.println("\nstarting tests, using toString to test init");
        Assert.assertEquals("[ 0 1 2 3 4 5 6 7 8 9 ]", list.toString());
    }

    @Test
    public void testEmptyList() {
        assert empty.length() == 0;
        assert empty.currPos() == 0;
        assert empty.getValue() == null;
        assert empty.remove() == null;
    }

    @Test
    public void testClearList() {
        assert list.length() == 10;
        list.clear();
        assert list.length() == 0;
        assert list.currPos() == 0;
    }

    @Test
    public void testClearEmptyList() {
        assert empty.length() == 0;
        empty.clear();
        assert empty.length() == 0;
        assert empty.currPos() == 0;
    }

    @Test
    public void testListInsert() {
        assert list.currPos() == 0;
        assert list.insert(11);
        assert list.getValue() == 11;
        assert list.length() == 11;
        assert list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListInsert() {
        assert empty.currPos() == 0;
        assert empty.insert(1);
        assert empty.length() == 1;
        assert empty.currPos() == 0;
        assert empty.getValue() == 1;
    }
    
    @Test
    public void testListAppend() {
        assert list.currPos() == 0;
        assert list.insert(11);
        assert list.getValue() == 0;
        assert list.length() == 11;
        assert list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListAppend() {
        assert list.currPos() == 0;
        assert list.append(1);
        assert empty.length() == 1;
        assert empty.currPos() == 0;
        assert empty.getValue() == 1;
    }
    
    @Test
    public void testListRemove() {
        assert list.currPos() == 0;
        assert list.remove() == 0;
        assert list.getValue() == 1;
        assert list.length() == 9;
        assert list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListRemove() {
        assert list.remove() == null;
        assert empty.length() == 0;
        assert empty.currPos() == 0;
        assert empty.getValue() == null;
    }
    
    @Test
    public void testListMoveToStart() {
        assert list.currPos() == 0;
        list.moveToEnd();
        list.moveToStart();
        assert list.getValue() == 0;
        assert list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListMoveToStart() {
        assert list.currPos() == 0;
        list.moveToStart();
        assert empty.currPos() == 0;
        assert empty.getValue() == null;
    }

    @Test
    public void testListMoveToEnd() {
        assert list.currPos() == 0;
        list.moveToEnd();
        assert list.getValue() == 9;
        assert list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListMoveToEnd() {
        assert list.currPos() == 0;
        list.moveToEnd();
        assert empty.currPos() == 0;
        assert empty.getValue() == null;
    }

    @Test
    public void testListNext() {
        assert list.currPos() == 0;
        list.next();
        assert list.currPos() == 1;
        for (int i = 0; i < 9; i++) {
            list.next();
        }
        assert list.currPos() == 9;
    }
    
    @Test
    public void testEmptyListNext() {
        assert list.currPos() == 0;
        list.next();
        assert empty.currPos() == 0;
        assert empty.getValue() == null;
    }
    @Test
    public void testListPrev() {
        assert list.currPos() == 0;
        list.moveToEnd();
        assert list.currPos() == 9;
        list.prev();
        assert list.currPos() == 8;
        for (int i = 0; i < 9; i++) {
            list.prev();
        }
        assert list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListPrev() {
        assert list.currPos() == 0;
        list.prev();
        assert empty.currPos() == 0;
    }

    @Test
    public void testListForward() {
        assert list.currPos() == 0;
        list.forward();
        assert list.currPos() == 1;
        for (int i = 0; i < 9; i++) {
            list.forward();
        }
        assert list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListForward() {
        assert list.currPos() == 0;
        list.forward();
        assert empty.currPos() == 0;
    }
    @Test
    public void testListBack() {
        assert list.currPos() == 0;
        list.moveToEnd();
        assert list.currPos() == 9;
        list.back();
        assert list.currPos() == 8;
        for (int i = 0; i < 9; i++) {
            list.back();
        }
        assert list.currPos() == 9;
    }
    
    @Test
    public void testEmptyListBack() {
        assert empty.currPos() == 0;
        empty.back();
        assert empty.currPos() == 0;
    }
    
    @Test
    public void testListMoveToPos() {
        assert list.currPos() == 0;
        list.moveToPos(5);
        assert list.currPos() == 5;
        assert list.getValue() == 5;
    }
    
    @Test
    public void testEmptyListMoveToPos() {
        assert empty.moveToPos(0);
        assert !empty.moveToPos(5);
    }
    
    @Test
    public void testListIsAtEnd() {
        assert list.currPos() == 0;
        list.moveToEnd();
        assert list.isAtEnd();
    }
    
    @Test
    public void testEmptyListIsAtEnd() {
        assert empty.currPos() == 0;
        assert list.isAtEnd();
    }
      
    @Test
    public void testListToString() {
        assert list.toString() == "[ 0 1 2 3 4 5 6 7 8 9 ]";
        list.remove();
        assert list.toString() == "[ 1 2 3 4 5 6 7 8 9 ]";
    }
    
    @Test
    public void testEmptyListToString() {
        assert empty.toString() == "[ ]";
    }
    
}