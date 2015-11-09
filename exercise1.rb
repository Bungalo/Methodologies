class Customer
	
	def initialize(fname, lname)
		@firstName = fname
		@lastName = lname
	end
end
class Shop
	@@orders = Array.new
	@@customers = Array.new
	def register_order(customer, sum)
		order = Order.new(customer, sum)
		@@orders << order
		if  !@@customers.include?(customer)
			@@customers << customer
		end
	end
	def orders_for (customer)
		sum = 0
		@@orders.each do |x|
			if x.returnCustomer == customer
				sum += x.returnSum
			end
		end
		puts
		puts customer.inspect
		puts "Sum of orders: #{sum}"
	end
	def orders_summary ()
	
		customerOrders = Array.new
		@@customers.each do |x|
			@@orders.each do |z|
				if x == z.returnCustomer
					customerOrders<<z.returnSum
				end
			end
			puts
			puts x.inspect
			puts "Orders of customer: #{customerOrders}"
			customerOrders.clear
		end
		@@customers.each
		totalSum = 0
		@@orders.each do |x|
			totalSum += x.returnSum
		end
		puts
		puts "Total sum of all customers: #{totalSum}"
	end
	
end
class Order
	def initialize(customer, sum)
		@customer = customer
		@sum = sum
	end
	def returnCustomer
		@customer
	end
	def returnSum()
		@sum
	end
end

shop = Shop.new
	
customer1 = Customer.new('John','Doe')
shop.register_order(customer1, 1000)
shop.register_order(customer1, 1200)
	
customer2 = Customer.new('Jane','Doe')
shop.register_order(customer2, 300)
	
shop.orders_for(customer1)
shop.orders_for(customer2)
shop.orders_summary