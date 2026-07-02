public class ChessLogic {
    public static int first = 1;
    public static int r = 1;


    public static void identify(String f, String t, char[][] a) {
        int f1 = '8' - f.charAt(1) + 2;
        int f2 = f.charAt(0) - 'a' + 2;
        int t1 = '8' - t.charAt(1) + 2;
        int t2 = t.charAt(0) - 'a' + 2;

        char piece = a[f1][f2];

        switch (piece) {
            case 'p': Black.bpawn(f1, f2, t1, t2, a); break;
            case 'P': White.wpawn(f1, f2, t1, t2, a); break;
            case 'r': Black.brook(f1, f2, t1, t2, a); break;
            case 'R': White.wrook(f1, f2, t1, t2, a); break;
            case 'n': Black.bknight(f1, f2, t1, t2, a); break;
            case 'N': White.wknight(f1, f2, t1, t2, a); break;
            case 'b': Black.bbishop(f1, f2, t1, t2, a); break;
            case 'B': White.wbishop(f1, f2, t1, t2, a); break;
            case 'q': Black.bqueen(f1, f2, t1, t2, a); break;
            case 'Q': White.wqueen(f1, f2, t1, t2, a); break;
            case 'k': Black.bking(f1, f2, t1, t2, a); break;
            case 'K': White.wking(f1, f2, t1, t2, a); break;
            default:
                System.out.println("Invalid Move !!");
                break;
        }
    }

    public static void check(boolean isValid, boolean isCap, char[][] y, int a, int b, int c, int d) {
        boolean isW = (r % 2 == 1);
        char p = y[a][b];

        if (!isValid || p == '.' || Character.isUpperCase(p) != isW) {
            System.out.println("Invalid Move !!");
            return;
        }

        char t = y[c][d];
        y[c][d] = p;
        y[a][b] = '.';

        if (Checkma.isCheck(y, isW)) {
            y[a][b] = p;
            y[c][d] = t;
            System.out.println("Invalid Move !! That leaves your King in Check.");
        } else {
            System.out.println("Moved Successfully !");
            if (isCap)
                System.out.println("One Piece Captured !");
            if (Checkma.isCheck(y, !isW))
                System.out.println("CHECK!");

            display(y, c, d);
        }
    }

    public static void display(char[][] y, int c, int d) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(y[i][j] + "  ");
            }
            System.out.println();
        }
        if (r % 2 == 1 && Character.isUpperCase(y[c][d])) {
            System.out.println("Black's Move.....");
        } else if (r % 2 == 0 && Character.isLowerCase(y[c][d])) {
            System.out.println("White's Move.....");
        }
        r++;
    }
    public static boolean valid(boolean isValid, boolean isCapture, int a, int b, int c, int d, char [][]y){
        boolean isW = (ChessLogic.r % 2 == 1);
        char p = y[a][b];

        if (!isValid || p == '.' || Character.isUpperCase(p) != isW) {
            return false;
        }

        char t = y[c][d];
        y[c][d] = p;
        y[a][b] = '.';

        if (Checkma.isCheck(y, isW)) {
            y[a][b] = p;
            y[c][d] = t;
            System.out.println("Invalid Move !! That leaves your King in Check.");
            return false;
        } else {
            if (isCapture){
                System.out.println("One Piece Captured !");
                return true;
            }
            if (Checkma.isCheck(y, !isW)){
                System.out.println("CHECK!");
                return true;
            }
        }       
      return true;      
    }
}