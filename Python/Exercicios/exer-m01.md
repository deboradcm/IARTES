# Responda as questões abaixo Conceitos básicos

1. Escreva um script python que define duas variáveis do tipo inteiro (__int__)
e atribui um valor positivo para elas. Imprima as duas variáveis.

script:
a =10
b=5
print(a)
print(b)

2. Seja x = 2 + 4j, descubra qual é tipo associado a essa variável pelo interpretador.

script:
print(type(2 + 4j))

tipo associado:
<class 'complex'>

3. Experimente a inicialização de variáveis como segue:
   1. urnas = {}
   2. sessao = {"escola municipal", 103}

   descubra qual é o tipo da variável __urnas__ e da variável __sessao__. Explique se necessário
a razão dos tipos serem distintos.

script:
urnas = {}
sessao = {"escola municipal", 103}

print(type(urnas))    
print(type(sessao))   

urnas é do tipo # <class 'dict'>
sessao é do tippo # <class 'set'>
 Elas são de tipos distintos porque a linguagem reconhece as chaves vazias como sendo relacionado ao tipo dicionário, pois nesse tipo cada elemento é separado por chaves individuais. Enquanto que sessao tem seus elementos separados por vírgula, o que é caracteristico do tipo conjunto.


4. Nas definições de nomes de variáveis abaixo quais têm nomes válidos e quais são invalidos
    1. ola = "mundo"
    2. _ola = "mundo"
    3. _1_ola = "mundo"
    4. 1_ola = "mundo"
    5. ola_1 = "mundo"
    6. meu mundo = "ola"

aponte as razões para os nomes inválidos, indicando o item e a razão da violação
das regras de nomeação de variável.

O item 4 está errado, pois nome da variável está começando com um número e isto não é permitido.
O item 6 está errado, pois o nome da variável conteḿ espaços, e isto não é permitido na atribuição de variáveis. 


5. No seguinte comando de atribuição 
   1. casa, senha = "minha", "ola"
   2. casa, senha = "minha"
   3. casa = "minha"

Quais foram as atribuições que funcionaram e quais não funcionaram? Explique a razão dos problemas.

As atribuições 1 e 3 funcionaram, porém a 2 não, isso ocorre porque na atribuição 2 são declaradas duas variáveis, mas é atribuido um valor somente a uma delas. 
   
6. Considere as seguintes operações matemáticas, e indique o resultado de cada uma:
   1. (10 - 6)**2  = 16
   2. 10 - 6**2  = -26
   3. 10 - 3 // 2  = 9
   4. 10 - 3 / 2  = 8.5

7. Qual a importância de se criar ambiente virtuais para o desenvolvimentos de projetos usando Python?

Eles são importantes, porque além de fornecer segurança, eles permitem isolamento de dependências, prevenção de conflitos, reprodutibilidade, economiza espaço e é de mais fácil limpeza. 

8. Descubra e responda qual versão do python está instalado no seu ambiente de desenvolvimento. Que comando você usou 
para obter essa informação?

3.10.12
No terminal utilizei:
python3 --version

9. Uma tupla é um tipo imutável, portanto qualquer variável desse tipo pode ser alterada desde que os seus elementos 
sejam individualizados, como no código abaixo:

   __comprado = ("carro", "GM", "20K")__

   __comprado[1] = "Ford"__

   você concorda com essa afirmação? justifique sua resposta.

A própria afirmação é contraditória, pois de fato uma tupla é imutável, logo não é possível reatribuir um valor aos seus elementos.

10. Considere o código abaixo:

      __numero = input()__
      
      print(numero*3)
   
      se o valor 3(três) for informado como entrada e armazenado na variável número.

Sendo o valor 3 for informado e armazenado na variável número, na tela será impresso o resultado 333, pois quando não é especificado o tipo, o input vai considerar a entrada como um string e seguindo a expressão dada, vai repetir três vezes a string recebida.  

11. Revise o código disponibilizado em src/primeiro.py. Em seguida altere o programa
para que ele se torne generalista, i.e., aceite qualquer quantidade de notas que cada
aluno pode ter. 

```
# Calcular a nota no curso com certo número de alunos
#
# Para cada aluno, Ler K notas(reais) e K pesos (inteiros)
# Cada nota tem um peso definido como uma entrada
# Caso de teste:
# 1) peso positivo
# 2) notas no intervalo de [0,10]


def validar_notas(notas, pesos):
    executa = True
    msg_erro = "\n"
    for i, (nota, peso) in enumerate(zip(notas, pesos)):
        if nota < 0 or nota > 10:
            executa = False
            msg_erro += "Nota %d tem valor inválido\n" % (i+1)
        if peso <= 0:
            executa = False
            msg_erro += "Peso %d tem valor inválido!\n" % (i+1)
    return executa, msg_erro.strip()

def calcular_media(notas, pesos):
    temp_nota = sum(nota * peso for nota, peso in zip(notas, pesos))
    temp_peso = sum(pesos)
    return temp_nota / temp_peso

def ler_notas(nro_notas):
    return [float(input("Digite a nota %d: " % (i+1))) for i in range(nro_notas)]

def ler_pesos(nro_notas):
    return [int(input("Digite o peso da nota %d: " % (i+1))) for i in range(nro_notas)]

def main():
    nro_alunos = int(input("Número de alunos: "))
    for i in range(nro_alunos):
        nro_notas = int(input("\nNúmero de notas para o aluno %d: " % (i+1)))
        notas = ler_notas(nro_notas)
        pesos = ler_pesos(nro_notas)
        
        executa, msg_erro = validar_notas(notas, pesos)
        
        if executa:
            media = calcular_media(notas, pesos)
            print("Média do aluno %d: %.2f\n" % (i+1, media))
        else:
            print("Entrada de dados inválida!")
            print(msg_erro)

if __name__ == "__main__":
    main()
    
```








