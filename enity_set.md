1. Customers
Stores information about people who purchase from the shop.
======================================================
Column Name | Data Type | Description
customer_id | INT (PK) | Unique ID for the customer
name | VARCHAR | Full name
email | VARCHAR | Unique email address
phone | VARCHAR | Contact number
address | TEXT | Delivery address
created_at | DATETIME | Account creation date
======================================================
2. Products (Phones)
Holds data about phones being sold.
======================================================
Column Name	Data Type	Description
product_id	INT (PK)	Unique ID
name	VARCHAR	Phone model name
brand	VARCHAR	Brand (e.g., Apple, Samsung)
specs	TEXT	Technical specifications
price	DECIMAL	Selling price
stock_quantity	INT	Number in stock
image_url	VARCHAR	Link to product image
created_at	DATETIME	Added to catalog
======================================================