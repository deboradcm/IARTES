from sensor import Sensor

class SensorTemperatura(Sensor):
    def __init__(self, nome, entrada, saida):
        super().__init__(entrada, saida)
        self.nome = nome

    def getNome(self):
        return self.nome
    
    def setNome(self, nome):
        self.nome = nome

if __name__ == "__main__":
    descricao = SensorTemperatura("Termistor","Temperatura","resistência")





    print(descricao.nome)  