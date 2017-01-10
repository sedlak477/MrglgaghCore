package com.github.sedlak477.mrglgaghcore.util.obj;

import com.github.sedlak477.mrglgaghcore.MrglgaghCore;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Point2f;
import javax.vecmath.Point3f;
import javax.vecmath.Point3i;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class OBJModel {

    private ArrayList<Point3f> vertices = new ArrayList<>();
    private ArrayList<Point2f> uvs = new ArrayList<>();
    private ArrayList<Point3f> normals = new ArrayList<>();
    private ArrayList<Triple<Point3i, Point3i, Point3i>> faces = new ArrayList<>();

    public OBJModel(String objFileContet){
        try {
            load(new StringReader(objFileContet));
        } catch (IOException e) {
            MrglgaghCore.logger.error("What?", e);
        }
    }

    public OBJModel(File objFile) throws IOException {
        load(new FileReader(objFile));
    }

    public void load(Reader data) throws IOException {
        vertices.clear();
        uvs.clear();
        normals.clear();
        faces.clear();
        BufferedReader reader = new BufferedReader(data);
        String line;
        int lineNumber = 1;
        while ((line = reader.readLine()) != null){
            StringTokenizer tokenizer = new StringTokenizer(line);
            if (tokenizer.hasMoreTokens()){
                switch (tokenizer.nextToken()){
                    case "v":
                        if (tokenizer.countTokens() != 3){
                            MrglgaghCore.logger.error("Malformed vertex on line "+lineNumber);
                            break;
                        }
                        vertices.add(new Point3f(Float.parseFloat(tokenizer.nextToken()),
                                Float.parseFloat(tokenizer.nextToken()),
                                Float.parseFloat(tokenizer.nextToken())));
                        break;
                    case "vt":
                        if (tokenizer.countTokens() != 2){
                            MrglgaghCore.logger.error("Malformed uv on line "+lineNumber);
                            break;
                        }
                        uvs.add(new Point2f(Float.parseFloat(tokenizer.nextToken()),
                                Float.parseFloat(tokenizer.nextToken())));
                        break;
                    case "vn":
                        if (tokenizer.countTokens() != 3){
                            MrglgaghCore.logger.error("Malformed vertex normal on line "+lineNumber);
                            break;
                        }
                        normals.add(new Point3f(Float.parseFloat(tokenizer.nextToken()),
                                Float.parseFloat(tokenizer.nextToken()),
                                Float.parseFloat(tokenizer.nextToken())));
                        break;
                    case "f":
                        if (tokenizer.countTokens() < 3){
                            MrglgaghCore.logger.error("Malformed face on line "+lineNumber);
                            break;
                        }
                        MutableTriple<Point3i, Point3i, Point3i> face = new MutableTriple<>();
                        String[] p1 = tokenizer.nextToken().split("/");
                        face.setLeft(new Point3i(Integer.parseInt(p1[0])-1, Integer.parseInt(p1[1])-1, Integer.parseInt(p1[2])-1));
                        String[] p2 = tokenizer.nextToken().split("/");
                        face.setMiddle(new Point3i(Integer.parseInt(p2[0])-1, Integer.parseInt(p2[1])-1, Integer.parseInt(p2[2])-1));
                        String[] p3 = tokenizer.nextToken().split("/");
                        face.setRight(new Point3i(Integer.parseInt(p3[0])-1, Integer.parseInt(p3[1])-1, Integer.parseInt(p3[2])-1));
                        faces.add(face);
                        break;
                }
            }
            lineNumber++;
        }
    }

    @SideOnly(Side.CLIENT)
    public void tessellate(VertexBuffer vb){
        vb.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_NORMAL);
        for (Triple<Point3i, Point3i, Point3i> face : faces) {
            vb.pos(vertices.get(face.getLeft().x).x, vertices.get(face.getLeft().x).y, vertices.get(face.getLeft().x).z)
                    .tex(uvs.get(face.getLeft().y).x, uvs.get(face.getLeft().y).y)
                    .normal(normals.get(face.getLeft().z).x, normals.get(face.getLeft().z).y, normals.get(face.getLeft().z).z).endVertex();
            vb.pos(vertices.get(face.getMiddle().x).x, vertices.get(face.getMiddle().x).y, vertices.get(face.getMiddle().x).z)
                    .tex(uvs.get(face.getMiddle().y).x, uvs.get(face.getMiddle().y).y)
                    .normal(normals.get(face.getMiddle().z).x, normals.get(face.getMiddle().z).y, normals.get(face.getMiddle().z).z).endVertex();
            vb.pos(vertices.get(face.getRight().x).x, vertices.get(face.getRight().x).y, vertices.get(face.getRight().x).z)
                    .tex(uvs.get(face.getRight().y).x, uvs.get(face.getRight().y).y)
                    .normal(normals.get(face.getRight().z).x, normals.get(face.getRight().z).y, normals.get(face.getRight().z).z).endVertex();
        }
    }

}
