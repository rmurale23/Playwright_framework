package com.murlee.utility;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.comparison.PointsMarkupPolicy;



public class CompareScreenShot {
	
	public  String automationPath = System.getProperty("user.dir");
	
	public boolean compareTowImages(BufferedImage expectedImage, BufferedImage actualImage) throws IOException {
	    ImageDiffer imageDiffer = new ImageDiffer();
	    ImageDiff diff = imageDiffer
	            .withDiffMarkupPolicy(new PointsMarkupPolicy()
	                    .withDiffColor(Color.YELLOW))
	            .makeDiff(expectedImage, actualImage);

	    // areImagesDifferent will be true if images are different, false - images the same
	    boolean areImagesDifferent = diff.hasDiff();
		/*
		 * System.out.println("The Boolean Expression is "+areImagesDifferent); if
		 * (areImagesDifferent) { System.out.println("Inside the loop"); BufferedImage
		 * diffImage = diff.getMarkedImage(); File img=new File("DiffImage.jpg");
		 * ImageIO.write(diffImage, "jpg", img); }
		 */
	    return areImagesDifferent; 
	}

	public void saveImage(BufferedImage image, String imageName) {
	    // Path where you are going to save image
	    String outputFilePath = String.format("target/%s.png", imageName);
	    File outputFile = new File(outputFilePath);
	    try {
	        ImageIO.write(image, "png", outputFile);
	    } catch (IOException e) {
	        
	    }
	}
	
	
}
