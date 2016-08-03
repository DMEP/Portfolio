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
// from user typing or from openvibe
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
    delay(400);
    } 
  }
}
