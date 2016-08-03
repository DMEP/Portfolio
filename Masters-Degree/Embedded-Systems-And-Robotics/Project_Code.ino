//Declare libraries
#include <Servo.h>

//Declare variables and servos
char input = ' ';
int stepSize = 15;
char inData[20];
int index = 0;

Servo one;
Servo two;
Servo three;
Servo four;
Servo five;


void setup() {
  Serial.begin(9600);
  one.attach(8);
  two.attach(9);
  three.attach(10);
  four.attach(11);
  five.attach(12);
  //Center all servos
  one.write(90);
  two.write(180);
  three.write(100);
  four.write(50);
  five.write(100);
  delay(1000);
  Serial.println("ready to program");
}

void loop() {
  delay(200);
  if(input == ' '){return;}
  int onePos = one.read();
  int twoPos = two.read();
  int threePos = three.read();
  int fourPos = four.read();
  int fivePos = five.read();

  switch(input){
    case 'q':onePos += stepSize; //Rotate Base Right
    break;
    case 'a':onePos -= stepSize;//Rotate Base Left
    break;
    
    case 'w':twoPos += stepSize;//Second Joint Up
    break;
    case 's':twoPos -= stepSize;//Second Joint Down
    break;
    
    case 'e':threePos += stepSize;//Third Joint Up
    break;
    case 'd':threePos -= stepSize;//Third Joint Down
    break;
    
    case 'r':fourPos += stepSize;//Fourth Joint Up
    break;
    case 'f':fourPos -= stepSize;//Fourth Joint Down
    break;
    
    case 't':fivePos += stepSize;//Close Gripper
    break;
    case 'g':fivePos -= stepSize;//Oppen Gripper
    break;
    }
  onePos = constrain(onePos, 0, 180);
  twoPos = constrain(twoPos, 0, 180);
  threePos = constrain(threePos, 0, 180);
  fourPos = constrain(fourPos, 0, 180);
  fivePos = constrain(fivePos, 0, 180);

  if(onePos != one.read()){
    Serial.println("writing onePos: " + (String) onePos);
    one.write(onePos);
    }
  if(twoPos != two.read()){
    Serial.println("writing twoPos: " + (String) twoPos);
    two.write(twoPos);
    }
  if(threePos != three.read()){
    Serial.println("writing threePos: " + (String) threePos);
    three.write(threePos);
    }
  if(fourPos != four.read()){
    Serial.println("writing fourPos: " + (String) fourPos);
    four.write(fourPos);
    }
  if(fivePos != five.read()){
    Serial.println("writing fivePos: " + (String) fivePos);
    five.write(fivePos);
    }

  if (input == 'h'){
    // Start playback
    // Parse movements and feed each successive character to the replay function.
    int indexC = 0;
    do
    {
      Replay(inData[indexC]);
    } 
    while(inData[indexC++] != 'h');
    delay(20);
    } 
  
  if (input == 'y'){
    // Reset all servos
    one.write(90);
    two.write(180);
    three.write(100);
    four.write(50);
    five.write(100);
  } 
  
  if (input == 'p'){
    // Clear the char array
    for( int i = 0; i < sizeof(inData);  ++i ){
      inData[i] = (char)0;    
    }
  }
  // Clear the input char each loop
  input = ' ';
}

/*
SerialEvent occurs whenever a new data comes in the
serial monitor. This routine is run between each
time loop() runs, so using delay inside loop can delay
response. Multiple bytes of data may be available.
*/
void serialEvent() {
  while (Serial.available()) {
    // get the new byte:
    char inChar = (char)Serial.read();
    input = inChar;
    // Store it in char array
    inData[index] = inChar;
    // Increment where to write next
    index++;
    }
}
/*
Replay runs whenever the user inputs the character 'h'
into the serial monitor it will run this method. It is essentially
the same as the loop method except it uses the char array inData
and is separate to the main loop
*/
void Replay(char inData){
  int onePos = one.read();
  int twoPos = two.read();
  int threePos = three.read();
  int fourPos = four.read();
  int fivePos = five.read();
  
  switch(inData){
    case 'q':onePos += stepSize;//Rotate Base Right
    break;
    case 'a':onePos -= stepSize;//Rotate Base Left
    break;
    
    case 'w':twoPos += stepSize;//Second Joint Up
    break;
    case 's':twoPos -= stepSize;//Second Joint Down
    break;
    
    case 'e':threePos += stepSize;//Third Joint Up
    break;
    case 'd':threePos -= stepSize;//Third Joint Down
    break;
    
    case 'r':fourPos += stepSize;//Fourth Joint Up
    break;
    case 'f':fourPos -= stepSize;//Fourth Joint Down
    break;
    
    case 't':fivePos += stepSize;//Close Gripper
    break;
    case 'g':fivePos -= stepSize;//Oppen Gripper
    break;
    }
    onePos = constrain(onePos, 0, 180);
    twoPos = constrain(twoPos, 0, 180);
    threePos = constrain(threePos, 0, 180);
    fourPos = constrain(fourPos, 0, 180);
    fivePos = constrain(fivePos, 0, 180);
    
    if(onePos != one.read()){
      Serial.println("writing onePos: " + (String) onePos);
      one.write(onePos);
      delay(200);
      }
    if(twoPos != two.read()){
      Serial.println("writing twoPos: " + (String) twoPos);
      two.write(twoPos);
      delay(200);
      }
    if(threePos != three.read()){
      Serial.println("writing threePos: " + (String) threePos);
      three.write(threePos);
      delay(200);
      }
    if(fourPos != four.read()){
      Serial.println("writing fourPos: " + (String) fourPos);
      four.write(fourPos);
      delay(200);
      }
    if(fivePos != five.read()){
      Serial.println("writing fivePos: " + (String) fivePos);
      five.write(fivePos);
      delay(200);
      }
}


