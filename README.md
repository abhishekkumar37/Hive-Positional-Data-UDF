# Missing Curve Prediction and Layers Identification

The objective of this project is to predict the missing logs as a function of other logs. 
The study of the pattern of the logs is instrumental in assessing the critical parameters of an oil well and thereby identifying the critical layers of hydrocarbon formation.

## Folder Structure</br>

```
│───TGS Curve Predictions
│───README.md
└───product
	│───Main.py
	│───Analyse_results.py
	│───Data_creation.py
	│───Execution.py
	│───Inputs.py	
	│───Model_library.py
	│───Plots.py
	│───Predictions.py		
	│───Train_score_params.py
	│───YAML.py
```

## Prerequisites

```
│───python>=3.6
└───Packages
    │───Pandas
    │───NumPy
    │───os
    │───math
    │───sys
    │───warnings
    │───glob
    │───lasio
    │───sklearn.model
    │───xgboost
    │───RandomForestRegressor
    │───MLPRegressor
    │───statsmodels.formula.api
    │───joblib
```

## Description

### 1. Steps to run the code
	
<br> 1.1 The execution goal (which type of execution is desired - Only Train, Only Scoring or Training and Scoring), the parameters of each execution goal and the paths must be defined before executing any code in 'Train_score_params.py' as per the comments mentioned. The name of the .yml file should be specified in YAML.py in case of any changes </br>
<br>1.2 Run the code 'Main.py' and obtain the outputs in the output path specified by the user in the 'Train_score_params.yml' according to the specified execution goal

### 2. Module Description

2.1 Train_score_params - Train_score_params.yml file is used to define the following:

  i. Execution_goal
<br>	a.Train           : 'TRAIN'
<br>	b.Score           : 'SCORE'
<br>	c.Train and Score : 'TRAIN_AND_SCORE'
	
  ii. Paths
  	a. scoring_file_path - Specify the path where the scoring .las files are present for scoring
  	b. trained_model_path - Specify the path where all the trained model pickle files are present
  	c. output_file_path - Specify the path where the predictions for each UWI and the scoring dataset are to be saved

  iii. Parameters
  	a. dependent_variable - Enter the corresponding mnemonic to the curve as mentioned below that needs to be predicted 
  		Sonic       :['SON']
  		Gamma       :['GAM']
  		Resistivity :['RES']
  		Neutron     :['NEU']
  	b. independent_variables - Choose any combination among the following based on your scenario(One curve present/ Two curves present/ All curves present) and the respective curves available:
	    One curve present: 
	    	Gamma
	    	Resistivity
	    	Neutron
	    Two curves present:
	    	Gamma, Neutron
	    	Gamma, Resistivity
	    	Neutron, Resistivity
	    All curves present:
	    	Gamma, Resistivity, Neutron
	c. Train_params:
		Input_file_path
		output_file_path
		Train_csv
		Validation_csv
		independent_variables
		model_name
		Model Parameters
	d. Scoring_params:
		scoring_file_path
		trained_model_path
		output_file_path
		independent_variables
		
2.2 Main - The Main code calls all the defined modules and execute the code to obtain the results according to the execution goal defined
	Python file - Main.py
	Output - a. For execution goal 'Train' - Trained Models, Analytical Dataset, Predictions, Plots          
	         b. For execution goal 'Score' - Scoring Dataset and Predictions          
	         c. For execution goal 'Train and Score' - Trained Models, Scoring Dataset and Predictions
						
2.3 YAML - This module loads the 'Train_score_params.yml' where the execution_goal, the parameters corresponding to the execution_goal and the paths are defined
	Python file - YAML.py
			
2.4 Analyse_results - Results (RMSEs) at UWIs for validation dataset are generated
	Python file - Analyse_results.py
	Output - A .csv containing the RMSEs corresponding to the UWIs
			
2.5 Dataset_Creation - This module aggregates multiple .Las files and selects the relevant features as specified in the .yml file for creation of master dataset and scoring dataset
   	Python file - Data_creation.py
			
2.6 Inputs - According to the dependent and independent variables in the .yml file,
	For execution goal 'Train' - The model trains according to the parameters defined in the .yml file 
	For execution goal 'Score' - Pickle file from the trained model folder for the case specified in the .yml file will be imported and the scoring process will be executed
	For execution goal 'Train and Score' - The model trains according to the parameters defined in the .yml file and the corresponding trained model is used to score for the LAS files as given in the scoring_file_path
	Python file - Inputs.py
			
2.7 Execution - This module calls input module for executing the goal specified in the .yml file
	Python file - Execution.py
	
2.8	Model_library.py - This module selects the model and the hyperparameters corresponding to that model according to the parameters in the .yml file
	Python file - Model_library.py 
	
2.9 Plots - This module plots for the top five UWIs based on the validation RMSEs
	Python file - Plots.py
	Output - .png files for top five UWIs
	
2.10 Predictions  - This module provides the predictions according to the execution goal
	For execution goal 'Train' - Predictions, RMSE and MAPE for training and validation dataset are obtained 
	For execution goal 'Score' - Predictions for scoring dataset is obtained
	For execution goal 'Train and Score' - Predictions for training, validation and scoring dataset are obtained along with training and validation RMSEs and MAPEs
	Python file -  Predictions.py
'''