Rede neural de perceptrons com três camadas, 
com a camada de entrada possuindo 16 neurônios, 
a camada intermediária 13 e a camada de saída 10. 

O processo de aprendizagem se dá pela aplicação do algoritmo de retropropagação, 
cada linha do arquivo dos dados de treinamento é inserida na camada de entrada 
a qual propaga esses dados até a camada de saída utilizando como função de ativação a função sigmoide. 

Após a propagação, os erros são calculados (utilizando a classe informada no arquivo de treinamento) 
e propagados por toda a rede através do modelo dummy, e então os pesos são atualizados, 
esse processo de aprendizagem ocorre para todo o arquivo de treinamento de acordo com 
o parâmetro número de iterações.


Com a rede treinada, os dados do arquivo de teste são aplicados na rede, 
uma matriz de confusão é gerada e algumas estatísticas são calculadas 
de acordo com a matriz de confusão, as quais são apresentadas na página seguinte.