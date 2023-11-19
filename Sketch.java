import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	PImage background;
  int intRectX = 150;
  int intRectY = 0;
  int intRectDir = 3;
  PImage plane;
  int intPlaneX = 0;
  int intPlaneY = 250;
  PImage bird;
  float cosAngle = 0;
  float sinAngle = 0;
  float X;
  float Y;

  // Called once at the beginning of execution, put your size all in this method 
  public void settings() {
	  // Put your size call here
    size(600, 600);
    // Set images to be used later 
    background = loadImage("Blue Sky w Clouds.jpg");
    plane = loadImage("Plane.png");
    bird = loadImage("Red Bird.png");
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    // Draw the image background
    image(background, 0, 0);
  }

  // Called repeatedly, anything drawn to the screen goes here 
  public void draw() {
	  // Reset background 
    image(background, 0, 0);
    
    // Draw a bouncing rectangle 
    noStroke();
    fill(225, 115, 245);
    rect(intRectX, intRectY, 25, 75);
    intRectY += intRectDir;

    if(intRectY < 0 || intRectY + 75 > height){
      intRectDir *= -1;
    }

    // Draw a bird that moves in a circle 
    bird.resize(50, 50);
    image(bird, X, Y);

    cosAngle += 0.025;
    sinAngle += 0.025;
    if(cosAngle > 360 || sinAngle > 360){
      cosAngle = 1;
      sinAngle = 1;
    }
    
    X = 400 + cos(cosAngle) * 125;
    Y = 250 + sin(sinAngle) * 125;

    // Draw a moving plane 
    plane.resize(100, 50);
    intPlaneX += 2;
    image(plane, intPlaneX, intPlaneY);

    if(intPlaneX > width){
      intPlaneX = 0;
    }

    // Detecting plane-rectangle collision 
    if(intPlaneX + 100 > intRectX && intPlaneX < intRectX + 50 && intPlaneY + 50 > intRectY && 
    intPlaneY < intRectY + 100){
      intPlaneX = 0;
    }

    //Detecting plane-bird collision
    if(intPlaneX + 100 > X && intPlaneX < X + 50 && intPlaneY + 50 > Y && 
    intPlaneY < Y + 50){
      intPlaneX = 0;
    }
  }
}
