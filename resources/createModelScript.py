"""
modelAExample.py

A simple example: Creating a part.
"""

from abaqus import *
from abaqusConstants import *
backwardCompatibility.setValues(includeDeprecated=True,
                                reportDeprecated=False)
import sketch
import part

myModel = mdb.Model(name='Model A')