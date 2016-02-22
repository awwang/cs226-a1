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
        Assert.assertEquals("Check list string containing ints 0-9","[ 0 1 2 3 4 5 6 7 8 9 ]", list.toString());
    }

    @Test
    public void testEmptyListString() {
        Assert.assertEquals("Check empty list string", "[ ]", empty.toString());
    }

    @Test
    public void testEmptyList() {
        Assert.assertEquals("List is not empty: got size " + empty.length(), 0, empty.length());
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + 0, empty.currPos(), empty.currPos());
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
        Assert.assertNull("Remove crashes on empty list",empty.remove());
    }

    @Test
    public void testClearList() {
        Assert.assertEquals("List is not at expected size 10: got size " + list.length(), list.length(), 10);
        list.clear();
        Assert.assertEquals(list.length(), 0);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
    }

    @Test
    public void testClearEmptyList() {
        Assert.assertEquals("List is not empty: got size " + empty.length(), 0, empty.length());
        empty.clear();
        Assert.assertEquals("List is not empty: got size " + empty.length(), 0, empty.length());
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
    }

    @Test
    public void testListInsertStart() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        Assert.assertTrue("Insert failure", list.insert(11));
        Assert.assertEquals("Incorrect value at cursor: expected 11, got " + list.getValue(), (Integer) 11, list.getValue());
        Assert.assertEquals("Expected final list length 11, got " + list.length(), 11, list.length());
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
    }

    @Test
    public void testListInsertMid() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        Assert.assertTrue("Move to position failure", list.moveToPos(5));
        Assert.assertTrue("Insert failure", list.insert(11));
        Assert.assertEquals("Incorrect value at cursor: expected 11, got " + list.getValue(), (Integer) 11, list.getValue());
        Assert.assertEquals("Expected final list length 11, got " + list.length(), 11, list.length());
        Assert.assertEquals("Cursor at wrong position", 5, list.currPos());
    }

    @Test
    public void testListInsertLast() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        Assert.assertTrue("Move to position failure", list.moveToPos(9));
        Assert.assertTrue("Insert failure", list.insert(11));
        Assert.assertEquals("Incorrect value at cursor: expected 11, got " + list.getValue(), (Integer) 11, list.getValue());
        Assert.assertEquals("Expected final list length 11, got " + list.length(), 11, list.length());
        Assert.assertEquals("Cursor at wrong position", 9, list.currPos());
    }

    @Test
    public void testEmptyListInsert() {
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
        Assert.assertTrue("Empty insert failure", empty.insert(1));
        Assert.assertEquals("Expected list size 1, got" + empty.length(), 1, empty.length());
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
        Assert.assertEquals("Expected element value 1, got " + empty.getValue(), (Integer) 1, empty.getValue());
    }
    
    @Test
    public void testListAppend() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        Assert.assertTrue("Insert failure", list.insert(11));
        Assert.assertEquals("Incorrect value at cursor", (Integer) 11, list.getValue());
        Assert.assertEquals("Expected final list length 11, got " + list.length(), 11, list.length());
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
    }
    
    @Test
    public void testEmptyListAppend() {
        Assert.assertEquals("Not at expected position 0: got position " + empty.currPos(), 0, empty.currPos());
        Assert.assertTrue("Append failure", empty.append(1));
        Assert.assertEquals("Expected list size 1, got " + empty.length(), 1, empty.length());
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
        Assert.assertEquals("Expected value 1, got " + empty.getValue(), (Integer) 1, empty.getValue());
    }
    
    @Test
    public void testListRemove() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        Assert.assertEquals("Did not remove 0 from list", (Integer) 0, list.remove());
        Assert.assertEquals("Incorrect value at cursor", (Integer) 1, list.getValue());
        Assert.assertEquals("Expected list size 9, got " + list.length(), 9, list.length());
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
    }

    @Test
    public void testListRemoveMid() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        Assert.assertTrue("Move to position failure", list.moveToPos(4));
        Assert.assertEquals("Did not remove 4 from list", (Integer) 4, list.remove());
        Assert.assertEquals("Incorrect value at cursor", (Integer) 5, list.getValue());
        Assert.assertEquals("Expected list size 9, got " + list.length(), 9, list.length());
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 4, list.currPos(), 4);
    }

    @Test
    public void testListRemoveEnd() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        Assert.assertTrue("Move to position failure", list.moveToPos(9));
        Assert.assertEquals("Did not remove 9 from list", (Integer) 9, list.remove());
        Assert.assertEquals("Incorrect value at cursor", (Integer) 0, list.getValue());
        Assert.assertEquals("Expected list size 9, got " + list.length(), 9, list.length());
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
    }

    @Test
    public void testEmptyListRemove() {
        Assert.assertNull(empty.remove());
        Assert.assertEquals("List is not empty: got size " + empty.length(), empty.length(), 0);
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
    }
    
    @Test
    public void testListMoveToStart() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        list.moveToEnd();
        list.moveToStart();
        Assert.assertEquals("Incorrect value at cursor", list.getValue(), (Integer) 0);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
    }
    
    @Test
    public void testEmptyListMoveToStart() {
        Assert.assertEquals("Not at expected position 0: got position " + empty.currPos(), 0, empty.currPos());
        list.moveToStart();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
    }

    @Test
    public void testListMoveToEnd() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        list.moveToEnd();
        Assert.assertEquals("Incorrect value at cursor", list.getValue(), (Integer) 9);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 9, list.currPos());
    }
    
    @Test
    public void testEmptyListMoveToEnd() {
        Assert.assertEquals("Not at expected position 0: got position " + empty.currPos(), 0, empty.currPos());
        list.moveToEnd();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
    }

    @Test
    public void testListNext() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        list.next();
        Assert.assertEquals("Cursor at wrong position", 1, list.currPos());
        for (int i = 0; i < 9; i++) {
            list.next();
        }
        Assert.assertEquals("Cursor at wrong position", 9, list.currPos());
    }
    
    @Test
    public void testEmptyListNext() {
        Assert.assertEquals("Not at expected position 0: got position " + empty.currPos(), 0, empty.currPos());
        empty.next();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
    }
    @Test
    public void testListPrev() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        list.moveToEnd();
        Assert.assertEquals("Cursor at wrong position", 9, list.currPos());
        list.prev();
        Assert.assertEquals("Cursor at wrong position", 8, list.currPos());
        for (int i = 0; i < 9; i++) {
            list.prev();
        }
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
    }
    
    @Test
    public void testEmptyListPrev() {
        Assert.assertEquals("Not at expected position 0: got position " + empty.currPos(), 0, empty.currPos());
        empty.prev();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
    }

    @Test
    public void testListForward() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        list.forward();
        Assert.assertEquals("Cursor at wrong position", 1, list.currPos());
        for (int i = 0; i < 9; i++) {
            list.forward();
        }
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
    }
    
    @Test
    public void testEmptyListForward() {
        Assert.assertEquals("Not at expected position 0: got position " + empty.currPos(), 0, empty.currPos());
        empty.forward();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
    }
    @Test
    public void testListBack() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        list.moveToEnd();
        Assert.assertEquals("Cursor at wrong position", 9, list.currPos());
        list.back();
        Assert.assertEquals("Cursor at wrong position", 8, list.currPos());
        for (int i = 0; i < 9; i++) {
            list.back();
        }
        Assert.assertEquals("Cursor at wrong position", 9, list.currPos());
    }
    
    @Test
    public void testEmptyListBack() {
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
        empty.back();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
    }
    
    @Test
    public void testListMoveToPos() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        list.moveToPos(5);
        Assert.assertEquals("Cursor at wrong position", 5, list.currPos());
        Assert.assertEquals("Incorrect value at cursor: expected 5, got " + list.getValue(), (Integer) 5, list.getValue());
    }
    
    @Test
    public void testEmptyListMoveToPos() {
        Assert.assertTrue(empty.moveToPos(0));
        Assert.assertFalse(empty.moveToPos(5));
    }
    
    @Test
    public void testListIsAtEnd() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), 0, list.currPos());
        list.moveToEnd();
        Assert.assertTrue(list.isAtEnd());
    }
    
    @Test
    public void testEmptyListIsAtEnd() {
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), 0, empty.currPos());
        Assert.assertTrue(empty.isAtEnd());
    }    
}