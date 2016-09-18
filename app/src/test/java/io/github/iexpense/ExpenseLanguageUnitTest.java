package io.github.iexpense;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ExpenseLanguageUnitTest {
    private ExpenseLanguage el;

    @Before
    public void setUp() {
        HashMap<Integer, String> tag_map = new HashMap<>();
        el = new ExpenseLanguage(tag_map);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEmptyExpense() {
        try {
            el.parseExpense("");
            fail();
        } catch (ParseExpenseException e) {
            assertEquals(e.getMessage(), "empty expense input");
        }
    }

    @Test
    public void testBaseExpense() throws ParseExpenseException {
        Expense e = new Expense();
        e.addTag(0);
        e.addTag(1);
        e.setAmount("100");
        assertTrue(e.valueEquals(el.parseExpense("100 #sbi #food")));
    }

    @Test
    public void testTransferExpense() throws ParseExpenseException {
        Expense e = new Expense();
        e.addTag(0);
        e.addTag(1);
        e.setAmount("13.46");
        e.setExpenseType(Expense.TYPE_TRANSFER);
        assertTrue(e.valueEquals(el.parseExpense("transfer 13.456 #sbi #food")));
    }

    @Test
    public void testIncomeExpense() throws ParseExpenseException {
        Expense e = new Expense();
        e.addTag(0);
        e.setAmount("100");
        e.setExpenseType(Expense.TYPE_INCOME);
        assertTrue(e.valueEquals(el.parseExpense("income 100 #cash")));
    }

    @Test
    public void testExpenseOne() {
        try {
            el.parseExpense("income #cash 100");
            fail();
        } catch (ParseExpenseException e) {
            assertEquals(e.getCause().getClass(), NumberFormatException.class);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testExpenseTwo() {
        try {
            el.parseExpense("income 5.45");
            fail();
        } catch (ParseExpenseException e) {
            assertEquals(e.getMessage(), "amount and at least 1 tag are required");
        }
    }
}
