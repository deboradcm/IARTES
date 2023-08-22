import pytest
from lib.animal import Animal

def test_setEspecie():
    a = Animal("homo","sapiens")
    especies_validas = ["sapiens", "neanderthalensis", "erectus", "habilis", "lupus","familiaris", "catus", "silvestris"]

    for especie in especies_validas:
        a.setEspecie(especie)
        assert a.getEspecie() == especie

    a.setEspecie("invalida")
    assert a.getEspecie() != "invalida"  # a especie não deve mudar para "invalida"
    
    
    
    
    
    