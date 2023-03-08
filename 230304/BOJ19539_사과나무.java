import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ19539 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		str = br.readLine().split(" ");
		
		int ones, twos, tmp;
		ones = twos = 0;
		for(int i = 0; i < n; i++) {
			tmp = Integer.parseInt(str[i]);
			ones += tmp % 2;
			twos += tmp/2;
		}
		
		if(ones == twos) {
			System.out.println("YES");
			return;
		}
		if(ones>twos) {
			System.out.println("NO");
			return;
		}
		
		twos = (twos - ones)*2;
		if(twos % 3 == 0) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		
	}

}
