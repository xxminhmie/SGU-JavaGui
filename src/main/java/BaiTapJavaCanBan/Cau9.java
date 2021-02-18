package BaiTapJavaCanBan;

public class Cau9 {
	java.util.Scanner sc;

	public String str;

	public Cau9(java.util.Scanner sc) {
		this.sc = sc;
	}

	public void run() {
		// 9. Viết chương trình nhập chuỗi s, xuất kết quả:
		System.out.println("\nTask 9:");
		System.out.print("Please insert a string: ");
		this.str = this.sc.next();
		this.a();
		this.b();
		this.c();
		this.d();
		this.e();
	}
	public void a() {
		System.out.println("\nTask 9a:");
		System.out.print("The length of String is: "+this.str.length());
	}
	public void b() {
		//a.	Xoá bỏ khoảng trắng thừa của s
		System.out.println("\nTask 9b:");
		System.out.print("The String after delete whitespace: "+this.str.replaceAll("\\s+",""));

	}
	public void c() {
		//public char charAt(int index)
		//boolean isDigit(char ch)
		System.out.println("\nTask 9c:");
		for(int i=0; i<this.str.length(); i++) {
			char temp = this.str.charAt(i);
			if(Character.isDigit(temp)==false) {
				System.out.println(temp);
			}
		}
	}
	public void d() {
		//nhập số tự nhiên k, xuất k k‎ý tự bên trái của s, k kí tự bên phải của s
		System.out.println("\nTask 9d:");
		System.out.print("Insert k: ");	
		int k = 0;
		try {
			k = Integer.parseInt(this.sc.next());
			System.out.print("Left character: ");
			for(int i=0; i<k; i++) {
				System.out.print(this.str.charAt(i)+"\t");
			}
			System.out.print("\n");
			System.out.print("Right character: ");
			int dem=0;
			for(int i=this.str.length();; i--) {
				dem++;
				System.out.print(this.str.charAt(this.str.length()-i)+"\t");
				if(dem==k) {
					break;
				}
			}
		}catch(NumberFormatException e) {
			System.err.println(e.getMessage());
		}
	}
	public void e() {
		//e.	nhập số tự nhiên k, n, xuất n kí tự của s kể từ vị trí k 
		System.out.println("\nTask 9e:");
		try {
			System.out.print("Insert k:");
			int k = Integer.parseInt(this.sc.next());
			System.out.print("Insert n:");
			int n = Integer.parseInt(this.sc.next());
			System.out.println("");
			if((this.str.length()-k)<=n){
				for(int i=0; i<(this.str.length()-k); i++) {
					System.out.print(this.str.charAt(i)+"\t");
				}
			}else {
				for(int i=0; i<(n); i++) {
					System.out.print(this.str.charAt(i)+"\t");
				}
			}
			
		}catch(NumberFormatException e) {
			System.err.println(e.getMessage());
		}	}

}
