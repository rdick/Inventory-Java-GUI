# Inventory Tracking Application

## What does this application do?

This application allows companies to track their current inventory.
The main functions of it are:
1. Add inventory items
2. Reduce item quantity
3. Add item quantity
4. Review a list of current inventory items and all of their information
5. Find total Cost of Inventory
6. Save inventory data
7. Load previous inventory data

## Who will use this application?

This application be used by *two groups* of people:
- **Material Buyers** - To see if the inventory is low and
  purchase additional stock 
- **Material Receivers** - To add to new inventory to stock quantity
 - **Material Users** - To use the material and reduce the inventory 
                        quantity
 


## Why create this projects?

One of the ***big problems*** at my company is 
 we are not tracking our inventory accurately. If this system is 
developed there will be more transparency on what items are 
currently in stock.

##User Story
Below are the following user stories

1. As a user, I want to be able to add an item (name, quantity
   , details) to my inventory  

2. As a user, I want to be able to increase the quantity of an 
item in my inventory

3. As a user, I want to be able to reduce the quantity of an 
item in my inventory

4. As a user, I want to be able to view a list of the items 
   and their corresponding id, quantity, cost per unit and details in my inventory
   
5. As a user, I want to be able to view the total value of all the 
   items in the inventory

6. As a user, I want to be able to save inventory data to a json file
   
7. As a user, I want to be able to load previous inventory data from a json file

## Phase 4: Task 2
**Task Completed:** Create A Robust Class
**Class Chosen:** Item
**Methods Affected:** Constructor and changeItemQuantity
**Effect:** If a user creates a new item with a negative quantity or cost per unit it will throw an error.
            If a user changes the quantity of an existing item to make the quantity of the item negative an error will 
be thrown.
    
## Phase 4: Task 3
During this project I tried to make my classes as simple as possible. An example of this is how I had one main class
that had my UI and I had 1 class for every tab of my UI.

If I were to change this Application I could (based on my UML Diagram):   

1. Create another object that would replace Inventory for some classes. This would result in lower
coupling. I noticed that I am using Inventory Object in almost every class.
   
2. Separate some of my function into additional classes. I found that to complete the project as quickly as possible I
   used the same functions in multiple methods. If I were to continue this GUI further I would do a deep dive into all
   of my code and extract common functions from the application. This would increase the cohesion of the application.
