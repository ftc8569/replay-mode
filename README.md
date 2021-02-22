# replay-mode

a simple and intuitive way to write you autonomous movements in as little as 30 seconds. 
Drive around the field and hit the button for waypoints where you want them, 
take the file that creates and run it through then interpreter, 
and copy paste the code into your auto where you need.

Replay Mode is built as an add on of sorts to RoadRunner, and requires it for functionality.

# installation

to install Replay Mode, either download it as a zip and then extract it or clone it, 
and then take the class in the save to file folder, copy paste that into android studio,
and change the `extends ScrimmageTeleOp` to `extends` and then whatever the name of your TeleOp you wanna use it with.
To use the interpreter, you can either open it in IntelliJ and use the command line args that are annoying to get to (if you knwo how to do this), 
or open cmd and navigate to ReplayMode\out\production\ReplayMode using the `cd [directory]` command, and type
`java com.roboknights.Main [input file path] [output file path]` which is not much easier, but hey, it works.

# usage

When you run the Replay Mode OpMode, it should be just like your TeleOp,
but when you hit whatever button you set it to, it saves a line to a file with your current x, y, and heading.
That file you can then find using the Android Studio device file explorer, save to your computer, and then give it as
the `[input file]` arg for the interpreter. This will give you both the Roadrunner Trajectory variables for each of those,
and the followTrajectory lines for wherever you want them. I would reccomend being comfortable withy roadrunner before trying to use this, 
but even if you aren't, just put the trajectory definitions in init, and then the followTrajectory() call wherever you want that trajectory. 
Feel free to add other code for arms and things in between.
