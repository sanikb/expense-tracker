package com.expense.jforce.controller;

import com.expense.jforce.dto.ExpenseDto;
import com.expense.jforce.enity.Expense;
import com.expense.jforce.mapper.ExpenseMapper;
import com.expense.jforce.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    // show add form
    @GetMapping("/add-expensesform")
    public String showAddExpenseForm(Model model) {
        model.addAttribute("expenseDto", new ExpenseDto());
        return "add-expensesform";
    }

    // save expense
    @PostMapping("/add")
    public String saveExpense(@Valid @ModelAttribute("expenseDto") ExpenseDto expenseDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-expensesform";
        }

        boolean savedExpenses = expenseService.saveExpense(expenseDto);

        if (savedExpenses) {
            redirectAttributes.addFlashAttribute("successMsg", "Expense has been saved");
            return "redirect:/expenses";
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong!!");
            return "redirect:/add-expensesform";
        }
    }

    // show all expenses
    @GetMapping("/expenses")
    public String listExpenses(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        return "list-expenses";
    }


    // show update form
    @GetMapping("/editExpense/{id}")
    public String showEditExpenseForm(@PathVariable("id") int id, Model model) {
        ExpenseDto expenseDto = expenseService.getExpenseById(id);
        model.addAttribute("expenseDto", expenseDto);
        return "update-expense";
    }

    // update expense
    @PostMapping("/updateExpense/{id}")
    public String updateExpense(@PathVariable("id") int id,
                                @Valid @ModelAttribute("expenseDto") ExpenseDto expenseDto,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "update-expense";
        }
        expenseService.updateExpense(id, expenseDto);
        redirectAttributes.addFlashAttribute("successMsg", "Expense updated successfully!");
        return "redirect:/expenses";
    }




}
