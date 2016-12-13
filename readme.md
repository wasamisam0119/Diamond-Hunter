<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">

</head>
<body>
<h2>A Simple Mapviewer application</h2>

<h3>Using Javafx to implement</h3>

<h4>Usage:</h4>

<p><img src="https://i.imgsafe.org/03f2d5d5d5.png" alt="Example" /></p>

<p>There are two buttons Boat and Axe.   <br/>
1. Firstly, chose a item that you want to set, just simply click on it.   <br/>
2. Click on the map, and the item will be set if the place is available and you can only set one item at each type, you can not set two axes. The coordinates of the item on be displayed on the screen.       <br/>
3. Press Save Button and the application will save the change. <br/>
4. Then Click StartGme Button to start the main game.  <br/ >
5. If you run the game, the item will appear on where you set it.</p>     


<p><strong>Note:</strong>  If you open the applicaiton again, it will read and display the item you set last time.</p>

<h4>Class:</h4>

<p>Basically, I use MVC Design Pattern to split the user interface, model and event.</p>

<p>GameMap is mainly to load the map and draw the map initially.</p>

<p>Control Class is used to handle events and operate model</p>

<p>Tuple is a representation of (x, y) coordinates, and I use a HashMap to maintain a reflection: Integer->Tuple, 0 represents Boat and 1 represents Axe.</p>

<p>Start Class is to lauch the application and set scene.</p>

<h4>Testing:</h4>

<p>Add some simple testing to test the setter, and check if map is loaded correctly.</p>
</body>
</html>