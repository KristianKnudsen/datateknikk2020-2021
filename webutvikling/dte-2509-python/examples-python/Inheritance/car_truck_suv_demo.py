# This program creates a Car object, a Truck object,
# and an SUV object.

import vehicles

def main():
    # Create empty list for vehicles
    vehicles_list = []
    # Create a Car object for a used 2001 BMW
    # with 70,000 miles, priced at $15,000, with
    # 4 doors.
    car = vehicles.Car('BMW', 2001, 70000, 15000.0, 4)
    vehicles_list.append(car)
    # Create a Truck object for a used 2002
    # Toyota pickup with 40,000 miles, priced
    # at $12,000, with 4-wheel drive.
    truck = vehicles.Truck('Toyota', 2002, 40000, 12000.0, '4WD')
    vehicles_list.append(truck)
    # Create an SUV object for a used 2000
    # Volvo with 30,000 miles, priced
    # at $18,500, with 5 passenger capacity.
    suv = vehicles.SUV('Volvo', 2000, 30000, 18500.0, 5)
    vehicles_list.append(suv)
    print('USED CAR INVENTORY')
    print('===================')
    
    # Display the vehicles's data.
    print('The following cars are in inventory:')
    for item in vehicles_list:
        print(item)
        print()
    
# Call the main function.
if __name__ == '__main__':
      main()