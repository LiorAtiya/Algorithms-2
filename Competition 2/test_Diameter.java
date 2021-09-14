public class test_Diameter {
    public static void main(String[] args){
//        boolean[][] arr = {{false,true,true},{true,false,false},{true,false,false}};
//
//        Expected = 4
        boolean[][] matrix_of_angel = {{false,true,false,false,false,false,false,false},
                                      {true,false,true,true,false,false,false,false},
                                      {false,true,false,false,false,false,false,false},
                                      {false,true,false,false,true,true,true,false},
                                      {false,false,false,true,false,false,false,false},
                                      {false,false,false,true,false,false,false,false},
                                      {false,false,false,true,false,false,false,true},
                                      {false,false,false,false,false,false,true,false}};
//        Expected = 5
        boolean[][] matrix_of_angel_with_9 = {{false,true,false,false,false,false,false,false,false},
                                               {true,false,true,true,false,false,false,false,false},
                                               {false,true,false,false,false,false,false,false,false},
                                               {false,true,false,false,true,true,true,false,false},
                                               {false,false,false,true,false,false,false,false,false},
                                               {false,false,false,true,false,false,false,false,false},
                                               {false,false,false,true,false,false,false,true,false},
                                               {false,false,false,false,false,false,true,false,true},
                                               {false,false,false,false,false,false,false,true,false}};

        // 0 - 1 - 2 - 3
        boolean[][] line1 = {{false,true,false,false},
                             {true,false,true,false},
                             {false,true,false,true},
                             {false,false,true,false}};

        // 0 - 1 - 2 - 3 - 4
//        Expected = 4
        boolean[][] line2 = {{false,true,false,false,false},
                            {true,false,true,false,false},
                            {false,true,false,true,false},
                            {false,false,true,false,true},
                            {false,false,false,true,false}};

        //Expected = 3
        boolean[][] example_of_vadim = {{false,true,false,false,false,false},
                                        {true,false,true,true,false,false},
                                        {false,true,false,false,false,false},
                                        {false,true,false,false,true,true},
                                        {false,false,false,true,false,false},
                                        {false,false,false,true,false,false}};

        boolean[][] arr = {{false, true, false}, {true, false, true}, {false, true, false}};

        boolean[][] arr1 = {{false, true, false, false, false, false,},
                {true, false, true, true, false, false},
                {false, true, false, false, false, false},
                {false, true, false, false, true, true},
                {false, false, false, true, false, false},
                {false, false, false, true, false, false}};

        boolean[][] matrixTest1 = {
                {false, true, false, false, false, false},
                {true, false, true, true, false, false},
                {false, true, false, false, false, false},
                {false, true, false, false, true, true},
                {false, false, false, true, false, false},
                {false, false, false, true, false, false}
        };

        boolean t = true, f = false;
        boolean[][] matrixTest2 = {
                {f, f, f, t, f, f, t, f, f, f, f, f},
                {f, f, f, f, f, f, f, f, f, t, f, f},
                {f, f, f, f, f, f, f, f, t, f, f, t},
                {t, f, f, f, f, f, f, f, f, f, f, f},
                {f, f, f, f, f, t, f, f, t, t, f, f},
                {f, f, f, f, t, f, t, t, f, f, f, f},
                {t, f, f, f, f, t, f, f, f, f, f, f},
                {f, f, f, f, f, t, f, f, f, f, f, f},
                {f, f, t, f, t, f, f, f, f, f, t, f},
                {f, t, f, f, t, f, f, f, f, f, f, f},
                {f, f, f, f, f, f, f, f, t, f, f, f},
                {f, f, t, f, f, f, f, f, f, f, f, f}};

        boolean[][] matrixTest3 = {{f, t, f, f, f, f, t, f, f, f, f, f, f, f, f, f, f},
                                    {t, f, f, f, f, t, f, f, f, f, f, t, f, f, f, f, f},
                                    {f, f, f, f, t, f, f, f, f, t, f, f, f, f, f, f, f},
                                    {f, f, f, f, t, f, f, t, f, f, f, f, f, f, f, f, f},
                                    {f, f, t, t, f, t, f, f, f, f, f, f, f, f, f, f, f},
                                    {f, t, f, f, t, f, f, f, f, f, f, f, f, f, f, f, f},
                                    {t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f},
                                    {f, f, f, t, f, f, f, f, f, f, f, f, t, t, f, f, f},
                                    {f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f},
                                    {f, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f},
                                    {f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f},
                                    {f, t, f, f, f, f, f, f, t, f, t, f, f, f, f, f, f},
                                    {f, f, f, f, f, f, f, t, f, f, f, f, f, f, f, f, f},
                                    {f, f, f, f, f, f, f, t, f, f, f, f, f, f, t, f, f},
                                    {f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f},
                                    {f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t},
                                    {f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f}};

        //--------------------------------Algo of lioz-------------------------------//

        long startTime = System.nanoTime();

        for(int i = 0 ; i < 10000000 ; i++) {
            Diameter2 d = new Diameter2(line1);
//            System.out.println("line1:" + d.get_diam());
            d.get_diam();

            Diameter2 d1 = new Diameter2(line2);
//            System.out.println("line2: " + d1.get_diam());
            d1.get_diam();

            Diameter2 d2 = new Diameter2(matrix_of_angel);
//            System.out.println("matrix_of_angel: " + d2.get_diam());
            d2.get_diam();

            Diameter2 d3 = new Diameter2(matrix_of_angel_with_9);
//            System.out.println("matrix_of_angel_with_9: " + d3.get_diam());
            d3.get_diam();
            ///////////////////////////////////////////////////////////////////////

            Diameter2 d4 = new Diameter2(arr);
//            System.out.println("arr: " + d4.get_diam());
            d4.get_diam();

            Diameter2 d5 = new Diameter2(arr1);
//            System.out.println("arr1: " + d5.get_diam());
            d5.get_diam();

            Diameter2 l = new Diameter2(matrixTest1);
//            System.out.println("Test 1: " + ((l.get_diam() == 3) ? "Pass." : "Fail."));
            l.get_diam();

            Diameter2 d6 = new Diameter2(matrixTest2);
//            System.out.println("Test 2: " + ((d6.get_diam() == 7) ? "Pass." : "Fail."));
            d6.get_diam();

            Diameter2 p = new Diameter2(matrixTest3);
//            System.out.println("Test 3: " + ((p.get_diam() == 10) ? "Pass." : "Fail."));
            p.get_diam();

//            System.out.println("Test 4: " + ((p.get_diam() == 10) ? "Pass." : "Fail."));
//            p.get_diam();
        }

        System.out.println("Lioz's Algo : " + (System.nanoTime() - startTime));

        //------------------------------Calculate of Option 1------------------------------//
        startTime = System.nanoTime();

        for(int i = 0 ; i < 10000000 ; i++) {
            Diameter d = new Diameter(line1);
//            System.out.println("line1:" + d.get_diam());
            d.get_diam();

            Diameter d1 = new Diameter(line2);
//            System.out.println("line2: " + d1.get_diam());
            d1.get_diam();

            Diameter d2 = new Diameter(matrix_of_angel);
//            System.out.println("matrix_of_angel: " + d2.get_diam());
            d2.get_diam();

            Diameter d3 = new Diameter(matrix_of_angel_with_9);
//            System.out.println("matrix_of_angel_with_9: " + d3.get_diam());
            d3.get_diam();
            ///////////////////////////////////////////////////////////////////////

            Diameter d4 = new Diameter(arr);
//            System.out.println("arr: " + d4.get_diam());
            d4.get_diam();

            Diameter d5 = new Diameter(arr1);
//            System.out.println("arr1: " + d5.get_diam());
            d5.get_diam();

            Diameter l = new Diameter(matrixTest1);
//            System.out.println("Test 1: " + ((l.get_diam() == 3) ? "Pass." : "Fail."));
            l.get_diam();

            Diameter d6 = new Diameter(matrixTest2);
//            System.out.println("Test 2: " + ((d6.get_diam() == 7) ? "Pass." : "Fail."));
            d6.get_diam();

            Diameter p = new Diameter(matrixTest3);
//            System.out.println("Test 3: " + ((p.get_diam() == 10) ? "Pass." : "Fail."));
            p.get_diam();

//            System.out.println("Test 4: " + ((p.get_diam() == 10) ? "Pass." : "Fail."));
//            p.get_diam();
        }

        System.out.println("Lior's Algo : " + (System.nanoTime() - startTime));

//        //------------------------------Calculate of Option 2------------------------------//
//        startTime = System.nanoTime();
//
//        for(int i = 0 ; i < 100000 ; i++) {
//            Diameter d = new Diameter(line1);
//            System.out.println("line1:" + d.get_diam2());
////            d.get_diam2();
//
//            Diameter d1 = new Diameter(line2);
//            System.out.println("line2: " + d1.get_diam2());
////            d1.get_diam2();
//
//            Diameter d2 = new Diameter(matrix_of_angel);
//            System.out.println("matrix_of_angel: " + d2.get_diam2());
////            d2.get_diam2();
//
//            Diameter d3 = new Diameter(matrix_of_angel_with_9);
//            System.out.println("matrix_of_angel_with_9: " + d3.get_diam2());
////            d3.get_diam2();
//            ///////////////////////////////////////////////////////////////////////
//
//            Diameter d4 = new Diameter(arr);
//            System.out.println("arr: " + d4.get_diam2());
////            d4.get_diam2();
//
//            Diameter d5 = new Diameter(arr1);
//            System.out.println("arr1: " + d5.get_diam2());
////            d5.get_diam2();
//
//            Diameter l = new Diameter(matrixTest1);
//            System.out.println("Test 1: " + ((l.get_diam2() == 3) ? "Pass." : "Fail."));
////            l.get_diam2();
//
//            Diameter d6 = new Diameter(matrixTest2);
//            System.out.println("Test 2: " + ((d6.get_diam2() == 7) ? "Pass." : "Fail."));
////            d6.get_diam2();
//
//            Diameter p = new Diameter(matrixTest3);
//            System.out.println("Test 3: " + ((p.get_diam2() == 10) ? "Pass." : "Fail."));
////            p.get_diam2();
//
//            System.out.println("Test 4: " + ((p.get_diam2() == 10) ? "Pass." : "Fail."));
////            p.get_diam2();
//        }
//
//        System.out.println("Average time of option 2 : " + (System.nanoTime() - startTime));
    }
}
