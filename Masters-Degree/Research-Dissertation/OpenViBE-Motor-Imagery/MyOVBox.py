import serial

port = "COM1"
baud = 9600
ser = serial.Serial(port, baud, timeout=1)   

class MyOVBox(OVBox):
   def __init__(self):
      OVBox.__init__(self)
      
   def initialize(self):
      # open the serial port
      if ser.isOpen():
		print(ser.name + ' is open...')      
      return
      
   def process(self):
     for chunkIndex in range( len(self.input[0]) ):
         chunk = self.input[0].pop()
         if(type(chunk) == OVStimulationSet):
            for stimIdx in range(len(chunk)):
               stim=chunk.pop();
               print 'At time ', stim.date, ' received stim ', stim.identifier
	    if(stim.identifier == 0x301):
	       print 'left'
	       ser.write("c\n")		
	    elif(stim.identifier == 0x302):
	       print 'right'
	       ser.write("v\n")
         else:
            print 'Received chunk of type ', type(chunk), " looking for StimulationSet"
     return
      
   def uninitialize(self):
      # close connection
      ser.close()
      return

box = MyOVBox()
