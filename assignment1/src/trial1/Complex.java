package trial1;

import trial1.Complex;

public class Complex {
	
	private double real;   
	private double imaginary;
	
	public Complex(double re, double im) {
       real = re;
       imaginary = im;
	}
	
	public String StringRep() {
       if (imaginary == 0) return real + "";
       if (real == 0) return imaginary + "i";
       if (imaginary <  0) return real + " - " + (-imaginary) + "i";
       return real + " + " + imaginary + "i";
    } 
	
	public double abs() {
		return Math.hypot(real, imaginary);
    }
	
	public Complex plus(Complex b) {
        Complex a = this;             // invoking object
        double Real = a.real + b.real;
        double imag = a.imaginary + b.imaginary;
        return new Complex(Real, imag);
    }
	
    public Complex addition(Complex a, Complex b) {
        double Real = a.real + b.real;
        double imag = a.imaginary + b.imaginary;
        Complex sum = new Complex(Real, imag);
        return sum;
    }
    
    public static Complex minus(Complex a, Complex b) {
        double Real = a.real - b.real;
        double imag = a.imaginary - b.imaginary;
        Complex difference = new Complex(Real, imag);
        return difference;
    }

    public Complex times(Complex b) {
        Complex a = this;
        double Real = a.real * b.real - a.imaginary * b.imaginary;
        double imag = a.real * b.imaginary + a.imaginary * b.real;
        return new Complex(Real, imag);
    }

    public Complex conjugate() {
        return new Complex(real, -imaginary);
    }

    public Complex reciprocal() {
        double scale = real*real + imaginary*imaginary;
        return new Complex(real / scale, -imaginary / scale);
    }

    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }
    
    public double real() { return real; }
    public double imag() { return imaginary; }
    
    public Complex multi(double b) {
        Complex a = this;
        double Real = (a.real) * b;
        double imag = (a.imaginary) * b;
        return new Complex(Real, imag);
    }
}
