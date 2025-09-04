package com.expense.jforce.service;

import com.expense.jforce.dto.ExpenseDto;
import com.expense.jforce.dto.UserDto;
import com.expense.jforce.enity.Expense;
import com.expense.jforce.enity.User;
import com.expense.jforce.mapper.ExpenseMapper;
import com.expense.jforce.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    // save expenses
    public boolean saveExpense(ExpenseDto expenseDto) {
        Expense expense = expenseMapper.mapExpenseDtoToExpense(expenseDto);
        Expense savedUser = expenseRepository.save(expense);
        return savedUser != null;
    }

    //getAllExpenses
    public List<ExpenseDto> getAllExpenses(){
        return expenseRepository.findAll().stream()
                .map(expenseMapper::mapExpenseToExpenseDto)
                .collect(Collectors.toList());
    }

    // get expense by id
    public ExpenseDto getExpenseById(int id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
        return expenseMapper.mapExpenseToExpenseDto(expense);
    }

    // update expense
    public boolean updateExpense(int id, ExpenseDto expenseDto) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        existingExpense.setProductName(expenseDto.getProductName());
        existingExpense.setProductAmount(expenseDto.getProductAmount());
        existingExpense.setProductDescription(expenseDto.getProductDescription());
        existingExpense.setCreatedAt(expenseDto.getCreatedAt());

        expenseRepository.save(existingExpense);
        return true;
    }








}
