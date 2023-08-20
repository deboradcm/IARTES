# Sobre estruturas de decisão e repetição

1. Os estatísticos gostariam de ter um conjunto de funções para calcular a __mediana__ e o __modo__ de uma lista de números. 
A mediana é o número de apareceria no ponto médio de uma lista se ela fosse ordenada. O modo é o número que aparece com maior 
frequência na lista. Defina essas funções em um módulo chamado stats.py. Também incluir uma função chamada __mean__ , que 
calcula a média de um conjunto de números. Cada função espera uma lista de números como um argumento e retorna um único número.
Seguem exemplos de entrada:
  * Exemplo 01:
    ```
    Lista01: {10, 5, 10, 6, 9, 10, 5, 8, 21, 1, 2, 3}
    Saida esperada:
      mediana: 6 8
      modo: 10
    ```
    _obs._ Quando a lista tem um número par de elementos a mediana é dada pelos dois elementos centrais caso a lista
    estivesse ordenada.
    
  * Exemplo 02:
    ```
    Lista01: {16, 7, 10, 11, 18, 18, 12, 15, 4, 6, 8}
    Saida esperada:
      mediana: 11
      modo: 18
    ```

   ## Resposta:
   ```
    def mediana(nums):
    sorted_nums = sorted(nums)
    n = len(sorted_nums)
    midpoint = n // 2
    if n % 2 == 1:
        return sorted_nums[midpoint]
    else:
        return sorted_nums[midpoint - 1], sorted_nums[midpoint]

def modo(nums):
    from collections import Counter
    count = Counter(nums)
    max_count = max(count.values())
    modos = [num for num, freq in count.items() if freq == max_count]
    if len(modos) == 1:
        return modos[0]
    return modos

def mean(nums):
    return sum(nums) / len(nums)
```
    
2. Em um dicionário (__dict__) Python foi armazenado o resultado dos testes realizados em um software. Processe o dicionário e
indique quais módulos tem uma cobertura de código menor que um certo percentual.

Entradas:
  * A estatística de cobertura em um arquivo;
  * O limite do percentual de cobertura;

O arquivo __outros/cobertura.txt__ pode ser usado como exemplo de entrada. Utilize as funções de leitura de linhas __readline()__ para ler cada 
entrada do arquivo. A cobertura coleta está indicada a última coluna (_cover_) do arquivo texto.


Saída:
  Nome dos módulos com cobertura menor que o percentual desejado. Informe o nome do módulo e o percentual de cobertura (por exemplo, 80%). 
  É possível que tenham mais que um módulo.

  
  Resposta:

def cobertura_inferior(arquivo: str, percentual_limite: int):
    módulos_inferiores = []
   
    linhas = arquivo.splitlines()

    for linha in linhas:
        if "%" in linha and "TOTAL" not in linha:
            partes = linha.split()
            nome_módulo = partes[0]
            cobertura = int(partes[-1].replace('%', ''))

            if cobertura < percentual_limite:
                módulos_inferiores.append((nome_módulo, cobertura))

    return módulos_inferiores

```
  # Testando:
arquivo = """
  Name              Stmts   Miss  Cover
  -------------------------------------
  main.py               2      2     0%
  moradia.py           44      7    84%
  test_moradia.py      15      0   100%
  -------------------------------------
  TOTAL                61      9    85%
"""
resultado = cobertura_inferior(arquivo, 85)
for modulo, cobertura in resultado:
    print(f"{modulo}: {cobertura}%")
```


3. Escreva um programa que permita o usuário navegar pelas linhas de texto em um arquivo. O programa deve solicitar ao usuário um nome de
   arquivo e inserir as linhas de texto em uma lista. O programa então deve entrar em um laço do qual imprima o número de linhas no arquivo
   e solicite ao usuário um número de linha. Os números reais das linhas variam de 1 ao número de linhas no arquivo. Se a entrada for 0(zero),
   o programa deve ser encerrado. Do contrário, o programa deve imprimir a linha associada a esse número.

   Uso o arquivo em __outros/arquivo_texto.md__ para testar a sua solução.


## Respostas
   ```
   def navegar_arquivo(arquivo: str):
    linhas = arquivo.splitlines()
    
    while True:
        print(f"O arquivo possui {len(linhas)} linhas.")
        
        try:
            numero_linha = int(input("Digite um número de linha (entre 1 e {} ou 0 para sair): ".format(len(linhas))))
            
            if numero_linha == 0:
                print("Encerrando o programa.")
                break
            elif 1 <= numero_linha <= len(linhas):
                print(linhas[numero_linha-1])
            else:
                print("Número de linha inválido.")
        except ValueError:
            print("Por favor, insira um número válido.")
    ```
    ```
# Testando
arquivo_teste = """
Name              Stmts   Miss  Cover
-------------------------------------
main.py               2      2     0%
moradia.py           44      7    84%
test_moradia.py      15      0   100%
-------------------------------------
TOTAL                61      9    85%
"""

navegar_arquivo(arquivo_teste)
```