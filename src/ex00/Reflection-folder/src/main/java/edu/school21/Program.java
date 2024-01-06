package edu.school21;
import java.lang.Package;

public class Program {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Class catClass = Cat.class;
        Class dogClass = dog.getClass();

        String packageName = "edu.school21";
        Package pkg = Package.getPackage(packageName);

        String impl = pkg.getImplementationTitle();
        System.out.println(catClass.getName());

        System.out.println(pkg);
        System.out.println(impl);
    }
}
