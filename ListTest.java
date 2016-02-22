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
        Assert.assertEquals("Check empty list string", empty.toString(), "[ ]");
    }

    @Test
    public void testEmptyList() {
        Assert.assertEquals("List is not empty: got size " + empty.length(), empty.length(), 0);
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
        Assert.assertNull("Remove crashes on empty list",empty.remove());
    }

    @Test
    public void testClearList() {
        Assert.assertEquals("List is not at expected size 10: got size " + list.length(), list.length(), 10);
        list.clear();
        Assert.assertEquals(list.length(), 0);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
    }

    @Test
    public void testClearEmptyList() {
        Assert.assertEquals("List is not empty: got size " + empty.length(), empty.length(), 0);
        empty.clear();
        Assert.assertEquals("List is not empty: got size " + empty.length(), empty.length(), 0);
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
    }

    @Test
    public void testListInsertStart() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        Assert.assertTrue("Insert failure", list.insert(11));
        Assert.assertEquals("Incorrect value at cursor: expected 11, got " + list.getValue(), list.getValue(), (Integer) 11);
        Assert.assertEquals("Expected final list length 11, got " + list.length(), list.length(), 11);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
    }

    @Test
    public void testListInsertMid() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        Assert.assertTrue("Move to position failure", list.moveToPos(5));
        Assert.assertTrue("Insert failure", list.insert(11));
        Assert.assertEquals("Incorrect value at cursor: expected 11, got " + list.getValue(), list.getValue(), (Integer) 11);
        Assert.assertEquals("Expected final list length 11, got " + list.length(), list.length(), 11);
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 5);
    }

    @Test
    public void testListInsertLast() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        Assert.assertTrue("Move to position failure", list.moveToPos(9));
        Assert.assertTrue("Insert failure", list.insert(11));
        Assert.assertEquals("Incorrect value at cursor: expected 11, got " + list.getValue(), list.getValue(), (Integer) 11);
        Assert.assertEquals("Expected final list length 11, got " + list.length(), list.length(), 11);
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 9);
    }

    @Test
    public void testEmptyListInsert() {
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        Assert.assertTrue("Empty insert failure", empty.insert(1));
        Assert.assertEquals("Expected list size 1, got" + empty.length(), empty.length(), 1);
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        Assert.assertEquals("Expected element value 1, got " + empty.getValue(), empty.getValue(), (Integer) 1);
    }
    
    @Test
    public void testListAppend() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        Assert.assertTrue("Insert failure", list.insert(11));
        Assert.assertEquals("Incorrect value at cursor", list.getValue(), (Integer) 11);
        Assert.assertEquals("Expected final list length 11, got " + list.length(), list.length(), 11);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
    }
    
    @Test
    public void testEmptyListAppend() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        Assert.assertTrue("Append failure", list.append(1));
        Assert.assertEquals("Expected list size 1, got " + empty.length(), empty.length(), 1);
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        Assert.assertEquals("Expected value 1, got " + empty.getValue(), empty.getValue(), (Integer) 1);
    }
    
    @Test
    public void testListRemove() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        Assert.assertEquals("Did not remove 0 from list", list.remove(), (Integer) 0);
        Assert.assertEquals("Incorrect value at cursor", list.getValue(), (Integer) 1);
        Assert.assertEquals("Expected list size 9, got " + list.length(), list.length(), 9);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
    }

    @Test
    public void testListRemoveMid() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        Assert.assertTrue("Move to position failure", list.moveToPos(4));
        Assert.assertEquals("Did not remove 4 from list", list.remove(), (Integer) 4);
        Assert.assertEquals("Incorrect value at cursor", list.getValue(), (Integer) 5);
        Assert.assertEquals("Expected list size 9, got " + list.length(), list.length(), 9);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 4);
    }

    @Test
    public void testListRemoveEnd() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        Assert.assertTrue("Move to position failure", list.moveToPos(9));
        Assert.assertEquals("Did not remove 9 from list", list.remove(), (Integer) 0);
        Assert.assertEquals("Incorrect value at cursor", list.getValue(), (Integer) 0);
        Assert.assertEquals("Expected list size 9, got " + list.length(), list.length(), 9);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
    }

    @Test
    public void testEmptyListRemove() {
        Assert.assertNull(empty.remove());
        Assert.assertEquals("List is not empty: got size " + empty.length(), empty.length(), 0);
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
    }
    
    @Test
    public void testListMoveToStart() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.moveToEnd();
        list.moveToStart();
        Assert.assertEquals("Incorrect value at cursor", list.getValue(), (Integer) 0);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
    }
    
    @Test
    public void testEmptyListMoveToStart() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.moveToStart();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
    }

    @Test
    public void testListMoveToEnd() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.moveToEnd();
        Assert.assertEquals("Incorrect value at cursor", list.getValue(), (Integer) 9);
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 9);
    }
    
    @Test
    public void testEmptyListMoveToEnd() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.moveToEnd();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
    }

    @Test
    public void testListNext() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.next();
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 1);
        for (int i = 0; i < 9; i++) {
            list.next();
        }
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 9);
    }
    
    @Test
    public void testEmptyListNext() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.next();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        Assert.assertNull("List is not empty: got size " + empty.length(), empty.getValue());
    }
    @Test
    public void testListPrev() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.moveToEnd();
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 9);
        list.prev();
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 8);
        for (int i = 0; i < 9; i++) {
            list.prev();
        }
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
    }
    
    @Test
    public void testEmptyListPrev() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.prev();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
    }

    @Test
    public void testListForward() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.forward();
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 1);
        for (int i = 0; i < 9; i++) {
            list.forward();
        }
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
    }
    
    @Test
    public void testEmptyListForward() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.forward();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
    }
    @Test
    public void testListBack() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.moveToEnd();
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 9);
        list.back();
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 8);
        for (int i = 0; i < 9; i++) {
            list.back();
        }
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 9);
    }
    
    @Test
    public void testEmptyListBack() {
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        empty.back();
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
    }
    
    @Test
    public void testListMoveToPos() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.moveToPos(5);
        Assert.assertEquals("Cursor at wrong position", list.currPos(), 5);
        Assert.assertEquals("Incorrect value at cursor: expected 5, got " + list.getValue(), list.getValue(), (Integer) 5);
    }
    
    @Test
    public void testEmptyListMoveToPos() {
        Assert.assertTrue(empty.moveToPos(0));
        Assert.assertFalse(empty.moveToPos(5));
    }
    
    @Test
    public void testListIsAtEnd() {
        Assert.assertEquals("Not at expected position 0: got position " + list.currPos(), list.currPos(), 0);
        list.moveToEnd();
        Assert.assertTrue(list.isAtEnd());
    }
    
    @Test
    public void testEmptyListIsAtEnd() {
        Assert.assertEquals("Not at expected position 0 in empty list: got position " + empty.currPos(), empty.currPos(), 0);
        Assert.assertTrue(list.isAtEnd());
    }    
}