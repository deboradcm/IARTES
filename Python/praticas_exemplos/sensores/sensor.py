class Sensor:
    def __init__(self, entrada, saida):
        self.entrada = entrada
        self.saida = saida
        
    
    def setEntrada(self, entrada):
        self.entrada = entrada

    def getEntrada(self):
        return self.entrada
    
    def setSaida(self, saida):
        self.saida = saida

    def getSaida(self):
        return self.saida
    

