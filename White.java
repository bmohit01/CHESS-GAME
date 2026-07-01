public class White {
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
            ChessLogic.check(isValid, isCapture, y, a, b, c, d);
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
            ChessLogic.check(isValid, isCapture, y, a, b, c, d);
        } else {
            ChessLogic.check(isValid, isCapture, y, a, b, c, d);
        }
    }
     public static void wking(int a, int b, int c, int d, char[][] y) {
        boolean isValid = false;
        boolean isCapture = false;
        char t = y[c][d];
        int t1 = Math.abs(a - c);
        int t2 = Math.abs(b - d);
        if (Character.isUpperCase(t))
           ChessLogic.check(isValid, isCapture, y, a, b, c, d);
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
        ChessLogic.check(isValid, isCapture, y, a, b, c, d);
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
        ChessLogic.check(isValid, isCapture, y, a, b, c, d);
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
        ChessLogic.check(isValid, isCapture, y, a, b, c, d);
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
        ChessLogic.check(isValid, isCapture, y, a, b, c, d);
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
        ChessLogic.check(isValid, isCapture, y, a, b, c, d);
    }
}
