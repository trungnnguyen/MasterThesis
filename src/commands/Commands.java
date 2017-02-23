package commands;

/**
 * Created by fkuba on 21.02.2017.
 */
public class Commands {
    private static String imports = "from abaqus import *\n" +
            "from abaqusConstants import *\n" +
            "import __main__\n" +
            "import section\n" +
            "import regionToolset\n" +
            "import displayGroupMdbToolset as dgm\n" +
            "import part\n" +
            "import material\n" +
            "import assembly\n" +
            "import step\n" +
            "import interaction\n" +
            "import load\n" +
            "import optimization\n" +
            "import job\n" +
            "import sketch\n" +
            "import visualization\n" +
            "import xyPlot\n" +
            "import displayGroupOdbToolset as dgo\n" +
            "import connectorBehavior\n";

    private static String createCube =
            "mdb.models['Model-1'].ConstrainedSketch(name='__profile__', sheetSize=40.0)\n" +
                    "mdb.models['Model-1'].sketches['__profile__'].rectangle(point1=(0.0, 0.0), \n" +
                    "point2=(10.0, 10.0))\n" +
                    "mdb.models['Model-1'].Part(dimensionality=THREE_D, name='Cube', type=\n" +
                    "DEFORMABLE_BODY)\n" +
                    "mdb.models['Model-1'].parts['Cube'].BaseSolidExtrude(depth=10.0, sketch=\n" +
                    "mdb.models['Model-1'].sketches['__profile__'])\n" +
                    "del mdb.models['Model-1'].sketches['__profile__']\n";

    private static String createMaterialSteel =
            "mdb.models['Model-1'].Material(name='Steel')\n" +
                    "mdb.models['Model-1'].materials['Steel'].Elastic(table=((210000.0, 0.3), ))\n";

    private static String createSection =
            "mdb.models['Model-1'].HomogeneousSolidSection(material='Steel', name=\n" +
                    "    'Section-1', thickness=None)\n";

    private static String assignSection =
            "mdb.models['Model-1'].parts['Cube'].Set(cells=\n" +
                    "    mdb.models['Model-1'].parts['Cube'].cells.getSequenceFromMask(('[#1 ]', ), \n" +
                    "    ), name='Set-1')\n" +
                    "mdb.models['Model-1'].parts['Cube'].SectionAssignment(offset=0.0, offsetField=\n" +
                    "    '', offsetType=MIDDLE_SURFACE, region=\n" +
                    "    mdb.models['Model-1'].parts['Cube'].sets['Set-1'], sectionName='Section-1', \n" +
                    "    thicknessAssignment=FROM_SECTION)\n";

    private static String createInstance = "mdb.models['Model-1'].rootAssembly.DatumCsysByDefault(CARTESIAN)\n" +
            "mdb.models['Model-1'].rootAssembly.Instance(dependent=ON, name='Cube-1', part=\n" +
            "    mdb.models['Model-1'].parts['Cube'])\n";

    private static String createStep = "mdb.models['Model-1'].StaticStep(name='Step-1', previous='Initial')\n";

    private static String createTopDisplacement = "mdb.models['Model-1'].rootAssembly.Set(faces=\n" +
            "    mdb.models['Model-1'].rootAssembly.instances['Cube-1'].faces.getSequenceFromMask(\n" +
            "    ('[#2 ]', ), ), name='Set-1')\n" +
            "mdb.models['Model-1'].DisplacementBC(amplitude=UNSET, createStepName='Step-1', \n" +
            "    distributionType=UNIFORM, fieldName='', fixed=OFF, localCsys=None, name=\n" +
            "    'topDisplacement', region=mdb.models['Model-1'].rootAssembly.sets['Set-1'], \n" +
            "    u1=0.0, u2=-5.0, u3=0.0, ur1=0.0, ur2=0.0, ur3=0.0)\n";


    private static String createBottomFix = "mdb.models['Model-1'].rootAssembly.Set(faces=\n" +
            "    mdb.models['Model-1'].rootAssembly.instances['Cube-1'].faces.getSequenceFromMask(\n" +
            "    ('[#8 ]', ), ), name='Set-2')\n" +
            "mdb.models['Model-1'].EncastreBC(createStepName='Step-1', localCsys=None, name=\n" +
            "    'bottomFix', region=mdb.models['Model-1'].rootAssembly.sets['Set-2'])\n";

    private static String applyMesh = "mdb.models['Model-1'].parts['Cube'].seedPart(deviationFactor=0.1, \n" +
            "    minSizeFactor=0.1, size=1.0)\n" +
            "mdb.models['Model-1'].parts['Cube'].generateMesh()\n";


    private static String createJob = "mdb.Job(atTime=None, contactPrint=OFF, description='', echoPrint=OFF, \n" +
            "    explicitPrecision=SINGLE, getMemoryFromAnalysis=True, historyPrint=OFF, \n" +
            "    memory=90, memoryUnits=PERCENTAGE, model='Model-1', modelPrint=OFF, \n" +
            "    multiprocessingMode=DEFAULT, name='squeezeSteelCube', nodalOutputPrecision=\n" +
            "    SINGLE, numCpus=1, numGPUs=0, queue=None, scratch='', type=ANALYSIS, \n" +
            "    userSubroutine='', waitHours=0, waitMinutes=0)\n";

    private static String save = "mdb.saveAs(pathName='C:/IdeaProjects/MasterThesis-Designer/resources/test')\n" +
            "a = mdb.models['Model-1'].rootAssembly\n" +
            "session.viewports['Viewport: 1'].setValues(displayedObject=a)\n" +
            "session.viewports['Viewport: 1'].assemblyDisplay.setValues(\n" +
            "optimizationTasks=OFF, geometricRestrictions=OFF, stopConditions=OFF)\n";

    private static String all = getCreateCube() + getCreateMaterialSteel()  + getCreateJob();


    public static String getImports() {
        return imports;
    }

    public static String getCreateCube() {
        return createCube;
    }

    public static String getApplyMesh() {
        return applyMesh;
    }

    public static String getSave() {
        return save;
    }

    public static String getCreateMaterialSteel() {
        return createMaterialSteel;
    }

    public static String getCreateSection() {
        return createSection;
    }

    public static String getAssignSection() {
        return assignSection;
    }

    public static String getCreateInstance() {
        return createInstance;
    }

    public static String getCreateStep() {
        return createStep;
    }

    public static String getCreateTopDisplacement() {
        return createTopDisplacement;
    }

    public static String getCreateBottomFix() {
        return createBottomFix;
    }

    public static String getCreateJob() {
        return createJob;
    }

    public static String getAll() {
        return all;
    }

}
