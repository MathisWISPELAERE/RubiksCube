import main.Java.RubiksCube;
import org.lwjgl.opengl.GL11;

public class Renderer {

    // Correspondance couleur → RGB
    // 0=Blanc, 1=Bleu, 2=Rouge, 3=Vert, 4=Orange, 5=Jaune
    private static final float[][] COLORS = {
        {1f, 1f, 1f},   // 0 Blanc
        {0f, 0f, 1f},   // 1 Bleu
        {1f, 0f, 0f},   // 2 Rouge
        {0f, 0.8f, 0f}, // 3 Vert
        {1f, 0.5f, 0f}, // 4 Orange
        {1f, 1f, 0f},   // 5 Jaune
    };

    private static final float STICKER = 28f;  // taille d'un sticker
    private static final float GAP     = 2f;   // espace entre stickers
    private static final float MARGIN  = 4f;   // espace entre faces

    // Disposition dépliée :
    //        [H]
    //   [G][A][D][P]
    //        [B]
    // Offset en cases (col, row) depuis l'origine
    private static final int[][] FACE_OFFSETS = {
        {3, 0},   // H (haut)     → col 3, row 0
        {3, 3},   // A (avant)    → col 3, row 3
        {6, 3},   // D (droite)   → col 6, row 3
        {9, 3},   // P (arrière)  → col 9, row 3  (optionnel)
        {0, 3},   // G (gauche)   → col 0, row 3
        {3, 6},   // B (bas)      → col 3, row 6
    };

    public void drawUnfolded(RubiksCube rubiksCube) {
        int[][][] cube = rubiksCube.getCube();

        for (int face = 0; face < 6; face++) {
            int faceColOffset = FACE_OFFSETS[face][0];
            int faceRowOffset = FACE_OFFSETS[face][1];

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int colorIndex = cube[face][row][col];
                    float[] color = COLORS[colorIndex];

                    float x = faceColOffset * (STICKER + GAP) + col * (STICKER + GAP) + MARGIN;
                    float y = faceRowOffset * (STICKER + GAP) + row * (STICKER + GAP) + MARGIN;

                    // Fond noir (bordure du sticker)
                    GL11.glColor3f(0f, 0f, 0f);
                    drawQuad(x - 1, y - 1, STICKER + 2, STICKER + 2);

                    // Sticker coloré
                    GL11.glColor3f(color[0], color[1], color[2]);
                    drawQuad(x, y, STICKER, STICKER);
                }
            }
        }
    }

    private void drawQuad(float x, float y, float w, float h) {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x,     y);
        GL11.glVertex2f(x + w, y);
        GL11.glVertex2f(x + w, y + h);
        GL11.glVertex2f(x,     y + h);
        GL11.glEnd();
    }
}