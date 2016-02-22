import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import junit.framework.*;

public class ListTest {
    CList<Integer> empty;
    static CList<Integer> list;

    @Before
    public static void init() {
        this.list = new CList();
        for (int i = 0; i < 10; ++i) {
            this.list.append(i);
        }
        this.empty = new CList();
    }

    @Test
    public void testString() {
        System.out.println("\nstarting tests, using toString to test init");
        Assert.assertEquals("[ 0 1 2 3 4 5 6 7 8 9 ]", list.toString());
    }

    @Test
    public void testEmptyList() {
        assert this.empty.length() == 0;
        assert this.empty.currPos() == 0;
        assert this.empty.getValue() == null;
        assert this.empty.remove() == null;
    }

    @Test
    public void testClearList() {
        assert list.length() == 10;
        this.list.clear();
        assert this.list.length() == 0;
        assert this.list.currPos() == 0;
    }

    @Test
    public void testClearEmptyList() {
        assert empty.length() == 0;
        this.empty.clear();
        assert this.empty.length() == 0;
        assert this.empty.currPos() == 0;
    }

    @Test
    public void testListInsert() {
        assert this.list.currPos() == 0;
        assert this.list.insert(11);
        assert this.list.getValue() == 11;
        assert this.list.length() == 11;
        assert this.list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListInsert() {
        assert this.empty.currPos() == 0;
        assert this.empty.insert(1);
        assert this.empty.length() == 1;
        assert this.empty.currPos() == 0;
        assert this.empty.getValue() == 1;
    }
    
    @Test
    public void testListAppend() {
        assert this.list.currPos() == 0;
        assert this.list.insert(11);
        assert this.list.getValue() == 0;
        assert this.list.length() == 11;
        assert this.list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListAppend() {
        assert this.list.currPos() == 0;
        assert this.list.append(1);
        assert this.empty.length() == 1;
        assert this.empty.currPos() == 0;
        assert this.empty.getValue() == 1;
    }
    
    @Test
    public void testListRemove() {
        assert this.list.currPos() == 0;
        assert this.list.remove();
        assert this.list.getValue() == 1;
        assert this.list.length() == 9;
        assert this.list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListRemove() {
        assert this.list.remove(1) == null;
        assert this.empty.length() == 0;
        assert this.empty.currPos() == 0;
        assert this.empty.getValue() == null;
    }
    
    @Test
    public void testListMoveToStart() {
        assert this.list.currPos() == 0;
        this.list.moveToEnd();
        this.list.moveToStart();
        assert this.list.getValue() == 0;
        assert this.list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListMoveToStart() {
        assert this.list.currPos() == 0;
        assert this.list.moveToStart();
        assert this.empty.currPos() == 0;
        assert this.empty.getValue() == null;
    }

    @Test
    public void testListMoveToEnd() {
        assert this.list.currPos() == 0;
        this.list.moveToEnd();
        assert this.list.getValue() == 9;
        assert this.list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListMoveToEnd() {
        assert this.list.currPos() == 0;
        this.list.moveToEnd();
        assert this.empty.currPos() == 0;
        assert this.empty.getValue() == null;
    }

    @Test
    public void testListNext() {
        assert this.list.currPos() == 0;
        this.list.next();
        assert this.list.currPos() == 1;
        for (int i = 0; i < 9; i++) {
            this.list.next();
        }
        assert this.list.currPos() == 9;
    }
    
    @Test
    public void testEmptyListNext() {
        assert this.list.currPos() == 0;
        this.list.next();
        assert this.empty.currPos() == 0;
        assert this.empty.getValue() == null;
    }
    @Test
    public void testListPrev() {
        assert this.list.currPos() == 0;
        this.list.moveToEnd();
        assert this.list.currPos() == 9;
        this.list.prev();
        assert this.list.currPos() == 8;
        for (int i = 0; i < 9; i++) {
            this.list.prev();
        }
        assert this.list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListPrev() {
        assert this.list.currPos() == 0;
        this.list.prev();
        assert this.empty.currPos() == 0;
    }

    @Test
    public void testListForward() {
        assert this.list.currPos() == 0;
        this.list.forward();
        assert this.list.currPos() == 1;
        for (int i = 0; i < 9; i++) {
            this.list.forward();
        }
        assert this.list.currPos() == 0;
    }
    
    @Test
    public void testEmptyListForward() {
        assert this.list.currPos() == 0;
        this.list.forward();
        assert this.empty.currPos() == 0;
    }
    @Test
    public void testListBack() {
        assert this.list.currPos() == 0;
        this.list.moveToEnd();
        assert this.list.currPos() == 9;
        this.list.back();
        assert this.list.currPos() == 8;
        for (int i = 0; i < 9; i++) {
            this.list.back();
        }
        assert this.list.currPos() == 9;
    }
    
    @Test
    public void testEmptyListBack() {
        assert this.empty.currPos() == 0;
        this.empty.back();
        assert this.empty.currPos() == 0;
    }
    
    @Test
    public void testListMoveToPos() {
        assert this.list.currPos() == 0;
        this.list.moveToPos(5);
        assert this.list.currPos() == 5;
        assert this.list.getValue() == 5;
    }
    
    @Test
    public void testEmptyListMoveToPos() {
        assert this.empty.moveToPos(0);
        assert !this.empty.moveToPos(5);
    }
    
    @Test
    public void testListIsAtEnd() {
        assert this.list.currPos() == 0;
        this.list.moveToEnd();
        assert this.list.isAtEnd();
    }
    
    @Test
    public void testEmptyListIsAtEnd() {
        assert this.empty.currPos() == 0;
        assert this.list.isAtEnd();
    }
      
    @Test
    public void testListToString() {
        assert this.list.toString() == "[ 0 1 2 3 4 5 6 7 8 9 ]";
        this.list.remove();
        assert this.list.toString() == "[ 1 2 3 4 5 6 7 8 9 ]";
    }
    
    @Test
    public void testEmptyListToString() {
        assert this.empty.toString() == "[ ]";
    }
    
}