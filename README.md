# IARTES

## Descrição
Este repositório contém o aplicativo Android "Marvin", localizado no diretório ProjFinal/Marvin. Marvin é uma aplicação para geração de botões aleatórios no Android. Ele incorpora a lógica de parser para ler o evento de clique do Android e integrar com JSON, além de fornecer comunicação e envio de dados em formato JSON para um servidor MQTT.

## Requisitos
Para acessar o servidor e realizar a leitura do JSON, siga os passos abaixo, dependendo do sistema operacional:

### Sistema Operacional Linux:
Via terminal, execute os seguintes comandos:

```bash
sudo apt-get update
sudo apt-get install mosquitto-clients
```

### Sistema Operacional Windows:
1. Faça o download do cliente Mosquitto através do site oficial: [Mosquitto Download](https://mosquitto.org/download/)
2. Instale o cliente Mosquitto seguindo as instruções fornecidas durante o processo de instalação.

## Assinatura do Tópico
Após a instalação do cliente Mosquitto, você pode assinar o tópico `app/robot_interactions` usando o seguinte comando:
```bash
mosquitto_sub -h broker.hivemq.comapp -t app/robot_interactions
```
Observação: Para realizar a assiantura do tópico no Windows é necessário executar o cmd como administrador. 
