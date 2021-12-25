//Eric
import java.util.ArrayList;

public class HiddenNeuron {

    double[] listofweights;
    ArrayList<Double> listofinputs;
    ArrayList<AnswerNeuron> answerneurons;
    double learningrate;
    int linknum;
    double biasweight;
    double hiddenresult;


//remove correct result
    HiddenNeuron(ArrayList<AnswerNeuron> someanswerneurons, int weightsnum, double learnrate,  int somelinknum){
        listofweights = new double[weightsnum];
        for(int x = 0; x<weightsnum; x++){
            double randnum = Math.random()*0.05;
            double plusormin = Math.random();
            if(plusormin>=0.5){
                listofweights[x] = randnum;
            }
            else{
                listofweights[x] = randnum*-1;
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
        answerneurons= someanswerneurons;
        linknum =somelinknum;

    }

    public void updateinputs(ArrayList<Double> input){
        listofinputs=input;

    }
    //make function to update list of inputs every time an example comes up

    public double getoutputvalue(){   //hidden neuron doesn't know correct output
        double totalinput = 0;

        for(int x = 0; x<listofweights.length; x++){ //possible size
            totalinput +=listofinputs.get(x)*listofweights[x];
        }
        totalinput+=biasweight;
        hiddenresult = 1/(1+Math.exp(-totalinput));
        return hiddenresult;


    }


    public void updateweights(){
        double errorsignal = 0;
        for(int x = 0; x<answerneurons.size(); x++){
            errorsignal += answerneurons.get(x).calcweightederrorsignal(linknum);
        }
        errorsignal= errorsignal*(hiddenresult)*(1-hiddenresult);
        for(int x= 0; x<listofweights.length; x++){
            listofweights[x] += errorsignal*learningrate*listofinputs.get(x);
            //System.out.println(listofweights[x] + "current weight");

        }
        biasweight+=errorsignal*learningrate;
        //bias weight

    }







}
