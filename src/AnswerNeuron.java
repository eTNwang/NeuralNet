//Eric
import java.util.ArrayList;
import java.util.Random;

public class AnswerNeuron {
    double mostrecentoutput;
    double[] listofweights;
    private ArrayList<Double> listofinputs = new ArrayList<>(); //data from hidden neurons
    double learningrate;
    double biasweight;
    double errorsignal;
    int categoryindex;
    //String category;



    AnswerNeuron(int weightsnum, double learnrate, String thecategory, int thecategoryindex){ //inputs not in constructor
        listofweights = new double[weightsnum];
       // this.category = thecategory;

        for(int x = 0; x<weightsnum; x++){
            double randnum = Math.random()*0.05;
            double plusormin = Math.random();
            if(plusormin>=0.5){
                listofweights[x] = (randnum);
            }
            else{
                listofweights[x] = (randnum*-1);
            }
        }
        double randnum = Math.random()*0.05;
        double plusormin = Math.random();
        if(plusormin>=0.5){
            biasweight=randnum;
        }
        else{
            biasweight=randnum*-1;
        }
        learningrate=learnrate;
        this.categoryindex=thecategoryindex;



    }
    public void updateinputs(ArrayList<Double> input){
        listofinputs=input;

    }




    public double getoutputvalue(){   //hidden neuron doesn't know correct output
        double totalinput = 0;
        for(int x = 0; x<listofinputs.size(); x++){
            totalinput +=listofinputs.get(x)*listofweights[x];
        }
        totalinput+=biasweight;
        mostrecentoutput=1/(1+Math.exp(-totalinput));
        return mostrecentoutput;


    }
    //public String getcategory(){
       // return category; //the category index
   // }

    public void updateweights(int correctcategoryindex){
        double correctresult;
        if(correctcategoryindex == categoryindex){
            correctresult = 1;
        }
        else{
            correctresult = 0;
        }
        errorsignal = (correctresult- mostrecentoutput) * (mostrecentoutput) * (1 - mostrecentoutput);

        for(int x = 0; x<listofweights.length; x++){

            listofweights[x] += errorsignal*learningrate*listofinputs.get(x);

        }
        //System.out.println(listofweights.length + " num weights");

        biasweight+=errorsignal*learningrate;

    }
    public double calcweightederrorsignal(int linknum){
        return listofweights[linknum]*errorsignal;
    }







}
