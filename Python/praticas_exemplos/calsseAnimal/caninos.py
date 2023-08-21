from animal import Animal

class Caninos(Animal):
    def __init__(self, genero, especie, nome):
        super().__init__(genero, especie)
        self.nome = nome

    def __str__(self):
        return f"O nome científico do animal {self.nome}, é {self._genero} {self._especie}"
    
    def fazer_som(self):
        print("auuuuu")
    

objeto1 = Caninos("canis","lupus","Leide")
print(objeto1)
objeto1.fazer_som()