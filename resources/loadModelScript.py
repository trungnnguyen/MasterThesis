from abaqus import *
from abaqusConstants import *
import visualization

myViewport = session.Viewport(name='Superposition example',
    origin=(10, 10), width=150, height=100)

# Open the tutorial output database.

myOdb = visualization.openOdb(path='?')

# Associate the output database with the viewport.

myViewport.setValues(displayedObject=myOdb)

# Create variables that refer to the first two steps.

firstStep = myOdb.steps['Step-1']
secondStep = myOdb.steps['Step-2']
