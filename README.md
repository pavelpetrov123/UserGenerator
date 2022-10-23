# Code challenge

Build an android application in java using mvvm architecture, observer concept and fragments to do the following:

1. Have a recycler view that display data from a sql lite db. Table in sql lite contains:
    Id, Name, Date (including hour, minute, second)

2. Have a background process, preferably a service write random data to the db every 1 minute.

3. When data is written to the db, the view refreshes to display the data.

4. The view of data shows all columns and is sortable by all columns.

5. When he clicks on one list item, he navigate to another fragment where he shows the details.
