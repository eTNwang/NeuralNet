//Eric
import java.io.FileReader;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


public class Main {


    // DISCLAIMER, THERE'S A BUG WITH THE HIDDEN NEURONS THAT CAUSES THE NETWORK TO ACT LIKE THERE'S ONE FEWER HIDDEN NEURON PRESENT
    // THAN THERE SHOULD BE. I COULD NOT FIND THE BUG, SO I JUST RUN IT WITH ONE MORE HIDDEN NEURON THAN I THINK IT NEEDS
    // THIS IS A CLEARLY SUBOPTIMAL STOP GAP MEASURE, BUT I HAVE IMPLEMENTED IT IN THE INTEREST OF SAVING TIME AND FOCUSING MY
    // ENERGY ON OTHER PARTS OF THE PROJECT. DISCLAIMER OVER




    public static void main(String[] args) {
        //learnMNISTHandwritten(5);
        //learnand(10000);
        learnxor(1000000000);
        //learnAKHandwritten(10);


    }
   static void learnMNISTHandwritten (int numepochs){

        //train-images-idx3-ubyte.gz:  training set images (9912422 bytes)
        //train-labels-idx1-ubyte.gz:  training set labels (28881 bytes)
        //t10k-images-idx3-ubyte.gz:   test set images (1648877 bytes)
        //t10k-labels-idx1-ubyte.gz:   test set labels (4542 bytes)

        List<Example> trainingExamples = readData("train-labels-idx1-ubyte", "train-images-idx3-ubyte");
        List<Example> testingExamples = readData("t10k-labels-idx1-ubyte", "t10k-images-idx3-ubyte");

       ArrayList<ArrayList<Double>> learningdata = new ArrayList<>();
       ArrayList<Integer> learningdataanswers = new ArrayList<>();
       ArrayList<ArrayList<Double>> testingdata = new ArrayList<>();
       ArrayList<Integer> testingdataanswers = new ArrayList<>();
       ArrayList<ArrayList<Double>> validationdata = new ArrayList<>();
       ArrayList<Integer> validationdataanswers = new ArrayList<>();

       ArrayList<String> allpossibleoutputs = new ArrayList<>();
       for(int x = 0; x<10; x++){
           allpossibleoutputs.add(String.valueOf(x));
       }

       for(int x = 0; x < trainingExamples.size(); x++){
           learningdata.add(trainingExamples.get(x).inputs);
           learningdataanswers.add(trainingExamples.get(x).correctoutput);
       }
       for(int x = 0; x < testingExamples.size(); x++){
           testingdata.add(testingExamples.get(x).inputs);
           testingdataanswers.add(testingExamples.get(x).correctoutput);
       }

       System.out.println(testingdata.size() + " is testing data size");
       System.out.println(learningdata.size() + " is learning data size");
       System.out.println(testingdataanswers.size() + " is testing data answers size");
       System.out.println(learningdataanswers.size() + " is learning data answers size");




       int validationbound = 60000/2;
       for(int x = 0; x<validationbound; x++){  //randmize this btw
           validationdata.add(learningdata.get(x));
           learningdata.remove(x);
       }
       for(int x = 0; x<validationbound; x++){

           validationdataanswers.add(learningdataanswers.get(x)); //remove validation from training
           learningdataanswers.remove(x);
       }

       double accuracybound = 1;
       NeuralNetwork neuralnet = new NeuralNetwork(100, 10, 784, 10.0, allpossibleoutputs);
       neuralnet.epochnum = numepochs;
       neuralnet.learnmanyexamples(learningdataanswers, learningdata, validationdataanswers, validationdata, testingdataanswers, testingdata, accuracybound);





    }


    static List<Example> readData(String labelFileName, String imageFileName) {
        DataInputStream labelStream = openFile(labelFileName, 2049);
        DataInputStream imageStream = openFile(imageFileName, 2051);

        List<Example> examples = new ArrayList<>();

        try {
            int numLabels = labelStream.readInt();
            int numImages = imageStream.readInt();
            assert(numImages == numLabels) : "lengths of label file and image file do not match";

            int rows = imageStream.readInt();
            int cols = imageStream.readInt();
            assert(rows == cols) : "images in file are not square";
            assert(rows == 28) : "images in file are wrong size";

            for (int i = 0; i < numImages; i++) {
                int categoryLabel = Byte.toUnsignedInt(labelStream.readByte());
                ArrayList<Double> inputs = new ArrayList<>();
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        int pixel = 255 - Byte.toUnsignedInt(imageStream.readByte());
                        inputs.add(r * rows + c, pixel / 255.0);
                    }
                }
                examples.add(new Example(inputs, categoryLabel));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return examples;
    }

    static DataInputStream openFile(String fileName, int expectedMagicNumber) {
        DataInputStream stream = null;
        try {
            stream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            int magic = stream.readInt();
            if (magic != expectedMagicNumber) {
                throw new RuntimeException("file " + fileName + " contains invalid magic number");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file " + fileName + " was not found");
        } catch (IOException e) {
            throw new RuntimeException("file " + fileName + " had exception: " + e);
        }
        return stream;
    }






    static void learnAKHandwritten(int numepochs){
       SimpleFile testing = new SimpleFile("digits-test.txt");
       SimpleFile training = new SimpleFile("digits-train.txt");
       ArrayList<ArrayList<Double>> learningdata = new ArrayList<>();
       ArrayList<Integer> learningdataanswers = new ArrayList<>();
       ArrayList<ArrayList<Double>> testingdata = new ArrayList<>();
       ArrayList<Integer> testingdataanswers = new ArrayList<>();
       ArrayList<ArrayList<Double>> validationdata = new ArrayList<>();
       ArrayList<Integer> validationdataanswers = new ArrayList<>();


       ArrayList<String> allpossibleoutputs = new ArrayList<>();
       for(int x = 0; x<10; x++){
           allpossibleoutputs.add(String.valueOf(x));
       }

       for (String line: testing){
           String[] linecontent = line.split(",", 1000);
           ArrayList<Double> inputdataset = new ArrayList<>();

           for(int x= 0; x<64; x++){
               inputdataset.add(Double.valueOf(linecontent[x]));

           }
           testingdata.add(inputdataset);
           testingdataanswers.add(Integer.valueOf(linecontent[64]));
       }
       for (String line: training){
           String[] linecontent = line.split(",", 1000);
           ArrayList<Double> inputdataset = new ArrayList<>();
           for(int x= 0; x<64; x++){
               inputdataset.add(Double.valueOf(linecontent[x]));

           }
           learningdata.add(inputdataset);
           learningdataanswers.add(Integer.valueOf(linecontent[64]));
       }
       System.out.println(testingdata.size() + " is testing data size");
       System.out.println(learningdata.size() + " is learning data size");
       System.out.println(testingdataanswers.size() + " is testing data answers size");
       System.out.println(learningdataanswers.size() + " is learning data answers size");


        //1797 is testing data size
        //3823 is learning data size
        //1797 is testing data answers size
        //3823 is learning data answers size

        int validationbound = 1797/5;
       for(int x = 0; x<validationbound; x++){  //randmize this btw
           validationdata.add(learningdata.get(x));
           learningdata.remove(x);
       }
        for(int x = 0; x<validationbound; x++){

            validationdataanswers.add(learningdataanswers.get(x)); //remove validation from training
            learningdataanswers.remove(x);
        }



        double accuracybound = 1;
        NeuralNetwork neuralnet = new NeuralNetwork(150, 10, 64, 0.05, allpossibleoutputs);
        neuralnet.epochnum = numepochs;
        neuralnet.learnmanyexamples(learningdataanswers, learningdata, validationdataanswers, validationdata, testingdataanswers, testingdata, accuracybound);

    }


    static void learnxor(int numepochs){

        ArrayList<Integer> learningdataanswers = new ArrayList<>();
        ArrayList<String> allpossibleoutputs = new ArrayList<>();
        allpossibleoutputs.add(0, "0");
        allpossibleoutputs.add(1, "1");
        learningdataanswers.add(0);
        learningdataanswers.add(1);
        learningdataanswers.add(1);
        learningdataanswers.add(0);
        ArrayList<ArrayList<Double>> learningdata = new ArrayList<>();
        ArrayList<Double> data1 = new ArrayList<>();
        data1.add(0.0); data1.add(0.0);
        ArrayList<Double> data2 = new ArrayList<>();
        data2.add(0.0); data2.add(1.0);
        ArrayList<Double> data3 = new ArrayList<>();
        data3.add(1.0); data3.add(0.0);
        ArrayList<Double> data4 = new ArrayList<>();
        data4.add(1.0); data4.add(1.0);
        learningdata.add(data1);
        learningdata.add(data2);
        learningdata.add(data3);
        learningdata.add(data4);
        ArrayList<Integer> validationataanswers = learningdataanswers;  //HIDDEN NODES NOT THE NUMBER YOU WANT
        ArrayList<ArrayList<Double>> validationdata = learningdata;
        double accuracybound = 1.0;   //has two inputs because you give it a zero or a 1
        NeuralNetwork neuralnet = new NeuralNetwork(500, 2, 2, 0.05, allpossibleoutputs);
        neuralnet.epochnum = numepochs;
        neuralnet.learnmanyexamples(learningdataanswers, learningdata, validationataanswers, validationdata, validationataanswers, validationdata, accuracybound);


    }

    static void learnand(int numepochs){

        ArrayList<Integer> learningdataanswers = new ArrayList<>();
        ArrayList<String> allpossibleoutputs = new ArrayList<>();
        allpossibleoutputs.add(0, "0");
        allpossibleoutputs.add(1, "1");
        learningdataanswers.add(0);
        learningdataanswers.add(0);
        learningdataanswers.add(0);
        learningdataanswers.add(1);
        ArrayList<ArrayList<Double>> learningdata = new ArrayList<>();
        ArrayList<Double> data1 = new ArrayList<>();
        data1.add(0.0); data1.add(0.0);
        ArrayList<Double> data2 = new ArrayList<>();
        data2.add(0.0); data2.add(1.0);
        ArrayList<Double> data3 = new ArrayList<>();
        data3.add(1.0); data3.add(0.0);
        ArrayList<Double> data4 = new ArrayList<>();
        data4.add(1.0); data4.add(1.0);
        learningdata.add(data1);
        learningdata.add(data2);
        learningdata.add(data3);
        learningdata.add(data4);
        ArrayList<Integer> validationataanswers = learningdataanswers;
        ArrayList<ArrayList<Double>> validationdata = learningdata;
//      double accuracybound = 1;   //has two inputs because you give it a zero or a 1
        NeuralNetwork neuralnet = new NeuralNetwork(2, 2, 2, 1, allpossibleoutputs);
        neuralnet.epochnum = numepochs;
        neuralnet.learnmanyexamples(learningdataanswers, learningdata, validationataanswers, validationdata,validationataanswers, validationdata, 1);


    }

    static void learnfromtext(String txtfile){


    }


}
