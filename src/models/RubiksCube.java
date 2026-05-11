package models;

public class RubiksCube {
//Blanc = 0
//Bleu = 1
//Rouge = 2
//Vert= 3
//Orange = 4
//Jaune = 5

// indice 0 = H
// "" 1 = A 
// "" 2 = D
// "" 3 = P
// "" 4 = G
// "" 5 = B
    static final int H = 0;
    static final int A = 1;
    static final int D = 2;
    static final int P = 3;
    static final int G = 4;
    static final int B = 5;
    private int[][][] cube = new int[6][3][3];

    public RubiksCube(){
        for(int x=0;x<6;x++){
            for (int y=0;y<3;y++){
                for(int z=0;z<3;z++){
                    this.cube[x][y][z]=x;
                    System.out.println(this.cube[x][y][z]);
                }
            }
        }
    }

    private int[] getRow(int face, int row){
        return this.cube[face][row].clone();
    }

    private void setRow(int face, int row, int[] values){
        System.arraycopy(values, 0, this.cube[face][row], 0, 3);
    }

    private int[] getCol(int face, int col){
        int[] result = new int[3];
        for(int i = 0; i < 3; i++){
            result[i] = this.cube[face][i][col];
        }
        return result;
    }

    private void setCol(int face, int col, int[] values){
        for(int i = 0; i < 3; i++){
            this.cube[face][i][col] = values[i];
        }
    }

    public String toString(){
        String res ="";
        for(int x=0;x<6;x++){
            res+= "Face "+x+":\n";
            for (int y=0;y<3;y++){
                res+="[";
                for(int z=0;z<3;z++){
                    if (z <2){
                        res +=this.cube[x][y][z]+",";
                    }else{
                        res +=this.cube[x][y][z];
                    }
                }
                res+="]\n";
            }
            res+="\n";
        }
        return res;
    }

    public void rotationFace(int face,boolean direction){
        int[][] tmp = new int[3][3];
        for (int i =0;i<3;i++){
            for (int j=0;j<3;j++){
                if (direction){
                    tmp[j][2 - i] = cube[face][i][j];
                }
                else{
                    tmp[2 - j][i] =cube[face][i][j];
                }
            }
        }
        switch(face){
            case RubiksCube.H: {

                int[] a = getRow(RubiksCube.A, 0);
                int[] d = getRow(RubiksCube.D, 0);
                int[] p = getRow(RubiksCube.P, 0);
                int[] g = getRow(RubiksCube.G, 0);
                if(direction){
                    setRow(RubiksCube.A, 0, d);
                    setRow(RubiksCube.D, 0, p);
                    setRow(RubiksCube.P, 0, g);
                    setRow(RubiksCube.G, 0, a);
                } else {
                    setRow(RubiksCube.A, 0, g);
                    setRow(RubiksCube.G, 0, p);
                    setRow(RubiksCube.P, 0, d);
                    setRow(RubiksCube.D, 0, a);
                }
                break;
            }
            case RubiksCube.B: {

                int[] a = getRow(RubiksCube.A, 2);
                int[] d = getRow(RubiksCube.D, 2);
                int[] p = getRow(RubiksCube.P, 2);
                int[] g = getRow(RubiksCube.G, 2);

                if(direction){

                    setRow(RubiksCube.A, 2, g);
                    setRow(RubiksCube.G, 2, p);
                    setRow(RubiksCube.P, 2, d);
                    setRow(RubiksCube.D, 2, a);

                } else {

                    setRow(RubiksCube.A, 2, d);
                    setRow(RubiksCube.D, 2, p);
                    setRow(RubiksCube.P, 2, g);
                    setRow(RubiksCube.G, 2, a);
                }
                break;
            }

            case RubiksCube.A:{
                int[] h = getRow(RubiksCube.H, 2);
                int[] d = getCol(RubiksCube.D, 0);
                int[] b = getRow(RubiksCube.B, 0);
                int[] g = getCol(RubiksCube.G, 2);
                if(direction){
                    this.setCol(RubiksCube.D,0,h);
                    this.setRow(RubiksCube.B,0,d);
                    this.setCol(RubiksCube.G, 2, b);
                    this.setRow(RubiksCube.H, 2, g);
                }else{
                    this.setCol(RubiksCube.G, 2, h);
                    this.setRow(RubiksCube.B, 0, g);
                    this.setCol(RubiksCube.D, 0, b);
                    this.setRow(RubiksCube.H, 2, d);
                }
            }

            case RubiksCube.P: {

                int[] h = getRow(RubiksCube.H, 0);
                int[] d = getCol(RubiksCube.D, 2);
                int[] b = getRow(RubiksCube.B, 2);
                int[] g = getCol(RubiksCube.G, 0);

                if(direction){

                    setCol(RubiksCube.D, 2, h);
                    setRow(RubiksCube.B, 2, d);
                    setCol(RubiksCube.G, 0, b);
                    setRow(RubiksCube.H, 0, g);

                } else {

                    setCol(RubiksCube.G, 0, h);
                    setRow(RubiksCube.B, 2, g);
                    setCol(RubiksCube.D, 2, b);
                    setRow(RubiksCube.H, 0, d);
                }
                break;
            }

            case RubiksCube.G: {

                int[] h = getCol(RubiksCube.H, 0);
                int[] a = getCol(RubiksCube.A, 0);
                int[] b = getCol(RubiksCube.B, 0);
                int[] p = getCol(RubiksCube.P, 2);

                if(direction){

                    setCol(RubiksCube.A, 0, h);
                    setCol(RubiksCube.B, 0, a);
                    setCol(RubiksCube.P, 2, b);
                    setCol(RubiksCube.H, 0, p);

                } else {

                    setCol(RubiksCube.P, 2, h);
                    setCol(RubiksCube.B, 0, p);
                    setCol(RubiksCube.A, 0, b);
                    setCol(RubiksCube.H, 0, a);
                }
                break;
            }
            case RubiksCube.D: {

                int[] h = getCol(RubiksCube.H, 2);
                int[] a = getCol(RubiksCube.A, 2);
                int[] b = getCol(RubiksCube.B, 2);
                int[] p = getCol(RubiksCube.P, 0);

                if(direction){

                    setCol(RubiksCube.P, 0, h);
                    setCol(RubiksCube.B, 2, p);
                    setCol(RubiksCube.A, 2, b);
                    setCol(RubiksCube.H, 2, a);

                } else {

                    setCol(RubiksCube.A, 2, h);
                    setCol(RubiksCube.B, 2, a);
                    setCol(RubiksCube.P, 0, b);
                    setCol(RubiksCube.H, 2, p);
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        RubiksCube ru = new RubiksCube();
        System.out.println(ru.toString());
        ru.rotationFace(RubiksCube.H,true);
        System.out.println(ru.toString());
        ru.rotationFace(RubiksCube.H,false);
        System.out.println(ru.toString());

    }
}