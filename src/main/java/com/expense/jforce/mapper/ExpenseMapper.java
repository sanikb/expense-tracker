package com.expense.jforce.mapper;

import com.expense.jforce.dto.ExpenseDto;
import com.expense.jforce.enity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public Expense mapExpenseDtoToExpense(ExpenseDto expenseDto){
        Expense expense =new Expense();
        expense.setProductId(expenseDto.getProductId());
        expense.setProductName(expenseDto.getProductName());
        expense.setProductAmount(expenseDto.getProductAmount());
        expense.setProductDescription(expenseDto.getProductDescription());
        expense.setCreatedAt(expenseDto.getCreatedAt());

        return expense;
    }

    public ExpenseDto mapExpenseToExpenseDto(Expense expense){
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setProductId(expense.getProductId());
        expenseDto.setProductName(expense.getProductName());
        expenseDto.setProductAmount(expense.getProductAmount());
        expenseDto.setProductDescription(expense.getProductDescription());
        expenseDto.setCreatedAt(expense.getCreatedAt());

        return  expenseDto;
    }
}

