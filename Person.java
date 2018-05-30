    public class Person {
      protected String name;
      protected char gender;
      protected int age;
      
      public Person() {
        name = null;
        gender = ' ';
        age = 0;
      }
      
      public Person(String n, char g, int a) {
        name = n;
        gender = g;
        age = a;
      }
      
      public void setName (String n) {
        name = n;
      }
      
      public void setGender(char g) {
        gender = g;
      }
      
      public void setAge(int a) {
        age = a;
      }
      
      public char getGender() {
        return gender;
      }
      
      public int getAge() {
        return age;
      }
      
      public String getName() {
        return name;
      }
      
      public boolean old () {
        return (age >= 65);
      }
      
      public boolean equals (Person other) {
        // comparing names
        return (other != null && name.equals(other.name));
      }
      
      public int compareTo (Person other) {
        return age - other.age;
      }
      
      public String toString() {
        return "Name: "+name+"\nGender: "+gender+"\nAge: "+age;
      }
      
    }