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
            "import mesh\n" +
            "import optimization\n" +
            "import job\n" +
            "import sketch\n" +
            "import visualization\n" +
            "import xyPlot\n" +
            "import displayGroupOdbToolset as dgo\n" +
            "import connectorBehavior\n";

    private static String createCube = "s = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', sheetSize=10.0)\n" +
            "g, v, d, c = s.geometry, s.vertices, s.dimensions, s.constraints\n" +
            "s.setPrimaryObject(option=STANDALONE)\n" +
            "s.rectangle(point1=(-1.0, -1.0), point2=(1.0, 1.0))\n" +
            "p = mdb.models['Model-1'].Part(name='Part-1', dimensionality=THREE_D, \n" +
            "    type=DEFORMABLE_BODY)\n" +
            "p = mdb.models['Model-1'].parts['Part-1']\n" +
            "p.BaseSolidExtrude(sketch=s, depth=2.0)\n" +
            "s.unsetPrimaryObject()\n" +
            "p = mdb.models['Model-1'].parts['Part-1']\n" +
            "session.viewports['Viewport: 1'].setValues(displayedObject=p)\n" +
            "del mdb.models['Model-1'].sketches['__profile__']\n";


    private static String mesh = "session.viewports['Viewport: 1'].partDisplay.setValues(mesh=ON)\n" +
            "session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(\n" +
            "    meshTechnique=ON)\n" +
            "session.viewports['Viewport: 1'].partDisplay.geometryOptions.setValues(\n" +
            "    referenceRepresentation=OFF)\n" +
            "p = mdb.models['Model-1'].parts['Part-1']\n" +
            "p.seedPart(size=0.2, deviationFactor=0.1, minSizeFactor=0.1)\n" +
            "p = mdb.models['Model-1'].parts['Part-1']\n" +
            "p.generateMesh()\n";

    private static String save = "mdb.saveAs(pathName='C:/IdeaProjects/MasterThesis-Designer/resources/test')";


    public static String getImports() {
        return imports;
    }

    public static String getCreateCube() {
        return createCube;
    }

    public static String getMesh() {
        return mesh;
    }

    public static String getSave() {
        return save;
    }
}
