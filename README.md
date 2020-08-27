# ExpenseTracker (MVVM Architecture)

### About App:

At the end of the week or month, have you ever wondered where all your savings went? Managing your funds sometimes is nightmare. Do you do it properly? Do you know that using the right mobile app can help you adopt a healthier budget? The android app expense tracker has been specifically designed to answer all such questions and give you a better overview and better control over your budgeting, spending, and tracking. Expense tracker helps you manage and track your daily expenditures in general. 

## Output:

![1](https://user-images.githubusercontent.com/30715919/91386113-ee855f80-e84f-11ea-8aa8-d11bfb47821a.png)
![2](https://user-images.githubusercontent.com/30715919/91386114-efb68c80-e84f-11ea-88da-fef7d55baf51.png)
![3](https://user-images.githubusercontent.com/30715919/91386110-ecbb9c00-e84f-11ea-8b67-4336298f8a3b.png)

### Architecture Used:

## MVVM (Model View-View Model)

MVVM separates your view (i.e. Activitys and Fragments) from your business logic. MVVM is enough for small projects, but when your codebase becomes huge, your ViewModels start bloating. Separating responsibilities becomes hard.

MVVM with Clean Architecture is pretty good in such cases. It goes one step further in separating the responsibilities of your code base. It clearly abstracts the logic of the actions that can be performed in your app.

The main players in the MVVM pattern are:

The View — that informs the ViewModel about the user’s actions

The ViewModel — exposes streams of data relevant to the View

The DataModel — abstracts the data source. The ViewModel works with the DataModel to get and save the data.

If you find this project intresting give a ⭐
