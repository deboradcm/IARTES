class Animal:
    def __init__(self, genero, especie):
        self._genero = genero
        self._especie = especie
        
    
    # Método público para obter o genero (encapsulando o acesso direto ao atributo _genero)
    def getGenero(self):
        return self._genero
    
    # Método público para definir o genero (encapsulando a modificação direta do atributo _genero)
    def setGenero(self, genero):
        if genero == "homo":
            self._genero = genero
        elif genero == "canis":
            self._genero = genero
        elif genero == "felis":
            self._genero = genero
        else:
            print("Gênero inválido, tente uma destas opções: homo, canis ou felis")

   # Método público para obter o especie (encapsulando o acesso direto ao atributo _especie)
    def getEspecie(self):
        return self._especie
    
    # Método público para definir o genero (encapsulando a modificação direta do atributo _genero)
    def setEspecie(self, especie):
        if especie == "sapiens":
            self._especie = especie
        elif especie == "neanderthalensis":
            self._especie = especie
        elif especie == "erectus":
            self._especie == especie
        elif especie == "habilis":
            self._especie = especie
        elif especie == "lupus":
            self._especie = especie
        elif especie == "familiaris":
            self._especie = especie
        elif especie == "catus":
            self._especie = especie
        elif especie == "silvestris":
            self._especie = especie
        else:
            print("Espécie inválida")

    def fazer_som(self):
        pass


