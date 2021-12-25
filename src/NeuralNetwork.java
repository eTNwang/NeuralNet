//Eric
import java.util.ArrayList;

public class NeuralNetwork {

    public  ArrayList<AnswerNeuron> answerneurons= new ArrayList<>();
    public  ArrayList<HiddenNeuron> hiddenneurons = new ArrayList<>();
    ArrayList<Double> hiddenenuronoutputs = new ArrayList<>();
    ArrayList<Double> answerneuronoutpts = new ArrayList<>();
    final int numhiddenneurons;
    double learningrate= 0;
    int epochnum;



    NeuralNetwork (int numhiddenneurons, int numanswerneurons, int thenumsensors, double thelearningrate, ArrayList<String> possibleoutputs) {

        learningrate = thelearningrate;
        for(int x=0; x<numanswerneurons; x++){
            answerneurons.add(new AnswerNeuron(numhiddenneurons,learningrate, possibleoutputs.get(x), x));
            answerneuronoutpts.add(0.0);

        }


        for(int x=0; x<numhiddenneurons; x++){
            hiddenneurons.add(new HiddenNeuron(answerneurons, thenumsensors, learningrate, x));
            hiddenenuronoutputs.add(0.0);
        }
        System.out.println("this is the number of hiddenneurons " + hiddenneurons.size());

        //LOOP NOT REACHING LAST HIDDEN NEURON
        this.numhiddenneurons = numhiddenneurons;

    }

    public int classifyoneexample(ArrayList<Double> inputs){
        for(int x=0; x<numhiddenneurons; x++){
            hiddenneurons.get(x).updateinputs(inputs);
            hiddenenuronoutputs.set(x, hiddenneurons.get(x).getoutputvalue()); //change instead of add
        }
        for(int x= 0; x<answerneurons.size(); x++){
            answerneurons.get(x).updateinputs(hiddenenuronoutputs);
            answerneuronoutpts.set(x, answerneurons.get(x).getoutputvalue());
        }
        double currentlargest = 0;
        int currentlargestindex=0;
        for(int x=0; x<answerneurons.size(); x++){
            double currentvalue = answerneuronoutpts.get(x);
            if(currentvalue>currentlargest){
                currentlargest=currentvalue;
                currentlargestindex=x;
            }

        }
        return currentlargestindex; //this returns index of the answerneuron with the right category
        //answerneurons.get(currentlargestindex).category is the actual category

    }
    public double calculateaccuracy(ArrayList<ArrayList<Double>> testingdatainputs, ArrayList<Integer> testindataanswers){
        double correctcounter =0;
        for(int x=0; x<testingdatainputs.size(); x++){
            if(testindataanswers.get(x).equals(classifyoneexample(testingdatainputs.get(x)))){
                correctcounter++;
            }
        }
        return correctcounter/testingdatainputs.size(); //arguments for numepochs and an argument for interval of accuracy reporting

    }
    public void learnmanyexamples(ArrayList<Integer> learningdataanswers, ArrayList<ArrayList<Double>> learningdata, ArrayList<Integer> validationdataanswers, ArrayList<ArrayList<Double>> validationdata, ArrayList<Integer> testingdataanswers, ArrayList<ArrayList<Double>> testingdata, double givenboundary){

        int epochcounter = 0;


        while(calculateaccuracy(validationdata, validationdataanswers)<givenboundary && epochcounter < epochnum){
            for(int x = 0; x< learningdata.size(); x++){
                learnoneexample(learningdata.get(x), learningdataanswers.get(x));
            }

            System.out.println(calculateaccuracy(validationdata, validationdataanswers) + " this is our current validation training accuracy " + "at " + epochcounter+ " epochs");

            epochcounter++;
        }
        System.out.println(epochcounter+ " epochs passed");
        System.out.println(calculateaccuracy(testingdata, testingdataanswers) + " this is our final testing accuracy");
        //validation data is subset of training data
        //testing data completely separate

        //MISSING TESTING DATA, use testing data for final print, not validation data



    }

    public void categoryindextostring(double index){


    }


    public void learnoneexample(ArrayList<Double> theinputs, int thecorrectoutput){
        classifyoneexample(theinputs);
        for(int x=0; x< answerneurons.size(); x++){
            answerneurons.get(x).updateweights(thecorrectoutput);
        }
        for(int x=0; x<numhiddenneurons; x++){
            hiddenneurons.get(x).updateweights();
        }
        //System.out.println(hiddenneurons.size() + " this is num hidden neurons");


    }


}

// reappaearing weight values
//NOT RANDOMIZING CORRECTLY ???