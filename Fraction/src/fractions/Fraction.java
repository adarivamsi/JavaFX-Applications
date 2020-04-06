/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractions;

/**
 *
 * @author adari
 */
public class Fraction {
    private int numerator;
    private int denominator;
    
    public Fraction(){
        this.numerator = 1;
        this.denominator = 1;
    }
    
    public Fraction(int num, int den){
        this.numerator = num;
        this.denominator = den;
    }
    
    public int getNumerator(){
        return this.numerator;
    }
    
    public int getDenominator(){
        return this.denominator;
    }
    
    public static int gcd(int x,int y){
        int r;
        while(y!=0)
        {
            r = x%y;
            x = y;
            y = r;
        }
        return x;
    }
    
    public Fraction reduce(){
        int temp = Fraction.gcd(this.numerator,this.denominator);
        return new Fraction(this.numerator/temp,this.denominator/temp);
    }
    
    public Fraction add(Fraction f){
        int newNumerator = this.numerator*f.getDenominator() + this.denominator*f.getNumerator();
        int newDenominator = this.denominator*f.getDenominator();
        return (new Fraction(newNumerator,newDenominator)).reduce();
    }
    
    public Fraction sub(Fraction f){
        int newNumerator = this.numerator*f.getDenominator() - this.denominator*f.getNumerator();
        int newDenominator = this.denominator*f.getDenominator();
        return (new Fraction(newNumerator,newDenominator)).reduce();
    }
    
    public Fraction mul(Fraction f){
        int newNumerator = this.numerator*f.getNumerator();
        int newDenominator = this.denominator*f.getDenominator();
        return (new Fraction(newNumerator,newDenominator)).reduce();
    }
    
    public Fraction div(Fraction f){
        int newNumerator = this.numerator*f.getDenominator();
        int newDenominator = this.denominator*f.getNumerator();
        return (new Fraction(newNumerator,newDenominator)).reduce();
    }

}
