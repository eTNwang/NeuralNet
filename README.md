# NeuralNet
A Brief Project Summary

During the course of this project, I’ve written a neural net with the ability to perform the following assignment tasks
Learn the AND boolean expression to an accuracy of 100% in 85 epochs with a learning rate of 1.0 and the use of 2 hidden neurons
Learn the XOR boolean expression to an accuracy of 100% in around 25,000-40,000 epochs with a learning rate of 0.05 and the use of 2 hidden neurons. 
Learn to classify digits from the Alpaydin and Kaynak’s Handwritten Digits set to a validation accuracy of around 97% and a testing accuracy of around 96% in 10 epochs with a learning rate of 0.05 and the use of 150 hidden neurons
Learn to classify digits from the MNIST Handwritten Digits set to a validation accuracy of around 91% in 5 epochs with a learning rate of 0.05 and the use of 150 hidden neurons


Experiment Log

The main focus of the experiments that I carried out with my neural network was to precisely determine the impact of the number of hidden neurons and the learning rate of the network on the accuracy of the neural net. 
Here are my experimental results:

Application: Digit Recognition, trained on Alpaydin and Kaynak’s Handwritten Digits Set

Testing Accuracy
Final Training Accuracy
Learning Rate
# of Hidden Neurons
0.9604897050639956
0.9777158774373259
0.05
2
0.9627156371730662
0.9805013927576601
0.05
4
0.9604897050639956
0.9749303621169917
0.05
6
0.9588202559821926
0.9805013927576601
0.05
8
0.9621591541457986
0.9749303621169917
0.05
10
0.9577072899276572
0.9749303621169917
0.05
20
0.9593767390094602
0.9805013927576601
0.05
30
0.9599332220367279
0.9777158774373259
0.05
40
0.9599332220367279
0.9721448467966574
0.05
50
0.9560378408458542
0.9749303621169917
0.05
60
0.9582637729549248
0.9749303621169917
0.05
100
0.9621591541457986
0.9805013927576601
0.05
150
0.9593767390094602
0.9777158774373259
0.05
300
0.9588202559821926
0.9805013927576601
0.05
600



Testing Accuracy
Final Training Accuracy
Learning Rate
# of Hidden Neurons
0.9577072899276572
0.9777158774373259
0.000001
100
0.9632721202003339
0.9777158774373259
0.00001
100
0.9610461880912632
0.9721448467966574
0.0001
100
0.9604897050639956
0.9749303621169917
0.001
100
0.9577072899276572
0.9693593314763231
0.01
100
0.9604897050639956
0.9777158774373259
0.02
100
0.9571508069003896
0.9832869080779945
0.03
100
0.9654980523094046
0.9749303621169917
0.04
100
0.9616026711185309
0.9749303621169917
0.05
100
0.9604897050639956
0.9777158774373259
0.1
100
0.9627156371730662
0.9749303621169917
0.5
100
0.9627156371730662
0.9805013927576601
1.0
100
0.9560378408458542
0.9749303621169917
5.0
100
0.9599332220367279
0.9721448467966574
10.0
100




Conclusions:

It seems that for applications that involve learning from relatively large data sets, neither learning rate nor hidden neuron amount seem to have much of any impact on learning rate or learning accuracy. 

Application: Use of a neural network to learn the XOR boolean operation.
Note: “Never reaches completion” means that the accuracy didn’t reach 1.0 after 10000000 epochs


Epochs to 100% Accuracy
Learning Rate
# of Hidden Neurons
35994
0.05
2
35090
0.05
4
30430
0.05
6
32254
0.05
8
28707
0.05
10
29110
0.05
20
26582
0.05
30
26620
0.05
40
24311
0.05
50
21236
0.05
60
19889
0.05
100
17844
0.05
150
15418
0.05
300
16563
0.05
400
Never Reaches Completion
0.05
1000
Never Reaches Completion
0.05
2000




Epochs to 100% Accuracy
Learning Rate
# of Hidden Neurons
Never reaches completion 
0.000001
2
Never reaches completion
0.00001
2
Never reaches completion
0.0001
2
Never reaches completion
0.001
2
479687
0.01
2
144460
0.02
2
127371
0.03
2
55709
0.04
2
35707
0.05
2
12581
0.1
2
667
1
2
130086
10
2
Never reaches completion
100
2
Never reaches completion
1000
2


Conclusion:

Unlike our first set of experiments, our experiments with a neural net’s ability to learn the XOR boolean operation returned far more interesting results. 

Our examination of hidden neuron impact seemed to suggest that having too small a number of hidden neurons in this application would result in the neural net taking longer to achieve a target accuracy, and gradually increasing the hidden neuron count had the capability to significantly speed up the neural net. However, after a certain threshold (in this case 400 neurons), the benefit conferred by the addition of more hidden neurons seemed to plateau before heading in the opposite direction and drastically increasing learning times. These results seem consistent with our understanding of how hidden neurons function, as too many hidden neurons will result in our network learning more parameters than it needs to for efficient functioning while having too few hidden neurons will result in our network considering far fewer parameters than necessary. 

Our examination of learning rate impact seemed to echo the results of our examination of hidden neuron impact, with extreme learning rates completely hamstring the ability for our network to function and more moderate learning rates around 1.0 resulting in the smallest epoch requirements for reaching a target accuracy. This is consistent with our understanding of how learning rates function, as an inordinately small learning rate would result in the network learning too slowly, while an extremely high learning rate would cause the network to learn far more quickly, at the cost of arriving at less accurate sets of weights each epoch. 





