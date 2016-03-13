/*
  Blink
  Turns on an LED on for one second, then off for one second, repeatedly.

  Most Arduinos have an on-board LED you can control. On the Uno and
  Leonardo, it is attached to digital pin 13. If you're unsure what
  pin the on-board LED is connected to on your Arduino model, check
  the documentation at http://www.arduino.cc

  This example code is in the public domain.

  modified 8 May 2014
  by Scott Fitzgerald
 */

// digital pin 2 has a pushbutton attached to it. Give it a name:
int pushButton = 7;

  int led1 = 2;
  int led2 = 3;
  int led3 = 4;
  int mode = 0;
  int buttonState = 0;         // current state of the button
int lastButtonState = 0;     // previous state of the button



// the setup function runs once when you press reset or power the board
void setup() {
  
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);

  // make the pushbutton's pin an input:
  pinMode(pushButton, INPUT);
  
  // initialize serial communication at 9600 bits per second:
  //Serial.begin(9600);
  // make the pushbutton's pin an input:
  //pinMode(pushButton, INPUT);
}

// the loop function runs over and over again forever
void loop() {
    // read the input pin:
  //int buttonState = digitalRead(pushButton);
  // print out the state of the button:
  //Serial.println(buttonState);
  //delay(1);        // delay in between reads for stability
 
    // read the pushbutton input pin:
   buttonState = digitalRead(pushButton);

  // compare the buttonState to its previous state
  if (buttonState != lastButtonState) {
      lastButtonState = buttonState;
  }


  if (buttonState == HIGH)
  {
  digitalWrite(led1, HIGH);
  digitalWrite(led2, LOW);
  digitalWrite(led3, LOW);
  delay(1000);              // wait for a second digitalWrite(led1, HIGH);
  digitalWrite(led1, LOW);
  digitalWrite(led2, HIGH);
  digitalWrite(led3, LOW);

   delay(1000);              // wait for a second digitalWrite(led1, HIGH);
  digitalWrite(led1, LOW);
  digitalWrite(led2, LOW);
  digitalWrite(led3, HIGH);
  delay(1000);
  }
  else
  {
     delay(1000);              // wait for a second digitalWrite(led1, HIGH);
  digitalWrite(led1, LOW);
  digitalWrite(led2, HIGH);
  digitalWrite(led3, LOW);

  } 
  
}
