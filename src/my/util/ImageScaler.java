package my.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class ImageScaler
{
	public static File scale(File oldFile,File newFile, int width, int height) {    

        try {     
            Image srcFile = ImageIO.read(oldFile);    
            int w = srcFile.getWidth(null);    
            int h = srcFile.getHeight(null);    
            double bili;    
            if(width>0){    
                bili=width/(double)w;    
                height = (int) (h*bili);    
            }else{    
                if(height>0){    
                    bili=height/(double)h;    
                    width = (int) (w*bili);    
                }    
            }   
            if(w <= width)
            {
            	System.out.println("Can't zoom " + w + "x" + h);
            	FileUtils.moveFile( oldFile, newFile);
            	return newFile;
            }
              
            String srcImgPath = newFile.getAbsoluteFile().toString();  
            System.out.println(srcImgPath);  
            String subfix = "jpg";  
            subfix = srcImgPath.substring(srcImgPath.lastIndexOf(".")+1,srcImgPath.length());  
  
            BufferedImage buffImg = null;   
            if(subfix.equals("png")){  
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  
            }else{  
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            }  
  
            Graphics2D graphics = buffImg.createGraphics();  
            graphics.setBackground(new Color(255,255,255));  
            graphics.setColor(new Color(255,255,255));  
            graphics.fillRect(0, 0, width, height);  
            graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);    
  
            ImageIO.write(buffImg, subfix, new File(srcImgPath));    
    
        } catch (FileNotFoundException e) {    
            e.printStackTrace();    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
        return newFile;    
    }    
}
