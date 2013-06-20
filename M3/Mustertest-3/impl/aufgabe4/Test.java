public class Test {
	public static void main(String[] args) {
		B b = new B();
	}
}

class A {
  public A() {
    System.out.println("Klasse A");
  }
}

class B extends A {
  public B() {
    System.out.println("Klasse B");
  }
}
