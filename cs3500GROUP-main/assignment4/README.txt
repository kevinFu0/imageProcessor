This image processor program is a model, view, controller design.
The ImageProcessorModelImpl stores images as 3d arrays where the index for each entry is the height,
width, and channels (R,G,B for ppm files) respectively. The model has 2 maps,
one that maps a name to the 3d array of pixel information and another that maps the name to
a maximum value. The code for reading, storing, modifying, and saving the images is all contained
in the model. We created an enum to represent the possibilities for red, green,
and blue color channels. Text-based user inputs are used to interact with the
ImageProcessController, which contains a map of commands
(classes which implement the command interface) to use with the model.
The TextView class is responsible for rendering messages as directed by the controller
to give the user feedback on what the image processor is doing and whether the inputs are valid.

The interface EnhancedImageProcessorModel extends the ImageProcessorWithCommand interface.
We made the choice to use inheritance because it seemed like the most convenient way to retain all
the previous functionality while adding the new methods that we needed. This choice also allowed
us to keep the old interface and implementation mostly as is. One change we did have to make was
switching the saveImage method from using string concatenation to now using an appendable
StringBuilder.

The new EnhancedImageProcessorModel interface has 2 new methods, applyFilter and applyTransformation.
Both methods take in a 2d array representing a kernel or a transformation matrix. This makes adding
a new filter or transformation as simple as adding a new Command that passes in the correct arguments.
So we added Commands for blur, sharpen, sepia, and greyscale and added them as script commands in the
controller.

This new interface is implemented by the class EnhancedProcessorImpl. This class implements the applyFilter
and applyTransformation classes. It also overrides the saveImage and loadImage methods so that they work
with ppm, bmp, png, and jpg images. These overridden methods check the file type in the string
representing the filepath and if it is a ppm file, calls the super versions of the methods that
originally handled these operations for ppm files. If not, it will either read or write a buffered
image to or from a 3d array representing the image data.

The TextController now uses the EnhancedImageProcessorModel interface as a field rather than the old
ImageProcessorModel interface. This was necessary in order to ensure that using filter and transformation
commands would be valid for the model given to the controller.

The main ProgramMain will also accept a script file as a command line option if the first argument is
-file and the second argument is the filename, but the program
is still interactive if no script file is given. An example script file with all commands as well as
the jar file is given in the res folder.

Image citation: The SunnyDay example image was created by myself, Jaden McMiller, for the purpose
of testing this program, so we are authorized to use it freely. The Bumblebee image was taken from
https://samplelib.com/sample-png.html






#This script loads an image, brightens it by 60, then saves it to the res directory
Example script (type in when the program runs):
load res/SunnyDay.ppm newSunny
brighten 60 newSunny newSunny2 save res/newSunny2.ppm newSunny2
q

