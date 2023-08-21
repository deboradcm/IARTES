from animal import Animal

class Felinos(Animal): # Aqui está a herança explícita
    def __init__(self, nome, genero, especie):
        super().__init__(genero, especie)
        self.nome = nome

    def __str__(self):
        return f"O nome científico do animal {self.nome}, é {self._genero} {self._especie}"
    
    def fazer_som(self):
        print("miauuu")


#testando classe
objeto1 = Felinos("Rabinho","felis","catus")
objeto1.__str__()
print(objeto1)
objeto1.fazer_som()

objeto1.setGenero("Astragalus")
print(objeto1)

objeto1.setEspecie("Pretinha")
print(objeto1)

objeto1.setGenero("canis")
print(objeto1)

objeto1.setEspecie("familiaris")
print(objeto1)