package io.github.iexpense;

import java.util.HashMap;
import java.util.Map;

// This class implements parsing functions to convert a given
// string into expense object.
public class ExpenseLanguage {
    HashMap<Integer, String> tag_map;
    int max_tag;

    public ExpenseLanguage(HashMap<Integer, String> tag_map, int max_tag) {
        this.tag_map = tag_map;
        this.max_tag = max_tag;
    }

    // Possible formats:
    //  - 100 #cash #electricity
    //  - transfer 100 #sbi #atm
    //  - income 100 #sbi
    public Expense parseExpense(String expense_input) throws ParseExpenseException {
        String[] tokens = expense_input.trim().split("\\s+");
        if (tokens.length <= 0 || tokens[0].equals("")) {
            throw new ParseExpenseException("empty expense input");
        }

        Expense expense = new Expense();
        int index = 0;

        String cmd = tokens[index];
        if (cmd.equals("transfer")) {
            expense.setExpenseType(Expense.TYPE_TRANSFER);
            index += 1;
        } else if (cmd.equals("income")) {
            expense.setExpenseType(Expense.TYPE_INCOME);
            index += 1;
        }

        if (tokens.length < (index + 2)) {
            throw new ParseExpenseException("amount and at least 1 tag are required");
        }

        String amount = tokens[index];
        try {
            expense.setAmount(amount);
        } catch (Exception e) {
            throw new ParseExpenseException(e);
        }
        index += 1;

        // Now we have tags left to process
        for (int i = index; i < tokens.length; i++) {
            String tag = tokens[i];
            for (Map.Entry<Integer, String> entry : tag_map.entrySet()) {
                if (tag.equals(entry.getValue()) || tag.equals(entry.getValue())) {
                    expense.addTag(entry.getKey());
                    break;
                }
            }

            this.tag_map.put(max_tag, tag);
            expense.addTag(max_tag);
            max_tag += 1;
        }

        return expense;
    }
}
