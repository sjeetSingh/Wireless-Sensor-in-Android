#Wireless-Sensor-in-Android

#### About this project
In this project, we have sensor tags positioned along different positions in the building. Each sensor tag is programmed to emit
a Blueeoth signal. Through a digital map created in the Android app, we are connecting to these signals. On selecting a sensor tag, the URL broadcasted by the sensor tag would be fetched. This is implemented using Google's Physical Web technology.

##### How we did it?
The project requires us to create a digital map which displays the sensortags and location of the sensor tags. Using a customized Android app, the digital map should be displayed. The digital map with the help of RSSI (or Received Signal Strength Indication) value, we can calculate the distance between the user and sensortags. The distance between the user and the sensortags can further be optimized using localization algorithm.
