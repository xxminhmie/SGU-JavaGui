package BaiTapJavaCanBan;
/*
 * 7.	Viết chương trình nhập số tự nhiên N, xuất kết quả :
 */

public class Cau7 {
	java.util.Scanner sc;

	public Cau7(java.util.Scanner sc) {
		this.sc = sc;
	}

	public void a(int n) {
		//a.	Các số tự nhiên <=N và tổng của chúng
		System.out.println("\nTask 7a:");
		System.out.print("--->List of natural number: ");
		int tonga = 0;
		for (int i = 0; i <= n; i++) {
			System.out.print(i + "\t");
			tonga += i;
		}
		System.out.println("\n--->Sum of list number: " + tonga);
	}

	public void b(int n) {
		//b.	Các số tự nhiên chẵn <=N và tổng của chúng
		System.out.println("\nTask 7b: ");
		System.out.print("-->List of even number: ");
		int tongb = 0;
		for (int i = 0; i <= n; i++) {
			if (i % 2 == 0) {
				System.out.print(i + "\t");
				tongb += i;
			}
		}
		System.out.println("\n--->Sum of list even number: " + tongb);

	}

	public void c(int n) {
		// c.	Các số tự nhiên lẻ <=N và tổng của chúng
		System.out.println("\nTaks 7c: ");
		System.out.print("-->List of odd number: ");
		int tongc = 0;
		for (int i = 0; i <= n; i++) {
			if (i % 2 != 0) {
				System.out.print(i + "\t");
				tongc += + i;
			}
		}
		System.out.println("\n--->Sum of list odd number: " + tongc);

	}

	public void d(int n) {
		//d.	Các số tự nhiên là số nguyên tố <=N và tổng của chúng
		System.out.println("\nTask 7d: ");
		System.out.print("-->List of prime number: ");
		int tongd = 0;
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= Math.sqrt(n); j++) {
				if (n % i == 0) {
					break;
				}else {
					System.out.print(i + "\t");
					tongd+=i;
				}
			}
		}
		System.out.println("\n--->Sum of list prime number: " + tongd);

	}

	public void e(int n) {
		//e.	N số nguyên tố đầu tiên
		System.out.println("\nTask 7e: ");
		System.out.print("-->List of N first prime number: ");
		int count = 0;
		int dem=2;
		while(count<n) {
			int check=0;
			for(int i=2; i<Math.sqrt(dem); i++) {
				if(dem%i==0) {
					check++;
				}
			}
			if(check==0) {
				System.out.print(dem+"\t");
				count++;
			}
			dem++;
		}
		


	}

	public void run() {
		System.out.println("\nTask 7:");
		System.out.print("Please insert a natural number: ");
		int n = 0;
		try {
			n = Integer.parseInt(this.sc.next());
		} catch (NumberFormatException e) {
			System.err.print(e.getMessage());
		}
		if (n < 0) {
			System.out.println("N is not a natural number");
		} else {
			this.a(n);
			this.b(n);
			this.c(n);
			this.d(n);
			this.e(n);
		}

	}

}
