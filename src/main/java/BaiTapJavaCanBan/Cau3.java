package BaiTapJavaCanBan;
/*
 * 3.	Viết chương trình nhập bán kính hình tròn, xuất chu vi, diện tích của hình tròn đó.
 */

public class Cau3 {
	java.util.Scanner sc;

	public Cau3(java.util.Scanner sc) {
		this.sc = sc;
	}
	
	public void run() {
		System.out.println("\nTask 3: ");
		System.out.println("Please insert radius of circle: ");
		System.out.print("Radius: ");
		double r = 0;
		r = Double.parseDouble(this.sc.next());

		System.out.println("Circuit: " + (r + r) * Math.PI);
		System.out.println("Area: " + (r * r * Math.PI));

	}

}
