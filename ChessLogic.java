public class ChessLogic {
    public static int first = 1;
    public static int r = 1;

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

    public static void identify(String f, String t, char[][] a) {
        int f1 = '8' - f.charAt(1) + 2;
        int f2 = f.charAt(0) - 'a' + 2;
        int t1 = '8' - t.charAt(1) + 2;
        int t2 = t.charAt(0) - 'a' + 2;

        char piece = a[f1][f2];

        switch (piece) {
            case 'p': bpawn(f1, f2, t1, t2, a); break;
            case 'P': wpawn(f1, f2, t1, t2, a); break;
            case 'r': brook(f1, f2, t1, t2, a); break;
            case 'R': wrook(f1, f2, t1, t2, a); break;
            case 'n': bknight(f1, f2, t1, t2, a); break;
            case 'N': wknight(f1, f2, t1, t2, a); break;
            case 'b': bbishop(f1, f2, t1, t2, a); break;
            case 'B': wbishop(f1, f2, t1, t2, a); break;
            case 'q': bqueen(f1, f2, t1, t2, a); break;
            case 'Q': wqueen(f1, f2, t1, t2, a); break;
            case 'k': bking(f1, f2, t1, t2, a); break;
            case 'K': wking(f1, f2, t1, t2, a); break;
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

        if (isCheck(y, isW)) {
            y[a][b] = p;
            y[c][d] = t;
            System.out.println("Invalid Move !! That leaves your King in Check.");
        } else {
            System.out.println("Moved Successfully !");
            if (isCap)
                System.out.println("One Piece Captured !");
            if (isCheck(y, !isW))
                System.out.println("CHECK!");

            display(y, c, d);
        }
    }

    public static void display(char[][] y, int c, int d) {
        System.out.println("+---------------------------------+");
        for (int i = 0; i < 11; i++) {
             System.out.print("| ");
            for (int j = 0; j < 11; j++) {
                System.out.print(y[i][j] + "  ");
            }
            System.out.println();
            System.out.println("+---------------------------------+");
        }
        if (r % 2 == 1 && Character.isUpperCase(y[c][d])) {
            System.out.println("Black's Move.....");
        } else if (r % 2 == 0 && Character.isLowerCase(y[c][d])) {
            System.out.println("White's Move.....");
        }
        r++;
    }

    public static void bqueen(int a, int b, int c, int d, char[][] y) {
        boolean isValid = false;
        boolean isCapture = false;
        char t = y[c][d];
        if (Math.abs(a - c) == Math.abs(b - d) && Math.abs(a - c) > 0) {
            boolean pathClear = true;
            int rowStep = a < c ? 1 : -1;
            int colStep = b < d ? 1 : -1;
            int currRow = a + rowStep;
            int currCol = b + colStep;
            while (currRow != c && currCol != d) {
                if (y[currRow][currCol] != '.') {
                    pathClear = false;
                    break;
                }
                currRow += rowStep;
                currCol += colStep;
            }
            if (pathClear) {
                if (t == '.' || Character.isUpperCase(t)) {
                    isValid = true;
                    if (Character.isUpperCase(t)) {
                        isCapture = true;
                    }
                }
            }
            check(isValid, isCapture, y, a, b, c, d);
        } else if (a == c || b == d) {
            boolean pathClear = true;
            if (a == c) {
                int min = Math.min(b, d);
                int max = Math.max(b, d);
                for (int i = min + 1; i < max; i++) {
                    if (y[a][i] != '.')
                        pathClear = false;
                }
            } else {
                int min = Math.min(a, c);
                int max = Math.max(a, c);
                for (int i = min + 1; i < max; i++) {
                    if (y[i][b] != '.')
                        pathClear = false;
                }
            }
            if (pathClear && (t == '.' || Character.isUpperCase(t))) {
                isValid = true;
                if (Character.isUpperCase(t))
                    isCapture = true;
            }
            check(isValid, isCapture, y, a, b, c, d);
        } else {
            check(isValid, isCapture, y, a, b, c, d);
        }
    }

    public static void wqueen(int a, int b, int c, int d, char[][] y) {
        boolean isValid = false;
        boolean isCapture = false;
        char t = y[c][d];
        if (Math.abs(a - c) == Math.abs(b - d) && Math.abs(a - c) > 0) {
            boolean pathClear = true;
            int rowStep = a < c ? 1 : -1;
            int colStep = b < d ? 1 : -1;
            int currRow = a + rowStep;
            int currCol = b + colStep;
            while (currRow != c && currCol != d) {
                if (y[currRow][currCol] != '.') {
                    pathClear = false;
                    break;
                }
                currRow += rowStep;
                currCol += colStep;
            }
            if (pathClear) {
                if (t == '.' || Character.isLowerCase(t)) {
                    isValid = true;
                    if (Character.isLowerCase(t)) {
                        isCapture = true;
                    }
                }
            }
            check(isValid, isCapture, y, a, b, c, d);
        } else if (a == c || b == d) {
            boolean pathClear = true;
            if (a == c) {
                int min = Math.min(b, d);
                int max = Math.max(b, d);
                for (int i = min + 1; i < max; i++) {
                    if (y[a][i] != '.')
                        pathClear = false;
                }
            } else {
                int min = Math.min(a, c);
                int max = Math.max(a, c);
                for (int i = min + 1; i < max; i++) {
                    if (y[i][b] != '.')
                        pathClear = false;
                }
            }
            if (pathClear && (t == '.' || Character.isLowerCase(t))) {
                isValid = true;
                if (Character.isLowerCase(t))
                    isCapture = true;
            }
            check(isValid, isCapture, y, a, b, c, d);
        } else {
            check(isValid, isCapture, y, a, b, c, d);
        }
    }

    public static void bking(int a, int b, int c, int d, char[][] y) {
        boolean isValid = false;
        boolean isCapture = false;
        char t = y[c][d];
        int t1 = Math.abs(a - c);
        int t2 = Math.abs(b - d);
        if (Character.isLowerCase(t))
            check(isValid, isCapture, y, a, b, c, d);
        if (t1 == 1 && t2 == 1) {
            if (t == '.' || Character.isUpperCase(t)) {
                isValid = true;
                if (Character.isUpperCase(t)) {
                    isCapture = true;
                }
            }
        } else if (Math.abs(a - c) == 1 || Math.abs(b - d) == 1) {
            if (t1 == 0 && t2 == 1) {
                isValid = true;
                if (Character.isUpperCase(t)) {
                    isCapture = true;
                }
            }
            if (t1 == 1 && t2 == 0) {
                isValid = true;
                if (Character.isUpperCase(t)) {
                    isCapture = true;
                }
            }
        }
        check(isValid, isCapture, y, a, b, c, d);
    }

    public static void wking(int a, int b, int c, int d, char[][] y) {
        boolean isValid = false;
        boolean isCapture = false;
        char t = y[c][d];
        int t1 = Math.abs(a - c);
        int t2 = Math.abs(b - d);
        if (Character.isUpperCase(t))
            check(isValid, isCapture, y, a, b, c, d);
        if (t1 == 1 && t2 == 1) {
            if (t == '.' || Character.isLowerCase(t)) {
                isValid = true;
                if (Character.isLowerCase(t)) {
                    isCapture = true;
                }
            }
        } else if (t1 == 1 || t2 == 1) {
            if (t1 == 0 && t2 == 1) {
                isValid = true;
                if (Character.isLowerCase(t)) {
                    isCapture = true;
                }
            }
            if (t1 == 1 && t2 == 0) {
                isValid = true;
                if (Character.isLowerCase(t)) {
                    isCapture = true;
                }
            }
        }
        check(isValid, isCapture, y, a, b, c, d);
    }

    public static void wknight(int a, int b, int c, int d, char[][] y) {
        int row = Math.abs(a - c);
        int col = Math.abs(b - d);
        boolean isValid = false;
        boolean isCapture = false;
        if ((row == 2 && col == 1) || (row == 1 && col == 2)) {
            char t = y[c][d];
            if (t == '.' || Character.isLowerCase(t)) {
                isValid = true;
                if (Character.isLowerCase(t)) {
                    isCapture = true;
                }
            }
        }
        check(isValid, isCapture, y, a, b, c, d);
    }

    public static void bknight(int a, int b, int c, int d, char[][] y) {
        int row = Math.abs(a - c);
        int col = Math.abs(b - d);
        boolean isValid = false;
        boolean isCapture = false;
        if ((row == 2 && col == 1) || (row == 1 && col == 2)) {
            char t = y[c][d];
            if (t == '.' || Character.isUpperCase(t)) {
                isValid = true;
                if (Character.isUpperCase(t)) {
                    isCapture = true;
                }
            }
        }
        check(isValid, isCapture, y, a, b, c, d);
    }

    public static void bbishop(int a, int b, int c, int d, char[][] y) {
        boolean isValid = false;
        boolean isCapture = false;
        if (Math.abs(a - c) == Math.abs(b - d) && Math.abs(a - c) > 0) {
            boolean pathClear = true;
            int rowStep = a < c ? 1 : -1;
            int colStep = b < d ? 1 : -1;
            int currRow = a + rowStep;
            int currCol = b + colStep;
            while (currRow != c && currCol != d) {
                if (y[currRow][currCol] != '.') {
                    pathClear = false;
                    break;
                }
                currRow += rowStep;
                currCol += colStep;
            }
            if (pathClear) {
                char t = y[c][d];
                if (t == '.' || Character.isUpperCase(t)) {
                    isValid = true;
                    if (Character.isUpperCase(t)) {
                        isCapture = true;
                    }
                }
            }
        }
        check(isValid, isCapture, y, a, b, c, d);
    }

    public static void wbishop(int a, int b, int c, int d, char[][] y) {
        boolean isValid = false;
        boolean isCapture = false;
        if (Math.abs(a - c) == Math.abs(b - d) && Math.abs(a - c) > 0) {
            boolean pathClear = true;
            int rowStep = a < c ? 1 : -1;
            int colStep = b < d ? 1 : -1;
            int currRow = a + rowStep;
            int currCol = b + colStep;
            while (currRow != c && currCol != d) {
                if (y[currRow][currCol] != '.') {
                    pathClear = false;
                    break;
                }
                currRow += rowStep;
                currCol += colStep;
            }
            if (pathClear) {
                char t = y[c][d];
                if (t == '.' || Character.isLowerCase(t)) {
                    isValid = true;
                    if (Character.isLowerCase(t)) {
                        isCapture = true;
                    }
                }
            }
        }
        check(isValid, isCapture, y, a, b, c, d);
    }

    public static void wpawn(int a, int b, int c, int d, char[][] y) {
        char t = y[c][d];
        boolean isValid = false;
        boolean isCapture = false;
        if (b == d && t == '.') {
            if (a - c == 1)
                isValid = true;
            else if (a - c == 2 && a == 8 && y[a - 1][b] == '.')
                isValid = true;
        } else if (a - c == 1 && Math.abs(b - d) == 1 && Character.isLowerCase(t)) {
            isValid = true;
            isCapture = true;
        }
        check(isValid, isCapture, y, a, b, c, d);
    }

    public static void bpawn(int a, int b, int c, int d, char[][] y) {
        char t = y[c][d];
        boolean isValid = false;
        boolean isCapture = false;

        if (b == d && t == '.') {
            if (c - a == 1)
                isValid = true;
            else if (c - a == 2 && a == 3 && y[a + 1][b] == '.')
                isValid = true;
        } else if (c - a == 1 && Math.abs(b - d) == 1 && Character.isUpperCase(t)) {
            isValid = true;
            isCapture = true;
        }
        check(isValid, isCapture, y, a, b, c, d);
    }

    public static void wrook(int a, int b, int c, int d, char[][] y) {
        boolean isValid = false;
        boolean isCapture = false;
        char t = y[c][d];
        if (a == c || b == d) {
            boolean pathClear = true;
            if (a == c) {
                int min = Math.min(b, d);
                int max = Math.max(b, d);
                for (int i = min + 1; i < max; i++) {
                    if (y[a][i] != '.')
                        pathClear = false;
                }
            } else {
                int min = Math.min(a, c);
                int max = Math.max(a, c);
                for (int i = min + 1; i < max; i++) {
                    if (y[i][b] != '.')
                        pathClear = false;
                }
            }
            if (pathClear && (t == '.' || Character.isLowerCase(t))) {
                isValid = true;
                if (Character.isLowerCase(t))
                    isCapture = true;
            }
        }
        check(isValid, isCapture, y, a, b, c, d);
    }

    public static void brook(int a, int b, int c, int d, char[][] y) {
        boolean isValid = false;
        boolean isCapture = false;
        char t = y[c][d];
        if (a == c || b == d) {
            boolean pathClear = true;
            if (a == c) {
                int min = Math.min(b, d);
                int max = Math.max(b, d);
                for (int i = min + 1; i < max; i++) {
                    if (y[a][i] != '.')
                        pathClear = false;
                }
            } else {
                int min = Math.min(a, c);
                int max = Math.max(a, c);
                for (int i = min + 1; i < max; i++) {
                    if (y[i][b] != '.')
                        pathClear = false;
                }
            }
            if (pathClear && (t == '.' || Character.isUpperCase(t))) {
                isValid = true;
                if (Character.isUpperCase(t))
                    isCapture = true;
            }
        }
        check(isValid, isCapture, y, a, b, c, d);
    }
}