# This program demonstrates polymorphism.

import animals

def main():
    # Create a Mammal object, a Dog object, and
    # a Cat object.
    mammal = animals.Mammal('regular animal')
    dog = animals.Dog()
    cat = animals.Cat()
    mammal2 = animals.Mammal('regular animal')
    animals1 = [mammal, dog, cat, mammal2]

    #
    # Display information about each one.
    print('Here are some animals and')
    print('the sounds they make.')
    print('--------------------------')

    for animal in animals1:
        show_mammal_info(animal)
        if(animal == mammal2):
            print('Same as ')
            animal.show_species()

# The show_mammal_info function accepts an object
# as an argument, and calls its show_species
# and make_sound methods.

def show_mammal_info(creature):
    creature.show_species()
    creature.make_sound()
    print()

# Call the main function.
if __name__ == '__main__':
      main()