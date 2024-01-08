package com.chitchat.matchmaking.logic;

import com.chitchat.matchmaking.utils.ArrayOperationsUtils;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HungarianLogicTest {

    @Test
    public void testHungarianLogic() {
        double[] data = {
                0,1.855,-1146.44454558142,-1146.44454558142,-3475.57312395978,-3475.57312395978,1.43782281969527,1.89048673284672,2.31679309781023,2.65783818978103,2.50469418223706,2.03052913221848,1.62410194648828,1.95124240045332,2.44833526724171,2.42208681062227,
                0,-1146.44454558142,-1146.44454558142,-3475.57312395978,-3475.57312395978,1.07881213896692,1.61765065927008,2.12921829722629,2.53847240759125,2.35905777401707,1.79005971399478,1.30234709111853,1.68726894705534,2.28378038720141,2.25008038020581,
                0,1.18055555555556,-3025.54919589638,-3025.54919589638,2.99339240071828,-1303.21129998813,-1303.21129998813,2.17837757743971,2.25808437323115,2.58488794100321,2.86500528480783,2.68487694845192,2.34227109343557,-1314.55356399285,
                0,-3025.54919589638,-3025.54919589638,0.662971721010411,1.44709609666992,2.19796547424604,2.79866097630695,2.54209223569972,1.70692756250447,0.991072128337116,1.54417009784193,2.41971839399482,2.36683242356241,
                0,2.50166666666667,9.38542534678808,8.82620483893146,8.23241084508233,7.75737565000303,7.89940060178766,8.55985604401007,9.12596070877214,8.79536944278914,8.10297828248015,8.17555493111006,
                0,5.12391523149588,6.59353942205875,7.97905874104004,9.08747419622507,8.59130630316352,7.05024360464457,5.72933272019975,6.78982263361828,8.40540200767259,8.31930561420408,
                0,1.67145926871844,1.32280090951131,-1005.05500840036,0.6275,1.58125,1.96083333333333,1.72692042913232,1.32330410670191,0.139464757616371,
                0,1.72178571428571,1.49678571428571,1.46974330947522,1.9408546561089,1.71932443509642,1.91338221751864,1.70846254268447,-1006.80316757079,
                0,1.74916666666667,1.7881330643503,1.79196908549337,1.29136832632232,1.69324075884956,1.9318529905919,-1006.80316757079,
                0,1.94924595235937,1.58482394376742,0.687194996288286,1.38564584399686,1.79426457892372,2.05642590457425,
                0,1.5675,0.500833333333333,-1010.29356100528,1.85977219670346,1.97251844570272,
                0,1.6075,1.86786982825065,-1010.29356100528,1.53607709139645,
                0,-1010.29356100528,1.27606792267661,0.0125,
                0,1.64675,1.2079160982114,
                0,1.91661802059624,
                0
        };

        HungarianLogic hungarianLogic = new HungarianLogic(data);
        runHungarian(hungarianLogic);

    }

    @Test
    public void testHungarianLogicOnReducedArray() {
        double[] data = {
                0,1.855,-1146.44454558142,-1146.44454558142,-3475.57312395978,-3475.57312395978,1.43782281969527,1.89048673284672,2.31679309781023,2.65783818978103,2.50469418223706,2.03052913221848,1.62410194648828,1.95124240045332,2.44833526724171,2.42208681062227,
                0,-1146.44454558142,-1146.44454558142,-3475.57312395978,-3475.57312395978,1.07881213896692,1.61765065927008,2.12921829722629,2.53847240759125,2.35905777401707,1.79005971399478,1.30234709111853,1.68726894705534,2.28378038720141,2.25008038020581,
                0,1.18055555555556,-3025.54919589638,-3025.54919589638,2.99339240071828,-1303.21129998813,-1303.21129998813,2.17837757743971,2.25808437323115,2.58488794100321,2.86500528480783,2.68487694845192,2.34227109343557,-1314.55356399285,
                0,-3025.54919589638,-3025.54919589638,0.662971721010411,1.44709609666992,2.19796547424604,2.79866097630695,2.54209223569972,1.70692756250447,0.991072128337116,1.54417009784193,2.41971839399482,2.36683242356241,
                0,2.50166666666667,9.38542534678808,8.82620483893146,8.23241084508233,7.75737565000303,7.89940060178766,8.55985604401007,9.12596070877214,8.79536944278914,8.10297828248015,8.17555493111006,
                0,5.12391523149588,6.59353942205875,7.97905874104004,9.08747419622507,8.59130630316352,7.05024360464457,5.72933272019975,6.78982263361828,8.40540200767259,8.31930561420408,
                0,1.67145926871844,1.32280090951131,-1005.05500840036,0.6275,1.58125,1.96083333333333,1.72692042913232,1.32330410670191,0.139464757616371,
                0,1.72178571428571,1.49678571428571,1.46974330947522,1.9408546561089,1.71932443509642,1.91338221751864,1.70846254268447,-1006.80316757079,
                0,1.74916666666667,1.7881330643503,1.79196908549337,1.29136832632232,1.69324075884956,1.9318529905919,-1006.80316757079,
                0,1.94924595235937,1.58482394376742,0.687194996288286,1.38564584399686,1.79426457892372,2.05642590457425,
                0,1.5675,0.500833333333333,-1010.29356100528,1.85977219670346,1.97251844570272,
                0,1.6075,1.86786982825065,-1010.29356100528,1.53607709139645,
                0,-1010.29356100528,1.27606792267661,0.0125,
                0,1.64675,1.2079160982114,
                0,1.91661802059624,
                0
        };

        int size = (int) Math.sqrt(2 * data.length);

        double[][] inputArray = ArrayOperationsUtils.createSymmetricMatrix(data, size);
//        ArrayOperationsUtils.printMatrix(inputArray);
        int[] col = new int[] {0,2,4,5,7,8,10};
        int[] row = new int[] {1,3,6,9,11,14,15};
        int[] colRow = ArrayOperationsUtils.appendArrays(col, row);
        double[][] output = ArrayOperationsUtils.removeRowsAndColumns(inputArray, colRow, colRow);

        ArrayOperationsUtils.printMatrix(output);

        HungarianLogic hungarianLogic = new HungarianLogic(output);
        runHungarian(hungarianLogic);
    }

    private static void runHungarian(HungarianLogic hungarianLogic) {
        Pair<Map<Integer, Integer>,Map<Integer, Double>>  selectedPairs = hungarianLogic.findOptimalAssignment();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        // Print the selected pairs
        System.out.println("Selected Pairs:");
        for (Map.Entry<Integer, Integer> pair : selectedPairs.getKey().entrySet()) {
            System.out.println("Col:" + (char)('A'+pair.getKey()) + " ,Row:" + (char)('A'+pair.getValue()) + " = " +
                    df.format(selectedPairs.getValue().get(pair.getKey())));
        }
    }
}
