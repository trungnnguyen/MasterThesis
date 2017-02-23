package commands;

/**
 * Created by fkuba on 21.02.2017.
 */
public class CubeCommands {

    private static String createCube =
            "mdb.models['Model-1'].ConstrainedSketch(name='__profile__', sheetSize=40.0)\n" +
                    "mdb.models['Model-1'].sketches['__profile__'].rectangle(point1=(0.0, 0.0), \n" +
                    "point2=(10.0, 10.0))\n" +
                    "mdb.models['Model-1'].Part(dimensionality=THREE_D, name='Part-1', type=\n" +
                    "DEFORMABLE_BODY)\n" +
                    "mdb.models['Model-1'].parts['Part-1'].BaseSolidExtrude(depth=10.0, sketch=\n" +
                    "mdb.models['Model-1'].sketches['__profile__'])\n" +
                    "del mdb.models['Model-1'].sketches['__profile__']\n";

    public static String getCreateCube() {
        return createCube;
    }

}
