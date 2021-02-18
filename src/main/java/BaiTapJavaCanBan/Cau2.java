package BaiTapJavaCanBan;
/*
 * 2.	Viết chương trình nhập chiều dài, chiều rộng hình chữ nhật, xuất chu vi, diện tích của hình chữ nhật đó.
 */

public class Cau2 {
	java.util.Scanner sc;

	public Cau2(java.util.Scanner sc) {
		this.sc = sc;
	}

	public void run() {
		System.out.println("\nTask 2: ");
		System.out.println("Please insert length and width of rectangle: ");
		System.out.print("Length: ");
		double length = 0;
		length = Double.parseDouble(this.sc.next());
		System.out.print("Width: ");
		double width = 0;
		width = Double.parseDouble(this.sc.next());

		System.out.println("Perimeter: " + (length + width) * 2);
		System.out.println("Area: " + (length * width));

	}

}
