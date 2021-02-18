package BaiTapJavaCanBan;
/*
 * 6.	Viết chương trình nhập số tự nhiên N, kiểm tra  và xuất kết quả N là số nguyên tố hay không.
 */

public class Cau6 {
	java.util.Scanner sc;

	public Cau6(java.util.Scanner sc) {
		this.sc = sc;
	}
	public void run() {
		System.out.println("\nTask 6: ");
		System.out.println("Please insert a natural number: ");
		int n = 0;
		try {
			n = Integer.parseInt(this.sc.next());
		} catch (NumberFormatException e) {
			System.err.print(e.getMessage());
		}
		if (n < 0) {
			System.out.println("N is not a natural number");
		} else if (n >= 2) {
			int count = 0;
			for (int i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0) {
					count++;
				}
			}
			if (count == 0) {
				System.out.println("N is a prime number");
			} else {
				System.out.println("N is not a prime number");
			}

		} else {
			System.out.println("N is not a prime number");

		}
	}

}
