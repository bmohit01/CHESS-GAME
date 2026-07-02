import java.util.Scanner;
public class Checkma {
    
    public static boolean isCheckmate(char[][] y, boolean isW) {
        if (!isCheck(y, isW)) return false;
        for (int a = 2; a <= 9; a++) {
            for (int b = 2; b <= 9; b++) {
                char p = y[a][b];
                if (p != '.' && Character.isUpperCase(p) == isW) {
                    for (int c = 2; c <= 9; c++) {
                        for (int d = 2; d <= 9; d++) {
                            if (canAttack(y, p, a, b, c, d)) {
                                char t = y[c][d];
                                y[c][d] = p;
                                y[a][b] = '.';
                                boolean stillCheck = isCheck(y, isW);
                                y[a][b] = p;
                                y[c][d] = t;
                                if (!stillCheck) return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isCheck(char[][] y, boolean isW) {
        int kr = -1, kc = -1;
        for (int i = 2; i <= 9; i++) {
            for (int j = 2; j <= 9; j++) {
                if (y[i][j] == (isW ? 'K' : 'k')) {
                    kr = i;
                    kc = j;
                }
            }
        }

        for (int i = 2; i <= 9; i++) {
            for (int j = 2; j <= 9; j++) {
                if (y[i][j] != '.' && Character.isLowerCase(y[i][j]) == isW) {
                    if (canAttack(y, y[i][j], i, j, kr, kc))
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean canAttack(char[][] y, char P, int a, int b, int c, int d) {
        char t = y[c][d];
        if (t != '.' && Character.isUpperCase(P) == Character.isUpperCase(t))
            return false;

        int dr = Math.abs(a - c), dc = Math.abs(b - d);
        char p = Character.toLowerCase(P);
        boolean clr = isPathClear(y, a, b, c, d);

        if (p == 'p') return (Character.isUpperCase(P) ? a - c == 1 : c - a == 1) && dc == 1;
        if (p == 'n') return (dr == 2 && dc == 1) || (dr == 1 && dc == 2);
        if (p == 'k') return dr <= 1 && dc <= 1;
        if (p == 'r') return (a == c || b == d) && clr;
        if (p == 'b') return (dr == dc) && clr;
        if (p == 'q') return (a == c || b == d || dr == dc) && clr;

        return false;
    }

    public static boolean isPathClear(char[][] y, int a, int b, int c, int d) {
        int dr = Integer.compare(c, a), dc = Integer.compare(d, b);
        for (int row = a + dr, col = b + dc; row != c || col != d; row += dr, col += dc)
            if (y[row][col] != '.') return false;
        return true;
    }
    public static void wpapro(boolean isValid, boolean isCapture, int a, int b, int c, int d, char [][]y){
        if(ChessLogic.valid(isValid,isCapture,a,b,c,d,y)){
            System.out.println("Pawn Promotion! Choose a piece to promote to (Q, R, B, N):");
            Scanner s= new Scanner(System.in);
            String q=s.nextLine();
            if(q.equals("Q")){
                y[c][d]='Q';
            }
            else if(q.equals("R")){
                y[c][d]='R';
            }
            else if(q.equals("B")){
                y[c][d]='B';
            }
            else if(q.equals("N")){
                y[c][d]='N';
                
            }
            else{
                System.out.println("Default P is promoted to Q");
                y[c][d]='Q';
            }
        ChessLogic.display(y, c, d);
        }
        else{
            System.out.println("Invalid");
        }
        
    }
    public static void bpapro(boolean isValid, boolean isCapture, int a, int b, int c, int d, char [][]y){
        if(ChessLogic.valid(isValid,isCapture,a,b,c,d,y)){
            System.out.println("Pawn Promotion! Choose a piece to promote to (q, r, b, n):");
            Scanner s= new Scanner(System.in);
            String q=s.nextLine();
            if(q.equals("q")){
                y[c][d]='q';
            }
            else if(q.equals("r")){
                y[c][d]='r';
            }
            else if(q.equals("b")){
                y[c][d]='b';
            }
            else if(q.equals("n")){
                y[c][d]='n';
            }
            else{
                System.out.println("Default P is promoted to q");
                y[c][d]='q';
            }
        ChessLogic.display(y, c, d);
        }
        else{
            System.out.println("Invalid");
        }
        
    }

}

