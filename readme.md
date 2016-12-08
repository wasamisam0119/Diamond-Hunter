##A Simple Mapviewer application   
    
###Using Javafx to implement   
    
<h1>heelo</h1>        
####Usage:   
![Example](https://cl.ly/0r1K2x3r3g3R)

There are two buttons Boat and Axe.     
1. Firstly, chose a item that you want to set, just simply click on it.     
2. Click on the map, and the item will be set if the place is available and you can only set one item at each type, you can not set two axes. The coordinates of the item on be displayed on the screen.         
3. Press Save&Exit Button and the application will exit.   
4. If you run the game, the item will appear on where you set it.     
    
**Note:**  If you open the applicaiton again, it will read and display the item you set last time.

####Class:    

Basically, I use MVC Design Pattern to split the user interface, model and event.       
    
GameMap is mainly to load the map and draw the map initially.    
   
Control Class is used to handle events and operate model    

Tuple is a representation of (x, y) coordinates, and I use a HashMap to maintain a reflection: Integer->Tuple, 0 represents Boat and 1 represents Axe.

Start Class is to lauch the application and set scene.  
  

####Testing:  
    
Add some simple testing to test the setter, and check if map is loaded correctly.

