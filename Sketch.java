import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	PImage background;
  int intRectX = 150;
  int intRectY = 0;
  int intRectDir = 2;
  PImage plane;
  int intPlaneX = 0;
  int intPlaneY = 250;
  PImage bird;
  float cosAngle = 0;
  float sinAngle = 0;
  float intBirdX;
  float intBirdY;

  // Called once at the beginning of execution, put your size all in this method 
  public void settings() {
	  // Put your size call here
    size(600, 600);
    // Load images to be used later 
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

  /** 
   * A method that draws input to the screen; called repeatedly 
   */
  public void draw() {
	  // Reset background 
    image(background, 0, 0);
    
    // Draw a rectangle that bounces off the top and bottom edges 
    noStroke();
    fill(225, 115, 245);
    rect(intRectX, intRectY, 25, 75);
    intRectY += intRectDir;

    if(intRectY < 0 || intRectY + 75 > height){
      intRectDir *= -1;
    }

    // Draw a bird that moves in a circle 
    bird.resize(50, 50);
    image(bird, intBirdX, intBirdY);

    // Set the circle motion 
    cosAngle += 0.025;
    sinAngle += 0.025;
    if(cosAngle > 360 || sinAngle > 360){
      cosAngle = 1;
      sinAngle = 1;
    }
    
    intBirdX = 400 + cos(cosAngle) * 125;
    intBirdY = 250 + sin(sinAngle) * 125;

    // Draw a plane that moves repeatedly across the screen 
    plane.resize(100, 50);
    intPlaneX += 2;
    image(plane, intPlaneX, intPlaneY);

    if(intPlaneX > width){
      intPlaneX = 0;
    }

    // Detecting plane-rectangle collision 
    if(intPlaneX + 100 > intRectX && intPlaneX < intRectX + 50 && intPlaneY + 50 > intRectY && intPlaneY < intRectY + 100){
      intPlaneX = 0;
    }

    // Detecting plane-bird collision
    if(intPlaneX + 100 > intBirdX && intPlaneX < intBirdX + 50 && intPlaneY + 50 > intBirdY && intPlaneY < intBirdY + 50){
      intPlaneX = 0;
    }
  }
}
