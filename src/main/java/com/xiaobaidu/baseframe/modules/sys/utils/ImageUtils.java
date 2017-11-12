package com.xiaobaidu.baseframe.modules.sys.utils;

import com.mortennobel.imagescaling.ResampleOp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @description
 * @auther hefaji
 * @date 2017/9/24
 */
public class ImageUtils {
    /** 
          * 用于获得等比例缩略图 
          * @param fromFileStr 源文件 
          * @param saveToFileStr 要保存的文件 
          * @param suffix 后缀名 
          * @param width 图片的宽度 
          * @param height 图片的高度 
          *  
          * */  
                  
       public  static boolean getPicThumb(String fromFileStr, String saveToFileStr,String suffix,int width,int height){
        File inputFile = new File(fromFileStr);
        File outFile = new File(saveToFileStr);    
        String outPath = outFile.getAbsolutePath();
           ImageUtils imageUtils = new ImageUtils();
        InputStream input;
        try {  
            input = new FileInputStream(inputFile);
            byte[] byteArrayImage= readBytesFromIS(input);
            input.read(byteArrayImage);    
            return  resizeImage(byteArrayImage, outPath, width, height, suffix);
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
          e.printStackTrace();
            return false;
        }    
  }  
                  
                  
                /**  
          * 接收输入流输生成图片  
          * @param input  
          * @param writePath  
          * @param width  
          * @param height  
          * @param format  
          * @return  
          */    
      public static boolean resizeImage(InputStream input, String writePath,
                        Integer width, Integer height, String format) {    
        try {    
            BufferedImage inputBufImage = ImageIO.read(input);    
            ResampleOp resampleOp = new ResampleOp(width, height);// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,    
                                        null);    
            ImageIO.write(rescaledTomato, format, new File(writePath));    
            return true;
        } catch (IOException e) {    
            e.printStackTrace();    
            return false;    
        }    
    
    }    
                
                /**  
          * 接收File输出图片  
          * @param file  
          * @param writePath  
          * @param width  
          * @param height  
          * @param format  
          * @return  
          */
     public static boolean resizeImage(File file, String writePath, Integer width,
                        Integer height, String format) {    
        try {    
            BufferedImage inputBufImage = ImageIO.read(file);
            inputBufImage.getType();    
            ResampleOp resampleOp = new ResampleOp(width, height);// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,    
                                        null);    
            ImageIO.write(rescaledTomato, format, new File(writePath));    
            return true;
        } catch (IOException e) {
            e.printStackTrace();    
            return false;    
        }    
    
    }    
                
                /**  
          * 接收字节数组生成图片  
          * @param RGBS  
          * @param writePath  
          * @param width  
          * @param height  
          * @param format  
          * @return  
          */    
     public static boolean resizeImage(byte[] RGBS, String writePath, Integer width,
                        Integer height, String format) {    
        InputStream input = new ByteArrayInputStream(RGBS);
        return resizeImage(input, writePath, width, height, format);
    }    
                
    public static byte[] readBytesFromIS(InputStream is) throws IOException {
        int total = is.available();    
        byte[] bs = new byte[total];    
        is.read(bs);    
        return bs;    
    }

    public static void main(String[] args) {
        int width = 80;
        int height = 60;
        String fromPath = "D:\\mySource\\GluttonCatMall\\src\\main\\webapp/static\\uploadfile\\commImage\\20171015152520.jpg";
        String toPath = "D:\\mySource\\GluttonCatMall\\src\\main\\webapp/static\\uploadfile\\commImage\\small2.jpg";
        String suffix="jpg";
        getPicThumb(fromPath,toPath, suffix, width, height);
    }
}
