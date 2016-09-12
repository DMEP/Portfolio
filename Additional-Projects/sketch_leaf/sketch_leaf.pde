import gab.opencv.*;

OpenCV opencv, opencv2, opencv3, opencv4, opencv5, opencv6;

PImage src, h, s, v, canny, scharr, sobel,
       src2, h2, s2, v2, canny2, canny3,
       h3, s3, v3, canny4, canny5, canny6, canny7, canny8,
       dst, dst2, dst3, dst4, dst5, dst6, dst7, dst8;

int imgH, imgW;

ArrayList<Contour> contours, contours2, contours3, contours4;

void setup() {
  src = loadImage("leafs.jpg");
  src.resize(600,0);
  opencv = new OpenCV(this, src);  
  size(int(opencv.width*3), int(opencv.height*2));
  
  imgH = src.height/2;
  imgW = src.width/2;
  
  //hsv
  opencv.useColor(HSB);
  
  h = opencv.getSnapshot(opencv.getH());
  s = opencv.getSnapshot(opencv.getS());  
  v = opencv.getSnapshot(opencv.getV());
  /// css
  opencv = new OpenCV(this, src);
  opencv.findCannyEdges(20,75);
  canny = opencv.getSnapshot();
  
  opencv.loadImage(src);
  opencv.findScharrEdges(OpenCV.HORIZONTAL);
  scharr = opencv.getSnapshot();
  
  opencv.loadImage(src);
  opencv.findSobelEdges(1,0);
  sobel = opencv.getSnapshot();
  /////hc,sc,vc
  // h has to much noise on edge
  // s has noise within
  // v has a little noise inside
  //src2 = v;
  h2=h;
  s2=s;
  v2=v;
  opencv2 = new OpenCV(this, h2);
  //opencv2.loadImage(h2);
  opencv2.findCannyEdges(20,75);
  canny2 = opencv2.getSnapshot();
  
  opencv2.loadImage(s2);
  opencv2.findCannyEdges(20,75);
  canny3 = opencv2.getSnapshot();
  
  opencv2.loadImage(v2);
  opencv2.findCannyEdges(20,75);
  canny4 = opencv2.getSnapshot();
  ///////////////////////////////////////////
  //////////////////////////////////////////
  /////////////////////////////////////////
  //thread seg
  //sts
  opencv3 = new OpenCV(this, src);
  opencv3.gray();
  opencv3.threshold(230);
  
  dst = opencv3.getSnapshot();
  
  contours = opencv3.findContours();
  println("found " + contours.size() + " contours");
  //hts
  opencv3.loadImage(h2);
  opencv3.gray();
  opencv3.threshold(10);
  
  dst2 = opencv3.getSnapshot();
  
  contours2 = opencv3.findContours();
  println("found " + contours2.size() + " contours");
  
  //sts
  opencv3.loadImage(s2);
  opencv3.gray();
  opencv3.threshold(20);
  
  dst3 = opencv3.getSnapshot();
  
  contours3 = opencv3.findContours();
  println("found " + contours3.size() + " contours");
  //vts
  opencv3.loadImage(v2);
  opencv3.gray();
  opencv3.threshold(230);
  
  dst4 = opencv3.getSnapshot();
  
  contours4 = opencv3.findContours();
  println("found " + contours4.size() + " contours");
  
  /////
  opencv4 = new OpenCV(this, dst);
  opencv4.findCannyEdges(20,75);
  canny5 = opencv4.getSnapshot();
  
  opencv4.loadImage(dst2);
  opencv4.findCannyEdges(20,75);
  canny6 = opencv4.getSnapshot();
  
  opencv4.loadImage(dst3);
  opencv4.findCannyEdges(20,75);
  canny7 = opencv4.getSnapshot();
  
  opencv4.loadImage(dst4);
  opencv4.findCannyEdges(20,75);
  canny8 = opencv4.getSnapshot();
  
  
}

void draw() {
  fill(255,0,0);
  // 1 - r1+2
  image(src, 0, 0, imgW, imgH);
  image(h, imgW, 0, imgW, imgH);
  image(s, 0, imgH, imgW, imgH);
  image(v, imgW, imgH, imgW, imgH);
  
  text("Source", 75, 25); 
  text("Hue", imgW + 75, 25); 
  text("Saturation", 75, imgH + 25); 
  text("Value", imgW + 75, imgH + 25);
  //// 2 - r1+2
  pushMatrix();
  image(src, 2*imgW, 0, imgW, imgH);
  image(canny, 3*imgW, 0, imgW, imgH);
  image(scharr, 2*imgW, imgH, imgW, imgH);
  image(sobel, 3*imgW, imgH, imgW, imgH);
  popMatrix();
  
  text("Source", 2*imgW + 75, 25); 
  text("Canny", 3*imgW + 75, 25); 
  text("Scharr", 2*imgW + 75, imgH + 25); 
  text("Sobel", 3*imgW + 75, imgH + 25);
  // 3 - r1+2
  pushMatrix();
  image(src, 4*imgW, 0, imgW, imgH);
  image(canny2, 5*imgW, 0, imgW, imgH);
  image(canny3, 4*imgW, imgH, imgW, imgH);
  image(canny4, 5*imgW, imgH, imgW, imgH);
  popMatrix();
  
  text("Source", 4*imgW + 75, 25); 
  text("Hue - Canny", 5*imgW + 75, 25); 
  text("Saturation - Canny", 4*imgW + 75, imgH + 25); 
  text("Value - Canny", 5*imgW + 75, imgH + 25);
  // 4 - r3+4
 
  pushMatrix();
  image(dst, 0, 2*imgH, imgW, imgH);
  image(dst2, imgW, 2*imgH, imgW, imgH);
  image(dst3, 0, 3*imgH, imgW, imgH);
  image(dst4, imgW, 3*imgH, imgW, imgH);
  popMatrix();
  
  text("Source - Threshold", 75, 2*imgH + 25); 
  text("Hue - Threshold", imgW + 75, 2*imgH + 25); 
  text("Saturation - Threshold", 75, 3*imgH + 25); 
  text("Value - Threshold", imgW + 75, 3*imgH + 25);
 //// 5 - r3+4
 pushMatrix();
  image(canny5, 2*imgW, 2*imgH, imgW, imgH);
  image(canny6, 3*imgW, 2*imgH, imgW, imgH);
  image(canny7, 2*imgW, 3*imgH, imgW, imgH);
  image(canny8, 3*imgW, 3*imgH, imgW, imgH);
  popMatrix();
  
  text("Source - Threshold", 2*imgW + 75, 2*imgH + 25); 
  text("Hue - Threshold", 3*imgW + 75, 2*imgH + 25); 
  text("Saturation - Threshold", 2*imgW + 75, 3*imgH + 25); 
  text("Value - Threshold", 3*imgW + 75, 3*imgH + 25);
  ////
  pushMatrix();
  
  image(canny5, 4*imgW, 2*imgH, imgW, imgH);
  image(canny6, 5*imgW, 2*imgH, imgW, imgH);
  image(canny7, 4*imgW, 3*imgH, imgW, imgH);
  image(canny8, 5*imgW, 3*imgH, imgW, imgH);
  popMatrix();
      
}
