> **Activity 1 Due (20%): 15-10-2018 (Wk 7 Mon) 23:59**
> Submission details will be provided on Canvas
>
> **Activity 2 Due (30%): 19-11-2018 (Wk 12 Mon) 23:59**
> Submission details will be provided at Canvas
### Basic Tasks (20 points or 8% of course total.)

![](doc-img/summary.png)

1. Fill in the tab `Summary`  
	1. Correctly filled the tab. [#1](https://github.com/khwang0/2018F-COMP3111/issues/1)  [5]
	1. Refresh contents on another search. [5]
	1. Put "-" to Average selling price, lowest selling price and latest post for result not found. [3]
	1. Put a clickable URL in the field Lowest Selling Price and Latest Post.[3]
		1. Pop up a windows/browser showing that item.[4]
1. Be able to scrape data from a single webpage of a local/international selling/reselling portals (e.g. [carousell](https://hk.carousell.com), [dcfever](https://www.dcfever.com/trading/search.php), [preloveled](https://preloved.co.uk), [taobao](https://www.taobao.com) or any similar webpage. Please noted that we only accept websites written in English or Chinese) Note, there is no requirement to handle multiple pages data. 
	1. 	Print the scraped data in the console tab [6]
	1.	Modify the class Item so that it will also record which portal this item is coming from. [5]
	1.	The returned result of the function WebScraper.scrape contains the merged data from two portals. [5]
		1. The returned result is sorted by price. If two items have the same price, item sold on Craiglist go first. If two items from the same portal has the same price, they can be sorted in any order.[4]   
1. Handle pagination 
	1. scrape data from more than one pages if the search contains more than one pages of results.[12]
	1. Correctly determine the last page of the search and scrape data from all available pages.[4] 
	1. Write to the `Console` tab and show how many pages has already been scraped during the process. So that the user would aware the program is still running. (Note: asychronized I/O is not required.) [4]
1. Fill in the tab `Table` with all result of the current search.
	1. Correctly filled the result. [4]
	1. Sort the result in ascending order on user clicking each column, and sort in descending order when user click again. [4]
	1. Make all cells in the table not editable. [4]
	1. Pop up a new windows/browser showing the item when the `URL` is clicked. [4]
	1. Refresh the table on another search.  [4]
1. Refine search.
	1. Create an extra button that shows `Refine` just below the `Go` button with at least 5px of space. Make this button right align the `Go` button.[3]  
	1. when the refine button is clicked, filter the searched data and keep those items with their titles containing the keywords typed in the text area.[5]  
		1. Update all tabs on the right. (Note: the correctness of the info of the tabs may depend on other features. This requirement needs only to trigger the update process.) [4]
		1. Make the button `Refine` disabled (grey) before any keyword is being searched, or after `Refine` is clicked once.[4]
			1. Make the button `Refine` enabled after a new search ( e.g. `Go` button is clicked).[4]
1. Finish the rest of the menu bar except `Save` and `Open`
	1. Make `About your Team` showing a new simple dialog that shows all your team members name, itsc account, and github account.[3]
	1. Make `Quit` button exiting the program and close all connections.[3]
	1. `Close` will clear the current search record and initialize all tabs on the right to their initial state.[5]
	1. `New (call Controller.actionNew())` should be renamed to `Last Search` and revert your search result to the previous search.[5] 
		1. Before any keyword is being searched, or after `Last Search` is clicked once, it should be disabled (gray and unable to be clicked).[2]
			1. `Last Search` will be enabled after a new search ((`Go`) button is clicked).[2]


### Advanced Tasks (Each H course student is required to implement one of these tasks. 20 points or 8% of course total.)

1. Complete the Distribution tab.
	1. Fill the price histogram with 10 equal width bins. The range of the bins should be determined by the price range of the search product. The first bin should contain the lowest price while the last bin should contain highest price. [2.5] 
	1. The histogram has proper X-axis label, Y-axis label, legend which says `The selling prize of SEARCH_KEYWORD`.[2.5]
	1. The histogram is able to refresh (including the axis) on another search.[2.5]
	1. Leave the histogram empty if not result is found.[2.5]
	1. If there is only one price of the histogram, just have one bar in the chart.[2.5]
	1. Create an event to handle double-click on a particular bar of the histogram.[2.5]
		1. The console screen will be cleared and display those items with the price falling in this bar (in the default format).[2.5]
		1. The bar being double clicked will change its color and the rest of the bars should restore its color to default. [2.5]
1. Save and load your search - A search record can be saved into a file (no requirement on the file format). When the program restarts and the saved file is loaded, it will be able to reconstruct the same List of search result `List<Item>`. 
	1. Implement the save function using the button `save`. 
		1. When open the file using a text editor, it will able to see data are correctly stored. [2.5]
		1. Open a dialog box (any type of dialog box) that allow you to type your filename with a pre-defined file extension. (Name any file extension you want)[2.5]
		1. When an existed file is chosen for save, dialog box is prompted to warn the user he is about to overwrite a file.[2.5]
	1. Implement the load function using the button  `open`
		1. Able to correctly load the data. [2.5]
		1. the content of the items and the order of the item will be preserved.[2.5]
		1. Open a FileChooser `javafx.stage.FileChooser` that allows user to pick the file.[2.5]
		1. The FileChooser has a file extension filter. (e.g. display only file ended with .dat if .dat is your pre-defined file extension)[2.5]
		1. After the data is load, the console will be cleared. The sentence `--Data Loading from FILE_NAME--` will be written to the console followed by the data loaded from the file in correct order.[2.5]
1. Finish the `Trend` tab so that it shows the average price of a particular search of products changing over last 7 days. 
	1. The chart should be a line chart that contains 7 points. Each point denotes the average price of the products of the searched keyword those are posted on that day.[2.5]    
		1. When no data is available on a day the chart will draw one less point. [2.5]
	1.  Update the Search Record combo box that displays the keyword of the last five searched keywords.[2.5] 
		1. On select any of the search record and update the histogram instantly. [2.5]
	1. The chart has proper X-axis label (Date in mm/dd/yyyy format), Y-axis label, legend which says `The average selling prize of the SEARCH_KEYWORD`.[2.5]
	1. Create an event to handle double-click on a particular point of the chart.[2.5]
		1. The console screen will be cleared and display those items posted on that day.[2.5]
		1. The point being double-click will change its color and the rest of the points should restore its color to default.[2.5]
		 	
	
---

# Technical Requirement

1. The program must use Java 8.
1. The project must use Gradle to manage.
1. The program must use JavaFX as its only GUI framework. No Swing or AWT should be allowed.
1. You may choose your own IDE. We recommend Eclipse IDE.
1. JUnit 4.12 as your testing suite
1. Jacoco as your test coverage measurement
1. A Private GitHub repository for source control
	1.	students can apply for [GitHub Education](https://education.github.com/) to enjoy the benefits of creating unlimited private repositories at GitHub
	1.	In your private repository setting page, add your team members and all our TAs as collaborators.

---

# Activities to submit

## Activity 1 - System Requirement Specification (15%)

Based on the above project requirements, prepare and submit
1. A UML class diagram (6%) 
1. A use-case diagram (6%)
1. The workload distribution among your team (2%)
1. A github link properly setup (visible to TA) (1%)

## Activity 2 - Software Implementation and Testing (25%)
1.	Team work
	1. A running program and the source code. (8% for each completed task, total 16% for a student)
	1. A README file stating the name of team members and their tasks assigned.
1.	Individual work - Even if you are failed to implement all tasks, the unit testing and coverage report are still required.
	1. Unit testing of your implemented tasks. (100% pass - 2%; 0% otherwise)  
	1. Coverage test (>70% branch coverage- 2% ; 50-70% branch coverage - 1%; 0% otherwise)
	1. Git commit log at GitHub
		1. 3 "non-trivial" commits (1%)
		1. At least one "non-trivial" pull request (1%)
	1. Documenting your implemented features using JavaDoc (Full mark 1%: -0.25% for each missing argument, return value, exception, class description)
		1. If a function is responsible by more than one student, all of them will receive the penalty.
1. At least 3 meeting minutes. (1%) (submitted at the end of this course)
1. A Gantt chart (1%)

