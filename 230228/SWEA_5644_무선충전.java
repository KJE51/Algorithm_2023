import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class SWEA_5644 {
	static class Charge implements Comparable<Charge> {
		int i, k;
		int pow;
		int size;

		public Charge(int i, int k, int pow, int size) {
			super();
			this.i = i;
			this.k = k;
			this.pow = pow;
			this.size = size;
		}

		@Override
		public int compareTo(Charge o) {
			return o.pow - this.pow;
		}

		@Override
		public String toString() {
			return "Charge [i=" + i + ", k=" + k + ", pow=" + pow + ", size=" + size + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int test_case = Integer.parseInt(str[0]) + 1;

		for (int tc = 1; tc < test_case; tc++) {
			str = br.readLine().split(" ");

			// 사람 이동 수
			int n = Integer.parseInt(str[0]);
			// 충전기 수
			int k = Integer.parseInt(str[1]);

			// 사람 A 경로 입력
			str = br.readLine().split(" ");
			int[] p1 = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				p1[i] = Integer.parseInt(str[i - 1]);
			}
			// 사람 B 경로 입력
			str = br.readLine().split(" ");
			int[] p2 = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				p2[i] = Integer.parseInt(str[i - 1]);
			}

			// charges 입력받아 오름차순 power순서로 정렬
			ArrayList<Charge> charges = new ArrayList<>();
			int tmpI, tmpK, tmpS, tmpP;
			for (int i = 0; i < k; i++) {
				str = br.readLine().split(" ");
				tmpI = Integer.parseInt(str[1]) - 1;
				tmpK = Integer.parseInt(str[0]) - 1;
				tmpS = Integer.parseInt(str[2]);
				tmpP = Integer.parseInt(str[3]);
				charges.add(new Charge(tmpI, tmpK, tmpP, tmpS));
			}
			Collections.sort(charges);

			// charge 맵 깔기 int는 pow로 두기, 다이아몬드 대형
			// 그냥 boolean으로 충전 가능한 위치 표시하고 인덱스로 나누는 편이 더 낫지 않았을까
			int[][][] chargeMap = new int[k][10][10];
			for (int cNum = 0; cNum < k; cNum++) {
				tmpI = charges.get(cNum).i;
				tmpK = charges.get(cNum).k;
				tmpP = charges.get(cNum).pow;
				tmpS = charges.get(cNum).size;
				int tmp = 0;
				for (int i = tmpI - tmpS; i <= tmpI + tmpS; i++) {
					for (int k1 = tmpK - tmp; k1 <= tmpK + tmp; k1++) {
						if (i > -1 && i < 10 && k1 < 10 && k1 > -1)
							chargeMap[cNum][i][k1] = tmpP;
					}
					if (i >= tmpI) {
						tmp--;
					} else {
						tmp++;
					}
				}
			}

			int sum = 0;
			int[] di = { 0, -1, 0, +1, 0 };
			int[] dk = { 0, 0, +1, 0, -1 };
			int ai = 0, bi = 9, ak = 0, bk = 9;

			int a1, a2, b1, b2;
			int ak1, bk1;
			// 위치 이동 및 충전
			for (int i = 0; i <= n; i++) {
				a1 = a2 = b1 = b2 = 0;
				ak1 = bk1 = -1;
				// 위치 이동
				ai = ai + di[p1[i]];
				bi = bi + di[p2[i]];
				ak = ak + dk[p1[i]];
				bk = bk + dk[p2[i]];

				//A가 충전할 수 있는거 1, 2번 고름
				for (int k1 = 0; k1 < k; k1++) {
					if (chargeMap[k1][ai][ak] != 0) {
						if (a1 == 0) {
							a1 = chargeMap[k1][ai][ak];
							ak1 = k1;
						} else {
							a2 = chargeMap[k1][ai][ak];
							break;
						}
					}
				}
				
				//B가 충전 가능한 거 1, 2번 고름
				for (int k1 = 0; k1 < k; k1++) {
					if (chargeMap[k1][bi][bk] != 0) {
						if (b1 == 0) {
							b1 = chargeMap[k1][bi][bk];
							bk1 = k1;
						} else {
							b2 = chargeMap[k1][bi][bk];
							break;
						}
					}
				}
				
				//겹치는거 여부에 따라서 잘 출력하기
				if (a1 != b1) {
					sum = sum + a1 + b1;
				} else {
					if (ak1 != bk1) {
						sum = sum + a1 + b1;
					} else
						sum = sum + a1 + Math.max(a2, b2);
				}

			}
			System.out.println("#" + tc + " " + sum);
		}
	}
}