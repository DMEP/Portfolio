#include <Servo.h>
// Declare servo and variables
Servo one;
Servo two;
Servo three;
Servo four;
Servo five;

char input;
int onePos = 0;
int twoPos = 0;
int threePos = 0;
int fourPos = 0;
int fivePos = 0;

void setup()
{
  // Attach servo to pins
  one.attach(6);
  two.attach(5);
  three.attach(4);
  four.attach(3);
  five.attach(2);
    
  // Set servos to default position
  one.write(0);
  two.write(0);
  three.write(0);
  four.write(0);
  five.write(0);
  Serial.begin(9600);
  Serial.println("Ready for input");
}

// Loop iterates waiting for input either
// from user tpying or Emotiv Cognitive Suite
void loop()
{
  if (Serial.available()) {
    input = Serial.read();
    if (input == 'q') {     // Close whole hand
      closePos = 180;
      one.write(closePos);
      two.write(closePos);
      three.write(closePos);
      four.write(closePos);
      five.write(closePos);
    }
    if (input == 'a') {     // Open whole hand
      openPos = 0;
      one.write(openPos);
      two.write(openPos);
      three.write(openPos);
      four.write(openPos);
      five.write(openPos);
    }
    if (input == 'w') {     // Close one
      onePos = 180;
      one.write(onePos);
    }
    if (input == 's') {     // Open one
      onePos = 0;
      one.write(onePos);
    }
    if (input == 'e') {     // Close two 
      twoPos = 180;
      two.write(twoPos);
    }
    if (input == 'd') {    // Open two 
      twoPos = 0;
      two.write(twoPos);
    }
    if (input == 'r') {    // Close three 
      threePos = 180;
      three.write(threePos);
    }
    if (input == 'f') {    // Open three 
      threePos = 0;
      three.write(threePos);
    }
    if (input == 't') {   // Close four 
      fourPos = 180;
      four.write(fourPos);
    }
    if (input == 'g') {    // Open four 
      fourPos = 0;
      four.write(fourPos);
    } 
    if (input == 'y') {    // Close five
      fivePos = 180;
      five.write(fivePos);
    }
    if (input == 'h') {   // Open five
      fivePos = 0;
      five.write(fivePos);
    } 
  }
}
