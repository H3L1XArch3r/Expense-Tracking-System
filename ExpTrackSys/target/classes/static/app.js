const app = angular.module('ExpenseApp', []);

app.controller('ExpenseController', function ($scope, $http) {
    const apiUrl = '/api/expense'; // Corrected backend API endpoint

    $scope.expenses = [];
    $scope.newExpense = { expense: '', amount: 0 };

    // Fetch all expenses and display them in the table
    $scope.fetchExpenses = function () {
        $http.get(apiUrl)
            .then(function (response) {
                $scope.expenses = response.data; // Populate expenses with data
            })
            .catch(function (error) {
                alert('Failed to fetch expenses. Please try again.');
                console.error('Error fetching expenses:', error);
            });
    };

    // Add a new expense
    $scope.addExpense = function () {
        if ($scope.newExpense.expense && $scope.newExpense.amount > 0) {
            $http.post(apiUrl, $scope.newExpense)
                .then(function (response) {
                    $scope.expenses.push(response.data); // Add new expense to the list
                    $scope.newExpense = { expense: '', amount: 0 }; // Reset the form
                    alert('Expense added successfully.');
                })
                .catch(function (error) {
                    alert('Failed to add expense. Please try again.');
                    console.error('Error adding expense:', error);
                });
        } else {
            alert('Please enter valid expense details.');
        }
    };

    // Delete an expense
    $scope.deleteExpense = function (id) {
        $http.delete(`${apiUrl}/${id}`)
            .then(function () {
                $scope.expenses = $scope.expenses.filter((expense) => expense.id !== id);
                alert('Expense deleted successfully.');
            })
            .catch(function (error) {
                alert('Failed to delete expense. Please try again.');
                console.error('Error deleting expense:', error);
            });
    };
    $scope.addExpense = function () {
        if ($scope.newExpense.expense && $scope.newExpense.amount > 0) {
            $http.post(apiUrl, $scope.newExpense)
                .then(function (response) {
                    $scope.newExpense = { expense: '', amount: 0 }; // Reset the form
                    alert('Expense added successfully.');
                    $scope.fetchExpenses(); // Fetch the updated list
                })
                .catch(function (error) {
                    alert('Failed to add expense. Please try again.');
                    console.error('Error adding expense:', error);
                });
        } else {
            alert('Please enter valid expense details.');
        }
    };

    // Edit an expense
    $scope.editExpense = function (expense) {
        const updatedExpense = prompt('Enter new expense description:', expense.expense);
        const updatedAmount = prompt('Enter new amount:', expense.amount);

        if (updatedExpense && updatedAmount && !isNaN(updatedAmount)) {
            const updatedExpenseObj = { expense: updatedExpense, amount: parseInt(updatedAmount, 10) };
            $http.put(`${apiUrl}/${expense.id}`, updatedExpenseObj)
                .then(function (response) {
                    // Update the expense in the list
                    const index = $scope.expenses.findIndex((exp) => exp.id === expense.id);
                    if (index !== -1) {
                        $scope.expenses[index] = response.data;
                    }
                    alert('Expense updated successfully.');
                })
                .catch(function (error) {
                    alert('Failed to update expense. Please try again.');
                    console.error('Error updating expense:', error);
                });
        } else {
            alert('Invalid inputs for update.');
        }
    };

    // Initial fetch of expenses
    $scope.fetchExpenses();
});
